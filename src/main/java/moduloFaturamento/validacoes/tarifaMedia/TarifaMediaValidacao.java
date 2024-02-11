package moduloFaturamento.validacoes.tarifaMedia;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.validacoes.common.CommonUtilValidacoes;

public abstract class TarifaMediaValidacao {

    @Autowired
    private CommonUtilValidacoes commonUtilValidacoes;

    protected void gerarExcessaoDataReferenciaEValido(LocalDate refFatura) {

        commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(refFatura);
	}
    
}
