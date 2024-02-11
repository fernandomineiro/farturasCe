package moduloFaturamento.regras.leitura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.repository.LeituraRepository;

/**
 * LeituraRegraVerificarSeExisteFase01
 */
@Service
public class LeituraRegraVerificarSeExisteFase01NaReferencia {

    @Autowired
    private LeituraRepository repository;

    public boolean regraVerificarSePodeMudarFase01ParaFase02EmCronogramaFatura(Integer cdCidade, Short ciclo, Integer referencia) {

        Integer buscandoSeExisteUmaMatriculaNaFase01 = repository.verificarPeloMenosUmLeituraNaFase01(cdCidade, ciclo, referencia);

        if(buscandoSeExisteUmaMatriculaNaFase01 == null){
            return true;
        } else {
            return false;
        }
        
    }

    
}