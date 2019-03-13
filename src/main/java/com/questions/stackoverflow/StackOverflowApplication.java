package com.questions.stackoverflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@Slf4j
@RestController
public class StackOverflowApplication {

    @Autowired
    WelcomeController welcomeController;

    @Value("${paths}")
    List<String> paths;


    public static void main(String[] args) {
        SpringApplication.run(StackOverflowApplication.class, args);
    }



    @Bean
    public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        Map<String, Object> map = new HashMap<>();
        final Method getUser = ReflectionUtils.findMethod(WelcomeController.class, "ping");
        final HandlerMethod handlerMethod = new HandlerMethod(welcomeController, getUser);
        for (String path : paths) {
            map.put(path, handlerMethod);
        }
        simpleUrlHandlerMapping.setUrlMap(map);
        simpleUrlHandlerMapping.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return simpleUrlHandlerMapping;
    }


}
