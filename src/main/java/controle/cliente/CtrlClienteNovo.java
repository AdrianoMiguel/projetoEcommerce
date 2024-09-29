package controle.cliente;

import dominio.cliente.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CtrlClienteNovo extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Bandeira[] bandeiras = Bandeira.values();
        Genero[] generos = Genero.values();
        TpLogradouro[] tiposlograd = TpLogradouro.values();
        TpResidencia[] tiposresid = TpResidencia.values();
        TpTelefone[] tipostel = TpTelefone.values();

        request.setAttribute("bandeiras", bandeiras);
        request.setAttribute("generos", generos);
        request.setAttribute("tiposlograd", tiposlograd);
        request.setAttribute("tiposresid", tiposresid);
        request.setAttribute("tipostel", tipostel);

        // Encaminha para a página JSP
        request.getRequestDispatcher("/Cliente/cadastrarCliente.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);}
}
