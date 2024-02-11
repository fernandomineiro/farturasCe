package moduloFaturamento.dto.incentivoCliente;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroRespostaCadastroDetalheDTO;

import java.util.List;

public class IncentivoClienteRespostaEstruturaCadastroDTO {
    private final Integer codigo;
    private final Long idAnexo;
    private final List<IncentivoClienteParametroRespostaCadastroDetalheDTO> parametroDesconto;

    public IncentivoClienteRespostaEstruturaCadastroDTO(Integer codigo, Long idAnexo, List<IncentivoClienteParametroRespostaCadastroDetalheDTO> parametroDesconto) {
        this.codigo = codigo;
        this.idAnexo = idAnexo;
        this.parametroDesconto = parametroDesconto;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public List<IncentivoClienteParametroRespostaCadastroDetalheDTO> getParametroDesconto() {
        return parametroDesconto;
    }

    public Long getIdAnexo() {
        return idAnexo;
    }
}
