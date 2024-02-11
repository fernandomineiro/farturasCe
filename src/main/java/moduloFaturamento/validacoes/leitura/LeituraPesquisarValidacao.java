package moduloFaturamento.validacoes.leitura;

import org.springframework.stereotype.Service;

@Service
public class LeituraPesquisarValidacao extends LeituraValidacao{

    public void gerenciarValidacaoPesquisaLeitura(Integer cidade, short ciclo, Integer referencia) {
		super.gerarExcessaoSeDataEstaFormatoCorreto(referencia);
		super.gerarExcessaoSeNaoExisteMatricula(cidade, ciclo, referencia);		
	}

	public void gerenciarValidacaoCampoDataDaLeitura(Integer referencia) {
		super.gerarExcessaoSeDataEstaFormatoCorreto(referencia);
	
	}
    
}
