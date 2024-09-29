package controle.produto;

import controle.Fachada;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CtrlProdutoConsultar extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filtro = request.getParameter("filtro").toLowerCase().trim();
        Fachada fachada = new Fachada();

        request.setAttribute("vinhos", fachada.consultarVinhos(filtro));
        request.getRequestDispatcher("/Produto/consultarVinho.jsp").forward(request, response);


    }
}
