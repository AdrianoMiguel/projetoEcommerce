package dominio.compra;

import dominio.EntidadeDominio;
import dominio.cliente.Cartao;
import dominio.cliente.Cupom;
import dominio.cliente.Endereco;

import java.sql.Timestamp;
import java.util.List;

public class Notificacao extends EntidadeDominio {
    private Integer clienteId;
    private String mensagem;


    public Notificacao (Integer id) {
        setId(id);
    }

    public Notificacao (Integer id, Integer clienteId, String mensagem) {
        setId(id);
        this.clienteId = clienteId;
        this.mensagem = mensagem;
    }

    public Notificacao(Integer clienteId, String mensagem) {
        this.clienteId = clienteId;
        this.mensagem = mensagem;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
