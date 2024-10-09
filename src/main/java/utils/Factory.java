package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import dominio.cliente.*;

public class Factory {
    private final static FakerModificado faker = new FakerModificado();

    public static Cliente ClienteTeste() {


        List<Endereco> endEnt = new ArrayList<>();
        endEnt.add(new Endereco("Endereco de teste de Entrega",
                faker.tipoDeResidencia(),
                faker.tipoDeLogradouro(),
                faker.logradouro(),
                Integer.valueOf(faker.numerify("###")),
                new Bairro(
                        "Centro",
                        new Cidade(
                                faker.address().cityName(),
                                new Estado(
                                        faker.address().state(),
                                        new Pais("Brasil")
                                ))),
                faker.address().zipCode(),
                faker.address().secondaryAddress()
        ));
        endEnt.add(new Endereco("Endereco de teste de Entrega 2",
                faker.tipoDeResidencia(),
                faker.tipoDeLogradouro(),
                faker.logradouro(),
                Integer.valueOf(faker.numerify("###")),
                new Bairro(
                        "Centro",
                        new Cidade(
                                faker.address().cityName(),
                                new Estado(
                                        faker.address().state(),
                                        new Pais("Brasil")
                                ))),
                faker.address().zipCode(),
                faker.address().secondaryAddress()
        ));

 List<Cartao> cartoes = new ArrayList<>();
        cartoes.add(new Cartao(
                "Cartao de teste 1",
                faker.bandeira(),
                faker.numerify("################"),
                Integer.valueOf(faker.numerify("###")),
                true
        ));
        cartoes.add(new Cartao(
                "Cartao de teste 2",
                faker.bandeira(),
                faker.numerify("################"),
                Integer.valueOf(faker.numerify("###")),
                false
        ));

        Cliente cliente = new Cliente(
            faker.name().fullName(),
            faker.numerify("###########"),
            faker.date().birthday(),
                faker.genero(),
            new Endereco("Endereco de teste Residencial",
                    faker.tipoDeResidencia(),
                    faker.tipoDeLogradouro(),
                faker.logradouro(),
                    Integer.valueOf(faker.numerify("###")),
                new Bairro(
                    "Centro",
                    new Cidade(
                            faker.address().cityName(),
                            new Estado(
                                faker.address().state(),
                                new Pais("Brasil")
                            ))),
                    faker.address().zipCode(),
                    faker.address().secondaryAddress()
        ),
               new Endereco("Endereco de teste de Cobranca",
                        faker.tipoDeResidencia(),
                        faker.tipoDeLogradouro(),
                        faker.logradouro(),
                        Integer.valueOf(faker.numerify("###")),
                        new Bairro(
                                "Centro",
                                new Cidade(
                                        faker.address().cityName(),
                                        new Estado(
                                                faker.address().state(),
                                                new Pais("Brasil")
                                        ))),
                        faker.address().zipCode(),
                        faker.address().secondaryAddress()
                ),
                endEnt,
                new Contato(
                        faker.internet().emailAddress(),
                        new Telefone(
                                faker.tipoDeTelefone(),
                                11,
                                Long.valueOf(faker.numerify("9########"))
                        )),
                cartoes,
                faker.internet().password()+"@A1"
                );
        try {
            int id = Integer.parseInt(faker.idDeCliente());
            cliente.setId(id);
        } catch (Exception e) {
            System.out.println("Erro ao atribuir ID: " + e.getMessage());
        }
        return cliente;

    }
}
