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

import dominio.compra.Status;
import persistencia.CompraDAO;

public class CtrlCompraTransacoes extends HttpServlet {
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
        HttpSession session = request.getSession();
        String idStr = "";
        if (session.getAttribute("idCliente") != null) {
            idStr = session.getAttribute("idCliente").toString();
        }

        String encaminhamento = request.getParameter("encaminhamento");

        if (encaminhamento.equals("transacoes")) {
            idStr = request.getParameter("id");
        }


        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);

                List<Compra> compras = CompraDAO.listarPorClienteId(id);

                if (compras != null) {
                    request.setAttribute("compras", compras);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Compra/" + encaminhamento + ".jsp");
                    dispatcher.forward(request, response);

                }
            } catch (Exception e) {
                throw new ServletException("Erro ao buscar as compras", e);
            }
        } else {
            throw new ServletException("Erro ao buscar as compras");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String compraId = request.getParameter("id");
        CompraDAO compraDAO = new CompraDAO();
        Compra compra = CompraDAO.buscarCompraPorId(Integer.parseInt(compraId));
        List<Compra> compras = CompraDAO.listarPorClienteId(compra.getClienteId());

        compra.setStatus(compra.getProximoStatus());
        try {
            compraDAO.alterar(compra);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("mensagem","Status alterado com sucesso!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
        dispatcher.forward(request, response);

    }
}
