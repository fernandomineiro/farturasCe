package moduloFaturamento.dto.credito.projection;

import java.math.BigDecimal;

public interface CreditoParaPesqBancoProjectionDTO {

    short getCdCredito();
    String getCdServico();
    String getNomeServico();
    BigDecimal getValorCredito();
    BigDecimal getSaldo();
    Integer getEncerramento();
    String getUsuario();

    Integer getRefAtendimnento();
    Integer getCdAtendimento();
    Short getSeqSS();

    Long getId();
    Integer getCdParcelamento();
    Integer getNumeroParcela();
    Short getOrigemFatura();
    Integer getRefFatura();    
}
