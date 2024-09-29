package dominio.produto;

public class Motivo {
    private MotivoCategoria categoria;
    private String justificativa;

    public Motivo (MotivoCategoria categoria, String justificativa) {
        this.categoria = categoria;
        this.justificativa = justificativa;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public MotivoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(MotivoCategoria categoria) {
        this.categoria = categoria;
    }
}
