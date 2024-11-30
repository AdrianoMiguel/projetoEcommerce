package utils;
import com.github.javafaker.Faker;
import dominio.cliente.*;
import dominio.estoque.Fornecedor;
import dominio.produto.*;
import dominio.produto.Pais;
import persistencia.ClienteDAO;
import persistencia.ProdutoDAO;

import java.math.BigInteger;
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

    public List<TpUva> tipoDeUva() {

        List<TpUva> tipoUva = new ArrayList<>();

        TpUva[] tiposDeUva = TpUva.values();

        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            int index = random.nextInt(tiposDeUva.length - 1) + 1;
            tipoUva.add(tiposDeUva[index]);
        }

        return tipoUva;

    }

    public TpVinho tipoDeVinho() {
        Random random = new Random();
        TpVinho[] tipoVinho = TpVinho.values();

        int index = random.nextInt(tipoVinho.length - 1) + 1;
        return tipoVinho[index];
    }

    public Pais pais() {
        Random random = new Random();
        Pais[] paises = Pais.values();

        int index = random.nextInt(paises.length - 1) + 1;
        return paises[index];
    }

    public Precificacao precificacao() {
        Random random = new Random();
        Precificacao[] precificacoes = Precificacao.values();

        int index = random.nextInt(precificacoes.length - 1) + 1;
        return precificacoes[index];
    }

    public Fornecedor fornecedor() {
        Random random = new Random();
        Fornecedor[] fornecedores = Fornecedor.values();

        int index = random.nextInt(fornecedores.length - 1) + 1;
        return fornecedores[index];
    }

    public String nomeDoVinho() {
        Random random = new Random();
        List<String> prefixos = List.of("Chateau", "Domaine", "Vina", "Bodegas");
        int randomIndex = random.nextInt(prefixos.size());
        String prefixo = prefixos.get(randomIndex);
        String nome = this.name().lastName();
        return prefixo + " " + nome;
    }

    public Integer safra() {
        Random random = new Random();
        return random.nextInt(2024 - 1900) + 1900;
    }

    public Double teorAlcoolico() {
        Random random = new Random();
        double randomValue = random.nextDouble() * 30;
        return Math.round(randomValue * 2) / 2.0;
    }

    public Double preco() {
        Random random = new Random();
        return random.nextDouble() * 1000;
    }

    public Integer volume() {
        Random random = new Random();
        int[] volumes = {750, 1000, 1250};
        return volumes[random.nextInt(volumes.length)];
    }

    public BigInteger codigoDeBarras() {
        Random random = new Random();
        return BigInteger.valueOf(random.nextInt(999999999));
    }

    public String descricao() {
        return "Vinho de teste. " + this.lorem().sentence();
    }

    public Integer custo() {
        Random random = new Random();
        return random.nextInt(500) + 500;
    }

    public Integer qtdeEstoque() {
        Random random = new Random();
        return random.nextInt(20);
    }

}