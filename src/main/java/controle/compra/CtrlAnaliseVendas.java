package controle.compra;

import dominio.cliente.TpTelefone;
import dominio.compra.Compra;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import dominio.compra.Status;
import dominio.estoque.Fornecedor;
import dominio.produto.*;
import persistencia.CompraDAO;
import persistencia.ProdutoDAO;

public class CtrlAnaliseVendas extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Compra> compras = CompraDAO.listar();
        List<Vinho> vinhos = null;
        try {
            vinhos = ProdutoDAO.listar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (compras != null) {

                    if (compras.size() == 0) {
                        request.setAttribute("mensagem","Nao há compras registradas ainda.");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
                        dispatcher.forward(request, response);

                    }

                    TpVinho[] tiposVinho = TpVinho.values();
                    TpUva[] tiposUva = TpUva.values();
                    Pais[] paises = Pais.values();

                    request.setAttribute("tiposVinho", tiposVinho);
                    request.setAttribute("tiposUva", tiposUva);
                    request.setAttribute("paises", paises);

                    request.setAttribute("vinhos", vinhos);
                    request.setAttribute("compras", compras);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Compra/analiseVendas.jsp");
                    dispatcher.forward(request, response);

                }

            }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
