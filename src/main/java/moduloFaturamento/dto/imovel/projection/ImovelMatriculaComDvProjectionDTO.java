package moduloFaturamento.dto.imovel.projection;

public class ImovelMatriculaComDvProjectionDTO {
    private Integer matricula;
    private Short dv;

    public ImovelMatriculaComDvProjectionDTO() {
    }

    public ImovelMatriculaComDvProjectionDTO(Integer matricula, Short dv) {
        this.matricula = matricula;
        this.dv = dv;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public Short getDv() {
        return dv;
    }
}
