package br.org.marciro.rabbitmq.messaging.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TesteMessagePublisher {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${marciro.rabbitmq.queue}")
	private String queueName;
	
	
	public void send(String payload) {
		System.out.println("Publicando a mensagem em :"+queueName);
		rabbitTemplate.convertAndSend(queueName, payload);
	}
	
}
