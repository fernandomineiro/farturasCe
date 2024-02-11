package moduloFaturamento.service.impl;

import moduloFaturamento.dto.TipoFeriadoDTO;
import moduloFaturamento.mapper.TipoFeriadoMapper;
import moduloFaturamento.model.TipoFeriado;
import moduloFaturamento.repository.TipoFeriadoRepository;
import moduloFaturamento.service.TipoFeriadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoFeriadoServiceImpl implements TipoFeriadoService {
    @Autowired
    private TipoFeriadoRepository tipoFeriadoRepository;
    @Autowired
    private TipoFeriadoMapper tipoFeriadoMapper;
    @Override
    public List<TipoFeriadoDTO> buscarTodosTipoFeriado() {
        List<TipoFeriado> tipoFeriadoList =  this.tipoFeriadoRepository.findAll();
        return this.tipoFeriadoMapper.toDto(tipoFeriadoList);
    }
}
