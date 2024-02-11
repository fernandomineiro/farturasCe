package moduloFaturamento.model.common;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CDACI")
public class Localidade {

	@Id
	@Column(name = "CD_CIDADE")
	private Short cdCidade;

	@Column(name = "DC_CIDADE", length = 25)
	private String dcCidade;

	@Column(name = "CD_MUNICIPIO", nullable = false)
	private Short cdMunicipio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_REGIONAL")
	private Regional regional;

	@Column(name = "DATA_HORA_EXCLUSAO")
	private Date dataHoraExclusao;

	@Column(name = "CD_TARIFA")
	private Short cdTarifa;

	public Localidade() {

	}

	public Localidade(Short cdCidade) {
		this.cdCidade = cdCidade;
	}

	public Localidade(Short cdCidade, String dcCidade) {
		super();
		this.cdCidade = cdCidade;
		this.dcCidade = dcCidade;
	}

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

	public Date getDataHoraExclusao() {
		return dataHoraExclusao;
	}

	public void setDataHoraExclusao(Date dataHoraExclusao) {
		this.dataHoraExclusao = dataHoraExclusao;
	}

	public Short getCdMunicipio() {
		return cdMunicipio;
	}

	public void setCdMunicipio(Short cdMunicipio) {
		this.cdMunicipio = cdMunicipio;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	@Override
	public String toString() {
		return this.cdCidade.toString();
	}

	public Short getCdTarifa() {
		return cdTarifa;
	}

	public void setCdTarifa(Short cdTarifa) {
		this.cdTarifa = cdTarifa;
	}
}
