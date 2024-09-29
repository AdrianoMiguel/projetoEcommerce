package controle.produto;

import dominio.estoque.Fornecedor;
import dominio.produto.*;
import persistencia.ProdutoDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CtrlProdutoDetalhes extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Vinho vinho = ProdutoDAO.buscarPorId(id);

                if (vinho != null) {
                    request.setAttribute("vinho", vinho);



                    TpVinho[] tiposVinho = TpVinho.values();
                    TpUva[] tiposUva = TpUva.values();
                    Pais[] paises = Pais.values();
                    Precificacao[] gruposPrecif = Precificacao.values();
                    MotivoCategoria[] motivos = MotivoCategoria.values();

                    request.setAttribute("tiposVinho", tiposVinho);
                    request.setAttribute("tiposUva", tiposUva);
                    request.setAttribute("paises", paises);
                    request.setAttribute("gruposPrecif", gruposPrecif);
                    request.setAttribute("motivos", motivos);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Produto/detalhesVinho.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect("listarVinho.jsp");
                }
            } catch (Exception e) {
                throw new ServletException("Erro ao buscar o vinho", e);
            }
        } else {
            response.sendRedirect("listarVinho.jsp");
        }
    }
}
