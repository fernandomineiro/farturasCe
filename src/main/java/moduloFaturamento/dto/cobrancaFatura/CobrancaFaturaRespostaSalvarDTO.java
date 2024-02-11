package moduloFaturamento.dto.cobrancaFatura;

public class CobrancaFaturaRespostaSalvarDTO {
    private Long id;

    public CobrancaFaturaRespostaSalvarDTO() {
    }

    public CobrancaFaturaRespostaSalvarDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
