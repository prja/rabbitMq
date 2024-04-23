package com.prja.rabitmq;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;

@RestController
@RequestMapping("/api")
public class Producer {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	
	@GetMapping("/toquee/{empName}/{empEmail}")
	public  String  sendMessageToQueue(@PathVariable("empName") String empName, @PathVariable("empEmail") String  empEmail) {
		Employee emp= new Employee(empName,empEmail);
		rabbitTemplate.convertAndSend("HR", emp);
		return "passed";
		
	}
	@GetMapping("/todirectx/{empName}/{empEmail}")
	public  String  sendMessageToDirectX(@PathVariable("empName") String empName, @PathVariable("empEmail") String  empEmail) {
		Employee emp= new Employee(empName,empEmail);
		rabbitTemplate.convertAndSend("Direct-X", "hr", emp);
		rabbitTemplate.convertAndSend("Direct-X", "admin", emp);
		rabbitTemplate.convertAndSend("Fanout-X", "", emp);
		return "passed";
	}
	@GetMapping("/tofanoutx/{empName}/{empEmail}")
	public  String  sendMessageToFanoutX(@PathVariable("empName") String empName, @PathVariable("empEmail") String  empEmail) {
		Employee emp= new Employee(empName,empEmail);
		rabbitTemplate.convertAndSend("Fanout-X", "", emp);
		return "passed";
	}
	@GetMapping("/totopicx/{empName}/{empEmail}")
	public  String  sendMessageToTopicX(@PathVariable("empName") String empName, @PathVariable("empEmail") String  empEmail) {
		Employee emp= new Employee(empName,empEmail);
		rabbitTemplate.convertAndSend("Topic-X", "admin.hr.security", emp);
		return "passed";
	}
	@GetMapping("/toheaderx/{empName}")
	public  String  sendMessageToHeaderX(@PathVariable("empName") String empName) throws IOException {
		Employee emp= new Employee(empName,"abc@gmail.com");
		byte[] byteMessage = getByteArray(emp);
		Message message = MessageBuilder.withBody(byteMessage)
				.setHeader("item1", "hr")
				.setHeader("item2", "admin").build();
		rabbitTemplate.send("Header-X", "", message);
		return "passed";
	}
	private byte[] getByteArray(Employee emp) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);
		out.writeObject(emp);
		out.flush();
		out.close();
		
		byte[] byteMessage = bos.toByteArray();
		bos.close();
		return byteMessage;
	}
	
	
}
