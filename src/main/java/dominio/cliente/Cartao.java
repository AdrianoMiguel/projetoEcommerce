package dominio.cliente;

import dominio.EntidadeDominio;

public class Cartao extends EntidadeDominio {
    private String numero;
    private String nome;
    private Integer cod;
    private Bandeira bandeira;
    private Boolean principal;


    public Cartao(String nome,Bandeira bandeira, String numero, Integer cod, Boolean principal) {
        this.numero = numero;
        this.nome = nome;
        this.cod = cod;
        this.bandeira = bandeira;
        this.principal = principal;
    }

    public Cartao(Integer id, String nome,Bandeira bandeira, String numero, Integer cod, Boolean principal) {
        setId(id);
        this.numero = numero;
        this.nome = nome;
        this.cod = cod;
        this.bandeira = bandeira;
        this.principal = principal;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Bandeira getBandeira() {
        return bandeira;
    }

    public void setBandeira(Bandeira bandeira) {
        this.bandeira = bandeira;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }
}
