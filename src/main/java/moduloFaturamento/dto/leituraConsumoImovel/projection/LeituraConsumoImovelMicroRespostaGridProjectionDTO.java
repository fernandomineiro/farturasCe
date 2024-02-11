package moduloFaturamento.dto.leituraConsumoImovel.projection;

public class LeituraConsumoImovelMicroRespostaGridProjectionDTO {

    private Long id;
    private Integer matricula;
    private Short dv;
    private Integer consumoMedido;

    public LeituraConsumoImovelMicroRespostaGridProjectionDTO() {
    }

    public LeituraConsumoImovelMicroRespostaGridProjectionDTO(Long id, Integer matricula, Short dv, Integer consumoMedido) {
        this.id = id;
        this.matricula = matricula;
        this.dv = dv;
        this.consumoMedido = consumoMedido;
    }

    public Long getId() {
        return id;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public Short getDv() {
        return dv;
    }

    public Integer getConsumoMedido() {
        return consumoMedido;
    }
}
