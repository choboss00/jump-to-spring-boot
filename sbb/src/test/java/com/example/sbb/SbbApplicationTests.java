package com.example.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("classpath:db/teardown.sql")
class SbbApplicationTests {

	@Test
	void contextLoads() {
	}

}
