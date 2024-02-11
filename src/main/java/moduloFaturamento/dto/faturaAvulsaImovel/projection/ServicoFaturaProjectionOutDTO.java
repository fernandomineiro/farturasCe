package moduloFaturamento.dto.faturaAvulsaImovel.projection;

public class ServicoFaturaProjectionOutDTO {

	private Integer cdServico;

	private String dcServico;



	public ServicoFaturaProjectionOutDTO(Integer cdServico, String getDcServico) {

		super();
		this.cdServico = cdServico;
		this.setDcServico(getDcServico);
	}

	public String getCdDcServico() {
		return cdServico + " - " + dcServico;
	}

	public String getDcServico() {
		return dcServico;
	}

	public void setDcServico(String dcServico) {
		this.dcServico = dcServico;
	}

	public Integer getCdServico() {

		return cdServico;
	}

	public void setCdServico(Integer cdServico) {

		this.cdServico = cdServico;
	}

	
}
