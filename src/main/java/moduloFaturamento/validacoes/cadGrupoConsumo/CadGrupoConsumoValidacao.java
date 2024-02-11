package moduloFaturamento.validacoes.cadGrupoConsumo;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.common.CadGrupoConsumo;
import moduloFaturamento.repository.common.CadGrupoConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
abstract class CadGrupoConsumoValidacao {

    @Autowired
    private CadGrupoConsumoRepository cadGrupoConsumoRepository;

    protected void gerarExcecaoGrupoConsumoNaoEncontrado(Integer id){
        Optional<CadGrupoConsumo> cadGrupoConsumoOptional = this.cadGrupoConsumoRepository.findById(id);

        if(cadGrupoConsumoOptional.isEmpty()){
            throw new MsgGenericaPersonalizadaException("Grupo de consumo não encontrado");
        }
    }

}

