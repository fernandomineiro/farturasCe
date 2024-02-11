package moduloFaturamento.model.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CDAIM")
public class Imovel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MATRICULA_IMOVEL")
	private Integer matriculaImovel;
	@Column(name = "NR_MATRICULA_MACRO")
	private Integer matriculaMacro;
	@Column(name = "DV")
	private Short dv;
	@Column(name = "CICLO")
	private Short ciclo;
	@Column(name = "CD_CIDADE")
	private Short cdCidade;
	@Column(name = "DT_EXCLUSAO")
	private LocalDateTime dataHoraExclusao;
	@Column(name = "CD_CLIENTE")
	private Integer cdCliente;
	@Column(name = "DV_R")
	private Short dvCliente;
	@Column(name = "CD_BAIRRO")
	private Short cdBairro;
	@Column(name = "CD_LOGRADOURO")
	private Short cdLogradouro;
	@Column(name = "CEP")
	private Integer cep;

	@Column(name = "UN_CONSUMO")
	private Short unidadeConsumo;

	@Column(name = "NUM_ENDERECO")
	private Integer numEndereco;
	@Column(name = "COMPL_ENDERECO")
	private String complEndereco;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SIT_LIGACAO_AGUA")
	private SituacaoLigacaoAgua situacaoLigacaoAgua;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SIT_LIGACAO_ESGOTO")
	private SituacaoLigacaoEsgoto situacaoLigacaoEsgoto;
	@Column(name = "SEQ_ROTA")
	private BigDecimal sequenciaRota;
	@Column(name = "DT_REATIVACAO_AGUA")
	private Integer dataReativacaAgua;
	@Column(name = "TP_MEDICAO_IND")
	private Short tipoMedicaoInd;
	@Column(name = "CRIT_FATU_DECI_JUDI")
	private Short criticaFaturamentoDecisaoJudicial;
	@Column(name = "DT_LIGACAO_AGUA")
	private Integer dataLigacaoAgua;
	@Column(name = "DT_LIGACAO_ESGOTO")
	private Integer dataLigacaoEsgoto;
	@Column(name = "GRUPO_CONSUMO")
	private Short grupoDeConsumo;

	public Imovel() {

	}

	public Imovel(Integer matriculaImovel) {

		this.matriculaImovel = matriculaImovel;
	}

	public Short getUnidadeConsumo() {
		return unidadeConsumo;
	}

	public void setUnidadeConsumo(Short unidadeConsumo) {
		this.unidadeConsumo = unidadeConsumo;
	}

	public Integer getMatriculaImovel() {

		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {

		this.matriculaImovel = matriculaImovel;
	}

	public Short getDv() {

		return dv;
	}

	public void setDv(Short dv) {

		this.dv = dv;
	}

	public Short getCiclo() {

		return ciclo;
	}

	public void setCiclo(Short ciclo) {

		this.ciclo = ciclo;
	}

	public Short getCdCidade() {

		return cdCidade;
	}

	public void setCdCidade(Short cdCidade) {

		this.cdCidade = cdCidade;
	}

	public LocalDateTime getDataHoraExclusao() {

		return dataHoraExclusao;
	}

	public void setDataHoraExclusao(LocalDateTime dataHoraExclusao) {

		this.dataHoraExclusao = dataHoraExclusao;
	}

	public Short getDvCliente() {

		return dvCliente;
	}

	public void setDvCliente(Short dvCliente) {

		this.dvCliente = dvCliente;
	}

	public Integer getCdCliente() {

		return cdCliente;
	}

	public void setCdCliente(Integer cdCliente) {

		this.cdCliente = cdCliente;
	}

	public SituacaoLigacaoAgua getSituacaoLigacaoAgua() {

		return situacaoLigacaoAgua;
	}

	public void setSituacaoLigacaoAgua(SituacaoLigacaoAgua situacaoLigacaoAgua) {

		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}

	public SituacaoLigacaoEsgoto getSituacaoLigacaoEsgoto() {

		return situacaoLigacaoEsgoto;
	}

	public void setSituacaoLigacaoEsgoto(SituacaoLigacaoEsgoto situacaoLigacaoEsgoto) {

		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	public BigDecimal getSequenciaRota() {

		return sequenciaRota;
	}

	public void setSequenciaRota(BigDecimal sequenciaRota) {

		this.sequenciaRota = sequenciaRota;
	}

	public Integer getDataReativacaAgua() {

		return dataReativacaAgua;
	}

	public void setDataReativacaAgua(Integer dataReativacaAgua) {

		this.dataReativacaAgua = dataReativacaAgua;
	}

	public Short getTipoMedicaoInd() {

		return tipoMedicaoInd;
	}

	public void setTipoMedicaoInd(Short tipoMedicaoInd) {

		this.tipoMedicaoInd = tipoMedicaoInd;
	}

	public Short getCriticaFaturamentoDecisaoJudicial() {

		return criticaFaturamentoDecisaoJudicial;
	}

	public void setCriticaFaturamentoDecisaoJudicial(Short criticaFaturamentoDecisaoJudicial) {

		this.criticaFaturamentoDecisaoJudicial = criticaFaturamentoDecisaoJudicial;
	}

	public Integer getMatriculaMacro() {

		return matriculaMacro;
	}

	public void setMatriculaMacro(Integer matriculaMacro) {

		this.matriculaMacro = matriculaMacro;
	}

	public Integer getDataLigacaoAgua() {

		return dataLigacaoAgua;
	}

	public void setDataLigacaoAgua(Integer dataLigacaoAgua) {

		this.dataLigacaoAgua = dataLigacaoAgua;
	}

	public Integer getDataLigacaoEsgoto() {

		return dataLigacaoEsgoto;
	}

	public void setDataLigacaoEsgoto(Integer dataLigacaoEsgoto) {

		this.dataLigacaoEsgoto = dataLigacaoEsgoto;
	}

	public Short getGrupoDeConsumo() {

		return grupoDeConsumo;
	}

	public void setGrupoDeConsumo(Short grupoDeConsumo) {

		this.grupoDeConsumo = grupoDeConsumo;
	}

}
