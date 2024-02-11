package moduloFaturamento.regras.leituraConsumoImovel.consumoFaturadoEsgoto;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LeituraConsumoFaturadoEsgotoConsultaRegra extends LeituraConsumoFaturadoEsgotoRegra {

    public BigDecimal consultarConsumoFaturadoEsgoto(Integer matricula, Integer refFatura, BigDecimal consumoFaturadoEsgoto){

        List<Integer> matriculasSecundarias = super.retornarMatriculasSecundariaDeUmaMatriculaMae(matricula);

        BigDecimal somatorioConsumoFaturadoEsgoto = super.retornarSomatorioConsumoFaturadoDeMatriculasSecundarias(matriculasSecundarias, refFatura);

        return super.retornarNovoConsumoFaturadoEsgotoParaMatriculaMae(somatorioConsumoFaturadoEsgoto, consumoFaturadoEsgoto);
    }


    public boolean consultarSeHouveAlteracaoEmAlgumaMatriculaSecundaria(Integer matricula, Integer refFatura){
        List<Integer> matriculasSecundarias = super.retornarMatriculasSecundariaDeUmaMatriculaMae(matricula);

        return super.retornarSeHouveAlteracaoMatriculaSecundaria(matriculasSecundarias, refFatura);
    }




}
