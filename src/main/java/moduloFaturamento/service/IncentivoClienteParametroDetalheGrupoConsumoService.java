package moduloFaturamento.service;

import moduloFaturamento.dto.incentivoCliente.IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteGrupoConsumo.projection.IncentivoClienteGrupoConsumoRespostaProjectionDTO;
import moduloFaturamento.model.IncentivoClienteDetalhe;

import java.util.List;

public interface IncentivoClienteParametroDetalheGrupoConsumoService {

    List<IncentivoClienteGrupoConsumoRespostaProjectionDTO> buscarGrupoConsumoDoParametroDeIncentivoCliente(Integer idParametroDetalhe);
    List<IncentivoClienteGrupoConsumoRespostaProjectionDTO> listarTodosGrupoConsumo();
    List<IncentivoClienteGrupoConsumoRespostaProjectionDTO> listarGrupoConsumoAssociadoAoIncentivo(Integer idParametroDetalhe);
    List<IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO> executarFluxoCadastrarGrupoConsumoDoDetalheDoParametroIncentivo(List<Integer> listaGrupoConsumo,
                                                                                                                                   IncentivoClienteDetalhe codigoParametroDetalhe, String token);
    List<IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO> executarFluxoEditarGrupoConsumoDoDetalheDoParametroIncentivo(List<Integer> listaGrupoConsumo,
                                                                                                                                 IncentivoClienteDetalhe incentivoClienteDetalhe, String token);

}
