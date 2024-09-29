package negocio.cliente;

import dominio.EntidadeDominio;
import dominio.cliente.*;
import negocio.IStrategy;
import persistencia.ClienteDAO;

import java.util.ArrayList;
import java.util.List;

public class GeradorDescricaoAlteracoes implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente clienteEditado = (Cliente) entidade;
        Cliente clienteAntesEdicao = null;
        String alteracoesStr;

        if (clienteEditado.getId() != 0) {
            try {
                clienteAntesEdicao = ClienteDAO.buscarClientePorId(clienteEditado.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            List<String> alteracoes = new ArrayList<>();

            // Comparar os atributos
            if (!clienteEditado.getNome().equalsIgnoreCase(clienteAntesEdicao.getNome())) {
                alteracoes.add("Nome de: " + clienteAntesEdicao.getNome() + " para: " + clienteEditado.getNome());
            }

            if (!clienteEditado.getCpf().equalsIgnoreCase(clienteAntesEdicao.getCpf())) {
                alteracoes.add("CPF de:" + clienteAntesEdicao.getCpf() + " para: " + clienteEditado.getCpf());
            }

            if (!clienteEditado.getDataNascimento().equals(clienteAntesEdicao.getDataNascimento())) {
                alteracoes.add("Dt de Nasc de: " + clienteAntesEdicao.getDataNascimento() + " para: " + clienteEditado.getDataNascimento());
            }

            if (!clienteEditado.getGenero().equals(clienteAntesEdicao.getGenero())) {
                alteracoes.add("Gênero de: " + clienteAntesEdicao.getGenero() + " para: " + clienteEditado.getGenero());
            }

            if (!clienteEditado.getEndResid().getTipoResid().equals(clienteAntesEdicao.getEndResid().getTipoResid())) {
                alteracoes.add("Tipo de Resid do End Resid de: " + clienteAntesEdicao.getEndResid().getTipoResid() + " para: " + clienteEditado.getEndResid().getTipoResid());
            }

            if (!clienteEditado.getEndResid().getTipoLograd().equals(clienteAntesEdicao.getEndResid().getTipoLograd())) {
                alteracoes.add("Tipo de Lograd do End Res de: " + clienteAntesEdicao.getEndResid().getTipoLograd() + " para: " + clienteEditado.getEndResid().getTipoLograd());
            }

            if (!clienteEditado.getEndResid().getLogradouro().equalsIgnoreCase(clienteAntesEdicao.getEndResid().getLogradouro())) {
                alteracoes.add("Lograd do End Res de: " + clienteAntesEdicao.getEndResid().getLogradouro() + " para: " + clienteEditado.getEndResid().getLogradouro());
            }

            if (!clienteEditado.getEndResid().getNumero().equals(clienteAntesEdicao.getEndResid().getNumero())) {
                alteracoes.add("Num do End Res de: " + clienteAntesEdicao.getEndResid().getNumero() + " para: " + clienteEditado.getEndResid().getNumero());
            }

            if (!clienteEditado.getEndResid().getBairro().getNome().equalsIgnoreCase(clienteAntesEdicao.getEndResid().getBairro().getNome())) {
                alteracoes.add("Bairro do End Res de: " + clienteAntesEdicao.getEndResid().getBairro() + " para: " + clienteEditado.getEndResid().getBairro());
            }

            if (!clienteEditado.getEndResid().getBairro().getCidade().getNome().equalsIgnoreCase(clienteAntesEdicao.getEndResid().getBairro().getCidade().getNome())) {
                alteracoes.add("Cidade do End Res de: " + clienteAntesEdicao.getEndResid().getBairro().getCidade() + " para: " + clienteEditado.getEndResid().getBairro().getCidade());
            }

            if (!clienteEditado.getEndResid().getBairro().getCidade().getEstado().getNome().equalsIgnoreCase(clienteAntesEdicao.getEndResid().getBairro().getCidade().getEstado().getNome())) {
                alteracoes.add("Estado do End Res de: " + clienteAntesEdicao.getEndResid().getBairro().getCidade().getEstado() + " para: " + clienteEditado.getEndResid().getBairro().getCidade().getEstado());
            }

            if (!clienteEditado.getEndResid().getBairro().getCidade().getEstado().getPais().getNome().equalsIgnoreCase(clienteAntesEdicao.getEndResid().getBairro().getCidade().getEstado().getPais().getNome())) {
                alteracoes.add("País do End Res de: " + clienteAntesEdicao.getEndResid().getBairro().getCidade().getEstado().getPais() + " para: " + clienteEditado.getEndResid().getBairro().getCidade().getEstado().getPais());
            }

            if (!clienteEditado.getEndResid().getCep().equalsIgnoreCase(clienteAntesEdicao.getEndResid().getCep())) {
                alteracoes.add("CEP do End Res de: " + clienteAntesEdicao.getEndResid().getCep() + " para: " + clienteEditado.getEndResid().getCep());
            }

            if (!clienteEditado.getEndResid().getObs().equalsIgnoreCase(clienteAntesEdicao.getEndResid().getObs())) {
                alteracoes.add("Obs do End Res alteradas de " + clienteAntesEdicao.getEndResid().getObs() + " para: " + clienteEditado.getEndResid().getObs());
            }
            if (!clienteEditado.getEndCob().getTipoResid().equals(clienteAntesEdicao.getEndCob().getTipoResid())) {
                alteracoes.add("Tipo de Resid do End de Cob de: " + clienteAntesEdicao.getEndCob().getTipoResid() + " para: " + clienteEditado.getEndCob().getTipoResid());
            }
            if (!clienteEditado.getEndCob().getTipoLograd().equals(clienteAntesEdicao.getEndCob().getTipoLograd())) {
                alteracoes.add("Tipo de Lograd do End de Cob de: " + clienteAntesEdicao.getEndCob().getTipoLograd() + " para: " + clienteEditado.getEndCob().getTipoLograd());
            }
            if (!clienteEditado.getEndCob().getLogradouro().equalsIgnoreCase(clienteAntesEdicao.getEndCob().getLogradouro())) {
                alteracoes.add("Lograd do End de Cob de: " + clienteAntesEdicao.getEndCob().getLogradouro() + " para: " + clienteEditado.getEndCob().getLogradouro());
            }
            if (!clienteEditado.getEndCob().getNumero().equals(clienteAntesEdicao.getEndCob().getNumero())) {
                alteracoes.add("Num do End de Cob de: " + clienteAntesEdicao.getEndCob().getNumero() + " para: " + clienteEditado.getEndCob().getNumero());
            }
            if (!clienteEditado.getEndCob().getBairro().getNome().equalsIgnoreCase(clienteAntesEdicao.getEndCob().getBairro().getNome())) {
                alteracoes.add("Bairro do End de Cob de: " + clienteAntesEdicao.getEndCob().getBairro().getNome() + " para: " + clienteEditado.getEndCob().getBairro().getNome());
            }
            if (!clienteEditado.getEndCob().getBairro().getCidade().getNome().equalsIgnoreCase(clienteAntesEdicao.getEndCob().getBairro().getCidade().getNome())) {
                alteracoes.add("Cidade do End de Cob de: " + clienteAntesEdicao.getEndCob().getBairro().getCidade().getNome() + " para: " + clienteEditado.getEndCob().getBairro().getCidade().getNome());
            }
            if (!clienteEditado.getEndCob().getBairro().getCidade().getEstado().getNome().equalsIgnoreCase(clienteAntesEdicao.getEndCob().getBairro().getCidade().getEstado().getNome())) {
                alteracoes.add("Estado do End de Cob de: " + clienteAntesEdicao.getEndCob().getBairro().getCidade().getEstado().getNome() + " para: " + clienteEditado.getEndCob().getBairro().getCidade().getEstado().getNome());
            }
            if (!clienteEditado.getEndCob().getBairro().getCidade().getEstado().getPais().getNome().equalsIgnoreCase(clienteAntesEdicao.getEndCob().getBairro().getCidade().getEstado().getPais().getNome())) {
                alteracoes.add("País do End de Cob de: " + clienteAntesEdicao.getEndCob().getBairro().getCidade().getEstado().getPais().getNome() + " para: " + clienteEditado.getEndCob().getBairro().getCidade().getEstado().getPais().getNome());
            }
            if (!clienteEditado.getEndCob().getCep().equalsIgnoreCase(clienteAntesEdicao.getEndCob().getCep())) {
                alteracoes.add("CEP do End de Cob de: " + clienteAntesEdicao.getEndCob().getCep() + " para: " + clienteEditado.getEndCob().getCep());
            }
            if (!clienteEditado.getEndCob().getObs().equalsIgnoreCase(clienteAntesEdicao.getEndCob().getObs())) {
                alteracoes.add("Obs do End de Cob alterado de " + clienteAntesEdicao.getEndCob().getObs() + " para: " + clienteEditado.getEndCob().getObs());
            }
            for (int i = 0; i < clienteAntesEdicao.getEndEnt().size(); i++) {
                if (!clienteEditado.getEndEnt().get(i).getNome().equalsIgnoreCase(clienteAntesEdicao.getEndEnt().get(i).getNome())) {
                    alteracoes.add("Nome do End de Ent " + i + 1 + " de: " + clienteAntesEdicao.getEndEnt().get(i).getNome() + " para: " + clienteEditado.getEndEnt().get(i).getNome());
                }
                if (!clienteEditado.getEndEnt().get(i).getTipoResid().equals(clienteAntesEdicao.getEndEnt().get(i).getTipoResid())) {
                    alteracoes.add("Tipo de Resid do End de Ent " + i + 1 + " de: " + clienteAntesEdicao.getEndEnt().get(i).getTipoResid() + " para: " + clienteEditado.getEndEnt().get(i).getTipoResid());
                }
                if (!clienteEditado.getEndEnt().get(i).getTipoLograd().equals(clienteAntesEdicao.getEndEnt().get(i).getTipoLograd())) {
                    alteracoes.add("Tipo de Lograd do End de Ent " + i + 1 + " de: " + clienteAntesEdicao.getEndEnt().get(i).getTipoLograd() + " para: " + clienteEditado.getEndEnt().get(i).getTipoLograd());
                }
                if (!clienteEditado.getEndEnt().get(i).getLogradouro().equalsIgnoreCase(clienteAntesEdicao.getEndEnt().get(i).getLogradouro())) {
                    alteracoes.add("Lograd do End de Ent " + i + 1 + " de: " + clienteAntesEdicao.getEndEnt().get(i).getLogradouro() + " para: " + clienteEditado.getEndEnt().get(i).getLogradouro());
                }
                if (!clienteEditado.getEndEnt().get(i).getNumero().equals(clienteAntesEdicao.getEndEnt().get(i).getNumero())) {
                    alteracoes.add("Num do End de Ent " + i + 1 + " de: " + clienteAntesEdicao.getEndEnt().get(i).getNumero() + " para: " + clienteEditado.getEndEnt().get(i).getNumero());
                }
                if (!clienteEditado.getEndEnt().get(i).getBairro().getNome().equalsIgnoreCase(clienteAntesEdicao.getEndEnt().get(i).getBairro().getNome())) {
                    alteracoes.add("Bairro do End de Ent " + i + 1 + " de: " + clienteAntesEdicao.getEndEnt().get(i).getBairro().getNome() + " para: " + clienteEditado.getEndEnt().get(i).getBairro().getNome());
                }
                if (!clienteEditado.getEndEnt().get(i).getBairro().getCidade().getNome().equalsIgnoreCase(clienteAntesEdicao.getEndEnt().get(i).getBairro().getCidade().getNome())) {
                    alteracoes.add("Cidade do End de Ent " + i + 1 + " de: " + clienteAntesEdicao.getEndEnt().get(i).getBairro().getCidade().getNome() + " para: " + clienteEditado.getEndEnt().get(i).getBairro().getCidade().getNome());
                }
                if (!clienteEditado.getEndEnt().get(i).getBairro().getCidade().getEstado().getNome().equalsIgnoreCase(clienteAntesEdicao.getEndEnt().get(i).getBairro().getCidade().getEstado().getNome())) {
                    alteracoes.add("Estado do End de Ent " + i + 1 + " de: " + clienteAntesEdicao.getEndEnt().get(i).getBairro().getCidade().getEstado().getNome() + " para: " + clienteEditado.getEndEnt().get(i).getBairro().getCidade().getEstado().getNome());
                }
                if (!clienteEditado.getEndEnt().get(i).getBairro().getCidade().getEstado().getPais().getNome().equalsIgnoreCase(clienteAntesEdicao.getEndEnt().get(i).getBairro().getCidade().getEstado().getPais().getNome())) {
                    alteracoes.add("País do End de Ent " + i + 1 + " de: " + clienteAntesEdicao.getEndEnt().get(i).getBairro().getCidade().getEstado().getPais().getNome() + " para: " + clienteEditado.getEndEnt().get(i).getBairro().getCidade().getEstado().getPais().getNome());
                }
                if (!clienteEditado.getEndEnt().get(i).getCep().equalsIgnoreCase(clienteAntesEdicao.getEndEnt().get(i).getCep())) {
                    alteracoes.add("CEP do End de Ent " + i + 1 + " de: " + clienteAntesEdicao.getEndEnt().get(i).getCep() + " para: " + clienteEditado.getEndEnt().get(i).getCep());
                }
                if (!clienteEditado.getEndEnt().get(i).getObs().equalsIgnoreCase(clienteAntesEdicao.getEndEnt().get(i).getObs())) {
                    alteracoes.add("Obs do End de Ent " + i + 1 + " alterado de " + clienteAntesEdicao.getEndEnt().get(i).getObs() + " para: " + clienteEditado.getEndEnt().get(i).getObs());
                }
            }

            if (clienteEditado.getEndEnt().size() > clienteAntesEdicao.getEndEnt().size()) {
                alteracoes.add(clienteEditado.getEndEnt().size() - clienteAntesEdicao.getEndEnt().size() + "Endereço de Entrega adicionado");
            }

            for (int i = 0; i < clienteAntesEdicao.getCartoes().size(); i++) {
                if (!clienteEditado.getCartoes().get(i).getNome().equalsIgnoreCase(clienteAntesEdicao.getCartoes().get(i).getNome())) {
                    alteracoes.add("Nome do Cartão " + i + 1 + " de: " + clienteAntesEdicao.getCartoes().get(i).getNome() + " para: " + clienteEditado.getCartoes().get(i).getNome());
                }
                if (!clienteEditado.getCartoes().get(i).getBandeira().equals(clienteAntesEdicao.getCartoes().get(i).getBandeira())) {
                    alteracoes.add("Bandeira do Cartão " + i + 1 + " de: " + clienteAntesEdicao.getCartoes().get(i).getBandeira() + " para: " + clienteEditado.getCartoes().get(i).getBandeira());
                }
                if (!clienteEditado.getCartoes().get(i).getNumero().equals(clienteAntesEdicao.getCartoes().get(i).getNumero())) {
                    alteracoes.add("Num do Cartão " + i + 1 + " de: " + clienteAntesEdicao.getCartoes().get(i).getNumero() + " para: " + clienteEditado.getCartoes().get(i).getNumero());
                }
                if (!clienteEditado.getCartoes().get(i).getCod().equals(clienteAntesEdicao.getCartoes().get(i).getCod())) {
                    alteracoes.add("CVC  " + i + 1 + " de: " + clienteAntesEdicao.getCartoes().get(i).getCod() + " para: " + clienteEditado.getCartoes().get(i).getCod());
                }
            }

            if (clienteEditado.getCartoes().size() > clienteAntesEdicao.getCartoes().size()) {
                alteracoes.add(clienteEditado.getCartoes().size() - clienteAntesEdicao.getCartoes().size() + " Cartão adicionado");
            }

            if (!clienteEditado.getContato().getEmail().equalsIgnoreCase(clienteAntesEdicao.getContato().getEmail())) {
                alteracoes.add("Email de: " + clienteAntesEdicao.getContato().getEmail() + " para: " + clienteEditado.getContato().getEmail());
            }
            if (!clienteEditado.getContato().getTelefone().getTipo().equals(clienteAntesEdicao.getContato().getTelefone().getTipo())) {
                alteracoes.add("Tipo de Tel de: " + clienteAntesEdicao.getContato().getTelefone().getTipo() + " para: " + clienteEditado.getContato().getTelefone().getTipo());
            }
            if (!clienteEditado.getContato().getTelefone().getDdd().equals(clienteAntesEdicao.getContato().getTelefone().getDdd())) {
                alteracoes.add("DDD de: " + clienteAntesEdicao.getContato().getTelefone().getDdd() + " para: " + clienteEditado.getContato().getTelefone().getDdd());
            }
            if (!clienteEditado.getContato().getTelefone().getNumero().equals(clienteAntesEdicao.getContato().getTelefone().getNumero())) {
                alteracoes.add("Num de Tel de: " + clienteAntesEdicao.getContato().getTelefone().getNumero() + " para: " + clienteEditado.getContato().getTelefone().getNumero());
            }
            if (!clienteEditado.getSenha().equals(clienteAntesEdicao.getSenha())) {
                alteracoes.add("Senha alterada");
            }
            if (!clienteEditado.getRanking().equals(clienteAntesEdicao.getRanking())) {
                alteracoes.add("Ranking de: " + clienteAntesEdicao.getRanking() + " para: " + clienteEditado.getRanking());
            }
            if (!clienteEditado.getStatus().equals(clienteAntesEdicao.getStatus())) {
                alteracoes.add("Status de: " + clienteAntesEdicao.getStatus() + " para: " + clienteEditado.getStatus());
            }
            alteracoesStr = String.join(", ", alteracoes);
            if (alteracoesStr.length() > 255) {
                alteracoesStr = alteracoesStr.substring(0, 252) + "...";
            }
            clienteEditado.setAlteracoes(alteracoesStr);
            return "";
        }
        return "";
    }
}

