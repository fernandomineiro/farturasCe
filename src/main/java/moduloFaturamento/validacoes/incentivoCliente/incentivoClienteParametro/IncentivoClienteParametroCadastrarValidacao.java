package moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametro;

import org.springframework.stereotype.Service;

@Service
public class IncentivoClienteParametroCadastrarValidacao extends IncentivoClienteParametroValidacao {

    public void validar(Integer id){
        super.gerarExcecaoTipoIncentivoNaoEncontrado(id);
    }

}
