package controle.compra;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import controle.Fachada;
import controle.IFachada;
import dominio.cliente.Cliente;
import dominio.compra.Carrinho;
import dominio.compra.Item;
import dominio.produto.*;
import persistencia.CarrinhoDAO;
import persistencia.ProdutoDAO;
import persistencia.ClienteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CtrlCompraAdicionarCarrinho extends HttpServlet {
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

        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer clienteId = getIntParameter(request, "clienteId");
        Integer vinhoId = getIntParameter(request, "vinhoId");
        if (clienteId == null) {
            request.setAttribute("mensagem","É necessário logar-se primeiro para adicionar produtos ao carrinho.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            Cliente cliente = ClienteDAO.buscarClientePorId(clienteId);
            Vinho vinho = ProdutoDAO.buscarVinhoPorId(vinhoId);
            vinho.setQtdeEstoque(vinho.getQtdeEstoque() - getIntParameter(request, "quantidade"));
            Carrinho carrinho = null;
            if (CarrinhoDAO.buscarCarrinhoNaoEfetivadoPorCliente(clienteId) == null) {
                List<Item> itens = new ArrayList<>();
                Item item = new Item(getIntParameter(request, "quantidade"), vinho);
                itens.add(item);
                carrinho = new Carrinho(cliente.getId(), itens, Time.valueOf(LocalTime.now()));
            }
            else {
                carrinho = CarrinhoDAO.buscarCarrinhoNaoEfetivadoPorCliente(clienteId);
                List<Item> itens = carrinho.getItens();
                boolean itemEncontrado = false;
                for (Item item : itens) {
                    if (item.getProduto().getId() == vinhoId) {
                        item.setQuantidade(item.getQuantidade() + getIntParameter(request, "quantidade"));
                        itemEncontrado = true;
                        break;
                    }
                }
                if (!itemEncontrado) {
                    Item item = new Item(getIntParameter(request, "quantidade"), vinho);
                    itens.add(item);
                }
                carrinho.setItens(itens);
                carrinho.setTempo(Time.valueOf(LocalTime.now()));
            }

            IFachada fachada = new Fachada();
            try {
                request.setAttribute("mensagem",fachada.salvar(carrinho));
                request.setAttribute("mensagem",fachada.alterar(vinho));
                request.setAttribute("mensagem","Vinho adicionado ao carrinho com sucesso.");
               // HttpSession session = request.getSession();
               // session.setAttribute("carrinho", carrinho);
                request.setAttribute("id", vinhoId);
                request.setAttribute("pagina", "CtrlProdutoDetalhes");
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
