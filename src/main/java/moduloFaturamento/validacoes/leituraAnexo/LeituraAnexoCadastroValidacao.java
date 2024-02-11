package moduloFaturamento.validacoes.leituraAnexo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.validacoes.common.CommonUtilValidacoes;

@Service
public class LeituraAnexoCadastroValidacao extends LeituraAnexoValidacao{

    @Autowired
    private CommonUtilValidacoes commonUtilValidacoes;

    static final List<String> listaExtensoes = List.of("pdf", "jpg", "jpeg");

    public void validarCadastro(String nomeArquivo, int tamanhoArquivo) {
        this.commonUtilValidacoes.gerarExcecaoExtensaoInvalida(nomeArquivo, listaExtensoes);
        super.gerarExcecaoTamanhoDoArquivo(tamanhoArquivo);
    }
}
