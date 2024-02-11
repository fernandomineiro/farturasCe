package moduloFaturamento.dto.simulacaoFatura;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class SimulacaoFaturaMatriculaReferenciaFilterDTO {

	@NotNull
	private Integer matricula;

	@NotNull
	private Short dv;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate referencia;

	public Integer getMatricula() {

		return matricula;
	}

	public void setMatricula(Integer matricula) {

		this.matricula = matricula;
	}

	public Short getDv() {

		return dv;
	}

	public void setDv(Short dv) {

		this.dv = dv;
	}

	public LocalDate getReferencia() {

		return referencia;
	}

	public void setReferencia(LocalDate referencia) {

		this.referencia = referencia;
	}

}
