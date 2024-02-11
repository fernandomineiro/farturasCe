package moduloFaturamento.regras.incentivoCliente.validacao;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IncentivoClienteAtualizacaoConsultaValidacaoRegra extends IncentivoClienteImplementaAtualizacaoValidacaoRegra{


    public void validar(LocalDateTime dataVigenteInicio, LocalDateTime dataVigenteFim, Integer idParametro){
        super.gerarExecaoTodosTiposIncentivoIncluindoNuloJaEstaEmAberto(dataVigenteInicio, dataVigenteFim, idParametro);
    }

    public void validar(LocalDateTime dataVigenteInicio, LocalDateTime dataVigenteFim, Integer idTipoIncentivo, Integer idParametro){
        super.gerarExecaoTipoInformadoPossuiRegistroComPeriodoVigenteEmAbertoJaExistente(dataVigenteInicio, dataVigenteFim, idTipoIncentivo, idParametro);
    }
}
