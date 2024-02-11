package moduloFaturamento.validacoes.parametroLeituraMensalFaturamento;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.ParametroLeituraMensalFaturamento;
import moduloFaturamento.repository.ParametroLeituraMensalFaturamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
abstract class ParametroValidacao {

    @Autowired
    private ParametroLeituraMensalFaturamentoRepository parametroLeituraMensalFaturamentoRepository;

    protected void gerarExcecaoParametroNaoEncontrado(Integer id){
        Optional<ParametroLeituraMensalFaturamento> entidadeOptional = this.parametroLeituraMensalFaturamentoRepository.findById(id);
        if(entidadeOptional.isEmpty()){
            throw new MsgGenericaPersonalizadaException("Não foi encontrado parâmetro de configuração");
        }
    }

    protected void gerarExcecaoGeracaoAutomaticaSemHorarioEstabelecido(Boolean geracao, LocalDateTime horario){
        final Boolean GERACAO_AUTOMATICA = true;
        if(geracao.equals(GERACAO_AUTOMATICA)){
            if(horario == null){
                throw new MsgGenericaPersonalizadaException("Favor informar um horário para a geração automática", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
    }
}
