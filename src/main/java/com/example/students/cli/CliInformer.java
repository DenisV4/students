package com.example.students.cli;

import lombok.RequiredArgsConstructor;
import org.jline.terminal.Terminal;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CliInformer {
    private final Terminal terminal;

    @EventListener
    public void inform(CliInfoEvent event) {
        terminal.writer().println(event.getMessage());
        terminal.writer().flush();
    }
}
