package dominio.compra;

public class Frete {
    private Double valor;
    private String prazo;

    public Frete(Double valor, String prazo) {
        this.valor = valor;
        this.prazo = prazo;
    }

    public Double getValor() {
        return valor;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }
}
