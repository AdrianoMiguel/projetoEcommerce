package controle;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Fachada fachada = new Fachada();
        try {
            request.setAttribute("clientes", fachada.listarClientes());
        } catch (Exception e) {
            request.setAttribute("mensagem","Não há clientes cadastrados");
            RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
            dispatcher.forward(request, response);
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}

