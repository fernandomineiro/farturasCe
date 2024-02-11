package moduloFaturamento.dto.faturaAvulsa.projection;

import java.math.BigDecimal;

public interface FaturaAvulsaPesquisaProjectionDTO {

    Integer getCdCliente();
    Integer getRefFatura();
	Short getOrigemFatura();

	Integer getMatriculaImovel();

	BigDecimal getValorFatura();

	Integer getDataVencimento();

	String getNomeBaixa();
	
	Integer getReferenciaBaixa();
	Short getDiaDaBaixa();

	Integer getRefAtendimento();
	Integer getCdAtendimento();
	Short getSeqSS();

	Long getId();
}
