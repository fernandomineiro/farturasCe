package moduloFaturamento.service;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.imovel.projection.ImovelMatriculaComDvProjectionDTO;
import moduloFaturamento.dto.leituraConsumoImovel.*;
import moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelCabecalhoDTO;
import moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelMicroRespostaGridProjectionDTO;
import moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelRespostaGridProjectionDTO;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface LeituraConsumoImovelService {
    List<LeituraConsumoImovelTipoMotivoEdicaoDTO> buscarListaMotivoEdicaoLeitura();
    List<ImovelMatriculaComDvProjectionDTO> buscarListaMatriculasMicroDeUmaMatriculaMacro(Integer matricula);
    LeituraConsumoImovelCabecalhoDTO buscarCabecalhoDadosDoImovel(Integer matricula, Short dv);
    LeituraConsumoImovelFaixaConsumoAnaliseDTO buscarValorMaximoEMinimoFaixaConsumoParaAnalise(BigDecimal mediaDiariaDaLeitura);
    LeituraConsumoImovelRespostaConsumoMedidoDTO buscarValorConsumoMedido(LeituraConsumoImovelConsumoMedidoDTO dto);
    Integer buscarConsumoMedidoMatriculaMacro(Integer matricula, LocalDate refFatura);
    Short buscarNovoTipoConsumoFaturado(Integer matricula, LocalDate refFatura, Short idTipoConsumoFaturado, LeituraConsumoImovelTipoConsumoFaturadoFilterDTO dto);
    BigDecimal buscarValorConsumoFaturado(Integer matricula, LocalDate refFatura, Short idTipoConsumoFaturado, LeituraConsumoImovelConsumoFaturadoFilterDTO dto);
    BigDecimal buscarConsumoFaturadoEsgotoParaMatriculaPrincipal(Integer matricula, LocalDate refFatura, BigDecimal consumoFaturado);
    Short buscarValorDiasDeVenda(Integer matricula, LocalDate dataLeitura, LocalDate refFatura);
    Short buscarValorDiasDeConsumo(Integer matricula, LocalDate dataLeitura, LocalDate refFatura);
    BigDecimal buscarMediaDiaria(Integer matricula, LocalDate dataReferencia);
    LeituraConsumoImovelRespostaConsultaDTO buscarLeituraConsumoImovelPorId(Long id);
    GenericoWrapperDTO<List<LeituraConsumoImovelMicroRespostaGridProjectionDTO>> buscarLeiturasSomenteComMatriculaMicroPorFiltro(Integer matriculaMacro, LocalDate refFatura, Pageable pageable);
    GenericoWrapperDTO<List<LeituraConsumoImovelRespostaGridProjectionDTO>> buscarLeiturasPorFiltro(Integer matricula, LocalDate refFatura, Pageable pageable);
    LeituraConsumoImovelGravadoRespostaIdentidadeDTO executarFluxoEditarLeitura(LeituraConsumoImovelAtualizarDTO dto, String token);

}
