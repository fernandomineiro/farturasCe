package moduloFaturamento.service.impl.common;

import moduloFaturamento.dto.imovelArrecadacao.ArrecadacaoImovelDTO;
import moduloFaturamento.dto.imovelArrecadacao.mapper.ArrecadacaoImovelConsultaMapper;
import moduloFaturamento.repository.common.ArrecadacaoImovelRepository;
import moduloFaturamento.service.common.ImovelArrecadacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ImovelArrecadacaoServiceImpl implements ImovelArrecadacaoService {

    @Autowired
    private ArrecadacaoImovelRepository arrecadacaoImovelRepository;
    @Autowired
    private ArrecadacaoImovelConsultaMapper arrecadacaoImovelConsultaMapper;

    @Override
    public ArrecadacaoImovelDTO buscarArrecadacaoPorMatriculaImovel(Integer matriculaImovel) {
        return this.retornarArrecadacaoImovelAtravesRequisicaoModuloDebitoContaCorrente(matriculaImovel);
    }

    private ArrecadacaoImovelDTO retornarArrecadacaoImovelAtravesRequisicaoModuloDebitoContaCorrente(Integer matriculaImovel) {
        return this.arrecadacaoImovelConsultaMapper.toDto(this.arrecadacaoImovelRepository.findByMatriculaImovel(matriculaImovel));
    }
}
