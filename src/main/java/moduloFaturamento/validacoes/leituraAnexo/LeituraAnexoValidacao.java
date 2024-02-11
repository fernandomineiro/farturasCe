package moduloFaturamento.validacoes.leituraAnexo;

import moduloFaturamento.anexos.model.LeituraAnexo;
import moduloFaturamento.anexos.repository.LeituraAnexoRepository;
import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
abstract class LeituraAnexoValidacao {

    @Autowired
    private LeituraAnexoRepository leituraAnexoRepository;

    protected void gerarExcecaoArquivoNaoEncontrado(Long id){
        Optional<LeituraAnexo> leitura = this.leituraAnexoRepository.findById(id);

        if(leitura.isEmpty()){
            throw new MsgGenericaPersonalizadaException("Arquivo de anexo não encontrado");
        }
    }


    protected void gerarExcecaoTamanhoDoArquivo (Integer length){
        if(length > 5000 * 1024){
            throw new MsgGenericaPersonalizadaException("Arquivo maior que 5MB", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
