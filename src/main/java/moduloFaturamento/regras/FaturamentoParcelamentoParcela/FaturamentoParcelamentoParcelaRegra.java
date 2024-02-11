package moduloFaturamento.regras.FaturamentoParcelamentoParcela;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaListaValoresDaParcelaDTO;
import moduloFaturamento.model.FaturamentoParcelamentoParcela;
import moduloFaturamento.model.IdFaturamentoParcelamentoParcela;
import moduloFaturamento.util.ConverterData;

public abstract class FaturamentoParcelamentoParcelaRegra {

    protected List<FaturamentoParcelamentoParcela> cadastrarParcelaDoParcelamento(Integer cdParcelamento, BigDecimal valorDaEntrada, LocalDate dataPagamentoDaEntrada,
        List<ParcelamentoFaturaListaValoresDaParcelaDTO> listaParcelas){

        List<FaturamentoParcelamentoParcela> listaDeParcelasDoParcelamento = new ArrayList<>();

        if (valorDaEntrada.compareTo(BigDecimal.ZERO) != 0) {

            IdFaturamentoParcelamentoParcela idFaturamentoParcelamentoParcela = new IdFaturamentoParcelamentoParcela();
            
            idFaturamentoParcelamentoParcela.setNumeroParcela(0);     
            idFaturamentoParcelamentoParcela.setCdParcelamento(cdParcelamento);

            FaturamentoParcelamentoParcela faturamentoParcelamentoParcela = instanciarEntidadeFaturamentoParcelamentoParcela(valorDaEntrada,
                dataPagamentoDaEntrada, idFaturamentoParcelamentoParcela);
            
            listaDeParcelasDoParcelamento.add(faturamentoParcelamentoParcela);
        }

        if (!listaParcelas.isEmpty()) {
            
            for (ParcelamentoFaturaListaValoresDaParcelaDTO parcelamentoFaturaListaValoresDaParcelaDTO : listaParcelas) {
            
                IdFaturamentoParcelamentoParcela idFaturamentoParcelamentoParcela = new IdFaturamentoParcelamentoParcela();

                idFaturamentoParcelamentoParcela.setCdParcelamento(cdParcelamento);
                idFaturamentoParcelamentoParcela.setNumeroParcela(Integer.valueOf(parcelamentoFaturaListaValoresDaParcelaDTO.getNumeroDeParcelas()));
                
                FaturamentoParcelamentoParcela faturamentoParcelamentoParcela = instanciarEntidadeFaturamentoParcelamentoParcela(
                    parcelamentoFaturaListaValoresDaParcelaDTO.getValorParcelas(),  parcelamentoFaturaListaValoresDaParcelaDTO.getDataVencimento(), 
                    idFaturamentoParcelamentoParcela);
                
                listaDeParcelasDoParcelamento.add(faturamentoParcelamentoParcela);
            }
        }       
        return listaDeParcelasDoParcelamento;
    }

    private FaturamentoParcelamentoParcela instanciarEntidadeFaturamentoParcelamentoParcela(BigDecimal valorDaEntrada, LocalDate dataPagamentoDaEntrada,
        IdFaturamentoParcelamentoParcela idFaturamentoParcelamentoParcela) {
            
        FaturamentoParcelamentoParcela faturamentoParcelamentoParcela = new FaturamentoParcelamentoParcela();
        faturamentoParcelamentoParcela.setIdFaturamentoParcelamentoParcela(idFaturamentoParcelamentoParcela);

        faturamentoParcelamentoParcela.setValorParcela(valorDaEntrada);
        faturamentoParcelamentoParcela.setDataVencimento(ConverterData.localDateEmIntegerDataFormatoDB(dataPagamentoDaEntrada)); 
        faturamentoParcelamentoParcela.setDataPagamento(0);         
        faturamentoParcelamentoParcela.setHoraPagamento(0);
        faturamentoParcelamentoParcela.setReferenciaBaixaContabil(0);
        faturamentoParcelamentoParcela.setTipoBaixa((short)0);
        faturamentoParcelamentoParcela.setIdLote(0);
        faturamentoParcelamentoParcela.setValorJuros(BigDecimal.ZERO);
        faturamentoParcelamentoParcela.setValorMulta(BigDecimal.ZERO);
        faturamentoParcelamentoParcela.setCsBaixaEnvio(" ");
        return faturamentoParcelamentoParcela;
    }
    
}
