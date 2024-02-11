package moduloFaturamento.regras.diaUtilCalendario;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class DiaUtilCalendarioMunicipioVitoriaConsultaRegra extends DiaUtilCalendarioRegra{

    public Boolean retornarDataInformadaNaoPertenceAUmDiaUtil(LocalDate data){
        boolean diaNaoUtil = false;
        boolean caiuEmUmDiaDeFeriado = super.retornarSeDataInfomadaEhUmFeriadoVitoriaOuNacional(data);
        boolean caiuEmUmFinalDeSemana = super.retornarSeDataInformadaEstaEmUmFinalDeSemana(data);

        if(caiuEmUmDiaDeFeriado){
            diaNaoUtil = true;
        }

        if(caiuEmUmFinalDeSemana && !caiuEmUmDiaDeFeriado){
            diaNaoUtil = true;
        }
        return diaNaoUtil;
    }
}
