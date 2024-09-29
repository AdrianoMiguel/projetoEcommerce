package utils;
import com.github.javafaker.Faker;
import dominio.cliente.*;
import java.util.Locale;
import java.util.Random;

public class FakerModificado extends Faker {

    public FakerModificado() {
        super(new Locale("pt-BR"));
    }

    public Genero genero() {
        Random random = new Random();
        Genero[] generos = Genero.values();

        int index = random.nextInt(generos.length - 1) + 1;
        return generos[index];
    }

    public TpResidencia tipoDeResidencia() {
        Random random = new Random();
        TpResidencia[] TpResid = TpResidencia.values();

        int index = random.nextInt(TpResid.length - 1) + 1;
        return TpResid[index];
    }

    public TpLogradouro tipoDeLogradouro() {
        Random random = new Random();
        TpLogradouro[] TpLograd = TpLogradouro.values();

        int index = random.nextInt(TpLograd.length - 1) + 1;
        return TpLograd[index];
    }

    public Bandeira bandeira() {
        Random random = new Random();
        Bandeira[] bandeiras = Bandeira.values();

        int index = random.nextInt(bandeiras.length - 1) + 1;
        return bandeiras[index];
    }

    public TpTelefone tipoDeTelefone() {
        Random random = new Random();
        TpTelefone[] tipos = TpTelefone.values();

        int index = random.nextInt(tipos.length - 1) + 1;
        return tipos[index];
    }

    public String logradouro() {
        String logradouro = this.address().streetName();

        // Encontrar o primeiro espaço e remover a primeira palavra
        return logradouro.substring(logradouro.indexOf(" ") + 1);
    }
}