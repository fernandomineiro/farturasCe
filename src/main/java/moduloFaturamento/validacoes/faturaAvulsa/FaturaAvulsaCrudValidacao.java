package moduloFaturamento.validacoes.faturaAvulsa;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class FaturaAvulsaCrudValidacao extends FaturaAvulsaValidacao{

    public void gerarExcecaoParaValidarMatricula(Integer cdCliente, Short dvCliente){
        super.gerarExcecaoSeCdClienteExiste(cdCliente, dvCliente);
    }

    public void gerarExcecaoParaCadastrarFaturaAvulsa(LocalDate refFatura, LocalDate dataVencimento, Integer refAtendimento, Integer cdAtendimento, Short seqSS){
        super.gerarExcecaoSeCampoPreenchidoVerificarReferenciaDataValido(refFatura);
        super.gerarExcecaoSeCampoPreenchidoVerificarReferenciaDataValido(dataVencimento);
        super.gerarExcecaoSeExisteSSValida(refAtendimento, cdAtendimento, seqSS);
        
    }

    public void gerarExcecaoParaEncerrarFaturaAvulsa(Long id){
        super.gerarExcecaoSeExisteUmaFaturaAvulsa(id);
    }
    
    
}
