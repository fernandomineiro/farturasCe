package moduloFaturamento.regras.incentivoCliente.validacao;

import moduloFaturamento.repository.IncentivoClienteRepository;
import moduloFaturamento.repository.TipoIncentivoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

abstract class IncentivoClienteValidacaoRegra {

    @Autowired
    protected IncentivoClienteRepository incentivoClienteRepository;
    @Autowired
    protected TipoIncentivoClienteRepository tipoIncentivoClienteRepository;

}
