package moduloFaturamento.model.common;

import moduloFaturamento.util.customAnnotation.JsonCesanNotSerializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CDALO")
public class Logradouro {

	@EmbeddedId
	@JsonCesanNotSerializable
	private IdLogradouro idLogradouro;

	@Column(name = "ID")
	@JsonCesanNotSerializable
	private Integer id;

	@Column(name = "CD_ATENDIMENTO")
	private Integer codAtendimento;

	@Column(name = "DC_LOGRADOURO")
	private String nomeLogradouro;

	@Column(name = "SIGLA_LOGRADOURO")
	private String siglaLogradouro;


	public IdLogradouro getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(IdLogradouro idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodAtendimento() {
		return codAtendimento;
	}

	public void setCodAtendimento(Integer codAtendimento) {
		this.codAtendimento = codAtendimento;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public String getSiglaLogradouro() {
		return siglaLogradouro;
	}

	public void setSiglaLogradouro(String siglaLogradouro) {
		this.siglaLogradouro = siglaLogradouro;
	}
}
