package dominio.compra;

import dominio.EntidadeDominio;
import dominio.cliente.Cartao;
import dominio.cliente.Cupom;
import dominio.cliente.Endereco;

import java.sql.Timestamp;
import java.util.List;

public class Compra extends EntidadeDominio {
    private Status status;
    private Integer clienteId;
    private Timestamp dataHora;
    private Carrinho carrinho;
    private List<Pagamento> pagamentos;
    private List<Cupom> cupons;
    private Endereco enderecoEntrega;
    private Frete frete;
    private Double valorFinal;
    private Status proximoStatus;


    public Compra (Integer id) {
        setId(id);
    }

    public Compra (Integer id, Integer clienteId, Status status, Carrinho carrinho, List<Pagamento> pagamentos, Endereco endereco, Frete frete) {
        setId(id);
        this.clienteId = clienteId;
        this.status = status;
        this.dataHora = Timestamp.valueOf(java.time.LocalDateTime.now());
        this.carrinho = carrinho;
        this.pagamentos = pagamentos;
        this.enderecoEntrega = endereco;
        this.frete = frete;
        calcularValorFinal();
    }


    private void calcularValorFinal() {
        valorFinal = carrinho.getTotal() + frete.getValor();

        if (cupons != null) {
            for (Cupom cupom : cupons){
                valorFinal -= cupom.getValor();
            }
        }
        valorFinal = Math.round(valorFinal * 100.0) / 100.0;
    }

    public Compra (Integer clienteId, Status status, Carrinho carrinho, List<Pagamento> pagamentos, Endereco endereco, Frete frete) {
        this.clienteId = clienteId;
        this.status = status;
        this.dataHora = Timestamp.valueOf(java.time.LocalDateTime.now());
        this.carrinho = carrinho;
        this.pagamentos = pagamentos;
        this.enderecoEntrega = endereco;
        this.frete = frete;
        calcularValorFinal();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public List<Cupom> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
        calcularValorFinal();
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = Math.round(valorFinal * 100.0) / 100.0;
    }

    public Frete getFrete() {
        return frete;
    }

    public void setFrete(Frete frete) {
        this.frete = frete;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public Status getProximoStatus() {
        Status[] statusArray = Status.values();
        int currentIndex = status.ordinal(); // Índice do status atual
        if (currentIndex == statusArray.length - 1) {
            return null;
        }else {
            return statusArray[currentIndex + 1];
        }
    }
}
