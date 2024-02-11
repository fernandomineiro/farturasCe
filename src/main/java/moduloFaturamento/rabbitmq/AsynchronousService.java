package moduloFaturamento.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import moduloFaturamento.rabbitmq.consumidores.RotinasAgendadasConsumer;
import moduloFaturamento.rabbitmq.consumidores.UsuarioConsumer;

@Service
public class AsynchronousService {

	@Autowired
	private TaskExecutor taskExecutor;

	@Autowired
	private ApplicationContext applicationContext;

	@EventListener(ApplicationReadyEvent.class)
	public void executeAsynchronously() throws Exception {

		UsuarioConsumer consumidor1 = applicationContext.getBean(UsuarioConsumer.class);
		consumidor1.criarFila();
		taskExecutor.execute(consumidor1);
		
		RotinasAgendadasConsumer consumidor2 = applicationContext.getBean(RotinasAgendadasConsumer.class);
		consumidor2.criarFila();
		taskExecutor.execute(consumidor2);
	}

}