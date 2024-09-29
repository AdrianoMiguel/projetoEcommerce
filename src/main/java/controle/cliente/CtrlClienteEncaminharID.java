package controle.cliente;

import dominio.cliente.*;
import persistencia.ClienteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CtrlClienteEncaminharID extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Cliente cliente = ClienteDAO.buscarClientePorId(id);

                if (cliente != null) {
                    request.setAttribute("cliente", cliente);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/alterarCliente.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect("/Cliente/consultarCliente.jsp");
                }
            } catch (Exception e) {
                throw new ServletException("Erro ao buscar o cliente", e);
            }
        } else {
            response.sendRedirect("/Cliente/consultarCliente.jsp");
        }
    }
}
