package moduloFaturamento.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroDetalheAtualizarDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroDetalheCadastroDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroDetalheRespostaConsultaDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroRespostaAtualizarDetalheDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroRespostaCadastroDetalheDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.projection.IncentivoClienteParametroDetalheRespostaGridProjectionDTO;
import moduloFaturamento.model.IncentivoCliente;

public interface IncentivoClienteParametroDetalheService {

    GenericoWrapperDTO<List<IncentivoClienteParametroDetalheRespostaGridProjectionDTO>> listarGrid(
            Integer idParametroIncentivoCliente, Pageable pageable);

    IncentivoClienteParametroDetalheRespostaConsultaDTO buscarDetalheParametroIncentivoPorId(
            Integer idDetalheParametroIncentivoCliente);

    List<IncentivoClienteParametroRespostaCadastroDetalheDTO> executarFluxoCadastrarDetalheParametroIncentivo(
            List<IncentivoClienteParametroDetalheCadastroDTO> dto,
            IncentivoCliente incentivoClienteDTO, String token);

    List<IncentivoClienteParametroRespostaAtualizarDetalheDTO> executarFluxoEditarDetalheParametroIncentivo(
            List<IncentivoClienteParametroDetalheAtualizarDTO> dto,
            IncentivoCliente incentivoClienteDTO, String token);
}
