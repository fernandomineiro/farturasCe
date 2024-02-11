package moduloFaturamento.service.impl.common;

import moduloFaturamento.model.common.HidrometroInstalado;
import moduloFaturamento.repository.HidrometroInstaladoRepository;
import moduloFaturamento.service.common.HidrometroInstaladoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class HidrometroInstaladoServiceImpl implements HidrometroInstaladoService {

    @Autowired
    private HidrometroInstaladoRepository hidrometroInstaladoRepository;

    @Override
    public HidrometroInstalado retornarEntidadeUltimoHidrometroInstaladoDaMatriculaImovel(Integer matriculaImovel) {
        return this.hidrometroInstaladoRepository.buscarUltimoHidrometroInstaladoPelaMatriculaImovel(matriculaImovel);
    }

    @Override
    public Boolean retornarSeMatriculaImovelPossuiHidrometroInstalado(Integer matriculaImovel){
        HidrometroInstalado hidrometroInstalado = this.retornarEntidadeUltimoHidrometroInstaladoDaMatriculaImovel(matriculaImovel);

        return hidrometroInstalado != null;
    }


}
