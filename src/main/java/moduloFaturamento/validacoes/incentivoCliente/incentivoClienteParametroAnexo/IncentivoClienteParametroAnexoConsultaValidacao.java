package moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroAnexo;

import org.springframework.stereotype.Service;

@Service
public class IncentivoClienteParametroAnexoConsultaValidacao extends IncentivoClienteParametroAnexoValidacao {


    public void validar(Integer id) {
        super.gerarExcecaoIdIncentivoNaoEncontrato(id);
    }
}
