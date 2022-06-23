package com.sudokuprod.trythislanguagebot.telegram.command.impl.results;


import com.sudokuprod.trythislanguagebot.telegram.command.TelegramCommand;
import com.sudokuprod.trythislanguagebot.telegram.keyboard.KeyboardBuilder;
import com.sudokuprod.trythislanguagebot.telegram.profile.UserProfile;
import com.sudokuprod.trythislanguagebot.telegram.response.ResponseCreator;
import com.sudokuprod.trythislanguagebot.telegram.state.State;
import com.sudokuprod.trythislanguagebot.telegram.state.StateProvider;
import com.sudokuprod.trythislanguagebot.telegram.state.UserState;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;
import java.util.List;

import static com.sudokuprod.trythislanguagebot.telegram.state.State.AUTHOR_PAGE;
import static com.sudokuprod.trythislanguagebot.telegram.state.State.RESULT_GO;

@Slf4j
@Component
public class ResultGo implements TelegramCommand {

    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;
    private final KeyboardBuilder keyboardBuilder;

    public ResultGo(ResponseCreator responseCreator,
                    StateProvider stateProvider,
                    KeyboardBuilder keyboardBuilder) {
        this.responseCreator = responseCreator;
        this.stateProvider = stateProvider;
        this.keyboardBuilder = keyboardBuilder;
    }

    @Override
    public void execute(@NonNull final TelegramLongPollingBot bot,
                        @NonNull final Message message,
                        @NonNull final UserState currentState,
                        @NonNull final UserProfile profile) throws TelegramApiException {
        final String goResult = "Полезные ссылки для изучения Golang: \n" +
                "Книга по изучению Go - https://www.zhashkevych.com/go-for-beginners \n" +
                "Сайт справочник - https://metanit.com/go/tutorial/\n" +
                "GOLANG NINJA - https://www.zhashkevych.com/golang-ninja\n" +
                "Архитектура Современных Веб-Приложений - https://www.zhashkevych.com/modern-web-architecture\n" +
                "Язык Go Для Начинающих - https://zhashkevych.com/go-for-beginners \n" +
                "A tour of Go - https://tour.golang.org/welcome/1 \n" +
                "HabrHabr golang - https://habr.com/ru/hub/go/all/"
                ;
        bot.execute(responseCreator.createMessage(message.getChatId(),  goResult));
        stateProvider.changeStateByChatId(message.getChatId(), AUTHOR_PAGE);
        bot.execute(responseCreator.createMessage(message.getChatId(), "На этом всё!\nС вопросами и пожеланиями можешь обратиться прямо ко мне, контакты ниже.",
                keyboardBuilder.createSingleButtonReplyKeyboard("Автор")));
    }

    @Override
    public List<String> getCommands() {
        return Collections.emptyList();
    }

    @Override
    public List<State> availCurrentStates() {
        return Collections.singletonList(RESULT_GO);
    }
}