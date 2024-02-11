package moduloFaturamento.service.impl;

import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.*;
import moduloFaturamento.model.common.SituacaoLigacaoAgua;
import moduloFaturamento.repository.IncentivoClienteHistoricoSituacaoAguaRepository;
import moduloFaturamento.repository.IncentivoClienteHistoricoSituacaoEsgotoRepository;
import moduloFaturamento.repository.IncentivoClienteSituacaoAguaRepository;
import moduloFaturamento.repository.IncentivoClienteSituacaoEsgotoRepository;
import moduloFaturamento.service.IncentivoClienteParametroDetalheLigacaoAguaService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IncentivoClienteParametroDetalheLigacaoAguaServiceImpl implements IncentivoClienteParametroDetalheLigacaoAguaService {

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
    public void executarFluxoCadastrarLigacao(IncentivoClienteDetalhe incentivoClienteDetalhe, List<Integer> situacaoLigacaoAgua, Long idUsuario, IncentivoClienteHistoricoDetalhe historicoDetalhe) {
        for (Integer ligacaoAgua : situacaoLigacaoAgua) {
            this.cadastrarSituacaoLigacaoAguaValido(incentivoClienteDetalhe, ligacaoAgua, idUsuario);
            this.salvarHistoricoLigacaoAgua(historicoDetalhe, ligacaoAgua);
        }
    }

    @Override
    public void executarFluxoRemoverTodasLigacoesDeUmParametroIncentivo(IncentivoClienteDetalhe incentivoClienteDetalhe){
        this.removerTodasLigacoesAguaDeUmParametroIncentivo(incentivoClienteDetalhe);
    }

    private void cadastrarSituacaoLigacaoAguaValido(IncentivoClienteDetalhe incentivoCLienteDetalhe, Integer situacaoLigacaoAgua, Long idUsuario) {
        IncentivoClienteSituacaoAgua entidade = new IncentivoClienteSituacaoAgua(incentivoCLienteDetalhe, new SituacaoLigacaoAgua(situacaoLigacaoAgua));
        this.incentivoClienteSituacaoAguaRepository.saveAndFlush(entidade);

        this.auditoriaService.gerarAuditoriaCadastramento(entidade.getId().longValue(), ConvertObjectToJsonCesan.execute(entidade), 73L, "Parâmetro de Negociação", idUsuario);
    }

    private void salvarHistoricoLigacaoAgua(IncentivoClienteHistoricoDetalhe incentivoClienteDetalheHistorico, Integer ligacaoAgua) {
        IncentivoClienteHistoricoSituacaoAgua incentivoClienteHistoricoSituacaoAgua = new IncentivoClienteHistoricoSituacaoAgua(incentivoClienteDetalheHistorico, ligacaoAgua);
        this.incenticoClienteHistoricoSituacaoAguaRepository.save(incentivoClienteHistoricoSituacaoAgua);
    }

    private void removerTodasLigacoesAguaDeUmParametroIncentivo(IncentivoClienteDetalhe incentivoClienteDetalhe){
        List<IncentivoClienteSituacaoAgua>  ligacoes = this.incentivoClienteSituacaoAguaRepository.findByIncentivoClienteDetalhe_Id(incentivoClienteDetalhe.getId());
        this.incentivoClienteSituacaoAguaRepository.deleteAll(ligacoes);
    }
}
