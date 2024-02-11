package moduloFaturamento.validacoes.feriado;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.Feriado;
import moduloFaturamento.repository.FeriadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Optional;


public abstract class FeriadoValidacao{
    private final short ID_TIPO_FERIADO_MUNICIPAL = 3;
    private final short ID_TIPO_FERIADO_ESTADUAL = 2;
    @Autowired
    private FeriadoRepository feriadoRepository;



    protected void gerarExcessaoFeriadoNaoEncontrato(Integer idFeriado){
        Optional<Feriado> feriadoOptional = this.feriadoRepository.findByIdAndDataHoraExclusaoIsNull(idFeriado);
        if(feriadoOptional.isEmpty()){
            throw new MsgGenericaPersonalizadaException("Feriado não encontrato ou foi removido");
        }
    }

    protected void gerarExcessaoLocalidadeInformadaETipoFeriadoDiferenteMunicipal(Short cdCidade, Short idTipoFeriado){
       short valorPadraoCidadeNaoEspecifica = 0;
       if((cdCidade != null && cdCidade != valorPadraoCidadeNaoEspecifica ) && idTipoFeriado != ID_TIPO_FERIADO_MUNICIPAL){
           throw new MsgGenericaPersonalizadaException("Localidade deve ser informada somente em feriado municipal", HttpStatus.UNPROCESSABLE_ENTITY);
       }
    }

    protected void gerarExcessaoFederacaoInformadaETipoFeriadoDiferenteEstadual(Integer idUnidadeFederativa, Short idTipoFeriado){
        if(idUnidadeFederativa != null && idTipoFeriado != ID_TIPO_FERIADO_ESTADUAL){
            throw new MsgGenericaPersonalizadaException("Unidade federativa deve ser informada somente em feriado estadual", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    protected void gerarExcessaoTipoFeriadoMunicialELocalidadeNaoInformada(Short idTipoFeriado, Short idLocalidade){
        if(idTipoFeriado == ID_TIPO_FERIADO_MUNICIPAL && idLocalidade == null){
            throw new MsgGenericaPersonalizadaException("Localidade deve ser informada quando tipo feriado for municipal", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    protected void gerarExcessaoTipoFeriadoEstadualEFederacaoNaoInformada(Short idTipoFeriado, Integer idTipoFederacao){
        if(idTipoFeriado == ID_TIPO_FERIADO_ESTADUAL && idTipoFederacao == null){
            throw new MsgGenericaPersonalizadaException("Federação deve ser informada quando tipo feriado for estadual", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    protected void gerarExcessaoFeriadoJaCadastradoParaMesmaDataFeriadoELocalidadeETipoFederacao(Integer dataFeriado, Short cdCidade, Integer idTipoFederacao){
        Optional<Feriado> feriadoOptional = this.feriadoRepository.findByIdFeriado_DtFeriadoAndIdFeriado_CdCidadeAndUnidadeFederativa_IdAndDataHoraExclusaoIsNull(dataFeriado, cdCidade, idTipoFederacao);
        if(feriadoOptional.isPresent()){
            throw new MsgGenericaPersonalizadaException("Feriado já cadastrado para esses parâmetros", HttpStatus.CONFLICT);
        }
    }
}
