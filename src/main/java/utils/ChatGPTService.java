package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import controle.Fachada;

import dominio.produto.Vinho;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatGPTService {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "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";
    private List<Map<String, String>> historicoMensagens = new ArrayList<>();

    public String gerarResposta(String mensagemUsuario) throws Exception {

        Fachada fachada = new Fachada();

        List<Vinho> vinhosNoEcommerce = fachada.listarVinhos();

        StringBuilder contextoBanco = new StringBuilder();
        contextoBanco.append("Informações do banco de dados:\n");

        if (vinhosNoEcommerce != null && !vinhosNoEcommerce.isEmpty()) {
            contextoBanco.append("Vinhos disponiveis no ecommerce:\n");
            for (Vinho vinho : vinhosNoEcommerce) {
                contextoBanco.append("- ")
                        .append(vinho.getNome())
                        .append(", Safra: ")
                        .append(vinho.getSafra().toString())
                        .append(", Teor Alcoolico: ")
                        .append(vinho.getTeorAlc().toString())
                        .append(", Descricao: ")
                        .append(vinho.getDescricao())
                        .append(", Tipo de Vinho: ")
                        .append(vinho.getTipoVinho().toString())
                        .append(", Tipos de Uva: ")
                        .append(vinho.getTipoUva().toString())
                        .append(", Origem: ")
                        .append(vinho.getPais().toString())
                        .append(", Qtde em Estoque: ")
                        .append(vinho.getQtdeEstoque().toString())

                        .append(") - ")

                        .append("R$ ")
                        .append(String.format("%.2f", vinho.getPreco()))
                        .append("\n");
            }
        } else {
            contextoBanco.append("Nenhuma recomendação de vinho disponível no momento.\n");
        }


        String contexto = "Você é um assistente especializado em vinhos. Sua única função é responder exclusivamente a perguntas relacionadas ao produto vinho." +
                "Voce pode fornecer informações do seu conhecimento sobre o que for relacionado ao vinho" +
                "caso seja perguntado, como areas de produção no mundo, premios, tipos, historia, etc, mas sempre relacionado a vinho. Você não deve responder a perguntas sobre cores, " +
                "moda, ou qualquer outro assunto não relacionado a vinhos. Se uma pergunta mencionar a cor vinho, interprete apenas se há relevância para o produto vinho. " +
                "Não misture assuntos. Tente ser resumido, quero somente respostas curtas e diretas de no maximo 150 caracteres. Voce deve fornecer respostas em uma única linha, " +
                "sem quebras de linha ou formatação especial como '\\n'. So recomende vinhos" +
                "da base de dados que te enviarei Use as informações fornecidas para responder: " +
                "O site so aceita cartoes de credito ou cupons de desconto como forma de pagamento no momento. Enviamos os produtos para todo o Brasil e para o exterior tambem." +
                "O valor do frete é calculado de acordo com o endereco do destinatario e a quantidade de vinhos. O frete para o estado de SP é mais barato, o frete para outros estados é um pouco mais caro, e o frete internacional é o mais caro." +
                "O prazo de entrega em SP é de até 5 dias úteis, para outros estados 10 dias úteis, e para outros países 20 dias úteis. Não há outras opções de frete. É preciso fazer login no site antes de" +
                "efetuar uma compra e adicionar itens ao carrinho de compras. Os vinhos desejados sao adicionados ao carrinho de compras, lá é possivel calcular o frete, inserir as formas de pagamento e finalizar o pedido." +
                "Os pagamentos com cartao de credito passam por uma validacao do pagamento pelo emissor do cartao. O pedido de compra primeiro passa pelo status EM_PROCESSAMENTO e depois para o status PAGAMENTO_APROVADO ou PAGAMENTO_REJEITADO" +
                "a depender da validacao do emissor do cartao. O pedido uma vez feito nao pode ser cancelado, mas pode ser gerado um pedido de troca depois que é recebido pelo cliente. A troca" +
                "pode ser do pedido total ou uma troca parcial. É necessario a devolucao dos itens de troca para que a troca seja efetuada. O pedido de troca concluido gera um cupom de desconto para uma proxima compra." +
                "O site envia uma notificacao para o cliente assim que o pedido de troca for concluido. O estoque esta em constante atualizacao, se o cliente perguntar por algum vinho que nao tenha no estoque, fale que vamos" +
                "considerar o vinho que ele procura para adicionar futuramente no site, e recomende outro parecido que tenha no estoque. Se o cliente pedir para falar com atendente, diga que nao temos atendentes disponiveis no momento." +
                "Caso o cliente queira saber mais sobre os vinhos, como informacoes que nao tenha na base de dados, considere usar seu conhecimento para informa-lo, como premiacoes, historico de vendas no mundo, etc."
                + contextoBanco;


        if (historicoMensagens.isEmpty()) {
            historicoMensagens.add(Map.of("role", "system", "content", contexto));
        }

        historicoMensagens.add(Map.of("role", "user", "content", mensagemUsuario));


        int maxInteracoes = 10;
        while (historicoMensagens.size() > maxInteracoes + 1) {
            historicoMensagens.remove(1);
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(API_URL);
        request.setHeader("Authorization", "Bearer " + API_KEY);
        request.setHeader("Content-Type", "application/json; charset=UTF-8");


        Map<String, Object> payload = new HashMap<>();
        payload.put("model", "gpt-3.5-turbo");
        payload.put("messages", historicoMensagens);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);

        request.setEntity(new StringEntity(json, "UTF-8"));


        String resposta = EntityUtils.toString(httpClient.execute(request).getEntity());
        httpClient.close();


        Map<String, Object> responseMap = mapper.readValue(resposta, Map.class);
        List<Map<String, Object>> choices = (List<Map<String, Object>>) responseMap.get("choices");
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");


        String respostaChatbot = (String) message.get("content");
        historicoMensagens.add(Map.of("role", "assistant", "content", respostaChatbot));

        return respostaChatbot;
    }

}
