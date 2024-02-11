package moduloFaturamento.validacoes.feriado;

import org.springframework.stereotype.Service;

@Service
public class FeriadoAtualizacaoValidacao extends FeriadoValidacao{

    public void gerenciarValidacaoDadosParaAtualizar(Integer id, Short idLocalidade, Short idTipoFeriado, Integer idUnidadeFederativa){
        super.gerarExcessaoFeriadoNaoEncontrato(id);
        super.gerarExcessaoLocalidadeInformadaETipoFeriadoDiferenteMunicipal(idLocalidade, idTipoFeriado);
        super.gerarExcessaoFederacaoInformadaETipoFeriadoDiferenteEstadual(idUnidadeFederativa, idTipoFeriado);
        super.gerarExcessaoTipoFeriadoMunicialELocalidadeNaoInformada(idTipoFeriado, idLocalidade);
        super.gerarExcessaoTipoFeriadoEstadualEFederacaoNaoInformada(idTipoFeriado, idUnidadeFederativa);
    }
}
