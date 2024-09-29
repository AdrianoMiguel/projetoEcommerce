package controle.compra;

import java.io.IOException;

import dominio.cliente.Cliente;
import dominio.compra.Carrinho;
import persistencia.CarrinhoDAO;
import persistencia.ClienteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CtrlCompraVisualizar extends HttpServlet {
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
        HttpSession session = request.getSession(false); // false significa não criar uma nova sessão se não existir

        Integer clienteId = (Integer) session.getAttribute("idCliente");
        if (clienteId == null) {
            request.setAttribute("mensagem","É necessário logar-se primeiro para acessar o carrinho.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
            dispatcher.forward(request, response);
            return;
        }
        try {
            Carrinho carrinho = CarrinhoDAO.buscarCarrinhoNaoEfetivadoPorCliente(clienteId);
            Cliente cliente = ClienteDAO.buscarClientePorId(clienteId);

            request.setAttribute("carrinho", carrinho);
            request.setAttribute("cliente", cliente);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Compra/meuCarrinho.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
