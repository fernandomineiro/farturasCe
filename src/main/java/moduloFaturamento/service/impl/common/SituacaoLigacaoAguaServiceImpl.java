package moduloFaturamento.service.impl.common;

import moduloFaturamento.dto.situacaoLigacaoAgua.SituacaoLigacaoAguaDTO;
import moduloFaturamento.dto.situacaoLigacaoAgua.mapper.SituacaoLigacaoAguaMapper;
import moduloFaturamento.model.common.SituacaoLigacaoAgua;
import moduloFaturamento.repository.common.SituacaoLigacaoAguaRepository;
import moduloFaturamento.service.common.SituacaoLigacaoAguaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SituacaoLigacaoAguaServiceImpl implements SituacaoLigacaoAguaService {

    @Autowired
    private SituacaoLigacaoAguaRepository situacaoLigacaoAguaRepository;
    @Autowired
    private SituacaoLigacaoAguaMapper situacaoLigacaoAguaMapper;

    @Override
    public List<SituacaoLigacaoAguaDTO> buscarTodasAsLigacoes() {
        List<SituacaoLigacaoAgua> all = this.situacaoLigacaoAguaRepository.findAll();
        return this.situacaoLigacaoAguaMapper.toDto(all);
    }
}
