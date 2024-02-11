package moduloFaturamento.infra.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import moduloFaturamento.rabbitmq.produtores.Produtor;
import moduloFaturamento.rabbitmq.produtores.mensagens.AuditoriaMsgProdutor;
import moduloFaturamento.util.Constants;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

	@Value("${rabbitmq.direct.produtorAuditoria.nomeExchange}")
	private String exchangeAuditoria;
	@Value("${rabbitmq.direct.produtorAuditoria.rota}")
	private String rotaAuditoria;
	@Autowired
	private Produtor produtor;

	@Override
	public void gerarAuditoriaCadastramentoSomenteChave(Long chave, Long idEntidade, String rotina, Long idUsuario) {

		String dadosAntesJson = Constants.EMPTY_STRING;
		String dadosDepoisJson = "{\"Id\":\"" + chave + "\"}";

		AuditoriaMsgProdutor auditoriaMsgProdutor = new AuditoriaMsgProdutor(dadosAntesJson, dadosDepoisJson, chave, idEntidade, rotina, idUsuario);
		produtor.envioMensagem(exchangeAuditoria, "direct", rotaAuditoria, auditoriaMsgProdutor);
	}

	@Override
	public void gerarAuditoriaCadastramento(Long chave, String dadosDepois, Long idEntidade, String rotina, Long idUsuario) {

		String dadosAntesJson = Constants.EMPTY_STRING;
		String dadosDepoisJson = dadosDepois;

		if (!Constants.EMPTY_STRING.equals(dadosDepois)) {

			AuditoriaMsgProdutor auditoriaMsgProdutor = new AuditoriaMsgProdutor(dadosAntesJson, dadosDepoisJson, chave, idEntidade, rotina, idUsuario);
			produtor.envioMensagem(exchangeAuditoria, "direct", rotaAuditoria, auditoriaMsgProdutor);
		}
	}

	@Override
	public void gerarAuditoriaCadastramentoLote(List<AuditoriaMsgProdutor> auditoriaMsgList) {

		List<AuditoriaMsgProdutor> filtered = auditoriaMsgList.stream().filter(audit -> {

			return !Constants.EMPTY_STRING.equals(audit.getDadosDepois());
		}).collect(Collectors.toList());

		produtor.envioMensagemLote(exchangeAuditoria, "direct", rotaAuditoria, filtered);
	}

	@Override
	public void gerarAuditoriaRemocao(Long chave, String dadosAntes, Long idEntidade, String rotina, Long idUsuario) {

		String dadosAntesJson = dadosAntes;
		String dadosDepoisJson = Constants.EMPTY_STRING;

		if (!Constants.EMPTY_STRING.equals(dadosAntes)) {

			AuditoriaMsgProdutor auditoriaMsgProdutor = new AuditoriaMsgProdutor(dadosAntesJson, dadosDepoisJson, chave, idEntidade, rotina, idUsuario);
			produtor.envioMensagem(exchangeAuditoria, "direct", rotaAuditoria, auditoriaMsgProdutor);
		}
	}

	@Override
	public void gerarAuditoriaAlteracao(Long chave, String dadosAntes, String dadosDepois, Long idEntidade, String rotina, Long idUsuario) {

		String dadosAntesJson = dadosAntes;
		String dadosDepoisJson = dadosDepois;

		if (dadosAntesJson != null && dadosDepoisJson != null && !dadosAntesJson.equals(dadosDepoisJson)) {

			List<String> listaDadosAntesDadosDepois = this.filtrarJson(dadosAntes, dadosDepois);
			dadosAntesJson = listaDadosAntesDadosDepois.get(0);
			dadosDepoisJson = listaDadosAntesDadosDepois.get(1);

			AuditoriaMsgProdutor auditoriaMsgProdutor = new AuditoriaMsgProdutor(dadosAntesJson, dadosDepoisJson, chave, idEntidade, rotina, idUsuario);
			produtor.envioMensagem(exchangeAuditoria, "direct", rotaAuditoria, auditoriaMsgProdutor);
		}
	}

	@Override
	public void gerarAuditoriaAlteracaoLote(List<AuditoriaMsgProdutor> auditoriaMsgList) {

		auditoriaMsgList.stream().filter(audit -> {

			String dadosAntesJson = audit.getDadosAntes();
			String dadosDepoisJson = audit.getDadosDepois();

			return dadosAntesJson != null && dadosDepoisJson != null && !dadosAntesJson.equals(dadosDepoisJson);
		}).map(audit -> {
			
			List<String> listaDadosAntesDadosDepois = this.filtrarJson(audit.getDadosAntes(), audit.getDadosDepois());
			
			audit.setDadosAntes(listaDadosAntesDadosDepois.get(0));
			audit.setDadosDepois(listaDadosAntesDadosDepois.get(1));

			return audit;

		}).collect(Collectors.toList());

		produtor.envioMensagemLote(exchangeAuditoria, "direct", rotaAuditoria, auditoriaMsgList);
	}

	@Override
	public void gerarAuditoria(Long chave, String dadosAntes, String dadosDepois, Long idEntidade, String rotina, Long idUsuario) {

		String dadosAntesJson;
		String dadosDepoisJson;
		if (!dadosAntes.equals(dadosDepois)) {

			if (dadosDepois.isEmpty()) {

				dadosAntesJson = dadosAntes;
				dadosDepoisJson = dadosDepois;
			} else if (dadosAntes.isEmpty()) {

				dadosAntesJson = dadosAntes;
				dadosDepoisJson = "{\"Id\":\"" + chave + "\"}";
			} else {

				List<String> listaDadosAntesDadosDepois = this.filtrarJson(dadosAntes, dadosDepois);
				dadosAntesJson = listaDadosAntesDadosDepois.get(0);
				dadosDepoisJson = listaDadosAntesDadosDepois.get(1);
			}

			AuditoriaMsgProdutor auditoriaMsgProdutor = new AuditoriaMsgProdutor(dadosAntesJson, dadosDepoisJson, chave, idEntidade, rotina, idUsuario);
			produtor.envioMensagem(exchangeAuditoria, "direct", rotaAuditoria, auditoriaMsgProdutor);
		}
	}

	@Override
	public void gerarAuditoriaAssociacaoDesassociacao(Long chave, String dadosAntes, String dadosDepois, Long idEntidade, String rotina, Long idUsuario) {

		AuditoriaMsgProdutor auditoriaMsgProdutor = new AuditoriaMsgProdutor(dadosAntes, dadosDepois, chave, idEntidade, rotina, idUsuario);
		produtor.envioMensagem(exchangeAuditoria, "direct", rotaAuditoria, auditoriaMsgProdutor);

	}

	private List<String> filtrarJson(String dadosAntes, String dadosDepois) {

		dadosAntes = dadosAntes.replaceAll("\\{", "").replaceAll("\\\"}", "");
		dadosDepois = dadosDepois.replaceAll("\\{", "").replaceAll("\\\"}", "");

		String listDadosAntes[] = dadosAntes.split("\",");
		String listDadosDepois[] = dadosDepois.split("\",");

		Map<String, String> mapDadosAntes = new TreeMap<>();
		Map<String, String> mapDadosDepois = new TreeMap<>();

		for (int indice = 0; indice < listDadosAntes.length; indice++) {

			String campo[] = listDadosAntes[indice].split(":");
			mapDadosAntes.put(campo[0], campo[1]);
		}
		for (int indice = 0; indice < listDadosDepois.length; indice++) {

			String campo[] = listDadosDepois[indice].split(":");
			mapDadosDepois.put(campo[0], campo[1]);
		}
		Set<String> listDados = mapDadosAntes.keySet();
		String dadosAntesNovo = "{";
		String dadosDepoisNovo = "{";
		for (String dado : listDados) {

			if (!mapDadosAntes.get(dado).equalsIgnoreCase(mapDadosDepois.get(dado))) {

				dadosAntesNovo = dadosAntesNovo + dado + ":" + mapDadosAntes.get(dado) + "\",";
				dadosDepoisNovo = dadosDepoisNovo + dado + ":" + mapDadosDepois.get(dado) + "\",";
			}

		}
		List<String> retorno = new ArrayList<String>();
		retorno.add(dadosAntesNovo.substring(0, dadosAntesNovo.length() - 1) + "}");
		retorno.add(dadosDepoisNovo.substring(0, dadosDepoisNovo.length() - 1) + "}");
		return retorno;
	}

	@Override
	public String forcarRegistroCamposEmAlteracao(String jsonAntes, String ... campos) {
		
		List<String> of = List.of(campos);
		
		String reduce = of.stream().reduce(jsonAntes, (json, campo) -> {
			
			String originalRegex = String.format("\"%s\":\".*?\"", campo);
			String padraoForcar = String.format("\"%s\":\"[Inalterado]\"", campo);
			
			return json.replaceFirst(originalRegex, padraoForcar);
		});
		
		return reduce;
	}
}
