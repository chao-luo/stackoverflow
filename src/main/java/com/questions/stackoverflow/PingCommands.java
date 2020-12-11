package com.questions.stackoverflow;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class PingCommands {

    @ShellMethod("Ping pong")
    public String ping(
            @ShellOption() String ping
    ) {
        return ping + " --> pong";
    }
}
