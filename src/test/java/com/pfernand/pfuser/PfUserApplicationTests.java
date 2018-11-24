package com.pfernand.pfuser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PfUserApplication.class, H2Config.class},
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PfUserApplicationTests {

	@Inject
	private ApplicationContext applicationContext;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(applicationContext);
	}

}
