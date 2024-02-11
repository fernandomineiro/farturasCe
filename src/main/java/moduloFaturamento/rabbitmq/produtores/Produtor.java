package moduloFaturamento.rabbitmq.produtores;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.BasicProperties.Builder;
import com.rabbitmq.client.Channel;

import moduloFaturamento.excecoes.ExcecaoRegraNegocio;
import moduloFaturamento.rabbitmq.EndPoint;
import moduloFaturamento.rabbitmq.produtores.mensagens.AuditoriaMsgProdutor;

@Service
public class Produtor {

	@Autowired
	private EndPoint endPoint;

	public Produtor() {

		super();
	}

	/**
	 * @param nomeExchange     segNomeComponente.exchange
	 * @param nomeTipoExchange fanout
	 * @param nomeRota         ""
	 */
	public String envioMensagem(String nomeExchange, String nomeTipoExchange, String nomeRota, Object objetoProdutor) throws ExcecaoRegraNegocio {

		Gson gson = new Gson();
		Channel channel = endPoint.criarExchange(nomeExchange, nomeTipoExchange);

		try {

			Builder builderPropsJson = new BasicProperties().builder().timestamp(new Date()).contentType("application/json")
					// .deliveryMode(2) é para manter a messagem gravada mesmo quando o rabbitmq é
					// reiniciado.
					.deliveryMode(2);

			BasicProperties basicProps = builderPropsJson.build();
			String message = gson.toJson(objetoProdutor);
			channel.basicPublish(nomeExchange, nomeRota, basicProps, message.getBytes());
			channel.getConnection().close();

		} catch (IOException ex) {

			throw new ExcecaoRegraNegocio("Erro ao enviar mensagem assíncrona.");
		}
		return "Sucesso";
	}

	public String envioMensagemLote(String nomeExchange, String nomeTipoExchange, String nomeRota, List<AuditoriaMsgProdutor> objetoProdutorList) throws ExcecaoRegraNegocio {

		Gson gson = new Gson();
		Channel channel = endPoint.criarExchange(nomeExchange, nomeTipoExchange);

		Builder builderPropsJson = new BasicProperties().builder().timestamp(new Date()).contentType("application/json")
				// .deliveryMode(2) é para manter a messagem gravada mesmo quando o rabbitmq é
				// reiniciado.
				.deliveryMode(2);

		BasicProperties basicProps = builderPropsJson.build();

		objetoProdutorList.stream().forEach(objetoProdutor -> {

			String message = gson.toJson(objetoProdutor);
			try {

				channel.basicPublish(nomeExchange, nomeRota, basicProps, message.getBytes());
			} catch (IOException ex) {

				throw new ExcecaoRegraNegocio("Erro ao enviar mensagem assíncrona.");
			}
		});

		try {

			channel.getConnection().close();
		} catch (IOException e) {

			// Nada mais a fazer
		}

		return "Sucesso";
	}

}
