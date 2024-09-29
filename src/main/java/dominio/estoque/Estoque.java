package dominio.estoque;

import dominio.EntidadeDominio;
import dominio.produto.Vinho;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Estoque extends EntidadeDominio {
    private Integer vinhoId;
    private Timestamp dataEntrada;
    private Double custoUnitario;
    private Integer quantidade;
    private Fornecedor fornecedor;

    public Estoque(Integer vinhoId, Double custoUnitario, Integer quantidade, Fornecedor fornecedor) {
        this.vinhoId = vinhoId;
        dataEntrada = Timestamp.valueOf(LocalDateTime.now());
        this.custoUnitario = custoUnitario;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;


    }

    public Integer getVinhoId() {
        return vinhoId;
    }

    public void setVinhoId(Integer vinhoId) {
        this.vinhoId = vinhoId;
    }

    public Timestamp getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Timestamp dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Double getCustoUnitario() {
        return custoUnitario;
    }

    public void setCustoUnitario(Double custoUnitario) {
        this.custoUnitario = custoUnitario;
    }
}


