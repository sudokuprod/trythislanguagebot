package com.sudokuprod.trythislanguagebot.telegram.listener;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramListener {
    void onUpdate(TelegramLongPollingBot bot, Update update) throws Exception;

}
