package com.bazinga;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BazingaCartApplicationTests {

	@Autowired
	PasswordEncoder encode;
	
	@Test
	void contextLoads() {
		System.out.println(encode.encode("pass"));
	}

}
