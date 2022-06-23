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
import static com.sudokuprod.trythislanguagebot.telegram.state.State.RESULT_KOTLIN;

@Slf4j
@Component
public class ResultKotlin implements TelegramCommand {

    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;
    private final KeyboardBuilder keyboardBuilder;

    public ResultKotlin(ResponseCreator responseCreator,
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
        final String kotlinResult = "Руководство по языку Kotlin: https://kotlinlang.ru/\n" +
                "Книги для начинающих Kotlin-программистов. Например, новичкам будут полезны:\n" +
                "\n" +
                "    \"Kotlin в действии\" С. Исаковой и Д. Жемерова;\n" +
                "    \"Kotlin Programming: The Big Nerd Ranch Guide\" от J. Skeen и D. Greenhalgh.\n" +
                "Если вы разработчик Java, который хочет изучить Kotlin и улучшить свои карьерные перспективы, то это ваш выбор: \n" +
                "https://www.udemy.com/course/kotlin-for-java-developers/ \n";
        bot.execute(responseCreator.createMessage(message.getChatId(),  kotlinResult));
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
        return Collections.singletonList(RESULT_KOTLIN);
    }
}