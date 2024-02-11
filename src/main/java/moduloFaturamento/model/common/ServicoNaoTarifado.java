
package moduloFaturamento.model.common;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="SRV_SERVICO_NAO_TARIFARIO")
public class ServicoNaoTarifado {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="VALOR")
	private BigDecimal valor;
	private LocalDateTime dataHoraExclusao;

	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataHoraExclusao() {
		return dataHoraExclusao;
	}
	public void setDataHoraExclusao(LocalDateTime dataHoraExclusao) {
		this.dataHoraExclusao = dataHoraExclusao;
	}
}
