package moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroDetalhe;

import org.springframework.stereotype.Service;

@Service
public class IncentivoClienteParametroDetalheConsultaValidacao extends IncentivoClienteParametroDetalheValidacao{

    public void validar(Integer id){
        super.gerarExcecaoRegistroNaoEncontrado(id);
    }

}
