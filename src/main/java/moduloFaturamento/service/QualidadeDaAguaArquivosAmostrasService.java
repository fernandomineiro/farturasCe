package moduloFaturamento.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.amostrasRealizadas.projection.PesquisaAmostrasArquivosProjectionRespostaDTO;
import moduloFaturamento.dto.amostrasRealizadas.projection.PesquisaAmostrasRealizadasProjectionRespostaDTO;

public interface QualidadeDaAguaArquivosAmostrasService {

	GenericoWrapperDTO<List<PesquisaAmostrasArquivosProjectionRespostaDTO>> pesquisaAmostrasArquivos(Pageable pageable, String token);

	GenericoWrapperDTO<List<PesquisaAmostrasRealizadasProjectionRespostaDTO>> pesquisaAmostrasRealizadas(Integer refAmostras, Pageable pageable, String token);

	Resource arquivoModeloAmostrasMinimasExigidas(String token);

	Resource arquivoModeloAmostrasMinimasRealizadas(String token);

	Resource downloadAmostrasExigidas(Integer dtInicio, String token);

	Resource downloadAmostrasRealizadas(Integer refAmostras, String token);

	void uploadAmostrasExigidas(MultipartFile file, String token);

	void uploadAmostrasRealizadas(MultipartFile file, String token);

	void removerAmostrasExigidas(Integer dtInicio, String token);

	void removerAmostrasRealizadas(Integer refAmostras, String token);

}
