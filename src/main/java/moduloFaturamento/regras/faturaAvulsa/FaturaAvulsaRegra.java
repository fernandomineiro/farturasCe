package moduloFaturamento.regras.faturaAvulsa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaListaLancamentoDTO;
import moduloFaturamento.model.FaturaAvulsa;
import moduloFaturamento.model.IdFaturaAvulsa;
import moduloFaturamento.model.IdLancamentoFaturaAvulsa;
import moduloFaturamento.model.LancamentosFaturaAvulsa;
import moduloFaturamento.repository.FaturaAvulsaRepository;
import moduloFaturamento.util.ConverterData;

public abstract class FaturaAvulsaRegra {

    @Autowired
    private FaturaAvulsaRepository repository;

    protected FaturaAvulsa cadastrarUmaFaturaAvulsa(Integer cdCliente, Integer refFatura, Short origemFatura,
            Integer refAtedimento, Integer cdAtendimento, Short seqSS, Short cdCidade, Integer dataVencimento,
            Short dvCliente, String mensagem01, String mensagem02, BigDecimal valorFatura, Short grupoConsumo,
            Integer matriculaImovel) {

        FaturaAvulsa faturaAvulsa = new FaturaAvulsa();
        IdFaturaAvulsa idFaturaAvulsa = new IdFaturaAvulsa();

        faturaAvulsa.setIdFaturaAvulsa(idFaturaAvulsa);

        idFaturaAvulsa.setCdCliente(cdCliente);
        idFaturaAvulsa.setRefFatura(refFatura);
        idFaturaAvulsa.setOrigemFatura(origemFatura);

        faturaAvulsa.setCdCidade(cdCidade);
        faturaAvulsa.setDocBaixa((short) 0);
        faturaAvulsa.setDataVencimento(dataVencimento);
        faturaAvulsa.setDvCliente(dvCliente);
        faturaAvulsa.setMatriculaImovel(matriculaImovel);
        faturaAvulsa.setGrupoConsumo(grupoConsumo);
        faturaAvulsa.setMensagem01(mensagem01);
        faturaAvulsa.setMensagem02(mensagem02);
        faturaAvulsa.setRefBaixa("");
        faturaAvulsa.setRefBaixaContabil(0);
        faturaAvulsa.setRefContabil(refFatura);
        faturaAvulsa.setTipoBaixa((short) 0);
        faturaAvulsa.setValorFatura(valorFatura);

        faturaAvulsa.setDiaBaixa((short) 0);

        verificarSeCamposDaSSEstaoNull(faturaAvulsa, refAtedimento, cdAtendimento, seqSS);

        return faturaAvulsa;
    }

    protected List<LancamentosFaturaAvulsa> cadastrarLancamentofaturaAvulsa(Integer cdCliente, Integer refFatura,
            Short origemFatura, List<FaturaAvulsaListaLancamentoDTO> listaDTO) {

        List<LancamentosFaturaAvulsa> listaLancamento = new ArrayList<>();

        for (FaturaAvulsaListaLancamentoDTO faturaAvulsaListaLancamentoDTO : listaDTO) {

            IdLancamentoFaturaAvulsa idLancamento = new IdLancamentoFaturaAvulsa(cdCliente, refFatura, origemFatura,
                    faturaAvulsaListaLancamentoDTO.getCdServico());
            LancamentosFaturaAvulsa avulsa = new LancamentosFaturaAvulsa(idLancamento,
                    faturaAvulsaListaLancamentoDTO.getDcServico(), faturaAvulsaListaLancamentoDTO.getValorServico());

            listaLancamento.add(avulsa);
        }
        return listaLancamento;
    }

    protected FaturaAvulsa encerrarFaturaAvulsa(Long id) {

        FaturaAvulsa faturaAvulsa = repository.findById(id).get();

        LocalDate diaDeHoje = LocalDate.now();

        Integer diaDeHojeEmReferenciaBD = ConverterData.localDateEmIntegerReferenciaFormatoDB(diaDeHoje);
        faturaAvulsa.setRefBaixa(diaDeHojeEmReferenciaBD.toString());

        faturaAvulsa.setRefBaixaContabil(diaDeHojeEmReferenciaBD);

        // 2 na tabela CAD_TP_BAIXA é cancelado
        faturaAvulsa.setTipoBaixa((short) 2);

        faturaAvulsa.setDiaBaixa((short) diaDeHoje.getDayOfMonth() );

        return faturaAvulsa;
    }

    /**
     * Métodos privados
     */
    private void verificarSeCamposDaSSEstaoNull(FaturaAvulsa faturaAvulsa, Integer refAtedimento, Integer cdAtendimento,
            Short seqSS) {

        if (refAtedimento == null || refAtedimento == 0) {
            faturaAvulsa.setRefAtendimento(0);
            faturaAvulsa.setCdAtendimento(0);
            faturaAvulsa.setSeqSS((short) 0);
        } else {
            faturaAvulsa.setRefAtendimento(refAtedimento);
            faturaAvulsa.setCdAtendimento(cdAtendimento);
            faturaAvulsa.setSeqSS(seqSS);
        }
    }

}
