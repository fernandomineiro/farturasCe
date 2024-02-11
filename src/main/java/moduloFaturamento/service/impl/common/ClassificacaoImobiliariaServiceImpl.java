package moduloFaturamento.service.impl.common;

import moduloFaturamento.dto.classificacaoImobiliaria.LeituraConsumoImovelCabecalhoClassificacaoImovelDTO;
import moduloFaturamento.dto.classificacaoImobiliaria.mapper.LeituraConsumoImovelCabecalhoClassificacaoImovelMapper;
import moduloFaturamento.repository.common.ClassificacaoImobiliariaRepository;
import moduloFaturamento.service.common.ClassificacaoImobiliariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClassificacaoImobiliariaServiceImpl implements ClassificacaoImobiliariaService {
    @Autowired
    private ClassificacaoImobiliariaRepository classificacaoImobiliariaRepository;
    @Autowired
    private LeituraConsumoImovelCabecalhoClassificacaoImovelMapper leituraConsumoImovelCabecalhoClassificacaoImovelMapper;

    @Override
    public List<LeituraConsumoImovelCabecalhoClassificacaoImovelDTO> buscarListaClassificacaoImobiliariaParaCabecalhoLeituraConsumoImovel(Integer matriculaImovel) {
        return this.leituraConsumoImovelCabecalhoClassificacaoImovelMapper.toDto(this.classificacaoImobiliariaRepository.findByImovel_MatriculaImovelAndAndDataHoraFimIsNull(matriculaImovel));
    }

    @Override
    public Integer buscarTotalEconomias(Integer matriculaImovel){
        List<LeituraConsumoImovelCabecalhoClassificacaoImovelDTO> dto = this.buscarListaClassificacaoImobiliariaParaCabecalhoLeituraConsumoImovel(matriculaImovel);

        Integer totalEconomiasClassificacoImovel = 0;

        for(LeituraConsumoImovelCabecalhoClassificacaoImovelDTO leituraConsumoImovelCabecalhoClassificacaoImovelDTO : dto){
            totalEconomiasClassificacoImovel += leituraConsumoImovelCabecalhoClassificacaoImovelDTO.getEconomias();
        }

        return totalEconomiasClassificacoImovel;

    }
}
