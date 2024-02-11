package moduloFaturamento.service;

import org.springframework.data.domain.Pageable;

import moduloFaturamento.dto.GenericoDownloadArquivoAnexoDTO;
import moduloFaturamento.dto.incentivoCliente.anexo.ParametroIncentivoAnexoDTO;
import moduloFaturamento.dto.incentivoCliente.anexo.ParametroIncentivoClienteAnexoRespostaWrapperDTO;
import moduloFaturamento.dto.incentivoCliente.anexo.ParametroIncentivoClienteRespostaAnexoDTO;
import moduloFaturamento.model.IncentivoCliente;

public interface IncentivoClienteParametroAnexoService {

    ParametroIncentivoClienteRespostaAnexoDTO executarFluxoSalvarAnexo(ParametroIncentivoAnexoDTO dto, IncentivoCliente salvo, String token);
    ParametroIncentivoClienteAnexoRespostaWrapperDTO buscarAnexoIncentivoCliente(Integer id, Pageable pageable);
    GenericoDownloadArquivoAnexoDTO retornarArquivoParaDownload(Long id);
}
