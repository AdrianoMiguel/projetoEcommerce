package dominio.produto;

import dominio.EntidadeDominio;
import dominio.estoque.Estoque;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Vinho extends EntidadeDominio {
    private String nome;
    private Integer safra;
    private Double teorAlc;
    private String descricao;
    private TpVinho tipoVinho;
    private List<TpUva> tipoUva;
    private Pais pais;
    private Double maiorCusto;
    private Double preco;
    private Integer qtdeEstoque;
    private Precificacao grupoPrecificacao;
    private String codBarras;
    private Integer volume;
    private Boolean status;
    private Motivo motivo;
    private Timestamp dataSemEstoque;
    private static Double valorVendaMinimoPredefinido = 10.0;
    private Double valorMinimoVenda;
    private String alteracoes;


    public Vinho(Integer id) {
        setId(id);
    }

    public Vinho (Integer id, String nome, Integer safra, Double teorAlc, String descricao, TpVinho tipoVinho,
                 List<TpUva> tipoUva, Pais pais, Double custo, Double preco, Integer qtdeEstoque, Precificacao grupoPrecificacao,
                 String codBarras, Integer volume, Boolean status, MotivoCategoria motivoCategoria, String justificativa) {
        this(nome, safra, teorAlc, descricao, tipoVinho, tipoUva, pais, custo, preco, qtdeEstoque, grupoPrecificacao, codBarras, volume, status, motivoCategoria, justificativa);
        setId(id);
    }

    public Vinho(String nome, Integer safra, Double teorAlc, String descricao, TpVinho tipoVinho,
                 List<TpUva> tipoUva, Pais pais, Double custo, Double preco, Integer qtdeEstoque, Precificacao grupoPrecificacao,
                 String codBarras, Integer volume, Boolean status, MotivoCategoria motivoCategoria, String justificativa) {
        this.nome = nome;
        this.safra = safra;
        this.teorAlc = teorAlc;
        this.descricao = descricao;
        this.tipoVinho = tipoVinho;
        this.tipoUva = tipoUva;
        this.pais = pais;
        maiorCusto = custo;
        this.preco = preco;
        this.qtdeEstoque = qtdeEstoque;
        this.grupoPrecificacao = grupoPrecificacao;
        this.codBarras = codBarras;
        this.volume = volume;
        this.status = status;
        this.motivo = new Motivo(motivoCategoria, justificativa);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSafra() {
        return safra;
    }

    public void setSafra(Integer safra) {
        this.safra = safra;
    }

    public Double getTeorAlc() {
        return teorAlc;
    }

    public void setTeorAlc(Double teorAlc) {
        this.teorAlc = teorAlc;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TpVinho getTipoVinho() {
        return tipoVinho;
    }

    public void setTipoVinho(TpVinho tipoVinho) {
        this.tipoVinho = tipoVinho;
    }

    public List<TpUva> getTipoUva() {
        return tipoUva;
    }

    public void setTipoUva(List<TpUva> tipoUva) {
        this.tipoUva = tipoUva;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(Integer qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    public Precificacao getGrupoPrecificacao() {
        return grupoPrecificacao;
    }

    public void setGrupoPrecificacao(Precificacao grupoPrecificacao) {
        this.grupoPrecificacao = grupoPrecificacao;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getMaiorCusto() {
        return maiorCusto;
    }

    public void setMaiorCusto(Double maiorCusto) {
        this.maiorCusto = maiorCusto;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public Timestamp getDataSemEstoque() {
        return dataSemEstoque;
    }

    public void setDataSemEstoque(Timestamp dataSemEstoque) {
        this.dataSemEstoque = dataSemEstoque;
    }

    public String getAlteracoes() {
        return alteracoes;
    }

    public void setAlteracoes(String alteracoes) {
        this.alteracoes = alteracoes;
    }

    public Double getValorMinimoVenda() {
        return valorMinimoVenda;
    }

    public void setValorMinimoVenda(Double valorMinimoVenda) {
        this.valorMinimoVenda = valorMinimoVenda;
    }
}