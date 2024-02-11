package moduloFaturamento.service.common;

import moduloFaturamento.model.common.HidrometroInstalado;

public interface HidrometroInstaladoService {

    HidrometroInstalado retornarEntidadeUltimoHidrometroInstaladoDaMatriculaImovel(Integer matriculaImovel);
    Boolean retornarSeMatriculaImovelPossuiHidrometroInstalado(Integer matriculaImovel);
}
