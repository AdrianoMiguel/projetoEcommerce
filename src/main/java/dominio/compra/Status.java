package dominio.compra;

public enum Status {
    Selecione,
    EM_PROCESSAMENTO,
    PAGAMENTO_REJEITADO,
    PAGAMENTO_APROVADO,
    EM_TRANSPORTE,
    ENTREGUE,
    EM_TROCA,
    TROCADO,
    FINALIZADO
}
