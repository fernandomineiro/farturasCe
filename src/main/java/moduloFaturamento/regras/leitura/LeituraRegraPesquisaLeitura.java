package moduloFaturamento.regras.leitura;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.model.Leitura;
import moduloFaturamento.repository.LeituraRepository;

@Service
public class LeituraRegraPesquisaLeitura {

    @Autowired
    private LeituraRepository repository;

    public List<Leitura> regraParaListarLeituras(Integer cdCidade, Short ciclo, Integer referencia) {

        List<Integer> listaDeMatriculasRelacionadoAFase01 = repository.buscarMatriculasNafase01(cdCidade, ciclo, referencia);

        Integer referenciaAnterior = buscarReferenciaAnterior(referencia);        

        return repository.findByIdLeituraFaturamentoMatriculaImovelInAndIdLeituraFaturamentoRefFaturaOrderBySeqRotaAsc(listaDeMatriculasRelacionadoAFase01, referenciaAnterior);
        
    }  

    // Métodos privados
    public Integer buscarReferenciaAnterior(Integer referencia) {

        Integer ano = referencia / 100;
        Integer mes = referencia % 100;

        if ((mes - 1) == 0) {
            ano --;
            mes = 12;            
        } else {
            mes --;
        }

        String format = String.format("%d%02d", ano, mes);

        return Integer.valueOf(format);
    }

}
