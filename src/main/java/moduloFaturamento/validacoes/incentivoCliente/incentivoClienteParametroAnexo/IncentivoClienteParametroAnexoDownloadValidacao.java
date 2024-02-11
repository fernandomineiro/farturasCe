package moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroAnexo;

import org.springframework.stereotype.Service;

@Service
public class IncentivoClienteParametroAnexoDownloadValidacao extends IncentivoClienteParametroAnexoValidacao {

    public void validar(Long id) {
        super.gerarExcecaoArquivoNaoEncontrado(id);
    }
}
