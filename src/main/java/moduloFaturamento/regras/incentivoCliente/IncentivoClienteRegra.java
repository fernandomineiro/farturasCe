package moduloFaturamento.regras.incentivoCliente;

import moduloFaturamento.regras.incentivoCliente.validacao.IncentivoClienteAtualizacaoConsultaValidacaoRegra;
import moduloFaturamento.regras.incentivoCliente.validacao.IncentivoClienteCadastroConsultaValidacaoRegra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
abstract class IncentivoClienteRegra {

    @Autowired
    private IncentivoClienteCadastroConsultaValidacaoRegra incentivoClienteCadastroConsultaValidacaoRegra;
    @Autowired
    private IncentivoClienteAtualizacaoConsultaValidacaoRegra incentivoClienteAtualizacaoConsultaValidacaoRegra;


    protected void impedirExecucaoDoFluxoSeTodosOsTiposIncluindoNuloEstaCadastradoEmAlgumIncentivo(LocalDateTime dataVigenteInicio, LocalDateTime dataVigenteFim){
        this.incentivoClienteCadastroConsultaValidacaoRegra.validar(dataVigenteInicio, dataVigenteFim);
    }

    protected void impedirExecucaoDoFluxoTipoInformadoJaEstaCadastradoEmAlgumIncentivo(LocalDateTime dataVigenteInicio, LocalDateTime dataVigenteFim, Integer idTipoIncentivo){
        this.incentivoClienteCadastroConsultaValidacaoRegra.validar(dataVigenteInicio, dataVigenteFim, idTipoIncentivo);
    }

    protected void impedirExecucaoDoFluxoSeTodosOsTiposIncluindoNuloEstaCadastradoEmAlgumIncentivo(LocalDateTime dataVigenteInicio, LocalDateTime dataVigenteFim, Integer idParametro){
        this.incentivoClienteAtualizacaoConsultaValidacaoRegra.validar(dataVigenteInicio, dataVigenteFim, idParametro);
    }

    protected void impedirExecucaoDoFluxoTipoInformadoJaEstaCadastradoEmAlgumIncentivo(LocalDateTime dataVigenteInicio, LocalDateTime dataVigenteFim, Integer idTipoIncentivo, Integer idParametro){
        this.incentivoClienteAtualizacaoConsultaValidacaoRegra.validar(dataVigenteInicio, dataVigenteFim, idTipoIncentivo, idParametro);
    }
}
