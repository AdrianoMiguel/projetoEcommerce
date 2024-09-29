package controle.compra;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import controle.Fachada;
import controle.IFachada;
import dominio.cliente.Cartao;
import dominio.cliente.Cliente;
import dominio.cliente.Cupom;
import dominio.cliente.Endereco;
import dominio.compra.*;
import dominio.produto.*;
import persistencia.CarrinhoDAO;
import persistencia.ClienteDAO;

import persistencia.CupomDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CtrlCompraFinalizar extends HttpServlet {
    private Integer getIntParameter(HttpServletRequest request, String parametro) {
        String paramValue = request.getParameter(parametro);
        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                return Integer.valueOf(paramValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private Double getDoubleParameter(HttpServletRequest request, String parametro) {
        String paramValue = request.getParameter(parametro);
        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                return Double.valueOf(paramValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer clienteId = (Integer) session.getAttribute("idCliente");
        IFachada fachada = new Fachada();

        try {
            Cliente cliente = ClienteDAO.buscarClientePorId(clienteId);
            Carrinho carrinho = CarrinhoDAO.buscarCarrinhoNaoEfetivadoPorCliente(clienteId);

            if (carrinho == null) {
                request.setAttribute("mensagem","O carrinho está vazio.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
                dispatcher.forward(request, response);
                return;
            }

            if (getDoubleParameter(request, "valorFreteHidden") == 0) {
                request.setAttribute("mensagem","Calcule o frete antes de realizar a compra.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // Lista de itens atualizada
            List<Item> itensAtualizados = new ArrayList<>();
            List<Vinho> vinhosAtualizados = new ArrayList<>();

            for (Item item : carrinho.getItens()) {
                // Coleta a nova quantidade para cada produto
                Integer novaQuantidade = getIntParameter(request, "quantidade_" + item.getProduto().getId());
                if (novaQuantidade == null) {
                    novaQuantidade = 0;
                }
                Integer diferencaNoEstoque = novaQuantidade - item.getQuantidade();

                if (!Objects.equals(item.getQuantidade(), novaQuantidade)) {
                    carrinho.setTempo(Time.valueOf(LocalTime.now()));
                }
                // Atualiza a quantidade do item
                item.setQuantidade(novaQuantidade);
                item.getProduto().setQtdeEstoque(item.getProduto().getQtdeEstoque() - diferencaNoEstoque);
                itensAtualizados.add(item);
                vinhosAtualizados.add(item.getProduto());

                if (novaQuantidade == 0) {
                    itensAtualizados.remove(item);
                }
            }

            // Atualiza o carrinho com os novos itens
            carrinho.setItens(itensAtualizados);

            try {
                fachada.salvar(carrinho);
                for (Vinho vinho : vinhosAtualizados) {
                    fachada.alterar(vinho);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            carrinho.setEfetivado(true);

            List<Pagamento> pagamentos = new ArrayList<>();
            List<Cupom> cupons = new ArrayList<>();

            int numCartoes = getIntParameter(request,"numCartoesHidden");

            // Função para encontrar o número de cartões enviados
            int numCupons = getIntParameter(request,"numCuponsHidden"); // Função para encontrar o número de cupons enviados


            // Itere sobre os cartões e valores recebidos
            for (int i = 0; i < numCartoes; i++) {
                // Coleta o ID do cartão e o valor inserido nos inputs
                Integer cartaoId = getIntParameter(request, "cartao_" + i);
                Double valor = getDoubleParameter(request,"valor_cartao_" + i);

                // Verifica se o parâmetro não está vazio (para evitar processar campos não preenchidos)
                if (valor != null) {
                    try {

                        // Crie um objeto Cartao (substitua pela sua classe real de cartão)
                        Cartao cartao = ClienteDAO.buscarCartaoPorId(cartaoId);

                        // Crie um objeto Pagamento (substitua pela sua classe real de pagamento)
                        Pagamento pagamento = new Pagamento(cartao,valor);

                        // Adicione o pagamento à lista
                        pagamentos.add(pagamento);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }

            for (int i = 0; i < numCupons; i++) {
                // Coleta o ID do cupom e o valor inserido nos inputs
                Integer cupomId = getIntParameter(request, "idCupomHidden_" + i);
                Double valor = getDoubleParameter(request,"valorCupomHidden_" + i);

                // Verifica se o parâmetro não está vazio (para evitar processar campos não preenchidos)
                if (cupomId != null) {
                    try {

                        // Crie um objeto Cupom (substitua pela sua classe real de cupom)
                        Cupom cupom = CupomDAO.buscarCupomPorId(cupomId);
                        cupom.setUtilizado(true);

                        // Adicione o pagamento à lista
                        cupons.add(cupom);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }

            Frete frete = new Frete (getDoubleParameter(request, "valorFreteHidden"), request.getParameter("prazoFreteHidden"));


            int endIndex = getIntParameter(request, "enderecoEntrega");
            Endereco endereco = cliente.getEndEnt().get(endIndex);

            Compra compra = new Compra(clienteId, Status.EM_PROCESSAMENTO, carrinho, pagamentos,endereco, frete);

                compra.setCupons(cupons);


            String mensagem = fachada.salvar(compra);
            if (mensagem.equals("Registro realizado com sucesso!") && compra.getValorFinal() < 0) {
                mensagem += "Um cupom de crédito no valor de R$" + compra.getValorFinal() + " foi gerado para você! Utilize o código CREDITO"+compra.getCarrinho().getId()+" para descontar em sua próxima compra.";
            }
            try {
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
