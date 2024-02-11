package moduloFaturamento.service.common;

import moduloFaturamento.model.common.HidrometroRetirado;

public interface HidrometroRetiradoService {

    HidrometroRetirado retornarEntidadeUltimoHidrometroRetiradoDaMatriculaImovel(Integer matriculaImovel);
    Boolean retornarSeMatriculaImovelPossuiAlgumHistoricoHidrometro(Integer matriculaImovel);

}
