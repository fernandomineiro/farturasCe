package moduloFaturamento.service.impl.common;

import moduloFaturamento.service.common.HidrometroInstaladoService;
import moduloFaturamento.service.common.HidrometroMovimentacaoInstalacaoRetiradaService;
import moduloFaturamento.service.common.HidrometroRetiradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class HidrometroMovimentacaoInstalacaoRetiradaServiceImpl implements HidrometroMovimentacaoInstalacaoRetiradaService {

    @Autowired
    private HidrometroInstaladoService hidrometroInstaladoService;
    @Autowired
    private HidrometroRetiradoService hidrometroRetiradoService;


    @Override
    public Boolean retornarSeHouveHistoricoInstalacaoRetiradaHidrometroPorMatricula(Integer matricula) {
        return hidrometroInstaladoService.retornarSeMatriculaImovelPossuiHidrometroInstalado(matricula) || hidrometroRetiradoService.retornarSeMatriculaImovelPossuiAlgumHistoricoHidrometro(matricula);
    }
}
