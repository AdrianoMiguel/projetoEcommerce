package controle.compra;

import controle.Fachada;
import controle.IFachada;
import dominio.cliente.TpTelefone;
import dominio.compra.Carrinho;
import dominio.compra.Compra;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import dominio.compra.Item;
import dominio.compra.Status;
import dominio.produto.Vinho;
import persistencia.CarrinhoDAO;
import persistencia.CompraDAO;
import persistencia.ProdutoDAO;

public class CtrlCompraTroca extends HttpServlet {
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
        Integer compraId = getIntParameter(request, "compraId");
        try {
            Compra compra = CompraDAO.buscarCompraPorId(compraId);
                request.setAttribute("compra", compra);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Compra/iniciarTroca.jsp");
                dispatcher.forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Erro ao buscar pedido", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer compraId = getIntParameter(request, "id");
        String[] itensSelecionados = request.getParameterValues("itensSelecionados");
        Compra compra = CompraDAO.buscarCompraPorId(compraId);
        List<Item> itens = new ArrayList<>();

        for (String itemId : itensSelecionados) {
            int id = Integer.parseInt(itemId);
            Item item = compra.getCarrinho().getItens().stream().filter(i -> i.getId() == id).findFirst().get();
            itens.add(item);
        }

        Carrinho carrinho = new Carrinho(compra.getClienteId(),itens, Time.valueOf(LocalTime.now()));

        IFachada fachada = new Fachada();
        try {
            fachada.salvar(carrinho);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        carrinho.setEfetivado(true);
        try {
            fachada.alterar(carrinho);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        Compra pedidoTrocaParcial = new Compra(compra.getClienteId(),Status.EM_TROCA,
                carrinho,null,compra.getEnderecoEntrega(),compra.getFrete());

        compra.setStatus(Status.FINALIZADO);
        CompraDAO compraDAO = new CompraDAO();
        try {
            compraDAO.alterar(compra);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            compraDAO.salvar(pedidoTrocaParcial);
            request.setAttribute("mensagem","Pedido em processo de troca.");
            request.setAttribute("encaminhamento","meusPedidos");
            request.setAttribute("pagina","CtrlCompraTransacoes");
            RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
