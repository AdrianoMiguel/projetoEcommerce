package controle.produto;

import controle.Fachada;
import dominio.cliente.Cliente;
import dominio.compra.Notificacao;
import persistencia.ClienteDAO;
import persistencia.NotificacaoDAO;
import persistencia.ProdutoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class CtrlProdutoListar extends HttpServlet {

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
        Integer idCliente = getIntParameter(request, "idClienteLogin");
        List<Notificacao> notificacoes = null;
        if (idCliente != null) {
            try {
                Cliente cliente = ClienteDAO.buscarClientePorId(idCliente);
                HttpSession session = request.getSession();
                session.setAttribute("idCliente", cliente.getId());
                session.setAttribute("nomeCliente", cliente.getNome().split(" ")[0]);
                NotificacaoDAO notificacaoDAO = new NotificacaoDAO();

                try {
                    notificacoes = notificacaoDAO.listar(cliente.getId());
                    if (notificacoes != null) {
                        request.setAttribute("notificacoes", notificacoes);
                        for (Notificacao n : notificacoes) {
                            notificacaoDAO.excluir(n);
                        }
                    }
                } catch (Exception e) {
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        Fachada fachada = new Fachada();
        try {
            request.setAttribute("vinhos", fachada.listarVinhos());
        } catch (Exception e) {
            request.setAttribute("mensagem","Não há vinhos cadastrados");
            RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
            dispatcher.forward(request, response);
        }
        request.getRequestDispatcher("/Produto/listarVinho.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("vinhos", ProdutoDAO.listar(request.getParameter("filtro")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/Produto/listarVinho.jsp").forward(request, response);
    }
}
