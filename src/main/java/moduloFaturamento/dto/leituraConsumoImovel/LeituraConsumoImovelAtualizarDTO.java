package moduloFaturamento.dto.leituraConsumoImovel;

import moduloFaturamento.regras.leituraConsumoImovel.spec.LeituraConsumoImovelTipoNumeroSolicitacaoEnum;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class LeituraConsumoImovelAtualizarDTO {

    @NotNull
    private Long id;
    @NotNull
    private Integer leitura;
    @NotNull
    private LocalDate dataDeLeitura;
    @NotNull
    private Boolean leituraCriada;
    @NotNull
    private Integer medido;
    @NotNull
    private BigDecimal mediaDiaria;
    @NotNull
    private Short diasVenda;
    @NotNull
    private Short diasConsumo;
    @NotNull
    private Short idTipoMotivoEdicao;
    @NotNull @Size(max = 400)
    private String justificativa;
    @NotNull
    private BigDecimal faixaConsumoParaAnaliseValorMaximo;
    @NotNull
    private BigDecimal faixaConsumoParaAnaliseValorMinimo;

    private Boolean excluirCalculoDaMedia;

    @Digits(integer=9, fraction = 1, message = "O valor do consumo faturado de água deve conter somente 1 casa decimal")
    private BigDecimal consumoFaturarAgua;
    @Digits(integer=9, fraction = 1, message = "O valor do consumo faturado de água deve conter somente 1 casa decimal")
    private BigDecimal consumoFaturarEsgoto;

    private Short idTipoConsumoFaturado;

    private Short ocorrenciaCodigo;
    private Short ocorrencia2Codigo;
    private Short ocorrencia3Codigo;

    private String alteradoFlag;

    private Integer refSolicitacao;
    private Integer codSolicitacao;
    private Short seqSolicitacao;

    private LeituraConsumoImovelTipoNumeroSolicitacaoEnum tipoSolicitacao;

    private Boolean matriculaMacro;
    private Boolean matriculaPrincipal;
    private Integer consumoMedidoMacro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLeitura() {
        return leitura;
    }

    public void setLeitura(Integer leitura) {
        this.leitura = leitura;
    }

    public Boolean getLeituraCriada() {
        return leituraCriada;
    }

    public void setLeituraCriada(Boolean leituraCriada) {
        this.leituraCriada = leituraCriada;
    }

    public Integer getMedido() {
        return medido;
    }

    public void setMedido(Integer medido) {
        this.medido = medido;
    }

    public BigDecimal getMediaDiaria() {
        return mediaDiaria;
    }

    public void setMediaDiaria(BigDecimal mediaDiaria) {
        this.mediaDiaria = mediaDiaria;
    }

    public Short getDiasVenda() {
        return diasVenda;
    }

    public void setDiasVenda(Short diasVenda) {
        this.diasVenda = diasVenda;
    }

    public Short getDiasConsumo() {
        return diasConsumo;
    }

    public void setDiasConsumo(Short diasConsumo) {
        this.diasConsumo = diasConsumo;
    }

    public Short getIdTipoMotivoEdicao() {
        return idTipoMotivoEdicao;
    }

    public void setIdTipoMotivoEdicao(Short idTipoMotivoEdicao) {
        this.idTipoMotivoEdicao = idTipoMotivoEdicao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public BigDecimal getConsumoFaturarAgua() {
        return consumoFaturarAgua;
    }

    public void setConsumoFaturarAgua(BigDecimal consumoFaturarAgua) {
        this.consumoFaturarAgua = consumoFaturarAgua;
    }

    public BigDecimal getConsumoFaturarEsgoto() {
        return Objects.requireNonNullElse(this.consumoFaturarEsgoto, BigDecimal.ZERO);
    }

    public void setConsumoFaturarEsgoto(BigDecimal consumoFaturarEsgoto) {
        this.consumoFaturarEsgoto = consumoFaturarEsgoto;
    }

    public Short getIdTipoConsumoFaturado() {
        return idTipoConsumoFaturado;
    }

    public void setIdTipoConsumoFaturado(Short idTipoConsumoFaturado) {
        this.idTipoConsumoFaturado = idTipoConsumoFaturado;
    }

    public Short getOcorrenciaCodigo() {
        return ocorrenciaCodigo;
    }

    public void setOcorrenciaCodigo(Short ocorrenciaCodigo) {
        this.ocorrenciaCodigo = ocorrenciaCodigo;
    }

    public Short getOcorrencia2Codigo() {
        return ocorrencia2Codigo;
    }

    public void setOcorrencia2Codigo(Short ocorrencia2Codigo) {
        this.ocorrencia2Codigo = ocorrencia2Codigo;
    }

    public Short getOcorrencia3Codigo() {
        return ocorrencia3Codigo;
    }

    public void setOcorrencia3Codigo(Short ocorrencia3Codigo) {
        this.ocorrencia3Codigo = ocorrencia3Codigo;
    }

    public Integer getRefSolicitacao() {
        return refSolicitacao;
    }

    public void setRefSolicitacao(Integer refSolicitacao) {
        this.refSolicitacao = refSolicitacao;
    }

    public Integer getCodSolicitacao() {
        return codSolicitacao;
    }

    public void setCodSolicitacao(Integer codSolicitacao) {
        this.codSolicitacao = codSolicitacao;
    }

    public Short getSeqSolicitacao() {
        return seqSolicitacao;
    }

    public void setSeqSolicitacao(Short seqSolicitacao) {
        this.seqSolicitacao = seqSolicitacao;
    }

    public LeituraConsumoImovelTipoNumeroSolicitacaoEnum getTipoSolicitacao() {
        return tipoSolicitacao;
    }

    public void setTipoSolicitacao(LeituraConsumoImovelTipoNumeroSolicitacaoEnum tipoSolicitacao) {
        this.tipoSolicitacao = tipoSolicitacao;
    }

    public String getAlteradoFlag() {

        return Objects.requireNonNullElse(this.alteradoFlag, "N");
    }

    public void setAlteradoFlag(String alteradoFlag) {
        this.alteradoFlag = Objects.requireNonNullElse(alteradoFlag, "N");
    }

    public BigDecimal getFaixaConsumoParaAnaliseValorMaximo() {
        return faixaConsumoParaAnaliseValorMaximo;
    }

    public void setFaixaConsumoParaAnaliseValorMaximo(BigDecimal faixaConsumoParaAnaliseValorMaximo) {
        this.faixaConsumoParaAnaliseValorMaximo = faixaConsumoParaAnaliseValorMaximo;
    }

    public BigDecimal getFaixaConsumoParaAnaliseValorMinimo() {
        return faixaConsumoParaAnaliseValorMinimo;
    }

    public void setFaixaConsumoParaAnaliseValorMinimo(BigDecimal faixaConsumoParaAnaliseValorMinimo) {
        this.faixaConsumoParaAnaliseValorMinimo = faixaConsumoParaAnaliseValorMinimo;
    }

    public Boolean getExcluirCalculoDaMedia() {
        return excluirCalculoDaMedia;
    }

    public void setExcluirCalculoDaMedia(Boolean excluirCalculoDaMedia) {
        this.excluirCalculoDaMedia = excluirCalculoDaMedia;
    }

    public Boolean getMatriculaMacro() {
        return Objects.requireNonNullElse(this.matriculaMacro, false);
    }

    public void setMatriculaMacro(Boolean matriculaMacro) {
        this.matriculaMacro = matriculaMacro;
    }

    public Integer getConsumoMedidoMacro() {
        return Objects.requireNonNullElse(this.consumoMedidoMacro, 0);
    }

    public void setConsumoMedidoMacro(Integer consumoMedidoMacro) {
        this.consumoMedidoMacro = consumoMedidoMacro;
    }

    public Boolean getMatriculaPrincipal() {
        return Objects.requireNonNullElse(this.matriculaPrincipal, false);
    }

    public void setMatriculaPrincipal(Boolean matriculaPrincipal) {
        this.matriculaPrincipal = matriculaPrincipal;
    }

    public LocalDate getDataDeLeitura() {
        return dataDeLeitura;
    }

    public void setDataDeLeitura(LocalDate dataDeLeitura) {
        this.dataDeLeitura = dataDeLeitura;
    }
}
