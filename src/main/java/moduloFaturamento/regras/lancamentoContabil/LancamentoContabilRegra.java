package moduloFaturamento.regras.lancamentoContabil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.dto.credito.projection.CreditoValoresParaLancamentoContabilProjectionDTO;
import moduloFaturamento.model.LancamentoContabil;
import moduloFaturamento.repository.CreditoRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.util.ConverterData;

public abstract class LancamentoContabilRegra {

    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private CreditoRepository repository;

    protected List<LancamentoContabil> lancamentoContabil(Short evento, Integer matricula, BigDecimal valorCredito, Integer cdCliente, Short cdCredito, Short cdServico, 
        String debitoOuCredito, Integer refFatura, Short origemFatura, Integer cdParcelamento,  Short numeroParcela, Integer cdCidade, Integer nuLoteArrec  ) {

        List<LancamentoContabil> listaDeLancamentosContabeis = new ArrayList<>();

        cdCidade = Optional.ofNullable(cdCidade).orElse(0) != 0 ? cdCidade : imovelRepository.buscarCdCidadeDoImovel(matricula);
        cdCliente = Optional.ofNullable(cdCliente).orElse(0) != 0 ? cdCliente : imovelRepository.buscarCdClienteDoImovel(matricula);
        matricula = Optional.ofNullable(matricula).orElse(0) != 0 ? matricula : 0 ;

        Integer dataDeHojeEmIntegerParaBD = ConverterData.localDateEmIntegerDataFormatoDB(LocalDate.now());
        Short cdFilialSap = imovelRepository.buscarCdFilialSap(cdCidade).shortValue();

        List<CreditoValoresParaLancamentoContabilProjectionDTO> buscarNumeroContaEContabilSapParaLancamentoContabil = repository
                .buscarNumeroContaEContabilSapParaLancamentoContabil(cdServico, evento, debitoOuCredito);

        for (CreditoValoresParaLancamentoContabilProjectionDTO creditoValoresParaLancamentoContabilProjectionDTO : buscarNumeroContaEContabilSapParaLancamentoContabil) {

            LancamentoContabil contabil = new LancamentoContabil();

            contabil.setDtContabil(dataDeHojeEmIntegerParaBD);
            contabil.setDtLancamento(dataDeHojeEmIntegerParaBD);
            contabil.setCdEvento(evento);
            contabil.setCdfilialSap(cdFilialSap);            
            contabil.setNumeroContaContabilSAP(creditoValoresParaLancamentoContabilProjectionDTO.getNrConta());
            contabil.setDebitoCreditoContabilSAP(creditoValoresParaLancamentoContabilProjectionDTO.getCtbSap());

            contabil.setCdCentroCustoSap(verificarSeTabelaCTTCFPossuei_S_ParaFornecerCentroCusto(
                cdCidade, cdServico, evento, creditoValoresParaLancamentoContabilProjectionDTO.getNrConta() ));

            contabil.setValorLancamento(valorCredito);
            contabil.setMatriculaImovel(matricula);

            contabil.setCdCliente(cdCliente);

            contabil.setRefFatura(refFatura);            
            contabil.setOrigemFatura(origemFatura);

            contabil.setCdParcelamento(cdParcelamento);
            contabil.setNumeroParcela(numeroParcela);

            contabil.setCdCredito(cdCredito);
            contabil.setCdServico(cdServico);
            
            contabil.setDebitoCreditoServico(debitoOuCredito);
            contabil.setNumeroLoteArrecadacao(nuLoteArrec);

            listaDeLancamentosContabeis.add(contabil);
        }
        return listaDeLancamentosContabeis;
    }

