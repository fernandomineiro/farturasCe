package moduloFaturamento.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CAD_LOCALIDADE_SICAT_FILIAL_SAP")
public class LocalidadeFilialSap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOCALIDADE_SICAT_FILIAL_SAP")
    private Integer id;

    @Column(name = "CD_CIDADE")
    private Short cdCidade;

    @Column(name = "ID_FILIAL_SAP")
    private Integer idFilialSap;

    @Column(name = "DT_INICIO_VALIDADE")
    private LocalDateTime dtInicioValidade;

    @Column(name = "DT_FIM_VALIDADE")
    private LocalDateTime dtFimValidade;

    @Column(name = "EST_REG")
    private char estReg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getCdCidade() {
		return cdCidade;
	}

	public void setCdCidade(Short cdCidade) {
		this.cdCidade = cdCidade;
	}

	public Integer getIdFilialSap() {
		return idFilialSap;
	}

	public void setIdFilialSap(Integer idFilialSap) {
		this.idFilialSap = idFilialSap;
	}

	public LocalDateTime getDtInicioValidade() {
		return dtInicioValidade;
	}

	public void setDtInicioValidade(LocalDateTime dtInicioValidade) {
		this.dtInicioValidade = dtInicioValidade;
	}

	public LocalDateTime getDtFimValidade() {
		return dtFimValidade;
	}

	public void setDtFimValidade(LocalDateTime dtFimValidade) {
		this.dtFimValidade = dtFimValidade;
	}

	public char getEstReg() {
		return estReg;
	}

	public void setEstReg(char estReg) {
		this.estReg = estReg;
	}

}
