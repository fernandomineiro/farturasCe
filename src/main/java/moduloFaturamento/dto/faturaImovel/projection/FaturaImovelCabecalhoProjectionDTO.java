package moduloFaturamento.dto.faturaImovel.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface FaturaImovelCabecalhoProjectionDTO {

	Long getId();

	Integer getMatricula();

	Integer getReferencia();

	Short getDv();

	Short getCiclo();

	Short getOrigem();

	Short getVersao();

	Integer getCdCliente();

	Integer getdvCliente();
	
	String getNomeCliente();

	Integer getDataLeitura();

	Integer getLeituraAnterior();

	Integer getLeituraAtual();

	Integer getMedido();

	Short getCdOcorrencia1();

	Short getCdOcorrencia2();

	Short getCdOcorrencia3();

	Short getTipoConsumoFaturamento();

	BigDecimal getConsumoFaturadoAgua();

	BigDecimal getConsumoFaturadoEsgoto();

	BigDecimal getConsumoDisponibilidadeFaturado();

	Short getDescontoEsgoto();
	
	Short getSubvencao();

	Short getDiasConsumo();

	Short getDiasVenda();

	String getTipoMedicaoEsgoto();

	String getSituacaoFatura();

	Integer getDataVencimento();

	Integer getDataTarifa();

	String getGrupoConsumo();

	String getTratamentoEsgoto();

	String getSituacaoAgua();

	String getSituacaoEsgoto();

	Short getNumeroEconomiasFaturamento();

	Short getNumeroEconomiasCadastro();

	BigDecimal getValorTotal();

	BigDecimal getValorTotalLigacao();

	BigDecimal getValorTotalEconomias();

	String getLocalidade();

	String getBairro();

	String getSigla();

	String getLogradouro();

	Integer getNumero();

	String getComplemento();
	
	Integer getDataBaixa();
	
	LocalDateTime getDataArrecadacao();
}
