package moduloFaturamento.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ARADC")
public class ArrecadacaoImovel {
	@Id
	@Column(name = "MATRICULA_IMOVEL")
	private Integer matriculaImovel;

	@Column(name = "DV")
	private Short dv;

	@Column(name = "AGENCIA_ARRECADADORA")
	private Short agenciaArrecadadora;

	@Column(name = "AGENTE_ARRECADADOR")
	private Short agenteArrecadador;

	@Column(name = "CD_CLIENTE")
	private Integer codCliente;

	@Column(name = "CONTA_CORRENTE")
	private String contaCorrente;

	@Column(name = "DIA_VENCIMENTO")
	private Short diaVencimento;

	@Column(name = "DT_INCLUSAO")
	private Integer dataInclusao;

	@Column(name = "MAINT")
	private char maint;

	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public Short getDl() {
		return dv;
	}

	public void setDv(Short dv) {
		this.dv = dv;
	}

	public Short getAgenciaArrecadadora() {
		return agenciaArrecadadora;
	}

	public void setAgenciaArrecadadora(Short agenciaArrecadadora) {
		this.agenciaArrecadadora = agenciaArrecadadora;
	}

	public Short getAgenteArrecadador() {
		return agenteArrecadador;
	}

	public void setAgenteArrecadador(Short agenteArrecadador) {
		this.agenteArrecadador = agenteArrecadador;
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public String getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(String contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public Short getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Short diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public Integer getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Integer dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public char getMaint() {
		return maint;
	}

	public void setMaint(char maint) {
		this.maint = maint;
	}

}