    protected List<LancamentoContabil> lancamentoContabilManual(Short evento, Integer matricula, BigDecimal valorASerLancado, Integer cdCliente, Short cdCredito, Short cdServico, 
        String debitoOuCredito, Integer refFatura, Short origemFatura, Integer cdParcelamento,  Short numeroParcela, Integer cdCidade, Integer nuLoteArrec,  
        Integer numeroDaContaContabilDebito, Integer numeroDaContaContabilCredito) {

        List<LancamentoContabil> listaDeLancamentosContabeis = new ArrayList<>();

        cdCidade = Optional.ofNullable(cdCidade).orElse(0) != 0 ? cdCidade : imovelRepository.buscarCdCidadeDoImovel(matricula);
        cdCliente = Optional.ofNullable(cdCliente).orElse(0) != 0 ? cdCliente : imovelRepository.buscarCdClienteDoImovel(matricula);
        matricula = Optional.ofNullable(matricula).orElse(0) != 0 ? matricula : 0 ;

        Integer dataDeHojeEmIntegerParaBD = ConverterData.localDateEmIntegerDataFormatoDB(LocalDate.now());
        Short cdFilialSap = imovelRepository.buscarCdFilialSap(cdCidade).shortValue();

        LancamentoContabil contabil = instanciarLancamentoContabilManual(dataDeHojeEmIntegerParaBD, evento, cdFilialSap, numeroDaContaContabilDebito, "D", cdCidade, cdServico, 
            valorASerLancado, matricula, cdCliente, refFatura, origemFatura, cdParcelamento, numeroParcela, cdCredito, debitoOuCredito, nuLoteArrec);

        LancamentoContabil contabil2 = instanciarLancamentoContabilManual(dataDeHojeEmIntegerParaBD, evento, cdFilialSap, numeroDaContaContabilCredito, "C", cdCidade, cdServico, 
            valorASerLancado, matricula, cdCliente, refFatura, origemFatura, cdParcelamento, numeroParcela, cdCredito, debitoOuCredito, nuLoteArrec);

        if(contabil != null) listaDeLancamentosContabeis.add(contabil);
        if(contabil2 != null) listaDeLancamentosContabeis.add(contabil2);
        
        return listaDeLancamentosContabeis;
    }


    private LancamentoContabil instanciarLancamentoContabilManual(Integer dataDeHojeEmIntegerParaBD, short evento, Short cdFilialSap, Integer numeroDaConta, 
        String creditoOuDebitoRelacionadoAoNumeroDaConta,  Integer cdCidade, short cdServico, BigDecimal valorASerLancado, Integer matricula, Integer cdCliente,
        Integer refFatura, short origemFatura, Integer cdParcelamento, short numeroParcela, short cdCredito, String debitoOuCredito, Integer nuLoteArrec) {

        if (numeroDaConta != null) {

            LancamentoContabil contabil = new LancamentoContabil();

            contabil.setDtContabil(dataDeHojeEmIntegerParaBD);
            contabil.setDtLancamento(dataDeHojeEmIntegerParaBD);
            contabil.setCdEvento(evento);
            contabil.setCdfilialSap(cdFilialSap);            
            contabil.setNumeroContaContabilSAP(numeroDaConta);
            contabil.setDebitoCreditoContabilSAP(creditoOuDebitoRelacionadoAoNumeroDaConta);
    
            contabil.setCdCentroCustoSap(verificarSeTabelaCTTCFPossuei_S_ParaFornecerCentroCusto(cdCidade, cdServico, evento, numeroDaConta));
    
            contabil.setValorLancamento(valorASerLancado);
            contabil.setMatriculaImovel(matricula);
    
            contabil.setCdCliente(cdCliente);
    
            contabil.setRefFatura(refFatura);            
            contabil.setOrigemFatura(origemFatura);
    
            contabil.setCdParcelamento(cdParcelamento);
            contabil.setNumeroParcela(numeroParcela);
    
            contabil.setCdCredito(cdCredito);
            contabil.setCdServico(cdServico);
                
            contabil.setDebitoCreditoServico(debitoOuCredito);
            contabil.setNumeroLoteArrecadacao(nuLoteArrec);
    
            return contabil;
        }
        return null;
    }

    private String verificarSeTabelaCTTCFPossuei_S_ParaFornecerCentroCusto(Integer cdCidade, Short cdServico, Short evento, Integer nrConta) {

        String buscarSeExisteUmRegristro = repository.verificarSeTabelaCTTCFPossuei_S_ParaFornecerCentroCusto(cdCidade, cdServico, evento, nrConta);

        return Objects.requireNonNullElse(buscarSeExisteUmRegristro, "");
    }

}
