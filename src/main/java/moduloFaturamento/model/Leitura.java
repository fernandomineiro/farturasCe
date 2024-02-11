package moduloFaturamento.model;

import moduloFaturamento.model.common.ItemAtendimento;
import moduloFaturamento.model.common.SolicitacaoServico;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "FTTLE")
public class Leitura {

    @EmbeddedId
    private IdLeitura idLeituraFaturamento;

    @Column(name = "DV")
    private Short dv;

    @Column(name = "CD_ANORMALIDADE")
    private Short cdAnormalidade;

    @Column(name = "ALTERADO")
    private String alterado;
    
    @Column(name = "AGENTE")
    private Integer agente;

    @Column(name = "CD_CIDADE")
    private Integer cidade;

    @Column(name = "CICLO")
    private Integer ciclo;

    @Column(name = "DT_LEITURA")
    private Integer dataDeleitura;

    @Column(name = "LEITURA")
    private Integer leitura;

    @Column(name = "LEITURA_CRIADA") @Size(max = 1)
    private String leituraCriada;

    @Column(name = "EXCLUI_CALC_MEDIA") @Size(max = 1)
    private String excluirCalculoMedia;

    @Column(name = "CD_OCORRENCIA")
    private Short ocorrencia;

    @Column(name = "CD_OCORRENCIA2")
    private Short ocorrencia2;

    @Column(name = "CD_OCORRENCIA3")
    private Short ocorrencia3;

    @Column(name = "SEQ_ROTA")
    private Integer seqRota;

    @Column(name = "ID_USUARIO")
    private String loginUsuario;

    @Column(name = "ID",insertable = false,updatable = false)
    private Long id;

    @Column(name = "MEDIDO")
    private Integer medido;

    @Column(name = "MEDIA_DIARIA") @Digits(integer=9, fraction=3)
    private BigDecimal mediaDiaria;

    @Column(name = "DIAS_VENDA")
    private Short diasVenda;

    @Column(name = "DIAS_CONSUMO")
    private Short diasConsumo;

    @Column(name = "CONSUMO_FATURAR") @Digits(integer=9, fraction=1)
    private BigDecimal consumoFaturarAgua;

    @Column(name = "CONSUMO_FATURAR_ESGOTO") @Digits(integer=9, fraction=1)
    private BigDecimal consumoFaturarEsgoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TP_CONSUMO_FATURAR", referencedColumnName = "ID")
    private TipoConsumoFaturado tipoConsumoFaturado;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns({
            @JoinColumn(name = "SS_CD_ATENDIMENTO", referencedColumnName="CD_ATENDIMENTO"),
            @JoinColumn(name = "SS_REF_ATENDIMENTO", referencedColumnName="REF_ATENDIMENTO"),
            @JoinColumn(name = "SS_SEQ", referencedColumnName="SEQ_SS")
    })
    private SolicitacaoServico solicitacaoServico;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns({
            @JoinColumn(name = "ITEM_CD_ATENDIMENTO", referencedColumnName="CD_ATENDIMENTO"),
            @JoinColumn(name = "ITEM_REF_ATENDIMENTO", referencedColumnName="REF_ATENDIMENTO"),
            @JoinColumn(name = "ITEM_SEQ", referencedColumnName="SEQ_ATEND")
    })
    private ItemAtendimento itemAtendimento;

    @Column(name = "DATA_HORA_EDICAO")
    private LocalDate dataHoraEdicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOTIVO_ALTERACAO", referencedColumnName = "ID")
    private TipoMotivoEdicaoLeituraConsumo tipoMotivoEdicaoLeituraConsumo;

    @Column(name = "CONSUMO_MEDIDO_MACRO")
    private Integer consumoMedidoMacro;

    @Column(name = "CONSUMO_MEDIDO_MICRO_MODIFICADO")
    private String consumoMedidoMicroModificado;

    @Column(name = "CONSUMO_FATURADO_ESGOTO_SECUNDARIO_MODIFICADO")
    private String consumoFaturadoEsgotoSecundarioModificado;

    public Leitura() {
    }

    public IdLeitura getIdLeituraFaturamento() {
        return idLeituraFaturamento;
    }

    public void setIdLeituraFaturamento(IdLeitura idLeituraFaturamento) {
        this.idLeituraFaturamento = idLeituraFaturamento;
    }

    public Integer getAgente() {
        return agente;
    }

    public void setAgente(Integer agente) {
        this.agente = agente;
    }

    public Integer getCidade() {
        return cidade;
    }

