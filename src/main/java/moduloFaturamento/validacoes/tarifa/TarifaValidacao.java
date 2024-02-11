package moduloFaturamento.validacoes.tarifa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.dto.tarifa.PesquisaTarifaRespostaDTO;
import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.Tarifa;
import moduloFaturamento.repository.TarifaRepository;
import moduloFaturamento.util.ConverterData;

public abstract class TarifaValidacao {

    @Autowired
    private TarifaRepository repository;

    protected void gerarExcessaoDataTarifaInvalida(LocalDate dataTarifa) {

        if (dataTarifa != null) {
            Integer dataTarifaInteger = ConverterData.localDateEmIntegerDataFormatoDB(dataTarifa);
            
            if (dataTarifaInteger == null) {
                throw new MsgGenericaPersonalizadaException(String.format("Data da Tarifa inválida."));
            }
        }
	}

    protected void gerarExcessaoDataNaoExistente(Integer dataTarifa, Integer idTarifa){

        List<Tarifa> verificarExisteListaParaDeletar = repository.findByIdIdTarifaAndIdDataTarifa(idTarifa, dataTarifa);

        if (verificarExisteListaParaDeletar.isEmpty()) {
            throw new MsgGenericaPersonalizadaException(String.format("Não foi encontrado Tarifas com os dados fornecidos."));
        }
    }

    protected void gerarExcessaoDataAindaSendoUtilizadoFatura(Integer dataTarifa, Integer idTarifa){

        Integer existeTaridaDataEmFatura = repository.pesquisarTarifaDataEmFatura(idTarifa, dataTarifa);

        if (existeTaridaDataEmFatura != null) {
            throw new MsgGenericaPersonalizadaException(String.format("Código de Tarifa e Data sendo utilizado em Fatura"));
        }
    }

    protected void gerarExcessaoDataAindaSendoUtilizadoCronograma(Integer dataTarifa, Integer idTarifa){
        
        Integer verificarTarifaExisteCronogramaLocalidade = repository.pesquisarTarifaCronogramaLocalidade(idTarifa, dataTarifa);

        if (verificarTarifaExisteCronogramaLocalidade != null) {
            throw new MsgGenericaPersonalizadaException(String.format("Código de Tarifa e Data sendo utilizado em Cronograma Faturamento com Localidade"));
        }
    }

    protected void gerarExcessaoSeListaEstiverVazia(List<PesquisaTarifaRespostaDTO> listaTarifas){

        if (listaTarifas.size() == 0) {
            throw new MsgGenericaPersonalizadaException("Dados não encontrado dados com essa Localidade");
        }
    }
    
}
