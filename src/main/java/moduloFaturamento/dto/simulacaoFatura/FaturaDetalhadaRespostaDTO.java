package moduloFaturamento.dto.simulacaoFatura;

public class FaturaDetalhadaRespostaDTO {

	private FaturaLeituraDadosDTO faturaLeituraDadosDTO;

	private FaturaDetalheDTO faturaFaixasDetalheDTO;

	public FaturaLeituraDadosDTO getFaturaLeituraDadosDTO() {

		return faturaLeituraDadosDTO;
	}

	public void setFaturaLeituraDadosDTO(FaturaLeituraDadosDTO faturaLeituraDadosDTO) {

		this.faturaLeituraDadosDTO = faturaLeituraDadosDTO;
	}

	public FaturaDetalheDTO getFaturaFaixasDetalheDTO() {

		return faturaFaixasDetalheDTO;
	}

	public void setFaturaFaixasDetalheDTO(FaturaDetalheDTO faturaFaixasDetalheDTO) {

		this.faturaFaixasDetalheDTO = faturaFaixasDetalheDTO;
	}

}