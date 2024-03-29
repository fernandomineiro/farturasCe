package moduloFaturamento.service;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteAtualizarDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteCadastroDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteRespostaEstruturaAtualizarDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteRespostaEstruturaCadastroDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroRespostaConsultaDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.projection.IncentivoClienteParametroRespostaGridProjectionDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IncentivoClienteParametroService {

    GenericoWrapperDTO<List<IncentivoClienteParametroRespostaGridProjectionDTO>> listarGrid(Pageable pageable);
    IncentivoClienteParametroRespostaConsultaDTO buscarParametroIncentivoPorId(Integer idParametroIncentivoCliente);
    IncentivoClienteRespostaEstruturaCadastroDTO executarFluxoCadastrarIncentivoDetalhesEGrupoConsumoAnexo(IncentivoClienteCadastroDTO dto, String token);
    IncentivoClienteRespostaEstruturaAtualizarDTO executarFluxoEditarIncentivoDetalhesEGrupoConsumoAnexo(IncentivoClienteAtualizarDTO dto, String token);
    void executarFluxoRemoverIncentivo(Integer id, String token);
}