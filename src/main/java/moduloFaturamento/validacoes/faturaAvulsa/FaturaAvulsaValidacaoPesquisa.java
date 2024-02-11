package moduloFaturamento.validacoes.faturaAvulsa;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class FaturaAvulsaValidacaoPesquisa extends FaturaAvulsaValidacao {

    public void gerarExcecoesParaPesquisa(Integer cdCliente, Short dvCliente, Integer matriculaImovel, Short dvMatriculaImovel, LocalDate refFatura){
        super.gerarExcecaoSeCampoPreenchidoVerificarMatriculaImovelSeExiste(matriculaImovel, dvMatriculaImovel);
        super.gerarExcecaoSeCdClienteExiste(cdCliente, dvCliente);
        super.gerarExcecaoSeCampoPreenchidoVerificarReferenciaDataValido(refFatura);
    }

    public void gerarExcecaoParaValidarMatricula(Integer matriculaImovel, Short dvMatriculaImovel){
        super.gerarExcecaoSeCampoPreenchidoVerificarMatriculaImovelSeExiste(matriculaImovel, dvMatriculaImovel);
    }
    
}
