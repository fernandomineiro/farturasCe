package moduloFaturamento.dto.faturaAvulsaImovel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


public class FaturaAvulsaImovelCadastrarDTO {

	@NotNull
	private Integer matricula;

	@NotNull
	private Short versaoFatura;

	@NotNull
	private boolean tratamentoEsgoto;

	@NotNull
	private Short dv;

	@NotNull
	private Short ciclo;

	@Null
	private String usuario;
	@NotNull
	private Integer diasVendas;
	@NotNull
	private LocalDate refFatura;
	@NotNull
	private LocalDate dataVencimento;
	@NotNull
	private BigDecimal valorTotal;
	@NotNull
	private Integer volume;
	@NotNull
	private Short origemFatura;
	@NotNull
	private List<FaturaAvulsaImovelServicoItem> itens;

	@NotNull
	private Short grupoConsumo;


	private BigDecimal volumeAgua;

	
	private BigDecimal volumeEsgoto;

	@NotNull
	private short tipoConsumoFaturado;

	
	private BigDecimal volumeDisponivel;


	private Integer cdCliente;

	private Integer dvCliente;

	public Integer getCdCliente() {
		return cdCliente;
	}

	public void setCdCliente(Integer cdCliente) {
		this.cdCliente = cdCliente;
	}

	public Integer getDvCliente() {
		return dvCliente;
	}

	public void setDvCliente(Integer dvCliente) {
		this.dvCliente = dvCliente;
	}

	
	public Integer getMatricula() {
		return matricula;
	}


	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}


	public Short getVersaoFatura() {
		return versaoFatura;
	}


	public void setVersaoFatura(Short versaoFatura) {
		this.versaoFatura = versaoFatura;
	}


	public boolean isTratamentoEsgoto() {
		return tratamentoEsgoto;
	}


	public void setTratamentoEsgoto(boolean tratamentoEsgoto) {
		this.tratamentoEsgoto = tratamentoEsgoto;
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


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public Integer getDiasVendas() {
		return diasVendas;
	}


	public void setDiasVendas(Integer diasVendas) {
		this.diasVendas = diasVendas;
	}


	public LocalDate getRefFatura() {
		return refFatura;
	}


	public void setRefFatura(LocalDate refFatura) {
		this.refFatura = refFatura;
	}


	public LocalDate getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


	public BigDecimal getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}


	public Integer getVolume() {
		return volume;
	}


	public void setVolume(Integer volume) {
		this.volume = volume;
	}


	public Short getOrigemFatura() {
		return origemFatura;
	}


	public void setOrigemFatura(Short origemFatura) {
		this.origemFatura = origemFatura;
	}


	public List<FaturaAvulsaImovelServicoItem> getItens() {
		return itens;
	}


	public void setItens(List<FaturaAvulsaImovelServicoItem> itens) {
		this.itens = itens;
	}


	public Short getGrupoConsumo() {
		return grupoConsumo;
	}


	public void setGrupoConsumo(Short grupoConsumo) {
		this.grupoConsumo = grupoConsumo;
	}


	public BigDecimal getVolumeAgua() {
		return volumeAgua;
	}


	public void setVolumeAgua(BigDecimal volumeAgua) {
		this.volumeAgua = volumeAgua;
	}


	public BigDecimal getVolumeEsgoto() {
		return volumeEsgoto;
	}


	public void setVolumeEsgoto(BigDecimal volumeEsgoto) {
		this.volumeEsgoto = volumeEsgoto;
	}


	public short getTipoConsumoFaturado() {
		return tipoConsumoFaturado;
	}


	public void setTipoConsumoFaturado(short tipoConsumoFaturado) {
		this.tipoConsumoFaturado = tipoConsumoFaturado;
	}


	public BigDecimal getVolumeDisponivel() {
		return volumeDisponivel;
	}


	public void setVolumeDisponivel(BigDecimal volumeDisponivel) {
		this.volumeDisponivel = volumeDisponivel;
	}


 

}
