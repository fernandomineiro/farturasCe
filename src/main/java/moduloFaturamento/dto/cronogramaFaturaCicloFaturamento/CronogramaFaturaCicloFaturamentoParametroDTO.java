package moduloFaturamento.dto.cronogramaFaturaCicloFaturamento;

import java.time.LocalDateTime;

public class CronogramaFaturaCicloFaturamentoParametroDTO {

    private final Integer id;
    private final Boolean geracaoAutomatica;
    private final LocalDateTime horario;

    public CronogramaFaturaCicloFaturamentoParametroDTO(Integer id, Short tipoGeracao, LocalDateTime horario) {
        this.id = id;
        this.geracaoAutomatica = tipoGeracao == 2;
        this.horario = horario;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getGeracaoAutomatica() {
        return geracaoAutomatica;
    }

    public LocalDateTime getHorario() {
        return horario;
    }
}
