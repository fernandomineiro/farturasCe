package moduloFaturamento.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ATTAE")
public class AmostrasExigidas {

	@EmbeddedId
	private IdAmostrasExigidas idAmostrasExigidas;

	@Column(name = "CLORO")
	private Short cloro;
	@Column(name = "COLI_TERMO")
	private Short coliTermo;
	@Column(name = "COLIFORMES_TOTAIS")
	private Short coliformesTotais;
	@Column(name = "COR")
	private Short cor;
	@Column(name = "FLUOR")
	private Short fluor;
	@Column(name = "MAINT")
	private String maint;
	@Column(name = "TURBIDEZ")
	private Short turbidez;
	@Column(name = "ESCHER_COLI")
	private Short escherColi;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "DATA_INSERCAO")
	private LocalDate dataInsercao;

	public IdAmostrasExigidas getIdAmostrasExigidas() {

		return idAmostrasExigidas;
	}

	public void setIdAmostrasExigidas(IdAmostrasExigidas idAmostrasExigidas) {

		this.idAmostrasExigidas = idAmostrasExigidas;
	}

	public Short getCloro() {

		return cloro;
	}

	public void setCloro(Short cloro) {

		this.cloro = cloro;
	}

	public Short getColiTermo() {

		return coliTermo;
	}

	public void setColiTermo(Short coliTermo) {

		this.coliTermo = coliTermo;
	}

	public Short getColiformesTotais() {

		return coliformesTotais;
	}

	public void setColiformesTotais(Short coliformesTotais) {

		this.coliformesTotais = coliformesTotais;
	}

	public Short getCor() {

		return cor;
	}

	public void setCor(Short cor) {

		this.cor = cor;
	}

	public Short getFluor() {

		return fluor;
	}

	public void setFluor(Short fluor) {

		this.fluor = fluor;
	}

	public String getMaint() {

		return maint;
	}

	public void setMaint(String maint) {

		this.maint = maint;
	}

	public Short getTurbidez() {

		return turbidez;
	}

	public void setTurbidez(Short turbidez) {

		this.turbidez = turbidez;
	}

	public Short getEscherColi() {

		return escherColi;
	}

	public void setEscherColi(Short escherColi) {

		this.escherColi = escherColi;
	}

	public String getUsuario() {

		return usuario;
	}

	public void setUsuario(String usuario) {

		this.usuario = usuario;
	}

	public LocalDate getDataInsercao() {

		return dataInsercao;
	}

	public void setDataInsercao(LocalDate dataInsercao) {

		this.dataInsercao = dataInsercao;
	}

}
