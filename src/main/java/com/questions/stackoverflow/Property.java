package com.questions.stackoverflow;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@Data
@Slf4j
public class Property {

    public Property() {
        log.info("test");
    }

    public List<String> getVars() {
        return vars;
    }

    public void setVars(List<String> vars) {
        this.vars = vars;
    }

    @Value("${some.vars}")
    private List<String> vars;
}
