package moduloFaturamento.dto.faturaAvulsa.projection;

import java.math.BigDecimal;

public interface FaturaAvulsaExibirDadosProjectionDTO {

    Integer getCdCliente();
    Short getDvCliente();
    String getNomeCliente();
    Integer getMatriculaImovel();
    String getNomeLocalidade();
    Integer getRefFatura();
    BigDecimal getValorFatura();
    Short getOrigemFatura();
    Integer getDataVencimento();
    String getTipoBaixa();
    String getDataBaixaCompleto();
    String getMensagem1();
    String getMensagem2();

    Integer getRefAtendimento();
    Integer getCodAtendimento();
    Short getSeqSS();
}
