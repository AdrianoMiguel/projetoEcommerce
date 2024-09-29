
package negocio.produto;

import dominio.EntidadeDominio;
import dominio.produto.*;
import negocio.IStrategy;


//RN011
public class ValidadorDadosObg implements IStrategy {
    public String processar(EntidadeDominio entidade) {
        Vinho vinho = (Vinho) entidade;

        if(vinho.getNome() == null || vinho.getNome().isBlank()){
            return "O campo nome é obrigatório";
        }
        //RN0020 - Autores de Livros
        if(vinho.getTipoVinho().equals(TpVinho.TIPO_DE_VINHO)){
            return "É obrigatório associar o tipo de Vinho";
        }
        //RN0019 - Categorias associadas a livro
        if(vinho.getTipoUva().isEmpty()){
            return "É obrigatório associar no mínimo um tipo de uva ao vinho";
        }
        if(vinho.getSafra() == null){
            return "O campo Safra é obrigatório";
        }
        if(vinho.getTeorAlc() == null){
            return "O campo teor alcoólico é obrigatório";
        }
        if (vinho.getPais().equals(Pais.PAÍS)) { //
            return "O campo País é obrigatório";
        }
        if(vinho.getDescricao() == null || vinho.getDescricao().isBlank()) {
            return "O campo descrição é obrigatório";
        }
        if (vinho.getGrupoPrecificacao() == Precificacao.GRUPO_DE_PRECIFICACAO) {
            return "O campo grupo de precificação é obrigatório";
        }
        if (vinho.getCodBarras() == null) {
            return "O campo código de barras é obrigatório";
        }
        if(vinho.getPreco() == null || vinho.getPreco() <= 0) { //
            return "O campo preço de venda é obrigatório e não pode ser menor ou igual a zero";
        }
        if (vinho.getQtdeEstoque() == null || vinho.getQtdeEstoque() < 0) { //
            return "O campo quantidade no estoque é obrigatório e não pode ser negativo";
        }
        if (vinho.getStatus() == null) {
            return "o campo Status é obrigatório";
        }

        return null;
    }
}

