package moduloFaturamento.service.impl;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroTipoDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.mapper.TipoIncentivoClienteMapper;
import moduloFaturamento.model.TipoIncentivoCliente;
import moduloFaturamento.repository.TipoIncentivoClienteRepository;
import moduloFaturamento.service.TipoIncentivoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipoIncentivoClienteServiceImpl implements TipoIncentivoClienteService {

    @Autowired
    private TipoIncentivoClienteRepository tipoIncentivoClienteRepository;
    @Autowired
    private TipoIncentivoClienteMapper tipoIncentivoClienteMapper;


    @Override
    public List<IncentivoClienteParametroTipoDTO> listarTodosTipoIncentivoCliente() {
        List<TipoIncentivoCliente> lista = this.tipoIncentivoClienteRepository.findAll();
        return this.tipoIncentivoClienteMapper.toDto(lista);
    }
}