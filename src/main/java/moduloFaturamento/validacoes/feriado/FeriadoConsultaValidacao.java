package moduloFaturamento.validacoes.feriado;

import org.springframework.stereotype.Service;

@Service
public class FeriadoConsultaValidacao extends FeriadoValidacao{

    public void validarConsulta(Integer idFeriado){
        super.gerarExcessaoFeriadoNaoEncontrato(idFeriado);
    }
}
