package moduloFaturamento.service.impl.common;

import moduloFaturamento.dto.ocorrenciaLeitura.TipoServicoOcorrenciaLeituraDTO;
import moduloFaturamento.mapper.TipoServicoOcorrenciaLeituraMapper;
import moduloFaturamento.model.common.TipoServicoOcorrenciaLeitura;
import moduloFaturamento.repository.common.TipoServicoOcorrenciaLeituraRepository;
import moduloFaturamento.service.common.TipoServicoOcorrenciaLeituraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipoServicoOcorrenciaLeituraServiceImpl implements TipoServicoOcorrenciaLeituraService {

    @Autowired
    private TipoServicoOcorrenciaLeituraRepository tipoServicoOcorrenciaLeituraRepository;
    @Autowired
    private TipoServicoOcorrenciaLeituraMapper tipoServicoOcorrenciaMapper;

    @Override
    public List<TipoServicoOcorrenciaLeituraDTO> buscarTodosTipoOcorrenciaLeitura() {
        List<TipoServicoOcorrenciaLeitura> tipoOcorrenciaLeituraList = this.tipoServicoOcorrenciaLeituraRepository.findAll();
        return this.tipoServicoOcorrenciaMapper.toDto(tipoOcorrenciaLeituraList);
    }
}
