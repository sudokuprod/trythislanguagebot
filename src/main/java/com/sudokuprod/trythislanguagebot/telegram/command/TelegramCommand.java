package com.sudokuprod.trythislanguagebot.telegram.command;

import com.sudokuprod.trythislanguagebot.telegram.profile.UserProfile;
import com.sudokuprod.trythislanguagebot.telegram.state.State;
import com.sudokuprod.trythislanguagebot.telegram.state.UserState;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public interface TelegramCommand {
    void execute(TelegramLongPollingBot bot, Message message, UserState currentState, UserProfile profile) throws TelegramApiException, InterruptedException;

    List<String> getCommands();

    List<State> availCurrentStates();

}
