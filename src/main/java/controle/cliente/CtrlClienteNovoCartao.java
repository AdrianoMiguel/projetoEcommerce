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


public class CtrlClienteNovoCartao extends HttpServlet {
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
        request.setAttribute("bandeiras", bandeiras);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Cliente/novoCartao.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer id = (Integer) session.getAttribute("idCliente");

        Cliente cliente = null;
        try {
            cliente = ClienteDAO.buscarClientePorId(id);

            List<Cartao> cartoes = cliente.getCartoes();
            Cartao cartao = new Cartao(
                    request.getParameter("cartaoNome1"),
                    Bandeira.valueOf(request.getParameter("bandeira1")),
                    request.getParameter("cartaoNum1"),
                    getIntParameter(request,("cartaoCodSeg1")),
                    false);
            
            cartoes.add(cartao);

            cliente.setCartoes(cartoes);
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
