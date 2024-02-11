package moduloFaturamento.service;

import java.util.List;
import org.springframework.core.io.Resource;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.bairroCiclo.TarifaLocalidadeRespostaDTO;
import moduloFaturamento.dto.tarifa.ParamametrosPesquisaTarifaFilterDTO;
import moduloFaturamento.dto.tarifa.PesquisaTarifaRespostaDTO;
import moduloFaturamento.dto.tarifa.TarifaDeletarDTO;

public interface TarifaService {

    GenericoWrapperDTO<List<PesquisaTarifaRespostaDTO>> pesquisarTarifas(ParamametrosPesquisaTarifaFilterDTO tarifaFilterDTO, Pageable pageable);

    List<TarifaLocalidadeRespostaDTO> buscarListaDasRegioes();

    String cadastrarNovasTarifas(MultipartFile file, String token);

    void deletarTarifaPorData(@Valid TarifaDeletarDTO tarifaDeletarDTO, String token);
    
    Resource downloadModeloXLS(String token);
    
}
