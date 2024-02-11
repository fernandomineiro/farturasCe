package moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection;

public class CronogramaFaturaCicloImovelRespostaProjectionDTO {
    private final Short fase;
    private final Integer matricula;
    private final Short dv;

    public CronogramaFaturaCicloImovelRespostaProjectionDTO(Short fase, Integer matricula, Short dv) {
        this.fase = fase;
        this.matricula = matricula;
        this.dv = dv;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public Short getDv() {
        return dv;
    }

    public Short getFase() {
        return fase;
    }
}
