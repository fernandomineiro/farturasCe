package moduloFaturamento.dto.faturaImovel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import moduloFaturamento.dto.faturaImovel.projection.FaturaImovelLancamentoProjectionDTO;

public class FaturaImovelDetalhadaDTO {

	private Long id;

	private Integer matricula;

	private LocalDate referencia;

	private Short dv;

	private Short ciclo;

	private Short origem;

	private Short versao;

	private Integer cdCliente;

	private Integer dvCliente;

	private String nomeCliente;

	private LocalDate dataLeitura;

	private Integer leituraAnterior;

	private Integer leituraAtual;

	private Integer medido;

	private Short cdOcorrencia1;

	private Short cdOcorrencia2;

	private Short cdOcorrencia3;

	private Short tipoConsumoFaturamento;

	private BigDecimal consumoFaturadoAgua;

	private BigDecimal consumoFaturadoEsgoto;

	private BigDecimal consumoDisponibilidadeFaturado;

	private Short descontoEsgoto;

	private Short subvencao;

	private Short diasConsumo;

	private Short diasVenda;

	private String tipoMedicaoEsgoto;

	private String situacaoFatura;

	private LocalDate dataVencimento;

	private LocalDate dataTarifa;

	private String grupoConsumo;

	private String tratamentoEsgoto;

	private String situacaoAgua;

	private String situacaoEsgoto;

	private Short numeroEconomiasFaturamento;

	private Short numeroEconomiasCadastro;

	private BigDecimal valorTotal;

	private BigDecimal valorTotalLigacao;

	private BigDecimal valorTotalEconomias;

	private String localidade;

	private String bairro;

	private String sigla;

	private String logradouro;

	private Integer numero;

	private String complemento;

	private LocalDate dataBaixa;

	private LocalDate dataArrecadacao;

	private List<FaturaImovelLancamentoProjectionDTO> lancamentosFatura;

	public Long getId() {

		return id;
	}

	public Integer getDvCliente() {
		return dvCliente;
	}

	public void setDvCliente(Integer dvCliente) {
		this.dvCliente = dvCliente;
	}

	public Integer getCdCliente() {
		return cdCliente;
	}

