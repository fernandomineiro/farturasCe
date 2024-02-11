package moduloFaturamento.validacoes.leituraAnexo;

import org.springframework.stereotype.Service;

@Service
public class LeituraAnexoRemocaoValidacao extends LeituraAnexoValidacao{

    public void validarConsulta(Long id){
        super.gerarExcecaoArquivoNaoEncontrado(id);
    }
}


