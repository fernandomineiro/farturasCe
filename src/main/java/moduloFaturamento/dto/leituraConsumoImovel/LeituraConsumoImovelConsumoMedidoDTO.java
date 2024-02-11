package moduloFaturamento.dto.leituraConsumoImovel;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LeituraConsumoImovelConsumoMedidoDTO {

    @NotNull
    private Integer matriculaImovel;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull private
    LocalDate refFatura;
    @NotNull
    private Integer leituraAtual;
    @NotNull
    private BigDecimal mediaDiariaLeitura;

    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    public LocalDate getRefFatura() {
        return refFatura;
    }

    public void setRefFatura(LocalDate refFatura) {
        this.refFatura = refFatura;
    }

    public Integer getLeituraAtual() {
        return leituraAtual;
    }

    public void setLeituraAtual(Integer leituraAtual) {
        this.leituraAtual = leituraAtual;
    }

    public BigDecimal getMediaDiariaLeitura() {
        return mediaDiariaLeitura;
    }

    public void setMediaDiariaLeitura(BigDecimal mediaDiariaLeitura) {
        this.mediaDiariaLeitura = mediaDiariaLeitura;
    }
}
