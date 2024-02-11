package moduloFaturamento.service.impl.common;

import moduloFaturamento.model.common.Imovel;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.service.common.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ImovelServiceImpl implements ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    @Override
    public Boolean retornarSeImovelEstaSomenteNoServicoColetaDeEsgoto(Integer matricula) {
        Imovel imovel = this.imovelRepository.findByMatriculaImovelAndDataHoraExclusaoIsNull(matricula);
        boolean imovelEstaSomenteNoServicoDeColetaDeEsgoto = false;

        final Integer LIGACAO_ATIVA = 1;
        final Integer LIGACAO_INATIVA = 3;

        if(imovel != null){
            Integer situacaoLigacaoAgua = imovel.getSituacaoLigacaoAgua().getId();
            Integer situacaoLigacaoEsgoto = imovel.getSituacaoLigacaoEsgoto().getId();

            if(situacaoLigacaoAgua == LIGACAO_INATIVA && situacaoLigacaoEsgoto == LIGACAO_ATIVA){
                imovelEstaSomenteNoServicoDeColetaDeEsgoto = true;
            }
        }
        return imovelEstaSomenteNoServicoDeColetaDeEsgoto;
    }
}
