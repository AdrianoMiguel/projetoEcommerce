package utils;
import com.github.javafaker.Faker;
import dominio.cliente.*;
import dominio.produto.Vinho;
import persistencia.ClienteDAO;
import persistencia.ProdutoDAO;

import java.util.ArrayList;
import java.util.List;
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

    public String cliente() throws Exception {
        List<Cliente> clientes = null;

            clientes = ClienteDAO.listar();

        String[] nomes = new String[clientes.size()];
        for (int i = 0; i < clientes.size(); i++) {
            nomes[i] = clientes.get(i).getNome();
        }
        return nomes[new Random().nextInt(nomes.length)];
    }

    public String idDeCliente() throws Exception {
        List<Cliente> clientes = null;

            clientes = ClienteDAO.listar();

        String[] ids = new String[clientes.size()];
        for (int i = 0; i < clientes.size(); i++) {
            ids[i] = String.valueOf(clientes.get(i).getId());
        }
        return ids[new Random().nextInt(ids.length)];
    }

    public String verDetalhes() {
        List<Vinho> vinhos = null;

        try {
            vinhos = ProdutoDAO.listar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<String> ids = new ArrayList<>(); // Lista para armazenar os ids

        for (Vinho vinho : vinhos) {
            if (vinho.getQtdeEstoque() > 0) {
                ids.add("detalhes_" + vinho.getId());
            }
        }

        if (!ids.isEmpty()) {
            return ids.get(new Random().nextInt(ids.size()));
        } else {
            return null;
        }
    }
}