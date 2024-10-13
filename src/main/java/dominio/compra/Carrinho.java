package dominio.compra;

import dominio.EntidadeDominio;

import java.sql.Time;
import java.util.List;

public class Carrinho extends EntidadeDominio {
    private Integer clienteId;
    private List<Item> itens;
    private Double total;
    private Time tempo;
    private Boolean efetivado;


    public Carrinho(Integer id) {
        setId(id);
    }

    public Carrinho(Integer clienteId, List<Item> itens, Time tempo) {
        this.clienteId = clienteId;
        this.itens = itens;
        this.tempo = tempo;
        this.efetivado = false;
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        total = 0.0;
        for (Item item : itens) {
            total += (item.getQuantidade() * item.getProduto().getPreco());
        }
        total = Math.round(total * 100.0) / 100.0;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Boolean getEfetivado() {
        return efetivado;
    }

    public void setEfetivado(Boolean efetivado) {
        this.efetivado = efetivado;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
        calcularValorTotal();
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Time getTempo() {
        return tempo;
    }

    public void setTempo(Time tempo) {
        this.tempo = tempo;
    }
}
