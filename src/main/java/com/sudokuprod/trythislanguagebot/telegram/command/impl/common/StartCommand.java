package com.sudokuprod.trythislanguagebot.telegram.command.impl.common;

import com.sudokuprod.trythislanguagebot.telegram.command.TelegramCommand;
import com.sudokuprod.trythislanguagebot.telegram.keyboard.KeyboardBuilder;
import com.sudokuprod.trythislanguagebot.telegram.profile.UserProfile;
import com.sudokuprod.trythislanguagebot.telegram.response.ResponseCreator;
import com.sudokuprod.trythislanguagebot.telegram.state.State;
import com.sudokuprod.trythislanguagebot.telegram.state.StateProvider;
import com.sudokuprod.trythislanguagebot.telegram.state.UserState;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;
import java.util.List;

import static com.sudokuprod.trythislanguagebot.telegram.state.State.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartCommand implements TelegramCommand {

    private final KeyboardBuilder keyboardBuilder;
    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;

    @Override
    public void execute(@NonNull final TelegramLongPollingBot bot,
                        @NonNull final Message message,
                        @NonNull final UserState currentState,
                        @NonNull final UserProfile profile) throws TelegramApiException {

        final String helloWorld = "Перед тобой судьбоносная викторина.\n" +
                "Жизнь разделится на до и после.\n" +
                "Первый язык программирования, как первая любовь, на всю жизнь.\n" +
                "Отвечай на вопросы честно, будь собой.\n" +
                "В конце ты получишь результаты и рекомендации по изучению языка.\n" +
                "Чтобы начать - введи своё имя";

        bot.execute(responseCreator.createPhoto(message.getChatId(), "static/images/start.jpg", helloWorld,
                keyboardBuilder.getBackReplyKeyboard()));
        stateProvider.changeStateByChatId(message.getChatId(), FIRST_Q);
    }

    @Override
    public List<String> getCommands() {
        return Collections.singletonList("/start");
    }

    @Override
    public List<State> availCurrentStates() {
        return Collections.singletonList(NO_ONE);
    }

}
