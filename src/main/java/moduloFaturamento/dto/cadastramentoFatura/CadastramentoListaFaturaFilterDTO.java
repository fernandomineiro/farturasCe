package moduloFaturamento.dto.cadastramentoFatura;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class CadastramentoListaFaturaFilterDTO {

	@NotNull
	private Integer matricula;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate refFaturamento;

	private Short codigoSituacaoBaixa;

	private Integer codigoClienteTitular;

	public Integer getMatricula() {

		return matricula;
	}

	public void setMatricula(Integer matricula) {

		this.matricula = matricula;
	}

	public LocalDate getRefFaturamento() {

		return refFaturamento;
	}

	public void setRefFaturamento(LocalDate refFaturamento) {

		this.refFaturamento = refFaturamento;
	}

	public Short getCodigoSituacaoBaixa() {

		return codigoSituacaoBaixa;
	}

	public void setCodigoSituacaoBaixa(Short codigoSituacaoBaixa) {

		this.codigoSituacaoBaixa = codigoSituacaoBaixa;
	}

	public Integer getCodigoClienteTitular() {

		return codigoClienteTitular;
	}

	public void setCodigoClienteTitular(Integer codigoClienteTitular) {

		this.codigoClienteTitular = codigoClienteTitular;
	}

}
