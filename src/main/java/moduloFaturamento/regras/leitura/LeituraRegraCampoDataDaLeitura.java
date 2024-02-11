package moduloFaturamento.regras.leitura;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.dto.leitura.LeituraMostraDataDTO;
import moduloFaturamento.repository.LeituraRepository;
import moduloFaturamento.util.ConverterData;

@Service
public class LeituraRegraCampoDataDaLeitura {

    @Autowired
    private LeituraRepository repository;

    public LeituraMostraDataDTO regraParaBuscarDataDaLeiturasPrevista(Integer cdCidade, Short ciclo, Integer referencia) {

        LocalDate dataPesquisada = ConverterData.integerEmLocalDateDataFormatoDB(repository.verificarDatadaleituraP(cdCidade, ciclo, referencia));

        return new LeituraMostraDataDTO(dataPesquisada);        
    }

}
