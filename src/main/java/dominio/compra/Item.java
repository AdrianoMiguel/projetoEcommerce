package dominio.compra;

import dominio.EntidadeDominio;
import dominio.produto.Vinho;

public class Item extends EntidadeDominio {
    private Integer quantidade;
    private Vinho produto;


    public Item (Integer itemId, Integer quantidade, Vinho produto) {
        setId(itemId);
        this.quantidade = quantidade;
        this.produto = produto;
    }

public Item (Integer quantidade, Vinho produto) {
    this.quantidade = quantidade;
    this.produto = produto;
}

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Vinho getProduto() {
        return produto;
    }

    public void setProduto(Vinho produto) {
        this.produto = produto;
    }
}
