package controle.cliente;

import controle.Fachada;
import controle.IFachada;
import dominio.cliente.Cliente;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CtrlClienteExcluir extends HttpServlet {
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

        Cliente cliente = new Cliente(getIntParameter(request, "id"));
        IFachada fachada = new Fachada();
        try {
            request.setAttribute("mensagem",fachada.excluir(cliente));
            request.getRequestDispatcher("resposta.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Erro ao excluir o cliente", e);
        }
    }
}
