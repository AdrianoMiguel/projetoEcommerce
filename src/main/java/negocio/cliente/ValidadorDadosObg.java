package negocio.cliente;
import dominio.EntidadeDominio;
import dominio.cliente.*;
import negocio.IStrategy;

public class ValidadorDadosObg implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            return "O campo nome é obrigatório";
        }
        if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            return "O campo CPF é obrigatório";
        }
        if (cliente.getGenero() == Genero.Genero) {
            return "O campo gênero é obrigatório";
        }
        if (cliente.getDataNascimento() == null || cliente.getDataNascimento().toString().isBlank()) {
            return "O campo data de nascimento é obrigatório";
        }
        if (cliente.getEndResid().getTipoResid() == TpResidencia.Tipo_de_Residencia) {
            return "O campo tipo de residência do endereco residencial é obrigatório";
        }
        if (cliente.getEndResid().getTipoLograd() == TpLogradouro.Tipo_de_Logr) {
            return "O campo tipo de logradouro do endereco residencial é obrigatório";
        }
        if (cliente.getEndResid().getLogradouro() == null || cliente.getEndResid().getLogradouro().isBlank()) {
            return "O campo logradouro do endereco residencial é obrigatório";
        }
        if (cliente.getEndResid().getNumero() == null) {
            return "O campo número do endereco residencial é obrigatório";
        }
        if (cliente.getEndResid().getBairro().getNome() == null || cliente.getEndResid().getBairro().getNome().isBlank()) {
            return "O campo bairro do endereco residencial é obrigatório";
        }
        if (cliente.getEndResid().getBairro().getCidade().getNome() == null || cliente.getEndResid().getBairro().getCidade().getNome().isBlank()) {
            return "O campo cidade do endereco residencial é obrigatório";
        }
        if (cliente.getEndResid().getBairro().getCidade().getEstado().getNome() == null || cliente.getEndResid().getBairro().getCidade().getEstado().getNome().isBlank()) {
            return "O campo estado do endereco residencial é obrigatório";
        }
        if (cliente.getEndResid().getBairro().getCidade().getEstado().getPais().getNome() == null || cliente.getEndResid().getBairro().getCidade().getEstado().getPais().getNome().isBlank()) {
            return "O campo país do endereco residencial é obrigatório";
        }
        if (cliente.getEndCob().getTipoResid() == TpResidencia.Tipo_de_Residencia) {
            return "O campo tipo de residência do endereco de cobrança é obrigatório";
        }
        if (cliente.getEndCob().getTipoLograd() == TpLogradouro.Tipo_de_Logr) {
            return "O campo tipo de logradouro do endereco de cobrança é obrigatório";
        }
        if (cliente.getEndCob().getLogradouro() == null || cliente.getEndCob().getLogradouro().isBlank()) {
            return "O campo logradouro do endereco de cobrança é obrigatório";
        }
        if (cliente.getEndCob().getNumero() == null) {
            return "O campo número do endereco de cobrança é obrigatório";
        }
        if (cliente.getEndCob().getBairro().getNome() == null || cliente.getEndCob().getBairro().getNome().isBlank()) {
            return "O campo bairro do endereco de cobrança é obrigatório";
        }
        if (cliente.getEndCob().getBairro().getCidade().getNome() == null || cliente.getEndCob().getBairro().getCidade().getNome().isBlank()) {
            return "O campo cidade do endereco de cobrança é obrigatório";
        }
        if (cliente.getEndCob().getBairro().getCidade().getEstado().getNome() == null || cliente.getEndCob().getBairro().getCidade().getEstado().getNome().isBlank()) {
            return "O campo estado do endereco de cobrança é obrigatório";
        }
        if (cliente.getEndCob().getBairro().getCidade().getEstado().getPais().getNome() == null || cliente.getEndCob().getBairro().getCidade().getEstado().getPais().getNome().isBlank()) {
            return "O campo país do endereco de cobrança é obrigatório";
        }
        for (Endereco endereco : cliente.getEndEnt()) {
            if (endereco.getNome() == null || endereco.getNome().isBlank()) {
                return "O campo nome do endereco de entrega é obrigatório";
            }
            if (endereco.getTipoResid() == TpResidencia.Tipo_de_Residencia) {
                return "O campo tipo de residência do endereco de entrega é obrigatório";
            }
            if (endereco.getTipoLograd() == TpLogradouro.Tipo_de_Logr) {
                return "O campo tipo de logradouro do endereco de entrega é obrigatório";
            }
            if (endereco.getLogradouro() == null || endereco.getLogradouro().isBlank()) {
                return "O campo logradouro do endereco de entrega é obrigatório";
            }
            if (endereco.getNumero() == null) {
                return "O campo número do endereco de entrega é obrigatório";
            }
            if (endereco.getBairro().getNome() == null || endereco.getBairro().getNome().isBlank()) {
                return "O campo bairro do endereco de entrega é obrigatório";
            }
            if (endereco.getBairro().getCidade().getNome() == null || endereco.getBairro().getCidade().getNome().isBlank()) {
                return "O campo cidade do endereco de entrega é obrigatório";
            }
            if (endereco.getBairro().getCidade().getEstado().getNome() == null || endereco.getBairro().getCidade().getEstado().getNome().isBlank()) {
                return "O campo estado do endereco de entrega é obrigatório";
            }
            if (endereco.getBairro().getCidade().getEstado().getPais().getNome() == null || endereco.getBairro().getCidade().getEstado().getPais().getNome().isBlank()) {
                return "O campo país do endereco de entrega é obrigatório";
            }
        }
        for (Cartao cartao : cliente.getCartoes()) {
            if (cartao.getNome() == null || cartao.getNome().isBlank()) {
                return "O campo nome impresso do cartão é obrigatório";
            }
            if (cartao.getBandeira() == Bandeira.Bandeira) {
                return "O campo bandeira do cartão é obrigatório";
            }
            if (cartao.getNumero() == null ) {
                return "O campo número do cartão é obrigatório";
            }
            if (cartao.getCod() == null) {
                return "O campo código de segurança do cartão é obrigatório";
            }
        }

        if (cliente.getContato().getEmail() == null || cliente.getContato().getEmail().isBlank()) {
            return "O campo email é obrigatório";
        }
        if (cliente.getContato().getTelefone().getTipo() == TpTelefone.Telefone) {
            return "O campo tipo de telefone é obrigatório";
        }
        if (cliente.getContato().getTelefone().getDdd() == null) {
            return "O campo DDD do telefone é obrigatório";
        }
        if (cliente.getContato().getTelefone().getNumero() == null) {
            return "O campo número do telefone é obrigatório";
        }
        return null;
    }
}