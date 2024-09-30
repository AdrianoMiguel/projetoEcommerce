package controle.cliente;

import controle.Fachada;
import dominio.cliente.Cupom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CtrlClienteCupom  extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer clienteId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("clienteId", clienteId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/novoCupom.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer clienteId = Integer.parseInt(request.getParameter("id"));
        String codigo = request.getParameter("codigo");
        Double valor = Double.parseDouble(request.getParameter("valor"));

        Cupom cupom = new Cupom(clienteId, codigo, valor);
        Fachada fachada = new Fachada();

        try {
            request.setAttribute("mensagem", fachada.salvar(cupom));
            request.setAttribute("id", clienteId);
            request.setAttribute("encaminhamento", "consultarCupons");
            request.setAttribute("pagina", "CtrlClienteAlterar");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/resposta.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}