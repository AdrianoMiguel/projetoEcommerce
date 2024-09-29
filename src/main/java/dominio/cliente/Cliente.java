package dominio.cliente;

import dominio.EntidadeDominio;

import java.util.Date;
import java.util.List;

public class Cliente extends EntidadeDominio {
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private Genero genero;
    private Endereco endResid;
    private Endereco endCob;
    private List<Endereco> endEnt;
    private Contato contato;
    private List<Cartao> cartoes;
    private String senha;
    private Integer ranking;
    private Boolean status;
    private Boolean senhaFoiCriptografada;
    private String alteracoes;
    private List<Cupom> cupons;

public Cliente(Integer id) {
    setId(id);
}

    public Cliente (Integer id, String nome, String cpf, Date dataNascimento, Genero genero, Endereco endResid,
                    Endereco endCob, List<Endereco> endEnt, Contato contato, List<Cartao> cartoes,
                    String senha, Integer ranking, Boolean status, Boolean senhaFoiCriptografada) {
        this(nome, cpf, dataNascimento, genero, endResid, endCob, endEnt, contato, cartoes, senha);
        this.setRanking(ranking);
        this.setStatus(status);
        this.setSenhaFoiCriptografada(senhaFoiCriptografada);
     this.setId(id);
    }

    public Cliente(String nome, String cpf, Date dataNascimento, Genero genero, Endereco endResid,
                   Endereco endCob, List<Endereco> endEnt, Contato contato, List<Cartao> cartoes,
                   String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.endResid = endResid;
        this.endCob = endCob;
        this.endEnt = endEnt;
        this.contato = contato;
        this.cartoes = cartoes;
        this.senha = senha;
        this.ranking = 1;
        this.status = true;
        this.senhaFoiCriptografada = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Endereco getEndResid() {
        return endResid;
    }

    public void setEndResid(Endereco endResid) {
        this.endResid = endResid;
    }

    public Endereco getEndCob() {
        return endCob;
    }

    public void setEndCob(Endereco endCob) {
        this.endCob = endCob;
    }

    public List<Endereco> getEndEnt() {
        return endEnt;
    }

    public void setEndEnt(List<Endereco> endEnt) {
        this.endEnt = endEnt;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getSenhaFoiCriptografada() {
        return senhaFoiCriptografada;
    }

    public void setSenhaFoiCriptografada(Boolean senhaFoiCriptografada) {
        this.senhaFoiCriptografada = senhaFoiCriptografada;
    }

    public String getAlteracoes() {
        return alteracoes;
    }

    public void setAlteracoes(String alteracoes) {
        this.alteracoes = alteracoes;
    }

    public List<Cupom> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }
}