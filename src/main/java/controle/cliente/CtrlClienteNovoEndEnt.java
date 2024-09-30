package controle.cliente;

import controle.Fachada;
import controle.IFachada;
import dominio.cliente.*;
import persistencia.ClienteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.IOException;
import java.util.*;


public class CtrlClienteNovoEndEnt extends HttpServlet {
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/novoEnderecoEntrega.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer id = (Integer) session.getAttribute("idCliente");

        Cliente cliente = null;
        try {
            cliente = ClienteDAO.buscarClientePorId(id);

            List<Endereco> enderecos = cliente.getEndEnt();
            Endereco endereco = new Endereco(request.getParameter("endEntNome1"),
                    TpResidencia.valueOf(request.getParameter("tiporesidEnt1")),
                    TpLogradouro.valueOf(request.getParameter("tipologradEnt1")),
                    request.getParameter("endEntLograd1"),
                    getIntParameter(request, "endEntNum1"),
                    new Bairro(request.getParameter("endEntBairro1"),
                            new Cidade(request.getParameter("endEntCidade1"),
                                    new Estado(request.getParameter("endEntEst1"),
                                            new Pais(request.getParameter("endEntPais1"))))),
                    request.getParameter("endEntCep1"),
                    request.getParameter("endEntObs1")
            );
            endereco.setPrincipal(false);
            enderecos.add(endereco);

            cliente.setEndEnt(enderecos);
            IFachada fachada = new Fachada();
            try {
                String mensagem = fachada.alterar(cliente);
                request.setAttribute("mensagem", mensagem);
                if (mensagem.equals("Registro atualizado com sucesso!")) {
                    request.setAttribute("id", cliente.getId());
                    request.setAttribute("pagina", "CtrlCompraVisualizar");
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Log log = new Log(cliente.getId(), cliente.getAlteracoes());
            try {
                fachada.salvar(log);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
