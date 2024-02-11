package moduloFaturamento.dto.parcelamentoFatura;

import java.math.BigDecimal;
import java.util.List;

public class ParcelamentoFaturaContasEmAbertoDoClienteDTO {

    private BigDecimal somatorioValorFatura;
    private BigDecimal somatorioValorJuros;
    private BigDecimal somatorioJurosMora;

    private BigDecimal somarioDescontosAutomaticosFatura;
    private BigDecimal somatorioDescontosAutomaticosJuros;
    private BigDecimal somatorioDescontosAutomaticosJurosMora;

    List<ParcelamentoFaturaListaFaturaEmAbertoDTO> listaDeFaturaEmAberto;

    public BigDecimal getSomatorioValorFatura() {
        return somatorioValorFatura;
    }

    public void setSomatorioValorFatura(BigDecimal somatorioValorFatura) {
        this.somatorioValorFatura = somatorioValorFatura;
    }

    public BigDecimal getSomatorioValorJuros() {
        return somatorioValorJuros;
    }

    public void setSomatorioValorJuros(BigDecimal somatorioValorJuros) {
        this.somatorioValorJuros = somatorioValorJuros;
    }

    public BigDecimal getSomatorioJurosMora() {
        return somatorioJurosMora;
    }

    public void setSomatorioJurosMora(BigDecimal somatorioJurosMora) {
        this.somatorioJurosMora = somatorioJurosMora;
    }


    public BigDecimal getSomarioDescontosAutomaticosFatura() {
        return somarioDescontosAutomaticosFatura;
    }

    public void setSomarioDescontosAutomaticosFatura(BigDecimal somarioDescontosAutomaticosFatura) {
        this.somarioDescontosAutomaticosFatura = somarioDescontosAutomaticosFatura;
    }

    public BigDecimal getSomatorioDescontosAutomaticosJuros() {
        return somatorioDescontosAutomaticosJuros;
    }

    public void setSomatorioDescontosAutomaticosJuros(BigDecimal somatorioDescontosAutomaticosJuros) {
        this.somatorioDescontosAutomaticosJuros = somatorioDescontosAutomaticosJuros;
    }

    public BigDecimal getSomatorioDescontosAutomaticosJurosMora() {
        return somatorioDescontosAutomaticosJurosMora;
    }

    public void setSomatorioDescontosAutomaticosJurosMora(BigDecimal somatorioDescontosAutomaticosJurosMora) {
        this.somatorioDescontosAutomaticosJurosMora = somatorioDescontosAutomaticosJurosMora;
    }

    public List<ParcelamentoFaturaListaFaturaEmAbertoDTO> getListaDeFaturaEmAberto() {
        return listaDeFaturaEmAberto;
    }

    public void setListaDeFaturaEmAberto(List<ParcelamentoFaturaListaFaturaEmAbertoDTO> listaDeFaturaEmAberto) {
        this.listaDeFaturaEmAberto = listaDeFaturaEmAberto;
    }


}
