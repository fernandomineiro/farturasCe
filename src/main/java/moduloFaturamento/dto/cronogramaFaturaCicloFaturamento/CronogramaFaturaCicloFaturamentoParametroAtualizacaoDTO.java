package moduloFaturamento.dto.cronogramaFaturaCicloFaturamento;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CronogramaFaturaCicloFaturamentoParametroAtualizacaoDTO {

    @NotNull
    private Integer id;
    @NotNull
    private Boolean geracaoAutomatica;
    private LocalDateTime horario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getGeracaoAutomatica() {
        return geracaoAutomatica;
    }

    public void setGeracaoAutomatica(Boolean geracaoAutomatica) {
        this.geracaoAutomatica = geracaoAutomatica;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
}
