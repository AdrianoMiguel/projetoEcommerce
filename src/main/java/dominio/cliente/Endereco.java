package dominio.cliente;

public class Endereco {
    private String nome;
    private TpResidencia tipoResid;
    private TpLogradouro tipoLograd;
    private String logradouro;
    private Integer numero;
    private Bairro bairro;
    private String cep;
    private String obs;
    private Boolean principal;

    public Endereco(String nome, TpResidencia tipoResid, TpLogradouro tipoLograd, String logradouro, Integer numero,
                    Bairro bairro, String cep, String obs) {
        this.nome = nome;
        this.logradouro = logradouro;
        this.tipoResid = tipoResid;
        this.tipoLograd = tipoLograd;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.obs = obs;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TpResidencia getTipoResid() {
        return tipoResid;
    }

    public void setTipoResid(TpResidencia tipoResid) {
        this.tipoResid = tipoResid;
    }

    public TpLogradouro getTipoLograd() {
        return tipoLograd;
    }

    public void setTipoLograd(TpLogradouro tipoLograd) {
        this.tipoLograd = tipoLograd;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }
}
