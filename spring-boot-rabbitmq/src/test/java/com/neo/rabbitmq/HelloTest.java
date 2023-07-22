package com.neo.rabbitmq;

import com.neo.rabbit.hello.HelloReceiver;
import com.neo.rabbit.hello.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloTest {

	@Autowired
	private HelloSender helloSender;

	@Autowired
	private HelloReceiver helloReceiver;

	@Test
	public void hello() throws Exception {
		helloSender.send();
	}

	@Test
	public void receive() throws IOException, TimeoutException {
		helloReceiver.process();
	}


}