package moduloFaturamento.validacoes.feriado;

import org.springframework.stereotype.Service;

@Service
public class FeriadoRemocaoValidacao extends FeriadoValidacao{

    public void gerenciarValidacaoDadosParaDeletar(Integer idFeriado){
        super.gerarExcessaoFeriadoNaoEncontrato(idFeriado);
    }
}
