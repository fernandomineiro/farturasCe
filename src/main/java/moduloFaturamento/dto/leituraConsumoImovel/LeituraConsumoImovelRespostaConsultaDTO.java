package moduloFaturamento.dto.leituraConsumoImovel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LeituraConsumoImovelRespostaConsultaDTO {
    private Long id;
    private Integer refFatura;
    private LocalDate dataDeleitura;
    private Integer leitura;
    private Boolean leituraCriada;
    private Boolean excluirCalculoMedia;
    private Short ocorrenciaCodigo;
    private String ocorrenciaDescricao;
    private Short ocorrencia2Codigo;
    private String ocorrencia2Descricao;
    private Short ocorrencia3Codigo;
    private String ocorrencia3Descricao;

    private Integer medido;
    private BigDecimal mediaDiaria;
    private Short diasVenda;
    private Short diasConsumo;
    private BigDecimal consumoFaturarAgua;
    private BigDecimal consumoFaturarEsgoto;
    private Short idTipoConsumoFaturado;

    private Integer ssCdAtendimento;
    private Integer ssRefAtendimento;
    private Short seqSS;
    private Integer itemCdAtendimento;
    private Integer itemRefAtendimento;
    private Short itemSeq;

    private BigDecimal faixaConsumoParaAnaliseValorMaximo;
    private BigDecimal faixaConsumoParaAnaliseValorMinimo;

    private Boolean matriculaMacro;
    private Integer consumoMedidoMacro;
    private Boolean consumoMedidoMicroModificado;

    private Boolean abrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaMicro;
    private Boolean abrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaSecundaria;

    private Boolean matriculaPrincipal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRefFatura() {
        return refFatura;
    }

    public void setRefFatura(Integer refFatura) {
        this.refFatura = refFatura;
    }

    public Integer getLeitura() {
        return leitura;
    }

    public void setLeitura(Integer leitura) {
        this.leitura = leitura;
    }

    public Short getOcorrenciaCodigo() {
        return ocorrenciaCodigo;
    }

    public void setOcorrenciaCodigo(Short ocorrenciaCodigo) {
        this.ocorrenciaCodigo = ocorrenciaCodigo;
    }

    public String getOcorrenciaDescricao() {
        return ocorrenciaDescricao;
    }

    public void setOcorrenciaDescricao(String ocorrenciaDescricao) {
        this.ocorrenciaDescricao = ocorrenciaDescricao;
    }

    public Short getOcorrencia2Codigo() {
        return ocorrencia2Codigo;
    }

    public void setOcorrencia2Codigo(Short ocorrencia2Codigo) {
        this.ocorrencia2Codigo = ocorrencia2Codigo;
    }

    public String getOcorrencia2Descricao() {
        return ocorrencia2Descricao;
    }

    public void setOcorrencia2Descricao(String ocorrencia2Descricao) {
        this.ocorrencia2Descricao = ocorrencia2Descricao;
    }

    public Short getOcorrencia3Codigo() {
        return ocorrencia3Codigo;
    }

    public void setOcorrencia3Codigo(Short ocorrencia3Codigo) {
        this.ocorrencia3Codigo = ocorrencia3Codigo;
    }

    public String getOcorrencia3Descricao() {
        return ocorrencia3Descricao;
    }

    public void setOcorrencia3Descricao(String ocorrencia3Descricao) {
        this.ocorrencia3Descricao = ocorrencia3Descricao;
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

    public Integer getSsCdAtendimento() {
        return ssCdAtendimento;
    }

    public void setSsCdAtendimento(Integer ssCdAtendimento) {
        this.ssCdAtendimento = ssCdAtendimento;
    }

    public Integer getSsRefAtendimento() {
        return ssRefAtendimento;
    }

    public void setSsRefAtendimento(Integer ssRefAtendimento) {
        this.ssRefAtendimento = ssRefAtendimento;
    }

    public Short getSeqSS() {
        return seqSS;
    }

    public void setSeqSS(Short seqSS) {
        this.seqSS = seqSS;
    }

    public Integer getItemCdAtendimento() {
        return itemCdAtendimento;
    }

    public void setItemCdAtendimento(Integer itemCdAtendimento) {
        this.itemCdAtendimento = itemCdAtendimento;
    }

    public Integer getItemRefAtendimento() {
        return itemRefAtendimento;
    }

    public void setItemRefAtendimento(Integer itemRefAtendimento) {
        this.itemRefAtendimento = itemRefAtendimento;
    }

    public Short getItemSeq() {
        return itemSeq;
    }

    public void setItemSeq(Short itemSeq) {
        this.itemSeq = itemSeq;
    }

    public Short getIdTipoConsumoFaturado() {
        return idTipoConsumoFaturado;
    }

    public void setIdTipoConsumoFaturado(Short idTipoConsumoFaturado) {
        this.idTipoConsumoFaturado = idTipoConsumoFaturado;
    }

    public BigDecimal getConsumoFaturarAgua() {
        return consumoFaturarAgua;
    }

    public void setConsumoFaturarAgua(BigDecimal consumoFaturarAgua) {
        this.consumoFaturarAgua = consumoFaturarAgua;
    }

    public Boolean getLeituraCriada() {
        return leituraCriada;
    }

    public void setLeituraCriada(Boolean leituraCriada) {
        this.leituraCriada = leituraCriada;
    }

    public Boolean getExcluirCalculoMedia() {
        return excluirCalculoMedia;
    }

    public void setExcluirCalculoMedia(Boolean excluirCalculoMedia) {
        this.excluirCalculoMedia = excluirCalculoMedia;
    }

    public BigDecimal getConsumoFaturarEsgoto() {
        return consumoFaturarEsgoto;
    }

    public void setConsumoFaturarEsgoto(BigDecimal consumoFaturarEsgoto) {
        this.consumoFaturarEsgoto = consumoFaturarEsgoto;
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

    public Integer getConsumoMedidoMacro() {
        return consumoMedidoMacro;
    }

    public void setConsumoMedidoMacro(Integer consumoMedidoMacro) {
        this.consumoMedidoMacro = consumoMedidoMacro;
    }

    public Boolean getConsumoMedidoMicroModificado() {
        return consumoMedidoMicroModificado;
    }

    public void setConsumoMedidoMicroModificado(Boolean consumoMedidoMicroModificado) {
        this.consumoMedidoMicroModificado = consumoMedidoMicroModificado;
    }

    public Boolean getMatriculaMacro() {
        return matriculaMacro;
    }

    public void setMatriculaMacro(Integer matriculaMacro) {
        this.matriculaMacro = matriculaMacro != null && matriculaMacro != 0;
    }

    public Boolean getAbrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaMicro() {
        return abrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaMicro;
    }

    public void setAbrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaMicro(Boolean abrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaMicro) {
        this.abrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaMicro = abrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaMicro;
    }

    public Boolean getMatriculaPrincipal() {
        return matriculaPrincipal;
    }

    public void setMatriculaPrincipal(List<Integer> matriculasSecundarias) {
        this.matriculaPrincipal = matriculasSecundarias != null;

    }

    public Boolean getAbrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaSecundaria() {
        return abrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaSecundaria;
    }

    public void setAbrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaSecundaria(Boolean abrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaSecundaria) {
        this.abrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaSecundaria = abrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaSecundaria;
    }

    public LocalDate getDataDeleitura() {
        return dataDeleitura;
    }

    public void setDataDeleitura(LocalDate dataDeleitura) {
        this.dataDeleitura = dataDeleitura;
    }
}
