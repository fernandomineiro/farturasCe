package moduloFaturamento.rabbitmq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import moduloFaturamento.excecoes.ExcecaoRegraNegocio;
import moduloFaturamento.infra.util.LerArquivo;

@Component
public class EndPoint {

	@Autowired
	private LerArquivo leraquivo;
	
	public Channel criarchannel() throws ExcecaoRegraNegocio {

		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri(leraquivo.LerLinkRabbitMQ());
			Connection connection = factory.newConnection();
						
			/*Criando o Canal*/
			Channel channel = connection.createChannel();
		
		return channel;
		} catch (KeyManagementException | NoSuchAlgorithmException | URISyntaxException e) {
 			throw new ExcecaoRegraNegocio("Erro no ConnectionFactory(RabbitMQ).");
		} catch (IOException e) {
			throw new ExcecaoRegraNegocio("Erro no Channel(RabbitMQ).");
		} catch (TimeoutException e) {
 			throw new ExcecaoRegraNegocio("Timeout ao criar conexão(RabbitMQ).");
		}
	}

    public Channel criarFila(String nomeExchange, String nomeTipoExchange, String nomeFila, String nomeRota, Map<String, Object> args) throws ExcecaoRegraNegocio{
    		
		try {
			/*Criando o Canal*/
			Channel channel = this.criarchannel();
			
			/*Criando o Exchange*/
			channel.exchangeDeclare(nomeExchange, nomeTipoExchange, true, false, false, null);
			
			/*Criando a Fila*/
			channel.queueDeclare(nomeFila, true, false, false, args);
			
			/*Criando o Bindo entre o Exchange e a File*/
			channel.queueBind(nomeFila, nomeExchange, nomeRota);
			
			return channel;
				
		} catch (IOException e) {
			
	 		throw new ExcecaoRegraNegocio("Erro no Fila(RabbitMQ).");
		}
    }
    
    
    public Channel criarExchange(String nomeExchangeFanout, String nomeTipoExchange) throws ExcecaoRegraNegocio{
    	
		try {
			/*Criando o Canal*/
			Channel channel = this.criarchannel();
			
			/*Criando o Exchange*/
			channel.exchangeDeclare(nomeExchangeFanout, nomeTipoExchange, true, false, false, null);
			
			return channel;
				
		} catch (IOException e) {
	 		throw new ExcecaoRegraNegocio("Erro na Exchange(RabbitMQ).");
		}
    }
}
