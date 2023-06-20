package br.org.marciro.rabbitmq.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

	
	@RabbitListener(queues = {"${marciro.rabbitmq.queue}"})
	public void receive(@Payload String payload) {
		System.out.println("Mensagem sendo consumida");
		System.out.println(payload);
		System.out.println("Mensagem consumida");
	}
	
	
}
