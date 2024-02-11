package moduloFaturamento.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.tarifaMedia.TarifaMediaPesquisaDTO;
import moduloFaturamento.dto.tarifaMedia.projection.TarifaMediaProjectionDTO;
import moduloFaturamento.dto.tarifaMedia.projection.TarifaMediaRegionalProjectionDTO;

public interface TarifaMediaService {

    GenericoWrapperDTO<List<TarifaMediaProjectionDTO>> buscarListaTarifaMedia(TarifaMediaPesquisaDTO pesquisaDTO, Pageable pageable);

    List<TarifaMediaRegionalProjectionDTO> buscarDropDwonRegionais();
    
}
