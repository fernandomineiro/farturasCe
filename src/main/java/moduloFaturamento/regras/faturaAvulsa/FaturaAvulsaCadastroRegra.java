package moduloFaturamento.regras.faturaAvulsa;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaListaLancamentoDTO;
import moduloFaturamento.model.FaturaAvulsa;
import moduloFaturamento.model.LancamentosFaturaAvulsa;

@Service
public class FaturaAvulsaCadastroRegra extends FaturaAvulsaRegra{

    @Override
    public FaturaAvulsa cadastrarUmaFaturaAvulsa(Integer cdCliente, Integer refFatura, Short origemFatura, 
                                                Integer refAtedimento, Integer cdAtendimento, Short seqSS, 
                                                Short cdCidade, Integer dataVencimento, Short dvCliente,
                                                String mensagem01, String mensagem02, 
                                                BigDecimal valorFatura, Short grupoConsumo, Integer matriculaImovel){
                                                    
        return super.cadastrarUmaFaturaAvulsa(cdCliente, refFatura, origemFatura, 
                                    refAtedimento, cdAtendimento, seqSS, 
                                    cdCidade, dataVencimento, dvCliente, mensagem01, mensagem02, 
                                    valorFatura, grupoConsumo, matriculaImovel);

    }

    public List<LancamentosFaturaAvulsa> cadastrarListaDeLancamentoFaturaAvulsa(Integer cdCliente, Integer refFatura, Short origemFatura, 
                                                                                    List<FaturaAvulsaListaLancamentoDTO> listaDTO){
        return super.cadastrarLancamentofaturaAvulsa(cdCliente, refFatura, origemFatura, listaDTO);
    }
    
}
