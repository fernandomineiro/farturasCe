package moduloFaturamento.dto.cronogramaFatura;

public class CronogramaFaturaSalvarRespostaDTO {

    private Long id;

    public CronogramaFaturaSalvarRespostaDTO() {
    }

    public CronogramaFaturaSalvarRespostaDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
