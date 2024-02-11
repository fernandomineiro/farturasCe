package moduloFaturamento.service;

import moduloFaturamento.dto.GenericoDownloadArquivoAnexoDTO;
import moduloFaturamento.dto.leitura.LeituraAnexoDTO;
import moduloFaturamento.dto.leituraAnexo.LeituraAnexoRespostaWrapperDTO;
import moduloFaturamento.dto.leituraConsumoImovel.LeituraConsumoImovelSalvarAnexoDTO;
import moduloFaturamento.model.Leitura;
import org.springframework.data.domain.Pageable;

public interface LeituraAnexoService {

    LeituraAnexoRespostaWrapperDTO buscarLeiturasPorIdLeitura(Long idLeitura, Pageable pageable);
    void executarFluxoSalvarAnexoConsumoImovel(LeituraConsumoImovelSalvarAnexoDTO dto, String token);
    void executarFluxoSalvarAnexoLeituraFatura(Leitura leitura, LeituraAnexoDTO anexoDTO , String token);
    void executarFluxoRemoverAnexo(Long id, String token);
    GenericoDownloadArquivoAnexoDTO retornarArquivoParaDownload(Long id);
}
