package negocio.cliente;

import dominio.EntidadeDominio;
import dominio.cliente.Cliente;
import negocio.IStrategy;

public class ValidadorSenhaForte implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {
        Cliente cliente = (Cliente) entidade;
        if (!cliente.getSenhaFoiCriptografada()) {
            if (cliente.getSenha().length() < 8) {
                return "Senha fraca, a senha deve conter no mínimo 8 caracteres, ter letras maiúsculas, minúsculas, e caracteres especiais.";
            }

            // Verifica se a senha contém pelo menos uma letra maiúscula
            if (!cliente.getSenha().matches(".*[A-Z].*")) {
                return "Senha fraca, a senha deve conter no mínimo 8 caracteres, ter letras maiúsculas, minúsculas, e caracteres especiais.";
            }

            // Verifica se a senha contém pelo menos uma letra minúscula
            if (!cliente.getSenha().matches(".*[a-z].*")) {
                return "Senha fraca, a senha deve conter no mínimo 8 caracteres, ter letras maiúsculas, minúsculas, e caracteres especiais.";
            }

            // Verifica se a senha contém pelo menos um caractere especial
            if (!cliente.getSenha().matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                return "Senha fraca, a senha deve conter no mínimo 8 caracteres, ter letras maiúsculas, minúsculas, e caracteres especiais.";
            }


        }
        return null;
    }
}
