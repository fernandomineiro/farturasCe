package moduloFaturamento.service;

import java.util.List;

import moduloFaturamento.dto.leituraConsumoImovel.LeituraConsumoImovelSalvarAnexoDTO;
import moduloFaturamento.model.Leitura;

import org.springframework.data.domain.Pageable;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.leitura.LeituraAgrupadorListAtualizarDTO;
import moduloFaturamento.dto.leitura.LeituraMostraDataDTO;
import moduloFaturamento.dto.leitura.LeituraPesquisarDTO;
import moduloFaturamento.dto.leitura.LeituraPesquisarListaDTO;
import moduloFaturamento.dto.leitura.projection.LeituraCicloProjectionDTO;
import moduloFaturamento.dto.leitura.projection.LeituraOcorrenciasProjectionDTO;
import moduloFaturamento.dto.leitura.projection.PesquisarNomeEIdLocalidadeProjectionDTO;

public interface LeituraService {

    List<PesquisarNomeEIdLocalidadeProjectionDTO> buscarNomeDaLocalidade();

    List<LeituraCicloProjectionDTO> buscarNumerosCiclos();

    GenericoWrapperDTO<List<LeituraPesquisarDTO>> listarLeituraLocalidadeCicloReferencia(LeituraPesquisarListaDTO listaDTO, Pageable pageable);

    void atualizarLeituras(LeituraAgrupadorListAtualizarDTO listaDeLeituraDTO, String token);

    void executarFluxoSalvarAnexoConsumoImovel(LeituraConsumoImovelSalvarAnexoDTO dto, String token);

    LeituraMostraDataDTO buscarDataDaleitura(LeituraPesquisarListaDTO listaDTO);

   List<LeituraOcorrenciasProjectionDTO> buscarOcorrecnias();

   List<Leitura> buscarLeituraExistentePorMatricula(Integer matricula);

    
}
