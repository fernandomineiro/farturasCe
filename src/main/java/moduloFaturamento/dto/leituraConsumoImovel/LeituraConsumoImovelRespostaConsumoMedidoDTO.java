package moduloFaturamento.dto.leituraConsumoImovel;

public class LeituraConsumoImovelRespostaConsumoMedidoDTO {

    private Integer valor;
    private Boolean abrirPopupLeituraConsumoForaDaFaixaPermitida;
    private Boolean abrirPopupConsumoMedidoModificadoParaMatriculaMicro;

    public LeituraConsumoImovelRespostaConsumoMedidoDTO(Integer valor, Boolean abrirPopupLeituraConsumoForaDaFaixaPermitida, Boolean abrirPopupConsumoMedidoModificadoParaMatriculaMicro) {
        this.valor = valor;
        this.abrirPopupLeituraConsumoForaDaFaixaPermitida = abrirPopupLeituraConsumoForaDaFaixaPermitida;
        this.abrirPopupConsumoMedidoModificadoParaMatriculaMicro = abrirPopupConsumoMedidoModificadoParaMatriculaMicro;
    }

    public Integer getValor() {
        return valor;
    }

    public Boolean getAbrirPopupLeituraConsumoForaDaFaixaPermitida() {
        return abrirPopupLeituraConsumoForaDaFaixaPermitida;
    }

    public Boolean getAbrirPopupConsumoMedidoModificadoParaMatriculaMicro() {
        return abrirPopupConsumoMedidoModificadoParaMatriculaMicro;
    }
}
