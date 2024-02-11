package moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro;

import java.time.LocalDateTime;

public class IncentivoClienteParametroRespostaConsultaDTO {

    private Integer id;
    private String descricao;
    private LocalDateTime dataInicioVigencia;
    private LocalDateTime dataFimVigencia;
    private Integer idTipoIncentivo;
    private LocalDateTime dataRegistro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public void setDataInicioVigencia(LocalDateTime dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public LocalDateTime getDataFimVigencia() {
        return dataFimVigencia;
    }

    public void setDataFimVigencia(LocalDateTime dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Integer getIdTipoIncentivo() {
        return idTipoIncentivo;
    }

    public void setIdTipoIncentivo(Integer idTipoIncentivo) {
        this.idTipoIncentivo = idTipoIncentivo;
    }
}
