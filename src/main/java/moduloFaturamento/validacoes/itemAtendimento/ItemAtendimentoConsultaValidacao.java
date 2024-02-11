package moduloFaturamento.validacoes.itemAtendimento;

import org.springframework.stereotype.Service;

@Service
public class ItemAtendimentoConsultaValidacao  extends ItemAtendimentoValidacao{

    public void gerarExcecaoItemAtendimentoNaoEncontrada(Integer refAtendimento, Integer codAtendimento, Short seqAtendimento){
        super.gerarExcecaoItemAtendimentoNaoEncontrado(refAtendimento, codAtendimento, seqAtendimento);
    }
}
