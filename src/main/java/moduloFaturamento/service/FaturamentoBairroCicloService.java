package moduloFaturamento.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.bairroCiclo.LocalidadeNomeBairroCicloFilterDTO;
import moduloFaturamento.dto.bairroCiclo.CicloBairroRespostaDTO;
import moduloFaturamento.dto.bairroCiclo.TarifaLocalidadeRespostaDTO;
import moduloFaturamento.dto.bairroCiclo.projection.LocalidadeIdNomeProjectionDTO;

public interface FaturamentoBairroCicloService {

    List<TarifaLocalidadeRespostaDTO> buscarListaDasRegioes();

    GenericoWrapperDTO<List<CicloBairroRespostaDTO>> pesquisarBairrosPorCiclo(LocalidadeNomeBairroCicloFilterDTO localidadeFilterDTO, Pageable pageable);

    List<Short> buscarCiclosDaLocalidade(Short cdCidade);

    List<LocalidadeIdNomeProjectionDTO> buscarBairrosDaLocalidade(Short cdCidade);

}
