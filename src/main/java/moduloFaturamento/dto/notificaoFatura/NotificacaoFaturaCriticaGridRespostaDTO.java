package moduloFaturamento.dto.notificaoFatura;

import java.util.List;

public class NotificacaoFaturaCriticaGridRespostaDTO {

	private Short cdCidade;

	private String dcCidade;

	private Short ciclo;

	private Integer refCronograma;

	private Integer matricula;

	private String matriculaDv;

	private Boolean cicloFechado;

	private Boolean existeOutraNotificacao;

	private List<String> criticas;

	public Short getCdCidade() {

		return cdCidade;
	}

	public void setCdCidade(Short cdCidade) {

		this.cdCidade = cdCidade;
	}

	public String getDcCidade() {

		return dcCidade;
	}

	public void setDcCidade(String dcCidade) {

		this.dcCidade = dcCidade;
	}

	public Short getCiclo() {

		return ciclo;
	}

	public void setCiclo(Short ciclo) {

		this.ciclo = ciclo;
	}

	public Integer getRefCronograma() {

		return refCronograma;
	}

	public void setRefCronograma(Integer refCronograma) {

		this.refCronograma = refCronograma;
	}

	public Integer getMatricula() {

		return matricula;
	}

	public void setMatricula(Integer matricula) {

		this.matricula = matricula;
	}

	public String getMatriculaDv() {

		return matriculaDv;
	}

	public void setMatriculaDv(String matriculaDv) {

		this.matriculaDv = matriculaDv;
	}

	public Boolean getCicloFechado() {

		return cicloFechado;
	}

	public void setCicloFechado(Boolean cicloFechado) {

		this.cicloFechado = cicloFechado;
	}

	public Boolean getExisteOutraNotificacao() {

		return existeOutraNotificacao;
	}

	public void setExisteOutraNotificacao(Boolean existeOutraNotificacao) {

		this.existeOutraNotificacao = existeOutraNotificacao;
	}

	public List<String> getCriticas() {

		return criticas;
	}

	public void setCriticas(List<String> criticas) {

		this.criticas = criticas;
	}

}
