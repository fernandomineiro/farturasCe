package moduloFaturamento.dto.leitura;

public class LeituraPesquisarDTO {

    private Integer seqRota;

    private Integer matricula;

    private short dv;

    private Integer leituraAnterior;

    public Integer getSeqRota() {
        return seqRota;
    }

    public void setSeqRota(Integer seqRota) {
        this.seqRota = seqRota;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public short getDv() {
        return dv;
    }

    public void setDv(short dv) {
        this.dv = dv;
    }

    public Integer getLeituraAnterior() {
        return leituraAnterior;
    }

    public void setLeituraAnterior(Integer leituraAnterior) {
        this.leituraAnterior = leituraAnterior;
    }

    public LeituraPesquisarDTO(Integer seqRota, Integer matricula, short dv,
            Integer leituraAnterior) {
        this.seqRota = seqRota;
        this.matricula = matricula;
        this.dv = dv;
        this.leituraAnterior = leituraAnterior;
    }
    
    
}
