package moduloFaturamento.regras.credito;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.model.Credito;
import moduloFaturamento.model.FaturamentoParcelamentoParcela;
import moduloFaturamento.model.IdCredito;
import moduloFaturamento.model.IdFaturamentoParcelamentoParcela;
import moduloFaturamento.repository.CreditoRepository;
import moduloFaturamento.repository.FaturamentoParcelamentoParcelaRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.util.ConverterData;

public abstract class CreditoRegras {

    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private CreditoRepository repository;
    @Autowired
    private FaturamentoParcelamentoParcelaRepository faturamentoParcelamentoParcelaRepository;

    protected Credito construirCredito (Integer matricula, String loginUsuario, BigDecimal valorCredito, Short cdServico, Short dv,
                        Integer refAtedimento, Integer cdAtendimento, Short seqSS,
                        Integer cdParcelamento,  Integer numeroParcela, 
                        Short origemFatura, LocalDate referenciaFatura,
                        String dcAnotacao){

        Credito credito = new Credito();

        Integer dataDeHojeEmInteiro = ConverterData.localDateEmIntegerDataFormatoDB(LocalDate.now());

        Short numeroProximaSeqCredido = (short) (repository.buscarProximaSequenciaCredito(matricula));
        IdCredito idCredito = new IdCredito(matricula, numeroProximaSeqCredido);
        credito.setIdCredito(idCredito);

        credito.setDv(dv);
        credito.setData(dataDeHojeEmInteiro);
        credito.setCdCliente(imovelRepository.buscarCdClienteDoImovel(matricula));
        credito.setUsuario(loginUsuario);
        credito.setValorCredito(valorCredito);
        credito.setValorSaldo(valorCredito);
        credito.setCdServico(cdServico);
        credito.setMaint('A');
        credito.setRefEncerramento(0);
        credito.setAgenteArrecadador((short)0);
        credito.setAgenciaArrecadadora((short)0);
        credito.setMaqReg((short)0);
        credito.setNumeroLoteArrecadador(0);
        credito.setTipoAgente((short)0);
        credito.setCdCobranca((short)0);
        credito.setDcAnotacao(dcAnotacao);

        verificarSeCamposDaSSEstaoNull(credito, refAtedimento, cdAtendimento, seqSS);
        verificarSeCamposFaturaEstaNull(credito, origemFatura, referenciaFatura);
        verificarSeCamposParcelamentoEstanull(credito, cdParcelamento, numeroParcela);

        return credito;
    }

    private void verificarSeCamposParcelamentoEstanull(Credito credito, Integer cdParcelamento, Integer numeroParcela) {

        if (cdParcelamento != null) {

            IdFaturamentoParcelamentoParcela id = new IdFaturamentoParcelamentoParcela(cdParcelamento, numeroParcela);
            FaturamentoParcelamentoParcela faturamentoParcelamentoParcela = faturamentoParcelamentoParcelaRepository.findById(id).get();
            credito.setFaturamentoParcelamentoParcela(faturamentoParcelamentoParcela);
        } 
    }

    private void verificarSeCamposFaturaEstaNull(Credito credito, Short origemFatura, LocalDate referenciaFatura) {

        if (referenciaFatura != null) {
            Integer dataConvertidaEmIntegerBD = ConverterData.localDateEmIntegerReferenciaFormatoDB(referenciaFatura);

            credito.setOrigemFatura(origemFatura);
            credito.setRefFatura(dataConvertidaEmIntegerBD); 
        } 
    }

    private void verificarSeCamposDaSSEstaoNull(Credito credito, Integer refAtedimento, Integer cdAtendimento, Short seqSS) {

        if (refAtedimento == null || refAtedimento == 0) {
            credito.setRefAtendimento(0);
            credito.setCdAtendimento(0);
            credito.setSeqSS((short) 0);
        } else {
            credito.setRefAtendimento(refAtedimento);
            credito.setCdAtendimento(cdAtendimento);
            credito.setSeqSS(seqSS);
        }
    }
    
}
