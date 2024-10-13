package controle.compra;

import dominio.cliente.TpTelefone;
import dominio.compra.Compra;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import dominio.compra.Item;
import dominio.compra.Notificacao;
import dominio.compra.Status;
import dominio.produto.Vinho;
import negocio.compra.GeradorCupomDeTroca;
import persistencia.CarrinhoDAO;
import persistencia.CompraDAO;
import persistencia.NotificacaoDAO;
import persistencia.CupomDAO;
import persistencia.ProdutoDAO;

public class CtrlCompraPedidosDeTroca extends HttpServlet {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                List<Compra> compras = CompraDAO.listarPedidosDeTroca();

                if (compras != null) {
                    request.setAttribute("compras", compras);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Compra/pedidosDeTroca.jsp");
                    dispatcher.forward(request, response);

                }
            } catch (Exception e) {
                request.setAttribute("mensagem","Não há pedidos de troca.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
                dispatcher.forward(request, response);
            }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = getIntParameter(request, "id");
        String status = request.getParameter("proximoStatus_"+id);
        Boolean reporEstoque = Boolean.parseBoolean(request.getParameter("reporEstoque_"+id));

        Compra compra = CompraDAO.buscarCompraPorId(id);
        CompraDAO compraDAO = new CompraDAO();

        compra.setStatus(Status.valueOf(status));

        if (status.equals("EM_TROCA")) {
            try {
                compraDAO.alterar(compra);
                request.setAttribute("mensagem","Pedido em processo de troca.");
                request.setAttribute("encaminhamento","meusPedidos");
                request.setAttribute("pagina","CtrlCompraTransacoes");
                RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (status.equals("TROCADO")) {
            GeradorCupomDeTroca geradorCupomDeTroca = new GeradorCupomDeTroca();
            geradorCupomDeTroca.processar(compra);
            try {
                StringBuilder mensagem = new StringBuilder();
                compraDAO.alterar(compra);
                mensagem.append("Troca realizada.");
                request.setAttribute("mensagem",mensagem);

                if (reporEstoque) {
                    CarrinhoDAO carrinho = new CarrinhoDAO();
                    carrinho.reporEstoque(compra.getCarrinho());
                    mensagem.append(" Os produtos foram repostos no estoque.");
                    request.setAttribute("mensagem",mensagem);
                }
                mensagem.append("Cupom de troca gerado com sucesso. Código TROCA"+ compra.getCarrinho().getId() + " no valor de R$"+ String.format("%.2f", compra.getCarrinho().getTotal()));
                Notificacao notificacao = new Notificacao(compra.getClienteId(), mensagem.toString());
                NotificacaoDAO notificacaoDAO = new NotificacaoDAO();
                notificacaoDAO.salvar(notificacao);
                request.setAttribute("id", compra.getClienteId());
                request.setAttribute("pagina", "CtrlCompraTransacoes");
                request.setAttribute("encaminhamento", "transacoes");
                RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
