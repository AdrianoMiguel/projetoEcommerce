package controle.cliente;
import controle.Fachada;
import controle.IFachada;
import dominio.*;
import dominio.cliente.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Objects;

public class CtrlClienteSalvar extends HttpServlet {
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


    private Long getLongParameter(HttpServletRequest request, String parametro) {
        String paramValue = request.getParameter(parametro);
        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                return Long.valueOf(paramValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome").toUpperCase();
        String cpf = request.getParameter("cpf");
        String generoStr = request.getParameter("genero");
        Genero genero = Genero.valueOf(generoStr);

        String dataNascimentoStr = request.getParameter("data-nascimento");
        Date dataNascimento = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dataNascimento = formatter.parse(dataNascimentoStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Captura do endereço residencial
        Endereco endResid = new Endereco("Residencial",
                TpResidencia.valueOf(request.getParameter("tiporesidRes")),
                TpLogradouro.valueOf(request.getParameter("tipologradRes")),
                request.getParameter("endResLograd"),
                getIntParameter(request,"endResNum"),
                new Bairro(request.getParameter("endResBairro"),
                new Cidade(request.getParameter("endResCidade"),
                new Estado(request.getParameter("endResEst"),
                new Pais(request.getParameter("endResPais"))))),
                request.getParameter("endResCep"),
                request.getParameter("endResObs")
        );

        // Captura do endereço de cobrança
        Endereco endCob = new Endereco("Cobranca",
                TpResidencia.valueOf(request.getParameter("tiporesidCob")),
                TpLogradouro.valueOf(request.getParameter("tipologradCob")),
                request.getParameter("endCobLograd"),
                getIntParameter(request,"endCobNum"),
                new Bairro(request.getParameter("endCobBairro"),
                        new Cidade(request.getParameter("endCobCidade"),
                                new Estado(request.getParameter("endCobEst"),
                                        new Pais(request.getParameter("endCobPais"))))),
                request.getParameter("endCobCep"),
                request.getParameter("endCobObs")
        );

        // Captura dos endereços de entrega
        List<Endereco> endEnt = new ArrayList<>();
        int enderecoCount = 1;
        while (request.getParameter("endEntNome" + enderecoCount) != null) {
            boolean enderecoPrincipal = String.valueOf(enderecoCount).equals(request.getParameter("endEntPrincipal"));
            Endereco endereco = new Endereco(request.getParameter("endEntNome" + enderecoCount),
                    TpResidencia.valueOf(request.getParameter("tiporesidEnt" + enderecoCount)),
                    TpLogradouro.valueOf(request.getParameter("tipologradEnt" + enderecoCount)),
                    request.getParameter("endEntLograd" + enderecoCount),
                    getIntParameter(request,"endEntNum" + enderecoCount),
                    new Bairro(request.getParameter("endEntBairro" + enderecoCount),
                            new Cidade(request.getParameter("endEntCidade" + enderecoCount),
                                    new Estado(request.getParameter("endEntEst" + enderecoCount),
                                            new Pais(request.getParameter("endEntPais" + enderecoCount))))),
                    request.getParameter("endEntCep" + enderecoCount),
                    request.getParameter("endEntObs" + enderecoCount)
            );
            endereco.setPrincipal(enderecoPrincipal);
            endEnt.add(endereco);
            enderecoCount++;
        }

        // Captura do contato
        Contato contato = new Contato(
                request.getParameter("email"),
                new Telefone(TpTelefone.valueOf(request.getParameter("tipotel")),
                getIntParameter(request,"ddd"),
                getLongParameter(request,"numerotel"))
        );

        // Captura dos cartões
        List<Cartao> cartoes = new ArrayList<>();
        int cartaoCount = 1;
        while (request.getParameter("cartaoNome" + cartaoCount) != null) {
            boolean cartaoPrincipal = String.valueOf(cartaoCount).equals(request.getParameter("cartaoPrincipal"));
            Cartao cartao = new Cartao(
                    request.getParameter("cartaoNome" + cartaoCount),
                    Bandeira.valueOf(request.getParameter("bandeira" + cartaoCount)),
                    request.getParameter("cartaoNum" + cartaoCount),
                    getIntParameter(request,"cartaoCodSeg" + cartaoCount),
                    cartaoPrincipal
            );
            cartoes.add(cartao);
            cartaoCount++;
        }

        // Captura da senha
        String senha = request.getParameter("senha");

        // Criação do objeto Cliente
        Cliente cliente = new Cliente(
                nome, cpf, dataNascimento, genero, endResid, endCob, endEnt, contato, cartoes, senha
        );


        IFachada fachada = new Fachada();
        try {

            request.setAttribute("mensagem",(fachada.salvar(cliente)));
            request.setAttribute("clienteId", cliente.getId());
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
    }
}
