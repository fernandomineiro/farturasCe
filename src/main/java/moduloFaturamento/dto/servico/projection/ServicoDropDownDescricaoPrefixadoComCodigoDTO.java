package moduloFaturamento.dto.servico.projection;

public class ServicoDropDownDescricaoPrefixadoComCodigoDTO {
    private Short cdServico;
    private String dcServico;

    public ServicoDropDownDescricaoPrefixadoComCodigoDTO() {
    }


    public ServicoDropDownDescricaoPrefixadoComCodigoDTO(Short cdServico, String dcServico) {
        this.cdServico = cdServico;
        this.dcServico = cdServico + " - " +dcServico.trim();
    }

    public Short getCdServico() {
        return cdServico;
    }

    public String getDcServico() {
        return dcServico;
    }
}
