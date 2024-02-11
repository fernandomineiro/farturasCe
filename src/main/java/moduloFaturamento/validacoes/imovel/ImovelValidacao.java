package moduloFaturamento.validacoes.imovel;


import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.common.Imovel;
import moduloFaturamento.repository.common.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

abstract class ImovelValidacao {
    @Autowired
    private ImovelRepository imovelRepository;

    protected void gerarExcecaoMatriculaImovelInexistente(Integer matriculaImovel, Short dv){
        Optional<Imovel> imovelOptional = this.imovelRepository.findByMatriculaImovelAndDvAndDataHoraExclusaoIsNull(matriculaImovel, dv);
        if(imovelOptional.isEmpty()){
            throw new MsgGenericaPersonalizadaException("Imóvel não encontrado");
        }
    }

    protected void gerarExcecaoMatriculaImovelInexistente(Integer matriculaImovel){
        Imovel imovel = this.imovelRepository.findByMatriculaImovelAndDataHoraExclusaoIsNull(matriculaImovel);
        if(imovel == null){
            throw new MsgGenericaPersonalizadaException("Imóvel não encontrado");
        }
    }
}
