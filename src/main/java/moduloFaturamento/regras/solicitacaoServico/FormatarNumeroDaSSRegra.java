package moduloFaturamento.regras.solicitacaoServico;

import org.springframework.stereotype.Service;

@Service
public class FormatarNumeroDaSSRegra extends SolicitacaoServicoRegra{

    public static String formatar(Integer refAtendimento, Integer codAtendimento, Short seqSS){
        return formatarSS(refAtendimento, codAtendimento, seqSS);
    }
}
