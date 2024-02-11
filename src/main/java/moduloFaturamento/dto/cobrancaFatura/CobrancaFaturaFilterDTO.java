package moduloFaturamento.dto.cobrancaFatura;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CobrancaFaturaFilterDTO {
    @NotNull
    private Integer matriculaImovel;
    @NotNull
    private Short dvMatricula;
    private Short codigoServico;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate referenciaFaturamento;

    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    public Short getDvMatricula() {
        return dvMatricula;
    }

    public void setDvMatricula(Short dvMatricula) {
        this.dvMatricula = dvMatricula;
    }

    public Short getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(Short codigoServico) {
        this.codigoServico = codigoServico;
    }

    public LocalDate getReferenciaFaturamento() {
        return referenciaFaturamento;
    }

    public void setReferenciaFaturamento(LocalDate referenciaFaturamento) {
        this.referenciaFaturamento = referenciaFaturamento;
    }
}
