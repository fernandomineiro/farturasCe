package moduloFaturamento.validacoes.common;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.common.Cliente;
import moduloFaturamento.repository.ClienteRepository;
import moduloFaturamento.util.ConverterData;

@Service
public class CommonUtilValidacoes {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public void gerarExcecaoExtensaoInvalida(String nomeArquivo, List<String> listaExtensoes) {
		
		if(!listaExtensoes.contains(extrairExtensao(nomeArquivo).toLowerCase())) {
			throw new MsgGenericaPersonalizadaException(String.format("Arquivo %s é de um tipo não permitido", extrairExtensao(nomeArquivo).toLowerCase()));
		}
	}

	public void gerarExcessaoReferenciaInvalidaFormatoBR(String referencia) {

		LocalDate localDate = ConverterData.stringEmLocalDateReferenciaFormatoBR(referencia);

		if (localDate == null) {
			throw new MsgGenericaPersonalizadaException(String.format("Referência %s inválida.", referencia));
		}
	}

	public void gerarExcessaoDataInvalidaFormatoBR(String data) {

		LocalDate localDate = ConverterData.stringEmLocalDateDataFormatoBR(data);

		if (localDate == null) {
			throw new MsgGenericaPersonalizadaException(String.format("Data %s inválida.", data));
		}
	}

	public void gerarExcessaoReferenciaInvalidaFormatoBR(Integer referencia) {

		LocalDate localDate = ConverterData.integerEmLocalDateReferenciaFormatoBR(referencia);

		if (localDate == null) {
			throw new MsgGenericaPersonalizadaException(String.format("Referência %d inválida.", referencia));
		}
	}

	public void gerarExcessaoDataInvalidaFormatoBR(Integer data) {

		LocalDate localDate = ConverterData.integerEmLocalDateDataFormatoBR(data);

		if (localDate == null) {
			throw new MsgGenericaPersonalizadaException(String.format("Data %d inválida.", data));
		}
	}

	public void gerarExcessaoReferenciaInvalidaFormatoDB(Integer referencia) {

		LocalDate localDate = ConverterData.integerEmLocalDateReferenciaFormatoDB(referencia);

		if (localDate == null) {
			throw new MsgGenericaPersonalizadaException(String.format("Referência %d inválida.", referencia));
		}
	}

	public void gerarExcessaoReferenciaInvalidaFormatoDB(LocalDate data) {

		Integer integerData = ConverterData.localDateEmIntegerReferenciaFormatoDB(data);

		if (integerData == null) {

			throw new MsgGenericaPersonalizadaException(String.format("Data "+data+" inválida."));
		}
	}

	public void gerarExcessaoDataInvalidaFormatoDB(Integer data) {

		LocalDate localDate = ConverterData.integerEmLocalDateDataFormatoDB(data);

		if (localDate == null) {

			throw new MsgGenericaPersonalizadaException(String.format("Data %d inválida.", data));
		}
	}


	public void gerarExcecaoColecaoNula(Collection<?> collection, String collectionName) {

		if (collection == null) {

			throw new MsgGenericaPersonalizadaException(String.format("%s está nulo(a).", collectionName));
		}
	}

	public void gerarExcecaoColecaoVazia(Collection<?> collection, String collectionName) {

		if (collection.isEmpty()) {

			throw new MsgGenericaPersonalizadaException(String.format("%s está vazio(a).", collectionName));
		}
	}

	public void gerarExcecaoColecaoNulaOuVazia(Collection<?> collection, String collectionName) {

		gerarExcecaoColecaoNula(collection, collectionName);
		gerarExcecaoColecaoVazia(collection, collectionName);
	}

	public void gerarExcecaoMapaNulo(Map<?, ?> map, String mapName) {

		if (map == null) {

			throw new MsgGenericaPersonalizadaException(String.format("%s está nulo(a).", mapName));
		}
	}

	public void gerarExcecaoMapaVazio(Map<?, ?> map, String mapName) {

		if (map.isEmpty()) {

			throw new MsgGenericaPersonalizadaException(String.format("%s está vazio(a).", mapName));
		}
	}

	public void gerarExcecaoMapaNuloOuVazio(Map<?, ?> map, String mapName) {

		gerarExcecaoMapaNulo(map, mapName);
		gerarExcecaoMapaVazio(map, mapName);
	}
	
	private static String extrairExtensao(String fileName) {

		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	public void gerarExcecaoValidarCdCliente(Integer cdCliente, Short dvCliente){

		Optional<Cliente> cliente = clienteRepository.findByCdClienteAndDvCliente(cdCliente, dvCliente);

		if (!cliente.isPresent()) {
			throw new MsgGenericaPersonalizadaException("Código " + cdCliente + " não existe cliente cadastrado");
		}
	}
}
