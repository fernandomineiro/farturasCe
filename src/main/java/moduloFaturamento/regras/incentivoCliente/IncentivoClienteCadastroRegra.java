package moduloFaturamento.regras.incentivoCliente;

import moduloFaturamento.model.IncentivoCliente;
import org.springframework.stereotype.Service;

@Service
public class IncentivoClienteCadastroRegra extends IncentivoClienteRegra {

    public void validar(IncentivoCliente entidade) {
        super.impedirExecucaoDoFluxoSeTodosOsTiposIncluindoNuloEstaCadastradoEmAlgumIncentivo(entidade.getDataInicioVigencia(), entidade.getDataFimVigencia());
        super.impedirExecucaoDoFluxoTipoInformadoJaEstaCadastradoEmAlgumIncentivo(entidade.getDataInicioVigencia(), entidade.getDataFimVigencia(), entidade.getTipoIncentivoCliente().getId());
    }
}
