package moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametro;

import org.springframework.stereotype.Service;

@Service
public class IncentivoClienteParametroAtualizarValidacao extends IncentivoClienteParametroValidacao {

    public void validar(Integer id){
        super.gerarExcecaoRegistroNaoEncontrado(id);
    }

}
