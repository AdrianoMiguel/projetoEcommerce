package controle.produto;

import controle.Fachada;
import controle.IFachada;
import dominio.produto.Vinho;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CtrlProdutoExcluir extends HttpServlet {
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

        Vinho vinho = new Vinho(getIntParameter(request, "id"));
        IFachada fachada = new Fachada();
        try {
            request.setAttribute("mensagem",fachada.excluir(vinho));
            request.getRequestDispatcher("resposta.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Erro ao excluir o vinho", e);
        }
    }
}

