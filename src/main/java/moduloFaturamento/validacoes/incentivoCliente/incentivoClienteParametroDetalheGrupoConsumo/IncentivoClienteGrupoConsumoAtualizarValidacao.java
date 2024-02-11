package moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroDetalheGrupoConsumo;

import org.springframework.stereotype.Service;

@Service
public class IncentivoClienteGrupoConsumoAtualizarValidacao extends IncentivoClienteGrupoConsumoValidacao{

    public void validar(Integer idGrupoConsumo){
        super.gerarExcecaoGrupoConsumoNaoEncontrado(idGrupoConsumo);
    }

}
