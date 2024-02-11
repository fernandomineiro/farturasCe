package moduloFaturamento.service;

import moduloFaturamento.model.IncentivoClienteDetalhe;
import moduloFaturamento.model.IncentivoClienteHistoricoDetalhe;

import java.util.List;

public interface IncentivoClienteParametroDetalheLigacaoAguaService {

    void executarFluxoCadastrarLigacao(IncentivoClienteDetalhe incentivoClienteDetalhe, List<Integer> situacaoLigacaoAgua, Long idUsuario, IncentivoClienteHistoricoDetalhe historicoDetalhe);
    void executarFluxoRemoverTodasLigacoesDeUmParametroIncentivo(IncentivoClienteDetalhe incentivoClienteDetalhe);
}
