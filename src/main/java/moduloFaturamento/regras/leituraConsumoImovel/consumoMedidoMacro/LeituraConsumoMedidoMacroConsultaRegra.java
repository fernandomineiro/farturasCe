package moduloFaturamento.regras.leituraConsumoImovel.consumoMedidoMacro;

import moduloFaturamento.util.ConverterData;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeituraConsumoMedidoMacroConsultaRegra extends  LeituraConsumoMedidoMacroRegra {

    public Integer consultarConsumoMedidoMacro(Integer matricula, LocalDate refFatura){
        Boolean matriculaMacro = super.retornarSeMatriculaInformadaEstaComoMacro(matricula);
        if(matriculaMacro){
            List<Integer> matriculasMicroDeUmaMatriculaMacro = super.listarTodasAsMatriculasMicroDeUmaMatriculaMacro(matricula);
            return super.somarValorMedidoMacro(matriculasMicroDeUmaMatriculaMacro, ConverterData.localDateEmIntegerReferenciaFormatoDB(refFatura));
        }else{
            return null;
        }
    }


    public boolean consultarSeHouveAlteracaoEmAlgumaMatriculaMicro(Integer matricula, Integer refFatura){
        Boolean matriculaMacro = super.retornarSeMatriculaInformadaEstaComoMacro(matricula);
        if(matriculaMacro){
            List<Integer> matriculasMicroDeUmaMatriculaMacro = super.listarTodasAsMatriculasMicroDeUmaMatriculaMacro(matricula);
            return super.retornarSeHouveAlteracaoMatriculaMicro(matriculasMicroDeUmaMatriculaMacro, refFatura);
        }
        return false;
    }
}
