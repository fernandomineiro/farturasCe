package moduloFaturamento.service;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroTipoDTO;

import java.util.List;

public interface TipoIncentivoClienteService {

    List<IncentivoClienteParametroTipoDTO> listarTodosTipoIncentivoCliente();
}
