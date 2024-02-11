package moduloFaturamento.regras.cronogramaFaturamento;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CronogramaFaturamentoCadastroRegra extends CronogramaFaturamentoRegra{

    public Short obterFaseCadastroCronograma(){
       return super.retornarFaseCadastrarUmNovoCronograma();
    }

    public List<Short> obterListaCidadesDoCronogramaBaseadoCicloEMesReferencia(Short ciclo, LocalDate dataReferencia){
        return super.retornarCidadesCadastradasPorCicloEMesReferencia(ciclo, dataReferencia);
    }





}
