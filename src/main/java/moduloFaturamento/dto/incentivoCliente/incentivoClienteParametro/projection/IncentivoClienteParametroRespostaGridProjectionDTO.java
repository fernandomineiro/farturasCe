package moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.projection;

import java.time.LocalDateTime;
import java.util.List;

public class IncentivoClienteParametroRespostaGridProjectionDTO {

    final private Integer id;
    final private String nomeDeliberacao;
    final private LocalDateTime dataInicioVigencia;
    final private LocalDateTime dataFimVigencia;
    final private String tipoIncentivo;

    public IncentivoClienteParametroRespostaGridProjectionDTO(Integer id, String nomeDeliberacao, LocalDateTime dataInicioVigencia, LocalDateTime dataFimVigencia, String tipoIncentivo) {
        this.id = id;
        this.nomeDeliberacao = (nomeDeliberacao != null ? nomeDeliberacao.trim() : null);
        this.dataInicioVigencia = dataInicioVigencia;
        this.dataFimVigencia = dataFimVigencia;
        this.tipoIncentivo = tipoIncentivo;
    }

    public Integer getId() {
        return id;
    }

    public String getNomeDeliberacao() {
        return nomeDeliberacao;
    }

    public LocalDateTime getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public LocalDateTime getDataFimVigencia() {
        return dataFimVigencia;
    }

    public String getTipoIncentivo() {
        return tipoIncentivo;
    }
}
