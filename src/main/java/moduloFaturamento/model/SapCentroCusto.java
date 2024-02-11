package moduloFaturamento.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "SAP_CENTRO_CUSTOS")
public class SapCentroCusto {
    
    @Column(name = "ID_SAP_CENTRO_CUSTOS")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CD_CCUSTO_SAP")
    private String cdCcustoSap;

    @Column(name = "CD_CIDADE")
    private Short cdCidade;

    @Column(name = "NR_CONTA_CONTABIL_SAP")
    private Integer nrContaContabilSap;

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

	public String getCdCcustoSap() {
		return cdCcustoSap;
	}

	public void setCdCcustoSap(String cdCcustoSap) {
		this.cdCcustoSap = cdCcustoSap;
	}

	public Short getCdCidade() {
		return cdCidade;
	}

	public void setCdCidade(Short cdCidade) {
		this.cdCidade = cdCidade;
	}

	public Integer getNrContaContabilSap() {
		return nrContaContabilSap;
	}

	public void setNrContaContabilSap(Integer nrContaContabilSap) {
		this.nrContaContabilSap = nrContaContabilSap;
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
