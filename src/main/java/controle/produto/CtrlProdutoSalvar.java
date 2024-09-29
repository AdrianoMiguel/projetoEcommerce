package controle.produto;

import dominio.cliente.Log;
import dominio.produto.*;
import dominio.estoque.*;
import controle.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CtrlProdutoSalvar extends HttpServlet {
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

    private Double getDoubleParameter(HttpServletRequest request, String parametro) {
        String paramValue = request.getParameter(parametro);
        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                return Double.valueOf(paramValue);
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
        Integer safra = getIntParameter(request, "safra");
        Double teorAlc = getDoubleParameter(request, "teorAlc");
        String descricao = request.getParameter("descricao");
        TpVinho tipoVinho = TpVinho.valueOf(request.getParameter("tipoVinho"));
        String tiposUvaStr = request.getParameter("tiposUvaSelecionadas");
        List<TpUva> tiposUva = new ArrayList<>();
        String[] tiposUvaSplit = tiposUvaStr.split(",");
        for (String tipoUvaStr : tiposUvaSplit) {
            try {
                TpUva tipoUva = TpUva.valueOf(tipoUvaStr.trim());
                tiposUva.add(tipoUva);
            } catch (IllegalArgumentException e) {
            }
        }

        Pais pais = Pais.valueOf(request.getParameter("pais"));
        Fornecedor fornecedor = Fornecedor.valueOf(request.getParameter("fornecedor"));
        Double custo = getDoubleParameter(request, "custo");
        Double preco = getDoubleParameter(request, "preco");
        Integer qtdeEstoque = getIntParameter(request, "qtdeEstoque");
        Precificacao grupoPrecificacao = Precificacao.valueOf(request.getParameter("grupoPrecif"));
        String codBarras = request.getParameter("codBarras");
        Integer volume = getIntParameter(request, "volume");
        Boolean status = Boolean.parseBoolean(request.getParameter("status"));
        MotivoCategoria motivoCategoria = MotivoCategoria.valueOf(request.getParameter("motivoCategoria"));
        String justificativa = request.getParameter("justificativa");

        Vinho vinho = new Vinho(nome, safra, teorAlc, descricao, tipoVinho, tiposUva, pais, custo, preco, 0, grupoPrecificacao, codBarras, volume, status, motivoCategoria, justificativa);

        IFachada fachada = new Fachada();

        try {

            String mensagem = fachada.salvar(vinho);
            Estoque estoque = new Estoque(vinho.getId(), custo, qtdeEstoque, fornecedor);
            mensagem += fachada.salvar(estoque);
            request.setAttribute("mensagem",mensagem);
            request.setAttribute("vinhoId", vinho.getId());
            RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
