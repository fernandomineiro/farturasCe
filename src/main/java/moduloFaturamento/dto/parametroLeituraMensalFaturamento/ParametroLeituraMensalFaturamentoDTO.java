package moduloFaturamento.dto.parametroLeituraMensalFaturamento;

import java.time.LocalDateTime;

public class ParametroLeituraMensalFaturamentoDTO {

    private Integer id;
    private Short tipoGeracao;
    private LocalDateTime horario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getTipoGeracao() {
        return tipoGeracao;
    }

    public void setTipoGeracao(Short tipoGeracao) {
        this.tipoGeracao = tipoGeracao;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
}
