package moduloFaturamento.dto.faturaAvulsa;

public class FaturaAvulsaValidarSSFilterDTO {

    private Integer refAtendimento;
    private Integer cdAtendimento;
    private Short seqSS;
    
    public FaturaAvulsaValidarSSFilterDTO() {
    }

    public FaturaAvulsaValidarSSFilterDTO(Integer refAtendimento, Integer cdAtendimento, Short seqSS) {
        this.refAtendimento = refAtendimento;
        this.cdAtendimento = cdAtendimento;
        this.seqSS = seqSS;
    }

    public Integer getRefAtendimento() {
        return refAtendimento;
    }

    public void setRefAtendimento(Integer refAtendimento) {
        this.refAtendimento = refAtendimento;
    }

    public Integer getCdAtendimento() {
        return cdAtendimento;
    }

    public void setCdAtendimento(Integer cdAtendimento) {
        this.cdAtendimento = cdAtendimento;
    }

    public Short getSeqSS() {
        return seqSS;
    }

    public void setSeqSS(Short seqSS) {
        this.seqSS = seqSS;
    }
    
    
}
