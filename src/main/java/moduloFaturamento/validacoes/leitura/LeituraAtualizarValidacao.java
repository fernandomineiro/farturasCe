package moduloFaturamento.validacoes.leitura;

import java.util.List;

import org.springframework.stereotype.Service;

import moduloFaturamento.dto.leitura.LeituraParaAtualizarDTO;

@Service
public class LeituraAtualizarValidacao extends LeituraValidacao {

    public void gerenciarValidacaoPesquisaLeitura(List<LeituraParaAtualizarDTO> listaDeLeituraDTO) {
        super.gerarExcessaoSeDataEstaFormatoCorreto(listaDeLeituraDTO.get(0).getRefFatura());
        super.gerarExcessaoCasoFalteCampoLeituraOuOcorrencia(listaDeLeituraDTO);
	}

    
}