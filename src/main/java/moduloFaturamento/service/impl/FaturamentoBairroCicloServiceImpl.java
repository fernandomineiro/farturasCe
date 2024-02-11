package moduloFaturamento.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import moduloFaturamento.comparator.BairroCicloGridComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.bairroCiclo.LocalidadeNomeBairroCicloFilterDTO;
import moduloFaturamento.dto.bairroCiclo.CicloBairroRespostaDTO;
import moduloFaturamento.dto.bairroCiclo.TarifaLocalidadeRespostaDTO;
import moduloFaturamento.dto.bairroCiclo.projection.LocalidadeIdNomeProjectionDTO;
import moduloFaturamento.repository.common.LocalidadeRepository;
import moduloFaturamento.service.FaturamentoBairroCicloService;
import moduloFaturamento.util.Paginacao;

@Service
public class FaturamentoBairroCicloServiceImpl implements FaturamentoBairroCicloService {

    @Autowired
    private LocalidadeRepository repository;

    @Override
    public List<TarifaLocalidadeRespostaDTO> buscarListaDasRegioes() {
        return repository.findByDataHoraExclusaoIsNullOrderByDcCidade()
                        .stream()
                        .map(cidade -> new TarifaLocalidadeRespostaDTO(cidade.getCdCidade(), cidade.getDcCidade()))
                        .sorted(Comparator.comparing(TarifaLocalidadeRespostaDTO::getDcCidade))
                        .collect(Collectors.toList());
    }

    @Override
    public List<Short> buscarCiclosDaLocalidade(Short cdCidade) {
        return repository.buscarCiclosdaLocalidade(cdCidade);
    }

    @Override
    public List<LocalidadeIdNomeProjectionDTO> buscarBairrosDaLocalidade(Short cdCidade) {
        return repository.buscarBairrodaLocalidade(cdCidade);
    }

    @Override
    public GenericoWrapperDTO<List<CicloBairroRespostaDTO>> pesquisarBairrosPorCiclo(LocalidadeNomeBairroCicloFilterDTO localidadeFilterDTO, Pageable pageable) {

        List<CicloBairroRespostaDTO> listaDaPesquisa = repository.pesquisarLocalidadeEBairroECiclo(localidadeFilterDTO.getCdCidade(), localidadeFilterDTO.getCdBairro(), localidadeFilterDTO.getCiclo())
                                                    .stream()
                                                    .map(dados -> new CicloBairroRespostaDTO(dados.getId(), dados.getNome().replaceAll("\\s+\\z", ""), dados.getCiclo()))
                                                    .collect(Collectors.toList());

        return Paginacao.paginarCampoUnico(new BairroCicloGridComparator(), pageable, listaDaPesquisa);
    }



}
