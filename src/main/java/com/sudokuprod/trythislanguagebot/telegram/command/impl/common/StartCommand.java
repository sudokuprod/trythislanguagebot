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

import static com.sudokuprod.trythislanguagebot.telegram.state.State.FIRST_Q;
import static com.sudokuprod.trythislanguagebot.telegram.state.State.NO_ONE;
import static com.sudokuprod.trythislanguagebot.telegram.utils.ConstantText.HELLO_WORLD;

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

        bot.execute(responseCreator.createPhoto(message.getChatId(), "static/images/start.jpg", HELLO_WORLD,
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
