package com.sudokuprod.trythislanguagebot.telegram;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.sudokuprod.trythislanguagebot.telegram.listener.TelegramListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;


@Slf4j
@RequiredArgsConstructor
@Component
public class Bot extends TelegramLongPollingBot {

    public static final String UNKNOWN_COMMAND_MSG = "Возникла ошибка, попробуй ещё раз";
    private final TelegramListener telegramListener;
    @Value("${telegram.api.botName}")
    private String botUsername;
    @Value("${telegram.api.botToken}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            telegramListener.onUpdate(this, update);
        } catch (final Exception ignored) {
        }
    }

    @Override
    public <T extends Serializable, Method extends BotApiMethod<T>> T execute(Method method) throws TelegramApiException {
        try {
            return super.execute(method);
        } catch (final TelegramApiException e) {
            if (!(e.getCause() instanceof JsonMappingException)) {
                throw e;
            } else {
                return null;
            }
        }
    }
}
