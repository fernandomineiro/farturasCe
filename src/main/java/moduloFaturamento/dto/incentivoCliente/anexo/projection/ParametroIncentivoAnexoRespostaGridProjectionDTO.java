package moduloFaturamento.dto.incentivoCliente.anexo.projection;

import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.customAnnotation.JsonCesanNotSerializable;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class ParametroIncentivoAnexoRespostaGridProjectionDTO {

    private Long id;
    private Integer idParametroIncentivo;
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


    public ParametroIncentivoAnexoRespostaGridProjectionDTO(Long id, Integer idParametroIncentivo, LocalDateTime dataHoraInclusao, String descricao,
                                                            String usuario, String nomeArquivo, int tamanhoArquivo, byte[] data) {
        super();
        this.id = id;
        this.idParametroIncentivo = idParametroIncentivo;
        this.dataHoraInclusao = dataHoraInclusao;
        this.descricao = descricao;
        this.usuario = usuario;
        this.nomeArquivo = nomeArquivo;
        this.tamanhoArquivo = tamanhoArquivo;
        this.data = Base64.encodeBase64String(data);
    }


    public ParametroIncentivoAnexoRespostaGridProjectionDTO(Integer tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public ParametroIncentivoAnexoRespostaGridProjectionDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public Integer getIdParametroIncentivo() {
        return idParametroIncentivo;
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
