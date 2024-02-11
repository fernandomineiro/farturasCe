package moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametro;

import org.springframework.stereotype.Service;

@Service
public class IncentivoClienteParametroRemocaoValidacao extends IncentivoClienteParametroValidacao {

    public void validar(Integer id){
        super.gerarExcecaoRegistroNaoEncontrado(id);
    }

}
