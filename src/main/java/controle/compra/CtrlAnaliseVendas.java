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
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dominio.compra.Item;
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
            // Estrutura para armazenar a quantidade de vinhos vendidos por data
            Map<String, Map<Vinho, Integer>> vinhosPorData = new HashMap<>();
            Map<String, Map<Vinho, Integer>> vinhosPorMes = new HashMap<>();

            // Agrupando vinhos vendidos por data
            for (Compra compra : compras) {
                if (compra.getStatus() != Status.EM_PROCESSAMENTO || compra.getStatus() != Status.EM_TROCA
                        || compra.getStatus() != Status.TROCADO || compra.getStatus() != Status.PAGAMENTO_REJEITADO) {
                    String dataCompra = new SimpleDateFormat("yyyy-MM-dd").format(compra.getDataHora());
                    String mesCompra = new SimpleDateFormat("yyyy-MM").format(compra.getDataHora());


                    for (Item item : compra.getCarrinho().getItens()) {
                        Vinho vinho = item.getProduto();
                        vinhosPorData.putIfAbsent(dataCompra, new HashMap<>());
                        vinhosPorMes.putIfAbsent(mesCompra, new HashMap<>());

                        vinhosPorData.get(dataCompra).put(vinho, vinhosPorData.get(dataCompra).getOrDefault(vinho, 0) + item.getQuantidade());
                        vinhosPorMes.get(mesCompra).put(vinho, vinhosPorMes.get(mesCompra).getOrDefault(vinho, 0) + item.getQuantidade());
                    }
                }
            }

                    TpVinho[] tiposVinho = TpVinho.values();
                    TpUva[] tiposUva = TpUva.values();
                    Pais[] paises = Pais.values();

                    request.setAttribute("tiposVinho", tiposVinho);
                    request.setAttribute("tiposUva", tiposUva);
                    request.setAttribute("paises", paises);

                    request.setAttribute("vinhos", vinhos);
                    request.setAttribute("vinhosPorData", vinhosPorData);
                    request.setAttribute("vinhosPorMes", vinhosPorMes);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Compra/analiseVendas.jsp");
                    dispatcher.forward(request, response);

                }

            }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
