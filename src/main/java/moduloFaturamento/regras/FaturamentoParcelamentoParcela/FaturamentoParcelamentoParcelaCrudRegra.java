package moduloFaturamento.regras.FaturamentoParcelamentoParcela;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaListaValoresDaParcelaDTO;
import moduloFaturamento.model.FaturamentoParcelamentoParcela;

@Service
public class FaturamentoParcelamentoParcelaCrudRegra extends FaturamentoParcelamentoParcelaRegra {

    public List<FaturamentoParcelamentoParcela> cadastrarNovasParcelasDoFaturamentoParcelamentoParcela(Integer cdParcelamento, BigDecimal valorDaEntrada, 
        LocalDate dataPagamentoDaEntrada, List<ParcelamentoFaturaListaValoresDaParcelaDTO> listaParcelas){

        return super.cadastrarParcelaDoParcelamento(cdParcelamento, valorDaEntrada, dataPagamentoDaEntrada, listaParcelas);
    }
    
}
