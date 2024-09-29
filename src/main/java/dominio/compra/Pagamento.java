package dominio.compra;

import dominio.cliente.Cartao;

public class Pagamento {

    private Cartao cartao;
    private Double valor;

    public Pagamento(Cartao cartao, Double valor) {
        this.cartao = cartao;
        this.valor = valor;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Double getValor() {
        return valor;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
