package moduloFaturamento.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.credito.CreditoAtualizarDTO;
import moduloFaturamento.dto.credito.CreditoCadastrarDTO;
import moduloFaturamento.dto.credito.CreditoCampoLancadoPesquisaDTO;
import moduloFaturamento.dto.credito.CreditoEncerrarDTO;
import moduloFaturamento.dto.credito.CreditoLancadoDTO;
import moduloFaturamento.dto.credito.CreditoParaEditarRespostaDTO;
import moduloFaturamento.dto.credito.CreditoPesquisaCreditoLancadoFilterDTO;
import moduloFaturamento.dto.credito.CreditoPesquisaDTO;
import moduloFaturamento.dto.credito.projection.CreditoServicosValidosprojectionDTO;
import moduloFaturamento.dto.leituraConsumoImovel.projection.EnderecoBasicoDoImovelProjectionDTO;

public interface CreditoService {

    GenericoWrapperDTO<List<CreditoPesquisaDTO>> pesquisaCreditoPorMatricula(Integer matricula, short dv, Pageable pageable);
    CreditoCampoLancadoPesquisaDTO pesquisarCampoCredito(CreditoPesquisaCreditoLancadoFilterDTO creditoLancadoDTO);
    GenericoWrapperDTO<List<CreditoLancadoDTO>> pesquisarCreditoOcorrencias(CreditoPesquisaCreditoLancadoFilterDTO creditoLancadoDTO,  Pageable pageable);
    EnderecoBasicoDoImovelProjectionDTO pesquisarEnderecoCompleto(Integer matricula);
    CreditoParaEditarRespostaDTO buscarDadosEditarCredito(Long id);
    void atualizarCredito(CreditoAtualizarDTO atualizarDTO, String token);
    List<CreditoServicosValidosprojectionDTO> pesquisarServicosQuePodemSerSelcionadoNoCredito();
    void cadastrarCredito(CreditoCadastrarDTO cadastroDTO, String token);
    void encerrarCredito(CreditoEncerrarDTO creditoEncerrarDTO, String token);
    
}
