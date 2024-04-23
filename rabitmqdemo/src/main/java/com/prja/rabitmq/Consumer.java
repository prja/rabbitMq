package com.prja.rabitmq;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
	@RabbitListener(queues = "HR")
	public void getMessage_hr(Employee emp) {
		System.out.println("HR==>"+emp.toString());
	}	
//	@RabbitListener(queues = "ADMIN")
//	public void getMessage_admin(Employee emp) {
//		System.out.println("ADMIN==>"+emp.toString());
//	}
//	
//	@RabbitListener(queues = "SECURITY")
//	public void getMessage_security(Employee emp) {
//		System.out.println("SECURITY==>"+emp.toString());
//	}
//	
	@RabbitListener(queues = "HR")
	public void getMessage(byte[] message) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(message);
		ObjectInput in = new ObjectInputStream(bis);
		Employee emp = (Employee) in.readObject();
		in.close();
		bis.close();
		System.out.println("header HR==>"+emp.toString());
	}
}
