package moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroAnexo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import moduloFaturamento.anexos.model.LeituraAnexo;
import moduloFaturamento.anexos.repository.LeituraAnexoRepository;
import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroConsultaValidacao;

@Service
abstract class IncentivoClienteParametroAnexoValidacao {

    @Autowired
    private LeituraAnexoRepository leituraAnexoRepository;
    @Autowired
    private IncentivoClienteParametroConsultaValidacao incentivoClienteParametroConsultaValidacao;

    protected void gerarExcecaoIdIncentivoNaoEncontrato(Integer id){
        this.incentivoClienteParametroConsultaValidacao.validar(id);
    }

    protected void gerarExcecaoArquivoNaoEncontrado(Long id){
        Optional<LeituraAnexo> leitura = this.leituraAnexoRepository.findById(id);

        if(leitura.isEmpty()){
            throw new MsgGenericaPersonalizadaException("Arquivo de anexo não encontrado");
        }
    }


    protected void gerarExcecaoTamanhoDoArquivo (Integer length){
        if(length > 10000 * 1024){
            throw new MsgGenericaPersonalizadaException("Arquivo maior que 10MB", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
