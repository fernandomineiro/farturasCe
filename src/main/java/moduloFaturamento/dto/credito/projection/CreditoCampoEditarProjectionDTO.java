package moduloFaturamento.dto.credito.projection;

import java.math.BigDecimal;

public interface CreditoCampoEditarProjectionDTO {
    
    Integer getMatricula();
    Short getDv();
    short getCdCredito();
    Integer getCdServico();
    String getDescricaoServico();
    BigDecimal getValorCredito();
    BigDecimal getValorSaldo();
    Integer getEncerramento();

    Integer getRefAtendimento();
    Integer getCodAtendimento();
	Integer getSeqSS();

    String getUsuario();

    Long getId();
    Integer getCdParcelamento();
    Integer getNumeroParcela();
    Short getOrigemFatura();
    Integer getReferenciaFatura();    

    String getJustificativa();
    
}
