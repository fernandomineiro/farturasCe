package moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroDetalheGrupoConsumo;

import moduloFaturamento.validacoes.cadGrupoConsumo.CadGrupoConsumoIncentivoClienteConsultaValidacao;
import moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroDetalheConsultaValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
abstract class IncentivoClienteGrupoConsumoValidacao {

    @Autowired
    private IncentivoClienteParametroDetalheConsultaValidacao incentivoClienteParametroDetalheConsultaValidacao;
    @Autowired
    private CadGrupoConsumoIncentivoClienteConsultaValidacao cadGrupoConsumoIncentivoClienteConsultaValidacao;

    protected void gerarExcecaoParametroDetalheNaoEncontrado(Integer id){
        this.incentivoClienteParametroDetalheConsultaValidacao.validar(id);
    }

    protected void gerarExcecaoGrupoConsumoNaoEncontrado(Integer id){
       this.cadGrupoConsumoIncentivoClienteConsultaValidacao.validar(id);
    }
}
