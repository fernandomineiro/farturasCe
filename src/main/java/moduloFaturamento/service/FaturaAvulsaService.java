package moduloFaturamento.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaCadastrarDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaCampoLancamentoDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaLocalidadeDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaParamServicoDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaPesquisaDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaPesquisaFilterDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaTotalServicoDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaValidarSSFilterDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaVerificarClienteDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaBuscarMatriculaImovelProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaEnderecoClienteProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaFiltroBaixaProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaLancamentosProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaServicosValidosProjectionDTO;

public interface FaturaAvulsaService {

    GenericoWrapperDTO<List<FaturaAvulsaPesquisaDTO>>buscarFaturaAvulsas(FaturaAvulsaPesquisaFilterDTO faturaAvulsaPesquisaFilterDTO, Pageable pageable);

    FaturaAvulsaEnderecoClienteProjectionDTO buscarEnderedoCliente(Integer cdCliente);

    FaturaAvulsaCampoLancamentoDTO buscarDadosFaturaAvulsa(Long id);

    List<FaturaAvulsaLancamentosProjectionDTO> buscarLancamentoFaturaAvulsa(Long id);

    List<FaturaAvulsaLocalidadeDTO> listaLocalidade();

    FaturaAvulsaBuscarMatriculaImovelProjectionDTO buscarMatriculaImovel(Integer matriculaImovel, Short dvMatriculaImovel);

    void cadastrarUmFaturaAvusa(FaturaAvulsaCadastrarDTO cadastroFaturaAvulsaDTO, String token);

    FaturaAvulsaVerificarClienteDTO retornarImpostoDeUmClienteCliente(Integer cdCliente, Short dvCliente);

    List<FaturaAvulsaServicosValidosProjectionDTO> buscarServicosValidosParaCadastrarFaturaAvulsa(Short cdCidade);
    
    List<FaturaAvulsaFiltroBaixaProjectionDTO>  buscarTipoBaixa();

    void encerrarUmaFaturaAvulsa(Long id, String token);

    FaturaAvulsaTotalServicoDTO verificarServicoSelecioandoETotalLancamento(FaturaAvulsaParamServicoDTO listaServico);

    boolean validarUmaSS(FaturaAvulsaValidarSSFilterDTO faturaAvulsaValidarSSDTO);
    
}
