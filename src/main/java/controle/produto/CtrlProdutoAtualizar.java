package controle.produto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controle.Fachada;
import controle.IFachada;
import dominio.produto.*;
import persistencia.ProdutoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CtrlProdutoAtualizar extends HttpServlet {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer id = getIntParameter(request, "id");

        Vinho vinho = null;
        try {
            vinho = ProdutoDAO.buscarVinhoPorId(id);
            vinho.setNome(request.getParameter("nome").toUpperCase());
            vinho.setSafra(Integer.valueOf(request.getParameter("safra")));
            vinho.setTeorAlc(getDoubleParameter(request, "teorAlc"));
            vinho.setDescricao(request.getParameter("descricao").toUpperCase());
            vinho.setTipoVinho(TpVinho.valueOf(request.getParameter("tipoVinho")));
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
            vinho.setTipoUva(tiposUva);
            vinho.setPais(Pais.valueOf(request.getParameter("pais")));
            vinho.setMaiorCusto(getDoubleParameter(request, "maiorCusto"));
            vinho.setPreco(getDoubleParameter(request, "preco"));
            vinho.setQtdeEstoque(Integer.valueOf(request.getParameter("qtdeEstoque")));
            vinho.setGrupoPrecificacao(Precificacao.valueOf(request.getParameter("grupoPrecif")));
            vinho.setCodBarras(request.getParameter("codBarras"));
            vinho.setVolume(Integer.valueOf(request.getParameter("volume")));
            vinho.setStatus(Boolean.valueOf(request.getParameter("status")));
            vinho.setMotivo(new Motivo(MotivoCategoria.valueOf(request.getParameter("motivoCategoria")),
                    request.getParameter("justificativa")));

            IFachada fachada = new Fachada();
            try {
                request.setAttribute("mensagem",fachada.alterar(vinho));
                request.setAttribute("vinhoId", vinho.getId());
                RequestDispatcher dispatcher = request.getRequestDispatcher("resposta.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
