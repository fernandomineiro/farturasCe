package moduloFaturamento.dto.faturaImovel;

import java.time.LocalDate;

public class BuscaValorServicoFilterDTO {
    private Short cdServico;
    private Integer volume;
    private Integer matricula;
    private String referencia;

    public Short getCdServico() {
        return cdServico;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public void setCdServico(Short cdServico) {
        this.cdServico = cdServico;
    }
}
