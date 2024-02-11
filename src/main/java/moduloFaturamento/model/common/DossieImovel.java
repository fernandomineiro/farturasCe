package moduloFaturamento.model.common;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ATTFL")
public class DossieImovel {

	@EmbeddedId
	private IdDossieImovel idDossieImovel;
	@Column(name = "CD_ANOTACAO")
	private Short cdAnotacao;
	@Column(name = "CD_CLIENTE")
	private Integer cdCliente;
	@Column(name = "DC_ANOTACAO")
	private String dcAnotacao;
	@Column(name = "DC_OBJETO")
	private String dcObjeto;
	@Column(name = "DV")
	private Short dv;
	@Column(name = "DV_C")
	private Short dvC;
	@Column(name = "ID_USUARIO")
	private String idUsuario;
	@Column(name = "MAINT")
	private char maint;
	@Column(name = "HR_ANOTACAO")
	private Short hrAnotacao;
	@Column(name = "MIN_ANOTACAO")
	private Short minAnotacao;
	@Column(name = "DATA_HORA_INCLUSAO", columnDefinition = "DATETIME")
	private LocalDateTime dataHoraInclusao;

	public IdDossieImovel getIdDossieImovel() {

		return idDossieImovel;
	}

	public void setIdDossieImovel(IdDossieImovel idDossieImovel) {

		this.idDossieImovel = idDossieImovel;
	}

	public Short getCdAnotacao() {

		return cdAnotacao;
	}

	public void setCdAnotacao(Short cdAnotacao) {

		this.cdAnotacao = cdAnotacao;
	}

	public Integer getCdCliente() {

		return cdCliente;
	}

	public void setCdCliente(Integer cdCliente) {

		this.cdCliente = cdCliente;
	}

	public String getDcAnotacao() {

		return dcAnotacao;
	}

	public void setDcAnotacao(String dcAnotacao) {

		this.dcAnotacao = dcAnotacao;
	}

	public String getDcObjeto() {

		return dcObjeto;
	}

	public void setDcObjeto(String dcObjeto) {

		this.dcObjeto = dcObjeto;
	}

	public Short getDv() {

		return dv;
	}

	public void setDv(Short dv) {

		this.dv = dv;
	}

	public Short getDvC() {

		return dvC;
	}

	public void setDvC(Short dvC) {

		this.dvC = dvC;
	}

	public String getIdUsuario() {

		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {

		this.idUsuario = idUsuario;
	}

	public char getMaint() {

		return maint;
	}

	public void setMaint(char maint) {

		this.maint = maint;
	}

	public Short getHrAnotacao() {

		return hrAnotacao;
	}

	public void setHrAnotacao(Short hrAnotacao) {

		this.hrAnotacao = hrAnotacao;
	}

	public Short getMinAnotacao() {

		return minAnotacao;
	}

	public void setMinAnotacao(Short minAnotacao) {

		this.minAnotacao = minAnotacao;
	}

	public LocalDateTime getDataHoraInclusao() {

		return dataHoraInclusao;
	}

	public void setDataHoraInclusao(LocalDateTime dataHoraInclusao) {

		this.dataHoraInclusao = dataHoraInclusao;
	}
}
