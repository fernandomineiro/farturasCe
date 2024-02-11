package moduloFaturamento.service;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.cobrancaFatura.*;
import moduloFaturamento.dto.cobrancaFatura.projection.CobrancaFaturaRespostaGridProjectionDTO;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CobrancaFaturaService {

    List<BigDecimal> buscarValoresCadastradosNoServicosNaoTarifados(Short cdServico, Integer matricula, Short dv);

    GenericoWrapperDTO<List<CobrancaFaturaRespostaGridProjectionDTO>> buscarCobrancaPorFiltro(CobrancaFaturaFilterDTO filter, Pageable pageable);

    CobrancaFaturaRespostaConsultaDTO buscarCobrancaPorId(Long id);

    CobrancaFaturaRespostaSalvarDTO executarFluxoCadastrarCobranca(CobrancaFaturaCadastrarDTO dto, String token);

    CobrancaFaturaRespostaSalvarDTO executarFluxoEditarCobranca(CobrancaFaturaAtualizarDTO dto, String token);

    void executarFluxoRemoverCobranca(Long id, String token);


}
