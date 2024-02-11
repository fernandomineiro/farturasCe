package moduloFaturamento.validacoes.leitura;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.anexos.model.LeituraAnexo;
import moduloFaturamento.validacoes.common.CommonUtilValidacoes;

@Service
public class LeituraAnexoValidacao extends LeituraValidacao {

    @Autowired
    private CommonUtilValidacoes commonUtilValidacoes;

    static final List<String> listaExtensoes = List.of("pdf", "jpg", "jpeg");

    public void gerenciarValidacaoAnexo(LeituraAnexo anexo) {
        commonUtilValidacoes.gerarExcecaoExtensaoInvalida(anexo.getNomeArquivo(), listaExtensoes);
        super.gerarExcessaoTamanhoDoArquivo(anexo.getTamanhoArquivo());
    }
    
}
