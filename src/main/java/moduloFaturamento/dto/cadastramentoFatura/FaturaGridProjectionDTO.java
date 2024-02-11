package moduloFaturamento.dto.cadastramentoFatura;

import java.math.BigDecimal;

public interface FaturaGridProjectionDTO {

	Integer getId();
	
	Integer getRefFatura();

	Integer getOrigemFatura();

	Integer getVersaoFatura();

	BigDecimal getValorFatura();

	Integer getDataVencimento();

	Integer getDataPagamento();

	String getSituacaoBaixa();

	Integer getCodigoCliente();

	String getNomeCliente();

	Integer getRefBaixa();

	Integer getDiaBaixa();

	Integer getDvCliente();
}
