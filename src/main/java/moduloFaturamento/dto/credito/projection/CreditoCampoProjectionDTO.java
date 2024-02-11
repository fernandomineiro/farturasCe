package moduloFaturamento.dto.credito.projection;

import java.math.BigDecimal;

public interface CreditoCampoProjectionDTO {

    Integer getCdServico();
    Integer getMatricula();
    short getDv();
    Short getCdCredito();
    BigDecimal getValorCredito();
    BigDecimal getSaldo();
    Integer getEncerramento();
    String getDescServico();

    Integer getRefAtendimento();
    Integer getCdAtendimento();
    short getSeqSS();
    
    Integer getCdParcelamento();
    Integer getNumeroParcela();
    Short getOrigemFatura();
    Integer getRefFatura();

}
