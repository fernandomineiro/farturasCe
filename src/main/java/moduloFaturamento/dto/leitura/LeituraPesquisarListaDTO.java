package moduloFaturamento.dto.leitura;

public class LeituraPesquisarListaDTO {

    private Integer idCidade;
    private short ciclo;
    private Integer referencia;
    
    public Integer getIdCidade() {
        return idCidade;
    }
    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }
    public short getCiclo() {
        return ciclo;
    }
    public void setCiclo(short ciclo) {
        this.ciclo = ciclo;
    }
    public Integer getReferencia() {
        return referencia;
    }
    public void setReferencia(Integer referencia) {
        this.referencia = referencia;
    }
   
}
