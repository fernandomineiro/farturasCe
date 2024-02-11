package moduloFaturamento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import moduloFaturamento.comparator.TarifaMediaComparador;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.tarifaMedia.TarifaMediaPesquisaDTO;
import moduloFaturamento.dto.tarifaMedia.projection.TarifaMediaProjectionDTO;
import moduloFaturamento.dto.tarifaMedia.projection.TarifaMediaRegionalProjectionDTO;
import moduloFaturamento.repository.RegionalRepository;
import moduloFaturamento.repository.TarifaMediaRepository;
import moduloFaturamento.service.TarifaMediaService;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.tarifaMedia.TarifaMediaPesquisaValidacao;

@Service
public class TarifaMediaServiceImpl implements TarifaMediaService{

    @Autowired
    private TarifaMediaPesquisaValidacao tarifaMediaPesquisaValidacao;
    @Autowired
    private TarifaMediaRepository repository;
    @Autowired
    private RegionalRepository regionalRepository;

    @Override
    public GenericoWrapperDTO<List<TarifaMediaProjectionDTO>> buscarListaTarifaMedia(TarifaMediaPesquisaDTO pesquisaDTO, Pageable pageable) {
        
        tarifaMediaPesquisaValidacao.gerenciarValidacaoPesquisaTarifaMedia(pesquisaDTO.getRefTarifaMedia());

        Integer dataEmInteger = ConverterData.localDateEmIntegerReferenciaFormatoDB(pesquisaDTO.getRefTarifaMedia());

        List<TarifaMediaProjectionDTO> listaTarifaMedia = repository.buscarPesquisaTarifaMedia(pesquisaDTO.getCdRegional(), dataEmInteger, pesquisaDTO.getIdGrupoConsumo() );

        return Paginacao.paginarCampoUnico(new TarifaMediaComparador(), pageable, listaTarifaMedia);
    }

    @Override
    public List<TarifaMediaRegionalProjectionDTO> buscarDropDwonRegionais() {

        return regionalRepository.buscarListaRegionais();
    }
    
}
