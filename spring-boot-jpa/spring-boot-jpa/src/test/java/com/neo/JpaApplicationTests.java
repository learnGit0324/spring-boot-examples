package com.neo;

import com.neo.model.User;
import com.neo.model.UserInfo;
import com.neo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {

	@Resource
	UserRepository userRepository;

	@Test
	public void contextLoads() {
		User adam = userRepository.findByUserName("ang");
		System.out.println(adam);
	}

}
