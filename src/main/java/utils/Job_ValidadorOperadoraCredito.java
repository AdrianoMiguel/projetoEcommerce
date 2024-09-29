package utils;

import controle.Fachada;
import controle.IFachada;
import dominio.compra.Carrinho;
import dominio.compra.Compra;
import negocio.compra.ValidacaoOperadoraDeCredito;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import persistencia.CarrinhoDAO;
import persistencia.CompraDAO;

import java.util.List;

public class Job_ValidadorOperadoraCredito implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ValidacaoOperadoraDeCredito rnValOpCred = new ValidacaoOperadoraDeCredito();

        List<Compra> compras = CompraDAO.listar();

        for (Compra compra : compras) {
            rnValOpCred.processar(compra);
        }

    }
}