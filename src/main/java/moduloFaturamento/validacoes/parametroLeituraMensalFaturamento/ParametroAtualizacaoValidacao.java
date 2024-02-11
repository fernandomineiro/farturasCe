package moduloFaturamento.validacoes.parametroLeituraMensalFaturamento;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ParametroAtualizacaoValidacao extends ParametroValidacao{

    public void validar(Integer id, LocalDateTime horario, Boolean tipoGeracao){
        super.gerarExcecaoParametroNaoEncontrado(id);
        super.gerarExcecaoGeracaoAutomaticaSemHorarioEstabelecido(tipoGeracao, horario);
    }
}
