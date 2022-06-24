package com.sudokuprod.trythislanguagebot.telegram.command.impl;

import com.sudokuprod.trythislanguagebot.telegram.command.TelegramCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public abstract class ParentByStepAndResult implements TelegramCommand {
    @Override
    public List<String> getCommands() {
        return Collections.emptyList();
    }

    public List<Map<String, String>> createInlineKeyboard(final String[][] buttons) {
        List<Map<String, String>> keyboard = new LinkedList<>();
        for (int i = 0; i < buttons.length; i++) {
            Map<String, String> inlineRow = new LinkedHashMap<>();
            inlineRow.put(buttons[i][0], buttons[i][1]);
            keyboard.add(inlineRow);
        }
        return keyboard;
    }
}
