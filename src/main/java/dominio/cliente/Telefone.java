package dominio.cliente;

public class Telefone {
    private TpTelefone tipo;
    private Integer ddd;
    private Long numero;

    public Telefone(TpTelefone tipo, Integer ddd, Long numero) {
        this.tipo = tipo;
        this.ddd = ddd;
        this.numero = numero;
    }

    public TpTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TpTelefone tipo) {
        this.tipo = tipo;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }
}
