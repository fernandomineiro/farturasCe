package moduloFaturamento.dto.incentivoCliente;

import java.util.List;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroRespostaAtualizarDetalheDTO;

public class IncentivoClienteRespostaEstruturaAtualizarDTO {
    private final Integer codigo;
    private final Long idAnexo;
    private final List<IncentivoClienteParametroRespostaAtualizarDetalheDTO> parametroDesconto;

    public IncentivoClienteRespostaEstruturaAtualizarDTO(Integer codigo, Long idAnexo, List<IncentivoClienteParametroRespostaAtualizarDetalheDTO> parametroDesconto) {
        this.codigo = codigo;
        this.idAnexo = idAnexo;
        this.parametroDesconto = parametroDesconto;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public List<IncentivoClienteParametroRespostaAtualizarDetalheDTO> getParametroDesconto() {
        return parametroDesconto;
    }

    public Long getIdAnexo() {
        return idAnexo;
    }
}
