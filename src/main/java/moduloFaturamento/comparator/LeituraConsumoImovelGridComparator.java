package moduloFaturamento.comparator;

import moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelRespostaGridProjectionDTO;

import java.util.ArrayList;
import java.util.List;

public class LeituraConsumoImovelGridComparator extends AbstractDTOComparator<LeituraConsumoImovelRespostaGridProjectionDTO> {

    @Override
    public int compare(LeituraConsumoImovelRespostaGridProjectionDTO o1, LeituraConsumoImovelRespostaGridProjectionDTO o2) {

        switch (getCampo()) {

            case "id":
                return compareSafeForNull(o1.getId(), o2.getId());

            case "referencia":
                return compareSafeForNull(o1.getReferencia(), o2.getReferencia());

            case "dataLeitura":
                return compareSafeForNull(o1.getDataLeitura(), o2.getDataLeitura());

            case "leitura":
                return compareSafeForNull(o1.getLeitura(), o2.getLeitura());

            case "leituraCriada":
                return compareSafeForNull(o1.getLeituraCriada(), o2.getLeituraCriada());

            case "ocorrenciaCodigo":

                List<String> o1L = new ArrayList<>();
                List<String> o2L = new ArrayList<>();

                if(o1.getOcorrenciaCodigo() != null){
                    o1L.add(o1.getOcorrenciaCodigo().toString());
                }
                if(o1.getOcorrencia2Codigo() != null){
                    o1L.add(o1.getOcorrencia2Codigo().toString());
                }
                if(o1.getOcorrencia3Codigo() != null){
                    o1L.add(o1.getOcorrencia3Codigo().toString());
                }

                if(o2.getOcorrenciaCodigo() != null){
                    o2L.add(o2.getOcorrenciaCodigo().toString());
                }
                if(o2.getOcorrencia2Codigo() != null){
                    o2L.add(o2.getOcorrencia2Codigo().toString());
                }
                if(o2.getOcorrencia3Codigo() != null){
                    o2L.add(o2.getOcorrencia3Codigo().toString());
                }

                return compareSafeForNull(o1L.toString(), o2L.toString());

            case "medido":
                return compareSafeForNull(o1.getMedido(), o2.getMedido());

            case "mediaDiaria":
                return compareSafeForNull(o1.getMediaDiaria(), o2.getMediaDiaria());

            case "diasVenda":
                return compareSafeForNull(o1.getDiasVenda(), o2.getDiasVenda());

            case "diasConsumo":
                return compareSafeForNull(o1.getDiasConsumo(), o2.getDiasConsumo());

            case "consumoFaturarAgua":
                return compareSafeForNull(o1.getConsumoFaturarAgua(), o2.getConsumoFaturarAgua());

            case "consumoFaturarEsgoto":
                return compareSafeForNull(o1.getConsumoFaturarEsgoto(), o2.getConsumoFaturarEsgoto());

            case "tipoConsumoFaturadoDescricao":
                return compareSafeForNull(o1.getTipoConsumoFaturadoDescricao(), o2.getTipoConsumoFaturadoDescricao());

            case "anormalidade":

                List<String> a1L = new ArrayList<>();
                List<String> a2L = new ArrayList<>();

                if(o1.getAnormalidade() != null){
                    a1L.add(o1.getAnormalidade());
                }
                if(o2.getAnormalidade() != null){
                    a2L.add(o2.getAnormalidade());
                }

                return compareSafeForNull(a1L.toString(), a2L.toString());

            case "dataUltimaAlteracao":
                return compareSafeForNull(o1.getDataUltimaAlteracao(), o2.getDataUltimaAlteracao());

            default:
                return 0;
        }
    }

}
