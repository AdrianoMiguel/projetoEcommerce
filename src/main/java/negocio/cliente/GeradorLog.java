package negocio.cliente;
import dominio.*;
import dominio.cliente.*;
import negocio.IStrategy;
import persistencia.ClienteDAO;
import persistencia.LogDAO;

import java.time.LocalDateTime;
import java.util.List;

//RNF0012 - Log de Transação
public class GeradorLog implements IStrategy {
    LogDAO logDAO = new LogDAO();

    public String processar(EntidadeDominio entidade) {
        Log logEntrada = (Log) entidade;
        Cliente cliente = null;
        try {
            cliente = ClienteDAO.buscarClientePorId(logEntrada.getClienteID());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//RNF0012 - Log de transação
        try {
            if (logEntrada.getDetalhesAcao() == null) {
                Log log = new Log(logEntrada.getClienteID(), Usuario.Usuario_1, "Inserção", "Cliente cadastrado");
                logDAO.salvar(log);
            } else {
                if (!logEntrada.getDetalhesAcao().equals("")) {
                    Log log = new Log(logEntrada.getClienteID(), Usuario.Usuario_1, "Alteração", logEntrada.getDetalhesAcao());
                    logDAO.salvar(log);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar cliente: " + e.getMessage());
            throw new RuntimeException("Erro ao processar cliente", e);
        }
        return null;
    }
}
