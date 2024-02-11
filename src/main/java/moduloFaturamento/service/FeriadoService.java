package moduloFaturamento.service;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.feriado.*;
import moduloFaturamento.model.Feriado;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeriadoService {
    FeriadoRespostaConsultaDTO buscarFeriadoPorId(Integer id);
    GenericoWrapperDTO<List<FeriadoRespostaGridDTO>> buscarFeriadosPorFiltro(FeriadoFilterDTO filter, Pageable pageable);
    FeriadoRespostaCadastrarDTO executarFluxoSalvar(FeriadoCadastrarDTO feriadoCadastrarDTO, String token);
    FeriadoRespostaAtualizarDTO executarFluxoAtualizar(FeriadoAtualizarDTO feriadoAtualizarDTO, String token);
    void executarFluxoDeletar(Integer id, String token);
    Feriado retornarEntidadeFeriadoExistente(Integer id);
}
