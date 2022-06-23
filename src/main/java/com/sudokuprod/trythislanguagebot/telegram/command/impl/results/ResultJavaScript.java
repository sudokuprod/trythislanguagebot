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
import static com.sudokuprod.trythislanguagebot.telegram.state.State.RESULT_JAVASCRIPT;

@Slf4j
@Component
public class ResultJavaScript implements TelegramCommand {

    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;
    private final KeyboardBuilder keyboardBuilder;

    public ResultJavaScript(ResponseCreator responseCreator,
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
        final String javaScriptResult = "Книги:\n" +
                "1) Изучаем программирование на JavaScript\n" +
                "2) JavaScript для детей\n" +
                "3) JavaScript для чайников\n" +
                "4) Как устроен JavaScript\n" +
                "5) Изучаем JavaScript\n" +
                "6) JavaScript и jQuery\n" +
                "7) Вы не знаете JS (серия книг): https://github.com/azat-io/you-dont-know-js-ru\n" +
                "8) You Don't Know JS (book series) https://github.com/getify/You-Dont-Know-JS\n" +
                "http://largescalejs.ru/\n" +
                "9) JavaScript Ниндзя\n" +
                "10) Выразительный JavaScript\n" +
                "11) Learning JavaScript Design Patterns https://github.com/getify/You-Dont-Know-JS\n" +
                "12) Паттерны для масштабируемых JS-приложений \n" +
                "\n" +
                "\n" +
                "Ресурсы:\n" +
                "1) Основной учебник (на русском): http://learn.javascript.ru/\n" +
                "2) The Modern JavaScript Tutorial https://javascript.info/\n" +
                "3) MDN web docs https://developer.mozilla.org/en-US/docs/Web/JavaScript\n" +
                "4) Таблица сравнения данных: https://dorey.github.io/JavaScript-Equality-Table/\n" +
                "5) Шпаргалка JS http://overapi.com/javascript\n" +
                "\n" +
                "Игры:\n" +
                "1) Cyber Dojo https://www.cyber-dojo.org/\n" +
                "2) Codewars https://www.codewars.com/\n" +
                "3) Code Combat https://codecombat.com/play\n" +
                "\n" +
                "Курсы:\n" +
                "1) CodeCademy https://www.codecademy.com/catalog/language/javascript\n" +
                "2) Cousera https://www.coursera.org/coronavirus \n\n\n" +
                "За предоставленные материалы выражаю благодарность https://www.youtube.com/c/%D0%90%D0%BD%D0%BD%D0%B0%D0%91%D0%BB%D0%BE%D0%BA";
        bot.execute(responseCreator.createMessage(message.getChatId(),  javaScriptResult));
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
        return Collections.singletonList(RESULT_JAVASCRIPT);
    }
}