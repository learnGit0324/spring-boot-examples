package com.neo.rabbit.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class HelloSender {
	private final static String QUEUE_NAME = "hello";
//	@Autowired
//	private AmqpTemplate rabbitTemplate;

	public void send() throws IOException, TimeoutException {
//		String context = "hello " + new Date();
//		System.out.println("Sender : " + context);
//		this.rabbitTemplate.convertAndSend("hello", context);

		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setVirtualHost("/");
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		//queue的生命第一个参数要和创建MQ的时候的设置保持一致 即是非持久化
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		String message = "hello world";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();
		connection.close();
	}

}