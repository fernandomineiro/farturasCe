package moduloFaturamento.validacoes.itemAtendimento;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.common.IdItemAtendimento;
import moduloFaturamento.model.common.ItemAtendimento;
import moduloFaturamento.repository.common.ItemAtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ItemAtendimentoValidacao {

    @Autowired
    private ItemAtendimentoRepository itemAtendimentoRepository;

    protected void gerarExcecaoItemAtendimentoNaoEncontrado(Integer refAtendimento, Integer codAtendimento, Short seq){
        IdItemAtendimento id = new IdItemAtendimento(codAtendimento, refAtendimento, seq);
        Optional<ItemAtendimento> itemAtendimentoOptional = this.itemAtendimentoRepository.findById(id);
        if(itemAtendimentoOptional.isEmpty()){
            throw new MsgGenericaPersonalizadaException("item atendimento não encontrado.");
        }
    }
}
