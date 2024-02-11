package moduloFaturamento.dto.parcelamentoFatura;

public class ParcelamentoDaFaturaAdutoriaDaFaturaDTO {

    private Integer referenciaDaFatura;
    private Short origemDaFatura;
    
    public void setOrigemDaFatura(Short origemDaFatura) {
        this.origemDaFatura = origemDaFatura;
    }

    public ParcelamentoDaFaturaAdutoriaDaFaturaDTO(Integer referenciaDaFatura, Short origemDaFatura) {
        this.referenciaDaFatura = referenciaDaFatura;
        this.origemDaFatura = origemDaFatura;
    }
    public Integer getReferenciaDaFatura() {
        return referenciaDaFatura;
    }
    public void setReferenciaDaFatura(Integer referenciaDaFatura) {
        this.referenciaDaFatura = referenciaDaFatura;
    }
    public Short getOrigemDaFatura() {
        return origemDaFatura;
    }
    public ParcelamentoDaFaturaAdutoriaDaFaturaDTO() {
    }
    @Override
    public String toString() {
        return "ParcelamentoDaFaturaAdutoriaDaFaturaProjectionDTO [origemDaFatura=" + origemDaFatura
                + ", referenciaDaFatura=" + referenciaDaFatura + "]";
    }  
        
}
