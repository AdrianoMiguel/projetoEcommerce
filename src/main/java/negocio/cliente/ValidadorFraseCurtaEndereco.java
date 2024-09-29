package negocio.cliente;
import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import dominio.cliente.Endereco;
import negocio.IStrategy;

public class ValidadorFraseCurtaEndereco implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        for (Endereco endereco : cliente.getEndEnt()) {
            if (!endereco.getNome().trim().contains(" ")) {
                return "O campo nome do endereco de entrega deve ser identificado com um nome composto de uma frase curta";
            }
            if (endereco.getNome().length() > 50) {
                return "O campo nome do endereco de entrega deve ser identificado com um nome composto de uma frase curta";
            }
        }

        return null;
    }
}
