package moduloFaturamento.service.impl;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.CronogramaFaturaCicloFaturamentoParametroAtualizacaoDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.CronogramaFaturaCicloFaturamentoParametroRespostaSalvarDTO;
import moduloFaturamento.dto.parametroLeituraMensalFaturamento.ParametroLeituraMensalFaturamentoDTO;
import moduloFaturamento.dto.parametroLeituraMensalFaturamento.mapper.ParametroLeituraMensalFaturamentoMapper;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.ParametroLeituraMensalFaturamento;
import moduloFaturamento.model.TipoGeracaoParametros;
import moduloFaturamento.repository.ParametroLeituraMensalFaturamentoRepository;
import moduloFaturamento.service.ParametroLeituraMensalFaturamentoService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.validacoes.parametroLeituraMensalFaturamento.ParametroAtualizacaoValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class ParametroLeituraMensalFaturamentoServiceImpl implements ParametroLeituraMensalFaturamentoService {

    @Autowired
    private ParametroLeituraMensalFaturamentoRepository parametroLeituraMensalFaturamentoRepository;
    @Autowired
    private ParametroLeituraMensalFaturamentoMapper parametroLeituraMensalFaturamentoMapper;
    @Autowired
    private ParametroAtualizacaoValidacao parametroAtualizacaoValidacao;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuditoriaService auditoriaService;

    @Override
    public ParametroLeituraMensalFaturamentoDTO buscarParametro() {
        return this.parametroLeituraMensalFaturamentoMapper.toDto(this.parametroLeituraMensalFaturamentoRepository.buscarParametro());
    }


    public CronogramaFaturaCicloFaturamentoParametroRespostaSalvarDTO executarFluxoAtualizarPorCronogramaFaturaCicloFaturamento(CronogramaFaturaCicloFaturamentoParametroAtualizacaoDTO dto, String token){
        this.parametroAtualizacaoValidacao.validar(dto.getId(), dto.getHorario(), dto.getGeracaoAutomatica());

        ParametroLeituraMensalFaturamento entidadeValida = this.parametroLeituraMensalFaturamentoRepository.buscarParametroExistente(dto.getId());

        String dadosAntesAtualizar = ConvertObjectToJsonCesan.execute(entidadeValida);

        LocalDateTime horario = dto.getHorario();
        Short tipoGeracao = (dto.getGeracaoAutomatica() ? (short) 2 : (short)1);

        ParametroLeituraMensalFaturamento entidadeAtualizada = this.atualizarParametroValido(entidadeValida, horario, tipoGeracao, dadosAntesAtualizar, this.jwtTokenProvider.buscarIdUsuario(token));

        return new CronogramaFaturaCicloFaturamentoParametroRespostaSalvarDTO(entidadeAtualizada.getId());
    }

    private ParametroLeituraMensalFaturamento atualizarParametroValido(ParametroLeituraMensalFaturamento entidadeValida, LocalDateTime horario, Short tipoGeracao, String dadosAntesAtualizar, Long idUsuario) {
        final Short GERACAO_AUTOMATICA = 2;
        if(tipoGeracao.equals(GERACAO_AUTOMATICA)){
            entidadeValida.setHorario(horario);
        }else{
            entidadeValida.setHorario(null);
        }
        entidadeValida.setTipoGeracaoParametros(new TipoGeracaoParametros(tipoGeracao));

        this.auditoriaService.gerarAuditoriaAlteracao(entidadeValida.getId().longValue(), dadosAntesAtualizar, ConvertObjectToJsonCesan.execute(entidadeValida), 74L,"Parâmetro de faturamento", idUsuario);

        return entidadeValida;
    }


}
