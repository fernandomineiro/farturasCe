package moduloFaturamento.dto.faturaAvulsaImovel;

import javax.validation.constraints.NotNull;


public class ParametroDropdownServicosFaturaAvulsaDTO {

    private String tipoServico;
    @NotNull
    private Integer matriculaSemDV;
	public String getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	public Integer getMatriculaSemDV() {
		return matriculaSemDV;
	}
	public void setMatriculaSemDV(Integer matriculaSemDV) {
		this.matriculaSemDV = matriculaSemDV;
	}
}
