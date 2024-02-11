package moduloFaturamento.dto.leituraAnexo.projection;

import java.time.LocalDateTime;

import javax.persistence.Column;

import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.customAnnotation.JsonCesanNotSerializable;

public class LeituraAnexoRespostaGridProjectionDTO {

    private Long id;
    private Long idLeitura;
    @JsonCesanNotSerializable
    private LocalDateTime dataHoraInclusao;
    @Column(length=150)
    private String descricao;
    @Column(length=60)
    private String usuario;
    @Column(length=60)
    private String nomeArquivo;
    private int tamanhoArquivo;
    @JsonCesanNotSerializable
    private Integer totalArquivo;
    private String data;




    public LeituraAnexoRespostaGridProjectionDTO(Long id, Long idLeitura, LocalDateTime dataHoraInclusao, String descricao, String usuario, String nomeArquivo, int tamanhoArquivo) {
        super();
        this.id = id;
        this.idLeitura = idLeitura;
        this.dataHoraInclusao = dataHoraInclusao;
        this.descricao = descricao;
        this.usuario = usuario;
        this.nomeArquivo = nomeArquivo;
        this.tamanhoArquivo = tamanhoArquivo;
    }


    public LeituraAnexoRespostaGridProjectionDTO(Integer tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public LeituraAnexoRespostaGridProjectionDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public Long getIdLeitura() {
        return idLeitura;
    }

    public LocalDateTime getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public int getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public Integer getTotalArquivo() {
        return totalArquivo;
    }

    public String getData() {
        return data;
    }

    public String toJson() {
        return ConvertObjectToJsonCesan.execute(this);
    }
}
