package controle.cliente;

import controle.Fachada;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CtrlClienteConsultar extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filtro = request.getParameter("filtro").toLowerCase().trim();
        Fachada fachada = new Fachada();

        request.setAttribute("clientes", fachada.consultarClientes(filtro));
        request.getRequestDispatcher("/Cliente/consultarCliente.jsp").forward(request, response);


    }
}