	public void setCdCliente(Integer cdCliente) {
		this.cdCliente = cdCliente;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Integer getMatricula() {

		return matricula;
	}

	public void setMatricula(Integer matricula) {

		this.matricula = matricula;
	}

	public LocalDate getReferencia() {

		return referencia;
	}

	public void setReferencia(LocalDate referencia) {

		this.referencia = referencia;
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

	public Short getOrigem() {

		return origem;
	}

	public void setOrigem(Short origem) {

		this.origem = origem;
	}

	public Short getVersao() {

		return versao;
	}

	public void setVersao(Short versao) {

		this.versao = versao;
	}



	public String getNomeCliente() {

		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {

		this.nomeCliente = nomeCliente;
	}

	public LocalDate getDataLeitura() {

		return dataLeitura;
	}

	public void setDataLeitura(LocalDate dataLeitura) {

		this.dataLeitura = dataLeitura;
	}

	public Integer getLeituraAnterior() {

		return leituraAnterior;
	}

	public void setLeituraAnterior(Integer leituraAnterior) {

		this.leituraAnterior = leituraAnterior;
	}

	public Integer getLeituraAtual() {

		return leituraAtual;
	}

	public void setLeituraAtual(Integer leituraAtual) {

		this.leituraAtual = leituraAtual;
	}

	public Integer getMedido() {

		return medido;
	}

	public void setMedido(Integer medido) {

		this.medido = medido;
	}

	public Short getCdOcorrencia1() {

		return cdOcorrencia1;
	}

	public void setCdOcorrencia1(Short cdOcorrencia1) {

		this.cdOcorrencia1 = cdOcorrencia1;
	}

	public Short getCdOcorrencia2() {

		return cdOcorrencia2;
	}

	public void setCdOcorrencia2(Short cdOcorrencia2) {

		this.cdOcorrencia2 = cdOcorrencia2;
	}

	public Short getCdOcorrencia3() {

		return cdOcorrencia3;
	}

	public void setCdOcorrencia3(Short cdOcorrencia3) {

		this.cdOcorrencia3 = cdOcorrencia3;
	}

	public Short getTipoConsumoFaturamento() {

		return tipoConsumoFaturamento;
	}

	public void setTipoConsumoFaturamento(Short tipoConsumoFaturamento) {

		this.tipoConsumoFaturamento = tipoConsumoFaturamento;
	}

	public BigDecimal getConsumoFaturadoAgua() {

		return consumoFaturadoAgua;
	}

	public void setConsumoFaturadoAgua(BigDecimal consumoFaturadoAgua) {

		this.consumoFaturadoAgua = consumoFaturadoAgua;
	}

	public BigDecimal getConsumoFaturadoEsgoto() {

		return consumoFaturadoEsgoto;
	}

	public void setConsumoFaturadoEsgoto(BigDecimal consumoFaturadoEsgoto) {

		this.consumoFaturadoEsgoto = consumoFaturadoEsgoto;
	}

	public BigDecimal getConsumoDisponibilidadeFaturado() {

		return consumoDisponibilidadeFaturado;
	}

	public void setConsumoDisponibilidadeFaturado(BigDecimal consumoDisponibilidadeFaturado) {

		this.consumoDisponibilidadeFaturado = consumoDisponibilidadeFaturado;
	}

	public Short getDescontoEsgoto() {

		return descontoEsgoto;
	}

	public void setDescontoEsgoto(Short descontoEsgoto) {

		this.descontoEsgoto = descontoEsgoto;
	}

	public Short getSubvencao() {

		return subvencao;
	}

	public void setSubvencao(Short subvencao) {

		this.subvencao = subvencao;
	}

	public Short getDiasConsumo() {

		return diasConsumo;
	}

	public void setDiasConsumo(Short diasConsumo) {

		this.diasConsumo = diasConsumo;
	}

	public Short getDiasVenda() {

		return diasVenda;
	}

	public void setDiasVenda(Short diasVenda) {

		this.diasVenda = diasVenda;
	}

	public String getTipoMedicaoEsgoto() {

		return tipoMedicaoEsgoto;
	}

	public void setTipoMedicaoEsgoto(String tipoMedicaoEsgoto) {

		this.tipoMedicaoEsgoto = tipoMedicaoEsgoto;
	}

	public String getSituacaoFatura() {

		return situacaoFatura;
	}

	public void setSituacaoFatura(String situacaoFatura) {

		this.situacaoFatura = situacaoFatura;
	}

	public LocalDate getDataVencimento() {

		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {

		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataTarifa() {

		return dataTarifa;
	}

	public void setDataTarifa(LocalDate dataTarifa) {

		this.dataTarifa = dataTarifa;
	}

	public String getGrupoConsumo() {

		return grupoConsumo;
	}

	public void setGrupoConsumo(String grupoConsumo) {

		this.grupoConsumo = grupoConsumo;
	}

	public String getTratamentoEsgoto() {

		return tratamentoEsgoto;
	}

	public void setTratamentoEsgoto(String tratamentoEsgoto) {

		this.tratamentoEsgoto = tratamentoEsgoto;
	}

	public String getSituacaoAgua() {

		return situacaoAgua;
	}

	public void setSituacaoAgua(String situacaoAgua) {

		this.situacaoAgua = situacaoAgua;
	}

	public String getSituacaoEsgoto() {

		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(String situacaoEsgoto) {

		this.situacaoEsgoto = situacaoEsgoto;
	}

	public Short getNumeroEconomiasFaturamento() {

		return numeroEconomiasFaturamento;
	}

	public void setNumeroEconomiasFaturamento(Short numeroEconomiasFaturamento) {

		this.numeroEconomiasFaturamento = numeroEconomiasFaturamento;
	}

	public Short getNumeroEconomiasCadastro() {

		return numeroEconomiasCadastro;
	}

	public void setNumeroEconomiasCadastro(Short numeroEconomiasCadastro) {

		this.numeroEconomiasCadastro = numeroEconomiasCadastro;
	}

	public BigDecimal getValorTotal() {

		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {

		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorTotalLigacao() {

		return valorTotalLigacao;
	}

	public void setValorTotalLigacao(BigDecimal valorTotalLigacao) {

		this.valorTotalLigacao = valorTotalLigacao;
	}

	public BigDecimal getValorTotalEconomias() {

		return valorTotalEconomias;
	}

	public void setValorTotalEconomias(BigDecimal valorTotalEconomias) {

		this.valorTotalEconomias = valorTotalEconomias;
	}

	public String getLocalidade() {

		return localidade;
	}

	public void setLocalidade(String localidade) {

		this.localidade = localidade;
	}

	public String getBairro() {

		return bairro;
	}

	public void setBairro(String bairro) {

		this.bairro = bairro;
	}

	public String getSigla() {

		return sigla;
	}

	public void setSigla(String sigla) {

		this.sigla = sigla;
	}

	public String getLogradouro() {

		return logradouro;
	}

	public void setLogradouro(String logradouro) {

		this.logradouro = logradouro;
	}

	public Integer getNumero() {

		return numero;
	}

	public void setNumero(Integer numero) {

		this.numero = numero;
	}

	public String getComplemento() {

		return complemento;
	}

	public void setComplemento(String complemento) {

		this.complemento = complemento;
	}

	public LocalDate getDataBaixa() {

		return dataBaixa;
	}

	public void setDataBaixa(LocalDate dataBaixa) {

		this.dataBaixa = dataBaixa;
	}

	public LocalDate getDataArrecadacao() {

		return dataArrecadacao;
	}

	public void setDataArrecadacao(LocalDate dataArrecadacao) {

		this.dataArrecadacao = dataArrecadacao;
	}

	public List<FaturaImovelLancamentoProjectionDTO> getLancamentosFatura() {

		return lancamentosFatura;
	}

	public void setLancamentosFatura(List<FaturaImovelLancamentoProjectionDTO> lancamentosFatura) {

		this.lancamentosFatura = lancamentosFatura;
	}

}
