package controle.produto;

import dominio.estoque.Fornecedor;
import dominio.produto.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CtrlProdutoNovo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        TpVinho[] tiposVinho = TpVinho.values();
        TpUva[] tiposUva = TpUva.values();
        Pais[] paises = Pais.values();
        Precificacao[] gruposPrecif = Precificacao.values();
        Fornecedor[] fornecedores = Fornecedor.values();
        MotivoCategoria[] motivos = MotivoCategoria.values();

        request.setAttribute("tiposVinho", tiposVinho);
        request.setAttribute("tiposUva", tiposUva);
        request.setAttribute("paises", paises);
        request.setAttribute("gruposPrecif", gruposPrecif);
        request.setAttribute("fornecedores", fornecedores);
        request.setAttribute("motivos", motivos);

        // Encaminha para a página JSP
        request.getRequestDispatcher("/Produto/cadastrarVinho.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);}
}

