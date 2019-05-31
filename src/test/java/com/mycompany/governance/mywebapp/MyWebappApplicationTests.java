package com.mycompany.governance.mywebapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyWebappApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void there_are_pending_unit_test_failures_in_this_feature_branch() {
		throw new AssertionError("There are pending unit test failures in this feature branch");
	}
}
