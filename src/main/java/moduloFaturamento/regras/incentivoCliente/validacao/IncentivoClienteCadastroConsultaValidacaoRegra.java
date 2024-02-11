package moduloFaturamento.regras.incentivoCliente.validacao;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IncentivoClienteCadastroConsultaValidacaoRegra extends IncentivoClienteImplementaCadastroValidacaoRegra {


    public void validar(LocalDateTime dataVigenteInicio, LocalDateTime dataVigenteFim){
        super.gerarExecaoTodosTiposIncentivoIncluindoNuloJaEstaEmAberto(dataVigenteInicio, dataVigenteFim);
    }

    public void validar(LocalDateTime dataVigenteInicio, LocalDateTime dataVigenteFim, Integer idTipoIncentivo){
        super.gerarExecaoTipoInformadoPossuiRegistroComPeriodoVigenteEmAbertoJaExistente(dataVigenteInicio, dataVigenteFim, idTipoIncentivo);
    }
}
