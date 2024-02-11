package moduloFaturamento.validacoes.ocorrenciaLeitura;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.common.OcorrenciaLeitura;
import moduloFaturamento.repository.common.OcorrenciaLeituraRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class OcorrenciaLeituraValidacao {
    @Autowired
    private OcorrenciaLeituraRepository ocorrenciaLeituraRepository;

    protected void gerarExcessaoOcorrenciaLeituraNaoEncontrato(Short cdOcorrencia){
        Optional<OcorrenciaLeitura> ocorrenciaLeituraOptional = this.ocorrenciaLeituraRepository.findByCdOcorrenciaAndDataHoraExclusaoIsNull(cdOcorrencia);
        if(ocorrenciaLeituraOptional.isEmpty()){
            throw  new MsgGenericaPersonalizadaException("Ocorrência leitura não encontrato ou foi removido");
        }
    }
    
    protected void gerarExcessaoCodigoOcorrenciaInformadoJaExistenteNaBaseDeDados(Short cdOcorrencia){
        Optional<OcorrenciaLeitura> ocorrenciaLeituraOptional = Optional.ofNullable(this.ocorrenciaLeituraRepository.findByCdOcorrencia(cdOcorrencia));
        if(ocorrenciaLeituraOptional.isPresent()){
            throw new MsgGenericaPersonalizadaException("Esse código de ocorrência já foi registrado anteriormente");
        }
    }
}
