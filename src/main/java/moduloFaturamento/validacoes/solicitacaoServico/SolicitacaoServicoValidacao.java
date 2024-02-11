package moduloFaturamento.validacoes.solicitacaoServico;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.common.IdSolicitacaoServico;
import moduloFaturamento.model.common.SolicitacaoServico;
import moduloFaturamento.repository.common.SolicitacaoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SolicitacaoServicoValidacao {

    @Autowired
    private SolicitacaoServicoRepository solicicitacaoServicoRepository;

    public void gerarExcecaoSSNaoEncontrada(Integer refAtendimento, Integer codAtendimento, Short seqAtendimento){
        IdSolicitacaoServico id = new IdSolicitacaoServico(codAtendimento, refAtendimento, seqAtendimento);
        Optional<SolicitacaoServico> solicitacaoServicoOptional = this.solicicitacaoServicoRepository.findById(id);
        if(solicitacaoServicoOptional.isEmpty()){
            throw new MsgGenericaPersonalizadaException("SS não encontrada");
        }

    }
}
