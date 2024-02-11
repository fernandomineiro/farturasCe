package moduloFaturamento.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ATTAR")
public class AmostrasRealizadas {

	@EmbeddedId
	private IdAmostrasRealizadas idAmostrasRealizadas;

	@Column(name = "CLORO_A")
	private Short cloroA;
	@Column(name = "CLORO_R")
	private Short cloroR;
	@Column(name = "COLI_TERMO_A")
	private Short coliTermoA;
	@Column(name = "COLI_TERMO_R")
	private Short coliTermoR;
	@Column(name = "COLIFORMES_A")
	private Short coliformesA;
	@Column(name = "COLIFORMES_R")
	private Short coliformesR;
	@Column(name = "CONCLUSAO")
	private String conclusao;
	@Column(name = "COR_A")
	private Short corA;
	@Column(name = "COR_R")
	private Short corR;
	@Column(name = "FLUOR_A")
	private Short fluorA;
	@Column(name = "FLUOR_R")
	private Short fluorR;
	@Column(name = "MAINT")
	private String maint;
	@Column(name = "TURBIDEZ_A")
	private Short turbidezA;
	@Column(name = "TURBIDEZ_R")
	private Short turbidezR;
	@Column(name = "ESCHER_COLI_A")
	private Short escherColiA;
	@Column(name = "ESCHER_COLI_R")
	private Short escherColiR;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "DATA_INSERCAO")
	private LocalDate dataInsercao;

	public IdAmostrasRealizadas getIdAmostrasRealizadas() {

		return idAmostrasRealizadas;
	}

	public void setIdAmostrasRealizadas(IdAmostrasRealizadas idAmostrasRealizadas) {

		this.idAmostrasRealizadas = idAmostrasRealizadas;
	}

	public Short getCloroA() {

		return cloroA;
	}

	public void setCloroA(Short cloroA) {

		this.cloroA = cloroA;
	}

	public Short getCloroR() {

		return cloroR;
	}

	public void setCloroR(Short cloroR) {

		this.cloroR = cloroR;
	}

	public Short getColiTermoA() {

		return coliTermoA;
	}

	public void setColiTermoA(Short coliTermoA) {

		this.coliTermoA = coliTermoA;
	}

	public Short getColiTermoR() {

		return coliTermoR;
	}

	public void setColiTermoR(Short coliTermoR) {

		this.coliTermoR = coliTermoR;
	}

	public Short getColiformesA() {

		return coliformesA;
	}

	public void setColiformesA(Short coliformesA) {

		this.coliformesA = coliformesA;
	}

	public Short getColiformesR() {

		return coliformesR;
	}

	public void setColiformesR(Short coliformesR) {

		this.coliformesR = coliformesR;
	}

	public String getConclusao() {

		return conclusao;
	}

	public void setConclusao(String conclusao) {

		this.conclusao = conclusao;
	}

	public Short getCorA() {

		return corA;
	}

	public void setCorA(Short corA) {

		this.corA = corA;
	}

	public Short getCorR() {

		return corR;
	}

	public void setCorR(Short corR) {

		this.corR = corR;
	}

	public Short getFluorA() {

		return fluorA;
	}

	public void setFluorA(Short fluorA) {

		this.fluorA = fluorA;
	}

	public Short getFluorR() {

		return fluorR;
	}

	public void setFluorR(Short fluorR) {

		this.fluorR = fluorR;
	}

	public String getMaint() {

		return maint;
	}

	public void setMaint(String maint) {

		this.maint = maint;
	}

	public Short getTurbidezA() {

		return turbidezA;
	}

	public void setTurbidezA(Short turbidezA) {

		this.turbidezA = turbidezA;
	}

	public Short getTurbidezR() {

		return turbidezR;
	}

	public void setTurbidezR(Short turbidezR) {

		this.turbidezR = turbidezR;
	}

	public Short getEscherColiA() {

		return escherColiA;
	}

	public void setEscherColiA(Short escherColiA) {

		this.escherColiA = escherColiA;
	}

	public Short getEscherColiR() {

		return escherColiR;
	}

	public void setEscherColiR(Short escherColiR) {

		this.escherColiR = escherColiR;
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
