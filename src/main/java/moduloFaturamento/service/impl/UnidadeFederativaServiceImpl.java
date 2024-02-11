package moduloFaturamento.service.impl;

import moduloFaturamento.dto.UnidadeFederativaDTO;
import moduloFaturamento.mapper.UnidadeFederativaMapper;
import moduloFaturamento.model.TipoUnidadeFederativa;
import moduloFaturamento.repository.UnidadeFederativaRepository;
import moduloFaturamento.service.UnidadeFederativaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeFederativaServiceImpl implements UnidadeFederativaService {
    @Autowired
    private UnidadeFederativaRepository unidadeFederativaRepository;
    @Autowired
    private UnidadeFederativaMapper unidadeFederativaMapper;

    @Override
    public List<UnidadeFederativaDTO> listarUnidadeFederativa() { List<TipoUnidadeFederativa> unidadeFederativaList = this.unidadeFederativaRepository.findAll();
       return this.unidadeFederativaMapper.toDto(unidadeFederativaList);
    }
}