    public void setCidade(Integer cidade) {
        this.cidade = cidade;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    public Integer getLeitura() {
        return leitura;
    }

    public void setLeitura(Integer leitura) {
        this.leitura = leitura;
    }

    public Short getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Short ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Short getOcorrencia2() {
        return ocorrencia2;
    }

    public void setOcorrencia2(Short ocorrencia2) {
        this.ocorrencia2 = ocorrencia2;
    }

    public Short getOcorrencia3() {
        return ocorrencia3;
    }

    public void setOcorrencia3(Short ocorrencia3) {
        this.ocorrencia3 = ocorrencia3;
    }

    public Integer getSeqRota() {
        return seqRota;
    }

    public void setSeqRota(Integer seqRota) {
        this.seqRota = seqRota;
    }

    public Integer getDataDeleitura() {
        return dataDeleitura;
    }

    public void setDataDeleitura(Integer dataDeleitura) {
        this.dataDeleitura = dataDeleitura;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SolicitacaoServico getSolicitacaoServico() {
        return solicitacaoServico;
    }

    public void setSolicitacaoServico(SolicitacaoServico solicitacaoServico) {
        this.solicitacaoServico = solicitacaoServico;
    }

    public ItemAtendimento getItemAtendimento() {
        return itemAtendimento;
    }

    public void setItemAtendimento(ItemAtendimento itemAtendimento) {
        this.itemAtendimento = itemAtendimento;
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

    public TipoConsumoFaturado getTipoConsumoFaturado() {
        return tipoConsumoFaturado;
    }

    public void setTipoConsumoFaturado(TipoConsumoFaturado tipoConsumoFaturado) {
        this.tipoConsumoFaturado = tipoConsumoFaturado;
    }

    public BigDecimal getConsumoFaturarAgua() {
        return consumoFaturarAgua;
    }

    public void setConsumoFaturarAgua(BigDecimal consumoFaturar) {
        this.consumoFaturarAgua = consumoFaturar;
    }

    public String getExcluirCalculoMedia() {
        return excluirCalculoMedia;
    }

    public void setExcluirCalculoMedia(String excluirCalculoMedia) {
        this.excluirCalculoMedia = excluirCalculoMedia;
    }

    public String getLeituraCriada() {
        return leituraCriada;
    }

    public void setLeituraCriada(String leituraCriada) {
        this.leituraCriada = leituraCriada;
    }

    public BigDecimal getConsumoFaturarEsgoto() {
        return consumoFaturarEsgoto;
    }

    public void setConsumoFaturarEsgoto(BigDecimal consumoFaturarEsgoto) {
        this.consumoFaturarEsgoto = consumoFaturarEsgoto;
    }

    public LocalDate getDataHoraEdicao() {
        return dataHoraEdicao;
    }

    public void setDataHoraEdicao(LocalDate dataHoraEdicao) {
        this.dataHoraEdicao = dataHoraEdicao;
    }

    public Short getCdAnormalidade() {
        return cdAnormalidade;
    }

    public void setCdAnormalidade(Short cdAnormalidade) {
        this.cdAnormalidade = cdAnormalidade;
    }

    public String getAlterado() {
        return alterado;
    }

    public void setAlterado(String alterado) {
        this.alterado = alterado;
    }

    public TipoMotivoEdicaoLeituraConsumo getTipoMotivoEdicaoLeituraConsumo() {
        return tipoMotivoEdicaoLeituraConsumo;
    }

    public void setTipoMotivoEdicaoLeituraConsumo(TipoMotivoEdicaoLeituraConsumo tipoMotivoEdicaoLeituraConsumo) {
        this.tipoMotivoEdicaoLeituraConsumo = tipoMotivoEdicaoLeituraConsumo;
    }

    public Short getDv() {
        return dv;
    }

    public void setDv(Short dv) {
        this.dv = dv;
    }

    public Integer getConsumoMedidoMacro() {
        return consumoMedidoMacro;
    }

    public void setConsumoMedidoMacro(Integer consumoMedidoMacro) {
        this.consumoMedidoMacro = consumoMedidoMacro;
    }

    public String getConsumoMedidoMicroModificado() {
        return consumoMedidoMicroModificado;
    }

    public void setConsumoMedidoMicroModificado(String consumoMedidoMicroModificado) {
        this.consumoMedidoMicroModificado = consumoMedidoMicroModificado;
    }

    public String getConsumoFaturadoEsgotoSecundarioModificado() {
        return consumoFaturadoEsgotoSecundarioModificado;
    }

    public void setConsumoFaturadoEsgotoSecundarioModificado(String consumoFaturadoEsgotoSecundarioModificado) {
        this.consumoFaturadoEsgotoSecundarioModificado = consumoFaturadoEsgotoSecundarioModificado;
    }
}
