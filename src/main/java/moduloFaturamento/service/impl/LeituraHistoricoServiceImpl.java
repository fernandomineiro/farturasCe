package moduloFaturamento.service.impl;

import moduloFaturamento.model.Leitura;
import moduloFaturamento.model.LeituraHistorico;
import moduloFaturamento.regras.leituraHistorico.LeituraHistoricoConsultaRegra;
import moduloFaturamento.repository.LeituraHistoricoRepository;
import moduloFaturamento.service.LeituraHistoricoService;
import moduloFaturamento.util.ConverterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LeituraHistoricoServiceImpl implements LeituraHistoricoService {

    @Autowired
    private LeituraHistoricoRepository leituraHistoricoRepository;
    @Autowired
    private LeituraHistoricoConsultaRegra leituraHistoricoConsultaRegra;

    @Override
    public Long executarFluxoCadastrarHistoricoLeituraValidaInformada(Leitura leitura) {
        boolean faturamentoEncerrado = this.leituraHistoricoConsultaRegra.retornarVerdadeiroSeCicloCronogramaEstiverFechado(leitura.getIdLeituraFaturamento().getMatriculaImovel(),
                ConverterData.integerEmLocalDateDataFormatoDB(leitura.getDataDeleitura()), leitura.getIdLeituraFaturamento().getRefFatura());

        if(faturamentoEncerrado){
            LeituraHistorico salvo =  this.salvarHistoricoDeUmaLeituraValida(leitura);
            return salvo.getId();
        }else{
            return null;
        }
    }

    private LeituraHistorico salvarHistoricoDeUmaLeituraValida(Leitura leitura){
        LeituraHistorico historico = new LeituraHistorico();
        
        historico.setMatriculaImovel(leitura.getIdLeituraFaturamento().getMatriculaImovel());
        historico.setRefFatura(leitura.getIdLeituraFaturamento().getRefFatura());
        historico.setDv(leitura.getDv());
        historico.setCdAnormalidade(leitura.getCdAnormalidade());
        historico.setAlterado(leitura.getAlterado());
        historico.setAgente(leitura.getAgente());
        historico.setCidade(leitura.getCidade());
        historico.setCiclo(leitura.getCiclo());
        historico.setDataDeleitura(leitura.getDataDeleitura());
        historico.setLeitura(leitura.getLeitura());
        historico.setDataDeleitura(leitura.getDataDeleitura());
        historico.setLeituraCriada(leitura.getLeituraCriada());
        historico.setExcluirCalculoMedia(leitura.getExcluirCalculoMedia());
        historico.setOcorrencia(leitura.getOcorrencia());
        historico.setOcorrencia2(leitura.getOcorrencia2());
        historico.setOcorrencia3(leitura.getOcorrencia3());
        historico.setSeqRota(leitura.getSeqRota());
        historico.setLoginUsuario(leitura.getLoginUsuario());
        historico.setMedido(leitura.getMedido());
        historico.setMediaDiaria(leitura.getMediaDiaria());
        historico.setDiasVenda(leitura.getDiasVenda());
        historico.setDiasConsumo(leitura.getDiasConsumo());
        historico.setConsumoFaturarAgua(leitura.getConsumoFaturarAgua());
        historico.setConsumoFaturarEsgoto(leitura.getConsumoFaturarEsgoto());
        historico.setTipoConsumoFaturado(leitura.getTipoConsumoFaturado());
        historico.setSolicitacaoServico(leitura.getSolicitacaoServico());
        historico.setItemAtendimento(leitura.getItemAtendimento());
        historico.setDataHoraEdicao(leitura.getDataHoraEdicao());
        historico.setTipoMotivoEdicaoLeituraConsumo(leitura.getTipoMotivoEdicaoLeituraConsumo());
        
        return this.leituraHistoricoRepository.saveAndFlush(historico);
    }

}
