package moduloFaturamento.dto.faturaAvulsaImovel.projection;

public interface DadosImovelClienteProjectionDTO {

	Short getOrigem();

	Integer getVersao();

	Short getCiclo();

	Integer getCdCliente();

	Integer getDvCliente();

	String getNomeCliente();

	String getDcCidade();

	String getDcBairro();

	String getDcLogradouro();

	Integer getNumero();

	String getComplemento();

	String getTratamentoEsgoto();
}
