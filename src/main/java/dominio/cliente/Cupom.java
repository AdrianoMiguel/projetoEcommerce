package dominio.cliente;

import dominio.EntidadeDominio;

public class Cupom extends EntidadeDominio {
    private Integer clienteId;
    private String codigo;
    private Double valor;
    private Boolean utilizado;

    public Cupom(Integer id, Integer clienteId, String codigo, Double valor, Boolean utilizado) {
        setId(id);
        this.clienteId = clienteId;
        this.codigo = codigo;
        this.valor = valor;
        this.utilizado = utilizado;
    }

    public Cupom(Integer clienteId, String codigo, Double valor) {
        this.clienteId = clienteId;
        this.codigo = codigo;
        this.valor = valor;
        this.utilizado = false;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Boolean getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(Boolean utilizado) {
        this.utilizado = utilizado;
    }
}
