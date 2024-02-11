package moduloFaturamento.service.impl.common;

import moduloFaturamento.model.common.HidrometroRetirado;
import moduloFaturamento.repository.common.HidrometroRetiradoRepository;
import moduloFaturamento.service.common.HidrometroRetiradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HidrometroRetiradoServiceImpl implements HidrometroRetiradoService {

    @Autowired
    private HidrometroRetiradoRepository hidrometroRetiradoRepository;

    @Override
    public HidrometroRetirado retornarEntidadeUltimoHidrometroRetiradoDaMatriculaImovel(Integer matriculaImovel) {
        return this.hidrometroRetiradoRepository.buscarUltimoHidrometroRetiradoPelaMatriculaImovel(matriculaImovel);
    }

    @Override
    public Boolean retornarSeMatriculaImovelPossuiAlgumHistoricoHidrometro(Integer matriculaImovel){
        List<HidrometroRetirado> historicoHidrometroRetirado = this.hidrometroRetiradoRepository.findByIdHidrometroRetirado_MatriculaImovel(matriculaImovel);

        return historicoHidrometroRetirado != null;
    }
}
