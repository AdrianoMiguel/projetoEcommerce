package controle.compra;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import controle.Fachada;
import controle.IFachada;

import dominio.compra.Carrinho;
import dominio.compra.Item;
import dominio.produto.*;
import persistencia.CarrinhoDAO;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CtrlCompraAtualizarCarrinho extends HttpServlet {
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
        HttpSession session = request.getSession();

        Integer clienteId = (Integer) session.getAttribute("idCliente");

        try {
            Carrinho carrinho = null;

            carrinho = CarrinhoDAO.buscarCarrinhoNaoEfetivadoPorCliente(clienteId);
            if (carrinho == null) {
                response.sendRedirect("CtrlProdutoListar");
                return;
            }

            // Lista de itens atualizada
            List<Item> itensAtualizados = new ArrayList<>();
            List<Vinho> vinhosAtualizados = new ArrayList<>();

            for (Item item : carrinho.getItens()) {
                // Coleta a nova quantidade para cada produto
                Integer novaQuantidade = getIntParameter(request, "quantidade_" + item.getProduto().getId());
                if (novaQuantidade == null) {
                    novaQuantidade = 0;
                }
                Integer diferencaNoEstoque = novaQuantidade - item.getQuantidade();

                    if (!Objects.equals(item.getQuantidade(), novaQuantidade)) {
                        carrinho.setTempo(Time.valueOf(LocalTime.now()));
                    }
                    // Atualiza a quantidade do item
                    item.setQuantidade(novaQuantidade);
                    item.getProduto().setQtdeEstoque(item.getProduto().getQtdeEstoque() - diferencaNoEstoque);
                    itensAtualizados.add(item);
                    vinhosAtualizados.add(item.getProduto());

                if (novaQuantidade == 0) {
                    itensAtualizados.remove(item);
                }
            }

            // Atualiza o carrinho com os novos itens
            carrinho.setItens(itensAtualizados);


            IFachada fachada = new Fachada();
            try {
                request.setAttribute("mensagem",fachada.salvar(carrinho));
                for (Vinho vinho : vinhosAtualizados) {
                    request.setAttribute("mensagem",fachada.alterar(vinho));
                }
                response.sendRedirect("CtrlProdutoListar");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
