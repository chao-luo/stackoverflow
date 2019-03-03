package com.questions.stackoverflow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@Slf4j
public class StackOverflowApplicationTests {

	@Test
	public void contextLoads() {
        ApplicationContextRunner runner = new ApplicationContextRunner();
        runner
                .withConfiguration(AutoConfigurations.of(Property.class))
                .withPropertyValues("some.vars=A,B,C")
                .run(ctx -> {
                    // some test assertions
                    final Property bean = ctx.getBean(Property.class);
                    log.info("length is {}", bean.getVars().size());
                    for (String var : bean.getVars()) {
                        log.info("value is {}", var);
                    }
                });
    }

}
