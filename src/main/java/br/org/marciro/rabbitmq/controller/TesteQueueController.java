package br.org.marciro.rabbitmq.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.marciro.rabbitmq.messaging.publisher.TesteMessagePublisher;

@RestController
@RequestMapping(value = "/test")
public class TesteQueueController {

	
	private static final int MS_IN_SECOND = 1000;
    private static final Random RANDOM = new Random();
	private static int request = 0;
	@Autowired
	
	private TesteMessagePublisher publisher; 
	
	@PostMapping(value="/queue")
	public ResponseEntity<String> sendToQueue(@Validated @RequestBody String body) {
		
		System.out.println("Recebendo da chamada HTTP - POST: "+body);
		request++;
		System.out.println("Mensagem "+request+" requerida");
		
		int bound = 5;
		int nextInt = RANDOM.nextInt(bound );
		System.out.println("Tempo de espera:"+ nextInt);
		sleepSeconds(nextInt);
		
		publisher.send(body);
		
		System.out.println("Mensagem "+request+" publicada");
		
		return ResponseEntity.ok().body("Mensagem publicada") ;
	}
	
	public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * MS_IN_SECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
	
	
}

