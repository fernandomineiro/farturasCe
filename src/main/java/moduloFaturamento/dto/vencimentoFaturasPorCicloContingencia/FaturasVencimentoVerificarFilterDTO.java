package moduloFaturamento.dto.vencimentoFaturasPorCicloContingencia;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class FaturasVencimentoVerificarFilterDTO {

	@NotNull
	private Integer referencia;
	@NotNull
	private Short cdCidade;
	@NotNull
	private Short ciclo;
	@NotBlank
	private String novoVencimento;
	@NotNull
	@NotEmpty
	private List<Short> fases;

	public Integer getReferencia() {

		return referencia;
	}

	public void setReferencia(Integer referencia) {

		this.referencia = referencia;
	}

	public Short getCdCidade() {

		return cdCidade;
	}

	public void setCdCidade(Short cdCidade) {

		this.cdCidade = cdCidade;
	}

	public Short getCiclo() {

		return ciclo;
	}

	public void setCiclo(Short ciclo) {

		this.ciclo = ciclo;
	}

	public String getNovoVencimento() {

		return novoVencimento;
	}

	public void setNovoVencimento(String novoVencimento) {

		this.novoVencimento = novoVencimento;
	}

	public List<Short> getFases() {

		return fases;
	}

	public void setFases(List<Short> fases) {

		this.fases = fases;
	}

}
