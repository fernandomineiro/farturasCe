package moduloFaturamento.validacoes.cadGrupoConsumo;

import org.springframework.stereotype.Service;

@Service
public class CadGrupoConsumoIncentivoClienteConsultaValidacao extends CadGrupoConsumoValidacao {

    public void validar(Integer id){
        super.gerarExcecaoGrupoConsumoNaoEncontrado(id);
    }
}
