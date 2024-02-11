package moduloFaturamento.dto.leitura;

public class ListarLeituraDTO {

    private Integer serRota;

    private Integer matriculaImovel;

    private Integer dv;

    private Integer leituraAnterior;

    public Integer getSerRota() {
        return serRota;
    }

    public void setSerRota(Integer serRota) {
        this.serRota = serRota;
    }

    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    public Integer getDv() {
        return dv;
    }

    public void setDv(Integer dv) {
        this.dv = dv;
    }

    public Integer getLeituraAnterior() {
        return leituraAnterior;
    }

    public void setLeituraAnterior(Integer leituraAnterior) {
        this.leituraAnterior = leituraAnterior;
    }

    public ListarLeituraDTO(Integer serRota, Integer matriculaImovel, Integer dv, Integer leituraAnterior) {
        this.serRota = serRota;
        this.matriculaImovel = matriculaImovel;
        this.dv = dv;
        this.leituraAnterior = leituraAnterior;
    }
    
}
