package moduloFaturamento.service.impl;

import moduloFaturamento.dto.tipoConsumoFaturado.TipoConsumoFaturadoDTO;
import moduloFaturamento.dto.tipoConsumoFaturado.mapper.TipoConsumoFaturadoMapper;
import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.TipoConsumoFaturado;
import moduloFaturamento.regras.leituraConsumoImovel.tipoConsumoFaturado.spec.TipoConsumoFaturadoEnum;
import moduloFaturamento.repository.TipoConsumoFaturadoRepository;
import moduloFaturamento.service.TipoConsumoFaturadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TipoConsumoFaturadoServiceImpl implements TipoConsumoFaturadoService {

    @Autowired
    private TipoConsumoFaturadoRepository tipoConsumoFaturadoRepository;
    @Autowired
    private TipoConsumoFaturadoMapper tipoConsumoFaturadoMapper;

    public List<TipoConsumoFaturadoDTO> buscarListaTipoConsumoFaturadoParaLeituraConsumoImovel(LocalDate referencia){
        LocalDate dataLimiteParaAceitarTipoConsumoFaturadoMinino = LocalDate.of(2021,10,1);
        List<TipoConsumoFaturado> tipoConsumoFaturados = this.tipoConsumoFaturadoRepository.buscarListaConsumoFaturadoLeituraConsumoImovel();
        for (int i = 0; i < tipoConsumoFaturados.size(); i++){
            if((referencia.isAfter(dataLimiteParaAceitarTipoConsumoFaturadoMinino)|| referencia.isEqual(dataLimiteParaAceitarTipoConsumoFaturadoMinino)) && tipoConsumoFaturados.get(i).getId().equals(TipoConsumoFaturadoEnum.MINIMO.getTipo())){
                tipoConsumoFaturados.remove(i);
            }
        }
        return this.tipoConsumoFaturadoMapper.toDto(tipoConsumoFaturados);
    }

}
