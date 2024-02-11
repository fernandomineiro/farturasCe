package moduloFaturamento.service.impl;

import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.IncentivoClienteDetalhe;
import moduloFaturamento.model.IncentivoClienteHistoricoDetalhe;
import moduloFaturamento.model.IncentivoClienteHistoricoSituacaoEsgoto;
import moduloFaturamento.model.IncentivoClienteSituacaoEsgoto;
import moduloFaturamento.model.common.SituacaoLigacaoEsgoto;
import moduloFaturamento.repository.IncentivoClienteHistoricoSituacaoAguaRepository;
import moduloFaturamento.repository.IncentivoClienteHistoricoSituacaoEsgotoRepository;
import moduloFaturamento.repository.IncentivoClienteSituacaoAguaRepository;
import moduloFaturamento.repository.IncentivoClienteSituacaoEsgotoRepository;
import moduloFaturamento.service.IncentivoClienteParametroDetalheLigacaoEsgotoService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IncentivoClienteParametroDetalheLigacaoEsgotoServiceImpl implements IncentivoClienteParametroDetalheLigacaoEsgotoService {

    @Autowired
    private IncentivoClienteSituacaoAguaRepository incentivoClienteSituacaoAguaRepository;
    @Autowired
    private IncentivoClienteHistoricoSituacaoAguaRepository incenticoClienteHistoricoSituacaoAguaRepository;
    @Autowired
    private IncentivoClienteSituacaoEsgotoRepository incentivoClienteSituacaoEsgotoRepository;
    @Autowired
    private IncentivoClienteHistoricoSituacaoEsgotoRepository incenticoClienteHistoricoSituacaoEsgotoRepository;
    @Autowired
    private AuditoriaService auditoriaService;


    @Override
    public void executarFluxoCadastrarLigacao(IncentivoClienteDetalhe incentivoClienteDetalhe, List<Integer> situacaoLigacaoEsgoto, Long idUsuario, IncentivoClienteHistoricoDetalhe historicoDetalhe) {
        for (Integer ligacaoEsgoto : situacaoLigacaoEsgoto) {
            this.cadastrarSituacaoLigacaoEsgotoValido(incentivoClienteDetalhe, ligacaoEsgoto, idUsuario);
            this.salvarHistoricoLigacaoEsgoto(historicoDetalhe, ligacaoEsgoto);
        }
    }

    @Override
    public void executarFluxoRemoverTodasLigacoesDeUmParametroIncentivo(IncentivoClienteDetalhe incentivoCLienteDetalhe){
        this.removerTodasLigacoesEsgotoDeUmParametroIncentivo(incentivoCLienteDetalhe);
    }


    private void cadastrarSituacaoLigacaoEsgotoValido(IncentivoClienteDetalhe incentivoCLienteDetalhe, Integer situacaoLigacaoEsgoto, Long idUsuario) {
        IncentivoClienteSituacaoEsgoto entidade = new IncentivoClienteSituacaoEsgoto(incentivoCLienteDetalhe, new SituacaoLigacaoEsgoto(situacaoLigacaoEsgoto));
        this.incentivoClienteSituacaoEsgotoRepository.saveAndFlush(entidade);

        this.auditoriaService.gerarAuditoriaCadastramento(entidade.getId().longValue(), ConvertObjectToJsonCesan.execute(entidade), 73L, "Parâmetro de Negociação", idUsuario);
    }

    private void salvarHistoricoLigacaoEsgoto(IncentivoClienteHistoricoDetalhe incentivoClienteDetalheHistorico, Integer ligacaoEsgoto) {
        IncentivoClienteHistoricoSituacaoEsgoto incentivoClienteHistoricoSituacaoEsgoto = new IncentivoClienteHistoricoSituacaoEsgoto(incentivoClienteDetalheHistorico, ligacaoEsgoto);
        this.incenticoClienteHistoricoSituacaoEsgotoRepository.save(incentivoClienteHistoricoSituacaoEsgoto);
    }


    private void removerTodasLigacoesEsgotoDeUmParametroIncentivo(IncentivoClienteDetalhe incentivoCLienteDetalhe){
        List<IncentivoClienteSituacaoEsgoto>  ligacoes = this.incentivoClienteSituacaoEsgotoRepository.findByIncentivoClienteDetalhe_Id(incentivoCLienteDetalhe.getId());
        this.incentivoClienteSituacaoEsgotoRepository.deleteAll(ligacoes);
    }
}
