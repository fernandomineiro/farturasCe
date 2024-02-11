package moduloFaturamento.dto.notificaoFatura;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NotificacaoFaturaCriticaDTO {

	@NotNull
	private Integer refCronograma;

	@NotEmpty
	private List<String> matriculas;

	public Integer getRefCronograma() {

		return refCronograma;
	}

	public void setRefCronograma(Integer refCronograma) {

		this.refCronograma = refCronograma;
	}

	public List<String> getMatriculas() {

		return matriculas;
	}

	public void setMatriculas(List<String> matriculas) {

		this.matriculas = matriculas;
	}

}
