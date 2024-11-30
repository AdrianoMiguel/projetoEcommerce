package controle;


import utils.ChatGPTService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChatbotServlet extends HttpServlet {
    private ChatGPTService chatGPTService = new ChatGPTService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mensagemUsuario = request.getParameter("mensagem");
        try {
            String resposta = chatGPTService.gerarResposta(mensagemUsuario);
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"resposta\": \"" + resposta.replace("\n", " ") + "\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Ocorreu um erro ao processar a solicitacao.\"}");
        }
    }
}
