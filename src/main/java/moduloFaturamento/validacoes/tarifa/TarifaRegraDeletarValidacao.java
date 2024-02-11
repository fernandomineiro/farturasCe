package moduloFaturamento.validacoes.tarifa;

import org.springframework.stereotype.Service;

@Service
public class TarifaRegraDeletarValidacao extends TarifaValidacao {
    
    public void gerenciarValidacaoVerificarExclucao(Integer dataTarifa, Integer idTarifa) {
        super.gerarExcessaoDataNaoExistente(dataTarifa, idTarifa); 
        super.gerarExcessaoDataAindaSendoUtilizadoFatura(dataTarifa, idTarifa);        
        super.gerarExcessaoDataAindaSendoUtilizadoCronograma(dataTarifa, idTarifa);       
    }



}
