package moduloFaturamento.dto.cobrancaFatura.projection;

import moduloFaturamento.regras.solicitacaoServico.FormatarNumeroDaSSRegra;
import moduloFaturamento.util.ConverterData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class CobrancaFaturaRespostaGridProjectionDTO {

    private Long id;
    private Short cdCobranca;
    private String servico;
    private BigDecimal valorCobranca;
    private LocalDate dataInclusao;
    private String responsavelInclusao;
    private LocalDate referenciaFaturada;
    private String numeroItemAtendimento;
    private String numeroSolicitacaoServico;


    public CobrancaFaturaRespostaGridProjectionDTO() {
    }

    public CobrancaFaturaRespostaGridProjectionDTO(Long id, Short cdCobranca, Short cdServico, String nomeServico, BigDecimal valorCobranca,
                                                   Integer dataInclusao, String responsavelInclusao, Integer codAtendimentoSS, Integer refAtendimentoSS, Short seqSS,
                                                   Integer codAtendimentoItem, Integer refAtendimentoItem, Short seqItem, Integer referenciaFaturada) {
        this.id = id;
        this.cdCobranca = cdCobranca;
        this.servico = cdServico + " - " + nomeServico.trim();
        this.valorCobranca = valorCobranca.setScale(2,RoundingMode.HALF_EVEN);
        this.dataInclusao = ConverterData.integerEmLocalDateFormatoDB(dataInclusao);
        this.responsavelInclusao = responsavelInclusao;
        this.numeroSolicitacaoServico = FormatarNumeroDaSSRegra.formatar(refAtendimentoSS, codAtendimentoSS, seqSS);
        this.numeroItemAtendimento = FormatarNumeroDaSSRegra.formatar(refAtendimentoItem, codAtendimentoItem, seqItem);
        this.referenciaFaturada = ConverterData.integerEmLocalDateFormatoDB(referenciaFaturada);
    }


    public Short getCdCobranca() {
        return cdCobranca;
    }

    public String getServico() {
        return servico;
    }

    public BigDecimal getValorCobranca() {
        return valorCobranca;
    }

    public LocalDate getDataInclusao() {
        return dataInclusao;
    }

    public String getResponsavelInclusao() {
        return responsavelInclusao;
    }

    public String getNumeroItemAtendimento() {
        return numeroItemAtendimento;
    }

    public String getNumeroSolicitacaoServico() {
        return numeroSolicitacaoServico;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getReferenciaFaturada() {
        return referenciaFaturada;
    }
}
