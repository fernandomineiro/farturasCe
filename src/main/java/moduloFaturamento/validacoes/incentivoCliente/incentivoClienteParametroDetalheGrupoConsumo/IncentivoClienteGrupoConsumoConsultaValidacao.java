package moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroDetalheGrupoConsumo;

import org.springframework.stereotype.Service;

@Service
public class IncentivoClienteGrupoConsumoConsultaValidacao extends IncentivoClienteGrupoConsumoValidacao {

    public void validarCodigoParametroDetalhe(Integer id){
        super.gerarExcecaoParametroDetalheNaoEncontrado(id);
    }

}
