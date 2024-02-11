package moduloFaturamento.validacoes.leitura;

import moduloFaturamento.dto.leitura.LeituraParaAtualizarDTO;
import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.IdLeitura;
import moduloFaturamento.model.Leitura;
import moduloFaturamento.repository.LeituraRepository;
import moduloFaturamento.util.ConverterData;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class LeituraValidacao {

	@Autowired
	private LeituraRepository repository;

	protected void gerarExcecaoLeituraNaoEncontrada(Long id){
		Optional<Leitura> leituraOptional = this.repository.findById(id);
		if(leituraOptional.isEmpty()){
			throw new MsgGenericaPersonalizadaException("Leitura não encontrada");
		}
	}

	protected void gerarExcecaoLeituraNaoEncontrada(Integer matricula, LocalDate refFatura){
		Optional<Leitura> leituraOptional = this.repository.findById(new IdLeitura(matricula, ConverterData.localDateEmIntegerReferenciaFormatoDB(refFatura)));
		if(leituraOptional.isEmpty()){
			throw new MsgGenericaPersonalizadaException("Leitura não encontrada");
		}
	}

	protected void gerarExcessaoSeNaoExisteMatricula(Integer cidade, short ciclo, Integer referencia) {

		Integer verificarSeExisteUmaMatricula = repository.verificarPeloMenosUmLeituraNaFase01(cidade, ciclo, referencia);

		if (verificarSeExisteUmaMatricula == null || verificarSeExisteUmaMatricula == 0) {

			throw new MsgGenericaPersonalizadaException("Não existe matricula para cidade, ciclo e referência.");
		}

	}

	protected void gerarExcessaoSeDataEstaFormatoCorreto(Integer referencia) {

		LocalDate dataEmInteiro = ConverterData.integerEmLocalDateReferenciaFormatoDB(referencia);

		if (dataEmInteiro == null) {

			throw new MsgGenericaPersonalizadaException("Data Está com formato incorreto.");
		}

	}

	protected void gerarExcessaoCasoFalteCampoLeituraOuOcorrencia(List<LeituraParaAtualizarDTO> listaDeLeituraDTO) {

		for (LeituraParaAtualizarDTO leituraParaAtualizarFiltroDTO : listaDeLeituraDTO) {

			if (leituraParaAtualizarFiltroDTO.getLeitura() == null && leituraParaAtualizarFiltroDTO.getIdOcorrencia() == null) {

				throw new MsgGenericaPersonalizadaException(
						"Campo leitura ou Ocorrencia devem ser preenchidos na matricula " + leituraParaAtualizarFiltroDTO.getMatricula());
			}
		}
	}

	protected void gerarExcessaoTamanhoDoArquivo (Integer length){
		if(length > 5000 * 1024){
			throw new MsgGenericaPersonalizadaException("Arquivo maior que 5MB");
        }
	}
}
