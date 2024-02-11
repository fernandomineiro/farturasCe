package moduloFaturamento.service.impl.common;

import moduloFaturamento.repository.common.ServicoNaoTarifadoMicroRepository;
import moduloFaturamento.service.common.ServicoNaoTarifadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ServicoNaoTarifadoServiceImpl implements ServicoNaoTarifadoService {

    @Autowired
    private ServicoNaoTarifadoMicroRepository servicoNaoTarifadoMicroRepository;

    @Override
    public List<BigDecimal> buscarListaValorServicoNaoTarifadoPorCodigoServicoELocalidadade(Short cdServico, Short cdLocalidade){
        return this.servicoNaoTarifadoMicroRepository.buscarValorServicosNaoTarifadoPorCodigoServicoELocalidade(cdServico, cdLocalidade);
    }
} 
