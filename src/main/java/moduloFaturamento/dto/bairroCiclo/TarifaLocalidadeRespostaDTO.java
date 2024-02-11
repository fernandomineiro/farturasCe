package moduloFaturamento.dto.bairroCiclo;

public class TarifaLocalidadeRespostaDTO {

    private short cdTarifa;
    private String dcCidade;

    public short getCdCidade() {
        return cdTarifa;
    }
    public void setCdCidade(short cdCidade) {
        this.cdTarifa = cdCidade;
    }
    public String getDcCidade() {
        return dcCidade;
    }
    public void setDcCidade(String dcCidade) {
        this.dcCidade = dcCidade;
    }
    public TarifaLocalidadeRespostaDTO(short cdCidade, String dcCidade) {
        this.cdTarifa = cdCidade;
        this.dcCidade = dcCidade;
    }
   
    
}
