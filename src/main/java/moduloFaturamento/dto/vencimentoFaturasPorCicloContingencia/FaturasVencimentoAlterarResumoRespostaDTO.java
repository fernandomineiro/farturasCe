package moduloFaturamento.dto.vencimentoFaturasPorCicloContingencia;

import java.time.LocalDate;

public class FaturasVencimentoAlterarResumoRespostaDTO {

	private LocalDate referencia;

	private Integer quantidadeDeFaturas;

	public LocalDate getReferencia() {

		return referencia;
	}

	public void setReferencia(LocalDate referencia) {

		this.referencia = referencia;
	}

	public Integer getQuantidadeDeFaturas() {

		return quantidadeDeFaturas;
	}

	public void setQuantidadeDeFaturas(Integer quantidadeDeFaturas) {

		this.quantidadeDeFaturas = quantidadeDeFaturas;
	}

}
