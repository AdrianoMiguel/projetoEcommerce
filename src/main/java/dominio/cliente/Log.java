package dominio.cliente;

import dominio.Usuario;
import dominio.EntidadeDominio;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Log extends EntidadeDominio {
    private Integer clienteID;
    private Timestamp dataHoraAcao;
    private Usuario usuarioAcao;
    private String tipoAcao;
    private String detalhesAcao;

    public Log(Integer clienteID){
        this.clienteID = clienteID;
    }

    public Log(Integer clienteID, String alteracoes) {
        this.clienteID = clienteID;
        this.detalhesAcao = alteracoes;
    }

    public Log(Integer clienteID, Usuario usuarioAcao, String tipoAcao, String detalhesAcao) {
        this.clienteID = clienteID;
        this.dataHoraAcao = Timestamp.valueOf(LocalDateTime.now());
        this.usuarioAcao = usuarioAcao;
        this.tipoAcao = tipoAcao;
        this.detalhesAcao = detalhesAcao;
    }

    public Timestamp getDataHoraAcao() {
        return dataHoraAcao;
    }

    public void setDataHoraAcao(Timestamp dataHoraAcao) {
        this.dataHoraAcao = dataHoraAcao;
    }

    public Usuario getUsuarioAcao() {
        return usuarioAcao;
    }

    public void setUsuarioAcao(Usuario usuarioAcao) {
        this.usuarioAcao = usuarioAcao;
    }

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public String getDetalhesAcao() {
        return detalhesAcao;
    }

    public void setDetalhesAcao(String detalhesAcao) {
        this.detalhesAcao = detalhesAcao;
    }

    public Integer getClienteID() {
        return clienteID;
    }

    public void setClienteID(Integer clienteID) {
        this.clienteID = clienteID;
    }

}
