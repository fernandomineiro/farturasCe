package moduloFaturamento.dto.leituraConsumoImovel.projection;

import moduloFaturamento.util.ConverterData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class LeituraConsumoImovelRespostaGridProjectionDTO {

    private Long id;
    private LocalDate referencia;
    private LocalDate dataLeitura;
    private Integer leitura;
    private Boolean leituraCriada;

    private Short ocorrenciaCodigo;
    private Short ocorrencia2Codigo;
    private Short ocorrencia3Codigo;

    private Integer medido;
    private BigDecimal mediaDiaria;

    private Short diasVenda;
    private Short diasConsumo;
    private BigDecimal consumoFaturarAgua;
    private BigDecimal consumoFaturarEsgoto;
    private Short tipoConsumoFaturadoId;
    private String tipoConsumoFaturadoDescricao;

    private String anormalidade;
    private String alteradoFlag;
    private LocalDate dataUltimaAlteracao;

    public LeituraConsumoImovelRespostaGridProjectionDTO() {
    }

    public LeituraConsumoImovelRespostaGridProjectionDTO(Long id, Integer referencia, Integer dataLeitura, Integer leitura, String leituraCriada, Short ocorrenciaCodigo, Short ocorrencia2Codigo,
                                                         Short ocorrencia3Codigo, Integer medido, BigDecimal mediaDiaria, Short diasVenda, Short diasConsumo, BigDecimal consumoFaturarAgua,
                                                         BigDecimal consumoFaturarEsgoto, Short tipoConsumoFaturadoId,  String tipoConsumoFaturadoDescricao, Short cdAnormalidade,
                                                         String alteradoFlag, LocalDate dataUltimaAlteracao) {
        this.id = id;
        this.referencia = ConverterData.integerEmLocalDateReferenciaFormatoDB(referencia);
        this.dataLeitura = ConverterData.integerEmLocalDateDataFormatoDB(dataLeitura);
        this.leitura = leitura;
        this.leituraCriada = (leituraCriada.equals("S"));
        this.ocorrenciaCodigo = ocorrenciaCodigo;
        this.ocorrencia2Codigo = ocorrencia2Codigo;
        this.ocorrencia3Codigo = ocorrencia3Codigo;
        this.medido = medido;
        this.mediaDiaria = mediaDiaria != null ? mediaDiaria.setScale(3, RoundingMode.FLOOR) : BigDecimal.ZERO.setScale(3, RoundingMode.FLOOR);
        this.diasVenda = diasVenda;
        this.diasConsumo = diasConsumo;
        this.consumoFaturarAgua = consumoFaturarAgua != null ? consumoFaturarAgua.setScale(3, RoundingMode.FLOOR) : BigDecimal.ZERO.setScale(3, RoundingMode.FLOOR);
        this.consumoFaturarEsgoto = consumoFaturarEsgoto != null ? consumoFaturarEsgoto.setScale(3, RoundingMode.FLOOR) : BigDecimal.ZERO.setScale(3, RoundingMode.FLOOR);
        this.tipoConsumoFaturadoId = tipoConsumoFaturadoId;
        this.tipoConsumoFaturadoDescricao = tipoConsumoFaturadoDescricao;
        this.setCdAnormalidade(cdAnormalidade);
        this.alteradoFlag =  alteradoFlag != null ? alteradoFlag.trim() : null;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getReferencia() {
        return referencia;
    }

    public LocalDate getDataLeitura() {
        return dataLeitura;
    }

    public Integer getLeitura() {
        return leitura;
    }

    public Boolean getLeituraCriada() {
        return leituraCriada;
    }

    public Short getOcorrenciaCodigo() {
        return ocorrenciaCodigo;
    }

    public Short getOcorrencia2Codigo() {
        return ocorrencia2Codigo;
    }

    public Short getOcorrencia3Codigo() {
        return ocorrencia3Codigo;
    }


    public Integer getMedido() {
        return medido;
    }

    public BigDecimal getMediaDiaria() {
        return mediaDiaria;
    }

    public Short getDiasVenda() {
        return diasVenda;
    }

    public Short getDiasConsumo() {
        return diasConsumo;
    }

    public BigDecimal getConsumoFaturarAgua() {
        return consumoFaturarAgua;
    }

    public BigDecimal getConsumoFaturarEsgoto() {
        return consumoFaturarEsgoto;
    }

    public Short getTipoConsumoFaturadoId() {
        return tipoConsumoFaturadoId;
    }

    public String getAnormalidade() {
        return anormalidade;
    }

    public LocalDate getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public String getTipoConsumoFaturadoDescricao() {
        return tipoConsumoFaturadoDescricao;
    }

    public String getAlteradoFlag() {
        return alteradoFlag;
    }

    private void setCdAnormalidade(int codigo){
        if(codigo == 1){
            this.anormalidade = "01";
        }

        if(codigo == 2){
            this.anormalidade = "02";
        }

        if(codigo == 10){
            this.anormalidade = "10";
        }
    }
}
