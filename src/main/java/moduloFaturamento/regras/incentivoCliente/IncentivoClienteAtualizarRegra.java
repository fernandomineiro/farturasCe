package moduloFaturamento.regras.incentivoCliente;

import moduloFaturamento.model.IncentivoCliente;
import org.springframework.stereotype.Service;

@Service
public class IncentivoClienteAtualizarRegra extends IncentivoClienteRegra {

    public void validar(IncentivoCliente entidade) {
        super.impedirExecucaoDoFluxoSeTodosOsTiposIncluindoNuloEstaCadastradoEmAlgumIncentivo(entidade.getDataInicioVigencia(), entidade.getDataFimVigencia(), entidade.getId());
        super.impedirExecucaoDoFluxoTipoInformadoJaEstaCadastradoEmAlgumIncentivo(entidade.getDataInicioVigencia(), entidade.getDataFimVigencia(), entidade.getTipoIncentivoCliente().getId(), entidade.getId());
    }
}
