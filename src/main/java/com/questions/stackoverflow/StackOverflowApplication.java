package com.questions.stackoverflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Slf4j
@Controller
public class StackOverflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackOverflowApplication.class, args);
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("regDemoInfo", new RegisteredTrainee());

        return "index";
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
        };
    }

    @RequestMapping("/demoTrainee")
    public @ResponseBody RegisteredTrainee getUser(RegisteredTrainee registeredTrainee) {
       log.info("{}", registeredTrainee);
       return registeredTrainee;
    }
}
