package moduloFaturamento.validacoes.cronogramaFaturamento;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.CronogramaFatura;
import moduloFaturamento.model.IdCronogramaFatura;
import moduloFaturamento.model.TipoFaseCronograma;
import moduloFaturamento.regras.cronogramaFaturamento.spec.CronogramaFaturamentoFaseEnum;
import moduloFaturamento.repository.CronogramaFaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public abstract class CronogramaFaturamentoValidacao {
    @Autowired
    private CronogramaFaturaRepository cronogramaFaturaRepository;

    protected void gerarExcecaoCronogramaNaoEncontrado(Long id){
        Optional<CronogramaFatura> cronogramaFaturamentoOptional = this.cronogramaFaturaRepository.findById(id);
        if(cronogramaFaturamentoOptional.isEmpty()){
            throw new MsgGenericaPersonalizadaException("Cronograma de faturamento não encontrato ou foi removido");
        }
    }

    protected void gerarExcecaoCronogramaJaCadastrado(Short cdCidade, Short ciclo, Integer dataReferencia){
        IdCronogramaFatura id = new IdCronogramaFatura(cdCidade, ciclo, dataReferencia);
        CronogramaFatura crogramaFatura = this.cronogramaFaturaRepository.findByIdCronogramaFatura(id);
        if(crogramaFatura != null){
            throw new MsgGenericaPersonalizadaException("Cronograma já registrado para esses parâmetros", HttpStatus.CONFLICT);
        }
    }

    protected void gerarExcecaoAvancarFaseTrueRetonarFaseTrue(boolean retornarFase, boolean avancarFase){
        if(retornarFase && avancarFase){
            throw new MsgGenericaPersonalizadaException("Não é possível avançar e retornar fase ao mesmo tempo", HttpStatus.BAD_REQUEST);
        }
    }

    protected void gerarExcecaoAvancarFasePoremFaseAtualNaoEstaEmIncluirLeitura(Long id, boolean avancarFase){
        CronogramaFatura cronogramaFatura = this.cronogramaFaturaRepository.buscarCronogramaExistentePorId(id);
        Integer proximaEtapaFase = Optional.ofNullable(cronogramaFatura.getFase()).map(TipoFaseCronograma::getId).orElse((short)0) + 1;
        if(avancarFase && proximaEtapaFase.shortValue() != CronogramaFaturamentoFaseEnum.FATURAR.getValor()){
            throw new MsgGenericaPersonalizadaException("Só é possível avançar a fase de 'Incluir Leitura' para 'Faturar' ", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    protected void gerarExcecaoRetonarFasePoremFaseAtualEstaEmGerarEspelho(Long id, boolean retornarFase){
        CronogramaFatura cronogramaFatura = this.cronogramaFaturaRepository.buscarCronogramaExistentePorId(id);
        Integer etapaAnteriorFase = Optional.ofNullable(cronogramaFatura.getFase()).map(TipoFaseCronograma::getId).orElse((short)0) - 1;
        if(retornarFase && etapaAnteriorFase.shortValue() < CronogramaFaturamentoFaseEnum.GERAR_ESPELHO.getValor()){
            throw new MsgGenericaPersonalizadaException("Não é possível retornar fase, ela já está em 'Gerar Espelho'", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
