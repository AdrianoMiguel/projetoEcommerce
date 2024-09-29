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

public class CtrlClienteAlterar extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String encaminhamento = request.getParameter("encaminhamento");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Cliente cliente = ClienteDAO.buscarClientePorId(id);

                if (cliente != null) {
                    request.setAttribute("cliente", cliente);

                    Bandeira[] bandeiras = Bandeira.values();
                    Genero[] generos = Genero.values();
                    TpLogradouro[] tiposlograd = TpLogradouro.values();
                    TpResidencia[] tiposresid = TpResidencia.values();
                    TpTelefone[] tipostel = TpTelefone.values();

                    request.setAttribute("bandeiras", bandeiras);
                    request.setAttribute("generos", generos);
                    request.setAttribute("tiposlograd", tiposlograd);
                    request.setAttribute("tiposresid", tiposresid);
                    request.setAttribute("tipostel", tipostel);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/" + encaminhamento +".jsp");
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
