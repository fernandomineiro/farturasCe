package moduloFaturamento.validacoes.solicitacaoServico;

import org.springframework.stereotype.Service;

@Service
public class SolicitacaoServicoConsultaValidacao extends SolicitacaoServicoValidacao{

    public void validarExistenciaSS(Integer refAtendimento, Integer codigoAtendimento, Short seqSS){
        super.gerarExcecaoSSNaoEncontrada(refAtendimento, codigoAtendimento, seqSS);
    }
}
