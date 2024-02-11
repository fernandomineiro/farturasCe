package moduloFaturamento.service.common;

import moduloFaturamento.dto.servico.projection.ServicoDropDownDescricaoPrefixadoComCodigoDTO;

import java.util.List;

public interface ServicoService {
    List<ServicoDropDownDescricaoPrefixadoComCodigoDTO> buscarServicosParaDropDownCadastroCobrancaEmFatura(Integer matriculaImovel, Short dv);

    List<ServicoDropDownDescricaoPrefixadoComCodigoDTO> buscarServicosParaDropDownEdicaoCobrancaEmFatura(Integer matriculaImovel,  Short dv);
}
