package moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroAnexo;

import moduloFaturamento.validacoes.common.CommonUtilValidacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncentivoClienteParametroAnexoCadastroValidacao extends IncentivoClienteParametroAnexoValidacao {

    @Autowired
    private CommonUtilValidacoes commonUtilValidacoes;

    static final List<String> listaExtensoes = List.of("pdf");

    public void validarCadastro(String nomeArquivo, int tamanhoArquivo) {
        this.commonUtilValidacoes.gerarExcecaoExtensaoInvalida(nomeArquivo, listaExtensoes);
        super.gerarExcecaoTamanhoDoArquivo(tamanhoArquivo);
    }
}
