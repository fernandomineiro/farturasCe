package moduloFaturamento.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoDaFaturaListaDasSituacoesDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoDaFaturaPorDeliberacaoDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaCadastroDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaContasEmAbertoDoClienteDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaEnvioDaSimulacaoDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaListaEmailDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSelecionarFaturaDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSelecionarFaturaParaParcelamentoDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSimulacaoPagamentoDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSimulacaoRespostaDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFauraSelecioadoDTO;
import moduloFaturamento.dto.parcelamentoFatura.projection.ParcelamentoFaturaCabecalhoProjectionDTO;
import moduloFaturamento.dto.parcelamentoFatura.projection.ParcelamentoFaturaPesquisaResponseProjectionDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSimuladoDTO;

public interface ParcelamentoFaturaService {

    GenericoWrapperDTO<List<ParcelamentoFaturaPesquisaResponseProjectionDTO>> buscarFaturasParaCliente(Integer cdCliente, Short dvCliente, String cpfOuCnpj, 
        Pageable pageable);

    ParcelamentoFaturaCabecalhoProjectionDTO buscarCabecalhoDaFaturaEmAberto(String cdCliente, Integer matriculaImovel);

    ParcelamentoFaturaContasEmAbertoDoClienteDTO listaDeFaturaEmAbertoDoClienteDaFaturaEMatriculaImovel(String cdCliente, Integer matriculaImovel);

    ParcelamentoFaturaSimulacaoRespostaDTO simularNegociacaoDivida(ParcelamentoFaturaSelecionarFaturaDTO parcelamentoFaturaSimulacaoDTO);

    ParcelamentoFaturaSimulacaoPagamentoDTO simularParcelamentoFatura(ParcelamentoFaturaSelecionarFaturaParaParcelamentoDTO parcelamentoFaturaSimulacaoDTO);

    LocalDate buscarProximoDiaUtil(Integer cdCidade);

    ParcelamentoFaturaListaEmailDTO buscarEmailDoCliente(Integer cdCliente);

    Void enviarEmailComPropostaDoParcelamento(ParcelamentoFaturaEnvioDaSimulacaoDTO envioDaSimulacaoDTO, String token);

    void cadastrarParcelamento(ParcelamentoFaturaCadastroDTO cadastroDTO, String token);

    String buscarNome(Integer cdCliente, String cpfOuCnpj);

    ParcelamentoFaturaContasEmAbertoDoClienteDTO quadroRefrenciasFaturaSelecioanda(ParcelamentoFauraSelecioadoDTO parcelamentoFaturaSimulacaoDTO);

    ParcelamentoDaFaturaListaDasSituacoesDTO buscarSituacaoDoParcelamento(Integer matriculaImovel, Integer cdCliente);

    ParcelamentoDaFaturaPorDeliberacaoDTO informacaoDeliberacao(Integer idParametroIncentivoCliente, Integer cdCliente, Pageable pageable);
    
}
