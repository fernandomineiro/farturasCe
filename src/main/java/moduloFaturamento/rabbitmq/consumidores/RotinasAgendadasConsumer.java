package moduloFaturamento.rabbitmq.consumidores;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import moduloFaturamento.infra.util.LogErro;
import moduloFaturamento.rabbitmq.EndPoint;
import moduloFaturamento.service.AtualizacaoCicloService;

@Component
@Scope("prototype")
public class RotinasAgendadasConsumer implements Runnable, Consumer {
	@Autowired
	private EndPoint endPoint;

	private Channel channel;

    @Autowired
    private AtualizacaoCicloService atualizacaoCicloService;

	@Value("${rabbitmq.faturamentoGatilhoGenerico.nomeFila}")
	private String nomeFila;
	@Value("${rabbitmq.faturamentoGatilhoGenerico.nomeRota}")
	private String nomeRota;
	@Value("${rabbitmq.faturamentoGatilhoGenerico.nomeExchange}")
	private String nomeExchange;

	@Autowired
	private LogErro logErro;

	public RotinasAgendadasConsumer() throws IOException {
	}

	public void criarFila() throws Exception {
	
		this.channel = endPoint.criarFila(nomeExchange, "direct", nomeFila, nomeRota, null);
	}

	public void run() {
		try {
			channel.basicConsume(nomeFila, false, this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// Chama esse método quando existe uma mensagem não lida na fila.
	@Override
	public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body)
			throws IOException {

		try {
			String message = new String(body, "UTF-8");
			
			if(message.equals("alteraCiclosGis")) {
		        atualizacaoCicloService.executarAtualizacaoDeCiclo();
			}
			
			channel.basicAck(env.getDeliveryTag(), false);
		} catch (Exception e) {
			logErro.logErro("Consumidor RotinasAgendadasConsumer: ", e.getMessage());
		}
	}

	@Override
	public void handleConsumeOk(String consumerTag) {

	}

	@Override
	public void handleCancelOk(String consumerTag) {

	}

	@Override
	public void handleCancel(String consumerTag) throws IOException {

	}

	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

	}

	@Override
	public void handleRecoverOk(String consumerTag) {

	}
}
