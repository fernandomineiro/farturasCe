package moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.projection;

import java.math.BigDecimal;
import java.util.List;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteGrupoConsumo.projection.IncentivoClienteGrupoConsumoRespostaProjectionDTO;
import moduloFaturamento.dto.situacaoLigacaoAgua.SituacaoLigacaoAguaDTO;
import moduloFaturamento.dto.situacaoLigacaoEsgoto.SituacaoLigacaoEsgotoDTO;

public class IncentivoClienteParametroDetalheRespostaGridProjectionDTO {

    private final Integer id;
    private final String descricaoDesconto;
    private final BigDecimal percentualEntradaMinimo;
    private final BigDecimal percentualEntradaMaximo;
    private final Integer descontoMultas;
    private final Integer descontoJuros;
    private final Integer descontoValorPrincipal;
    private final Short numeroMaximoParcelas;
    private final Short numeroMinimoDebitos;
    private final BigDecimal valorMaximoDebitos;
    private final BigDecimal valorMinimoDebitos;
    private final BigDecimal valorMinimoParcela;
    private final Short numeroMinimoDiasDesconto;
    private final Short numeroMaximoParcelasAgencia;
    private final BigDecimal valorMaximoDebitoAgencia;
    private final Boolean correcaoMonetaria;
    private final BigDecimal valorCorrecaoMonetaria;
    private List<SituacaoLigacaoAguaDTO> listaSituacaoAgua;
    private List<SituacaoLigacaoEsgotoDTO> listaSituacaoEsgoto;
    private List<IncentivoClienteGrupoConsumoRespostaProjectionDTO> listaGrupoConsumo;

    public IncentivoClienteParametroDetalheRespostaGridProjectionDTO(Integer id, String descricaoDesconto, BigDecimal percentualEntradaMinimo, BigDecimal percentualEntradaMaximo, Integer descontoMultas,
                                                                     Integer descontoJuros, Integer descontoValorPrincipal, Short numeroMaximoParcelas, Short numeroMinimoDebitos, BigDecimal valorMinimoDebitos,
                                                                     BigDecimal valorMinimoParcela, Short numeroMinimoDiasDesconto, Short numeroMaximoParcelasAgencia, BigDecimal valorMaximoDebitoAgencia,
                                                                     Short correcaoMonetaria, BigDecimal valorCorrecaoMonetaria, BigDecimal valorMaximoDebitos) {
        this.id = id;
        this.descricaoDesconto = (descricaoDesconto != null ? descricaoDesconto.trim() : null);
        this.percentualEntradaMinimo = percentualEntradaMinimo;
        this.percentualEntradaMaximo = percentualEntradaMaximo;
        this.descontoMultas = descontoMultas;
        this.descontoJuros = descontoJuros;
        this.descontoValorPrincipal = descontoValorPrincipal;
        this.numeroMaximoParcelas = numeroMaximoParcelas;
        this.numeroMinimoDebitos = numeroMinimoDebitos;
        this.valorMinimoDebitos = valorMinimoDebitos;
        this.valorMinimoParcela = valorMinimoParcela;
        this.numeroMinimoDiasDesconto = numeroMinimoDiasDesconto;
        this.numeroMaximoParcelasAgencia = numeroMaximoParcelasAgencia;
        this.valorMaximoDebitoAgencia = valorMaximoDebitoAgencia;
        this.correcaoMonetaria = (correcaoMonetaria == 1);
        this.valorCorrecaoMonetaria = valorCorrecaoMonetaria;
        this.valorMaximoDebitos = valorMaximoDebitos;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricaoDesconto() {
        return descricaoDesconto;
    }

    public Integer getDescontoMultas() {
        return descontoMultas;
    }

    public Integer getDescontoJuros() {
        return descontoJuros;
    }




    public BigDecimal getPercentualEntradaMinimo() {
        return percentualEntradaMinimo;
    }

    public BigDecimal getPercentualEntradaMaximo() {
        return percentualEntradaMaximo;
    }

    public Integer getDescontoValorPrincipal() {
        return descontoValorPrincipal;
    }


    public Short getNumeroMaximoParcelas() {
        return numeroMaximoParcelas;
    }

    public Short getNumeroMinimoDebitos() {
        return numeroMinimoDebitos;
    }

    public BigDecimal getValorMinimoDebitos() {
        return valorMinimoDebitos;
    }

    public BigDecimal getValorMinimoParcela() {
        return valorMinimoParcela;
    }

    public Short getNumeroMinimoDiasDesconto() {
        return numeroMinimoDiasDesconto;
    }

    public Short getNumeroMaximoParcelasAgencia() {
        return numeroMaximoParcelasAgencia;
    }

    public BigDecimal getValorMaximoDebitoAgencia() {
        return valorMaximoDebitoAgencia;
    }

    public Boolean getCorrecaoMonetaria() {
        return correcaoMonetaria;
    }

    public BigDecimal getValorCorrecaoMonetaria() {
        return valorCorrecaoMonetaria;
    }

    public List<SituacaoLigacaoAguaDTO> getListaSituacaoAgua() {
        return listaSituacaoAgua;
    }

    public void setListaSituacaoAgua(List<SituacaoLigacaoAguaDTO> listaSituacaoAgua) {
        this.listaSituacaoAgua = listaSituacaoAgua;
    }

    public List<SituacaoLigacaoEsgotoDTO> getListaSituacaoEsgoto() {
        return listaSituacaoEsgoto;
    }

    public void setListaSituacaoEsgoto(List<SituacaoLigacaoEsgotoDTO> listaSituacaoEsgoto) {
        this.listaSituacaoEsgoto = listaSituacaoEsgoto;
    }

    public List<IncentivoClienteGrupoConsumoRespostaProjectionDTO> getListaGrupoConsumo() {
        return listaGrupoConsumo;
    }

    public void setListaGrupoConsumo(List<IncentivoClienteGrupoConsumoRespostaProjectionDTO> listaGrupoConsumo) {
        this.listaGrupoConsumo = listaGrupoConsumo;
    }

    public BigDecimal getValorMaximoDebitos() {
        return valorMaximoDebitos;
    }
}
