package moduloFaturamento.service.impl.common;

import moduloFaturamento.dto.situacaoLigacaoEsgoto.SituacaoLigacaoEsgotoDTO;
import moduloFaturamento.dto.situacaoLigacaoEsgoto.mapper.SituacaoLigacaoEsgotoMapper;
import moduloFaturamento.model.common.SituacaoLigacaoEsgoto;
import moduloFaturamento.repository.common.SituacaoLigacaoEsgotoRepository;
import moduloFaturamento.service.common.SituacaoLigacaoEsgotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SituacaoLigacaoEsgotoServiceImpl implements SituacaoLigacaoEsgotoService {

    @Autowired
    private SituacaoLigacaoEsgotoRepository situacaoLigacaoEsgotoRepository;
    @Autowired
    private SituacaoLigacaoEsgotoMapper situacaoLigacaoEsgotoMapper;

    @Override
    public List<SituacaoLigacaoEsgotoDTO> buscarTodasAsLigacoes() {
        List<SituacaoLigacaoEsgoto> all = this.situacaoLigacaoEsgotoRepository.findAll();
        return this.situacaoLigacaoEsgotoMapper.toDto(all);
    }
}
