package com.questions.stackoverflow;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    public String ping() {
        return "pong";
    }
}
