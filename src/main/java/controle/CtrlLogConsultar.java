package controle;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CtrlLogConsultar extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clienteIdParam = request.getParameter("id");
        Integer clienteId = Integer.parseInt(clienteIdParam);
        if (clienteIdParam != null && !clienteIdParam.isEmpty()) {
            Fachada fachada = new Fachada();

            request.setAttribute("logs", fachada.consultarLogs(clienteId));
            request.getRequestDispatcher("/Cliente/consultarLogs.jsp").forward(request, response);

        } else {
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
