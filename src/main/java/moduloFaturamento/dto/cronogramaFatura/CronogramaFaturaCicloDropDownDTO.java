package moduloFaturamento.dto.cronogramaFatura;

public class CronogramaFaturaCicloDropDownDTO {

	private short ciclo;
	private boolean cicloFechado;

	public Short getCiclo() {

		return ciclo;
	}

	public void setCiclo(Short ciclo) {

		this.ciclo = ciclo;
	}

	public Boolean getCicloFechado() {

		return cicloFechado;
	}

	public void setCicloFechado(Boolean cicloFechado) {

		this.cicloFechado = cicloFechado;
	}

}
