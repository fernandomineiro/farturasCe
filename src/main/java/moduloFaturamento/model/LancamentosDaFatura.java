package moduloFaturamento.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FTTLA")
public class LancamentosDaFatura {

	@Id
	@Column(name = "ROWID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CD_SERVICO")
	private Short cdServico;

	@Column(name = "D_C")
	private String debitoOuCredito;

	@Column(name = "DT_LANCAMENTO")
	private Integer dataLancamento;

	@Column(name = "MATRICULA_IMOVEL")
	private Integer matriculaImovel;

	@Column(name = "ORIGEM_FATURA")
	private Short origemFatua;

	@Column(name = "REF_FATURA")
	private Integer referenciaFatura;

	@Column(name = "VR_SERVICO")
	private BigDecimal valorServico;

	@Column(name = "ID_FATURA")
	private Long idFatura;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getCdServico() {
		return cdServico;
	}

	public void setCdServico(Short cdServico) {
		this.cdServico = cdServico;
	}

	public String getDebitoOuCredito() {
		return debitoOuCredito;
	}

	public void setDebitoOuCredito(String debitoOuCredito) {
		this.debitoOuCredito = debitoOuCredito;
	}

	public Integer getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Integer dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public Short getOrigemFatura() {
		return origemFatua;
	}

	public void setOrigemFatura(Short origemFatua) {
		this.origemFatua = origemFatua;
	}

	public Integer getReferenciaFatura() {
		return referenciaFatura;
	}

	public void setReferenciaFatura(Integer referenciaFatura) {
		this.referenciaFatura = referenciaFatura;
	}

	public BigDecimal getValorServico() {
		return valorServico;
	}

	public void setValorServico(BigDecimal valorServico) {
		this.valorServico = valorServico;
	}

	public Long getIdFatura() {
		return idFatura;
	}

	public void setIdFatura(Long idFatura) {
		this.idFatura = idFatura;
	}

	
}
