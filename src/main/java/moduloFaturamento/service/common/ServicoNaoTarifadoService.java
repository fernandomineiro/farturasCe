package moduloFaturamento.service.common;

import java.math.BigDecimal;
import java.util.List;

public interface ServicoNaoTarifadoService {
    List<BigDecimal> buscarListaValorServicoNaoTarifadoPorCodigoServicoELocalidadade(Short cdServico, Short cdLocalidade);
}
