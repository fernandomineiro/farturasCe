package moduloFaturamento.regras.diaUtilCalendario;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class DiaUtilCalendarioPesquisaPorCidadeRegra extends DiaUtilCalendarioRegra{

    public LocalDate retornarOProximoDiaUtil(LocalDate data, Integer cdCidade){

        while (super.retornarSeDataInformadaEstaEmUmFinalDeSemana(data) ||
                super.retornarSeDataInfomadaEhUmFeriadoMunicipalDaCidadeSelecionada(data, cdCidade)){

            data = data.plusDays(1);
        } 
        return data;
     }
}
