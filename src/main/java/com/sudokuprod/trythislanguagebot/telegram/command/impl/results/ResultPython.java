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
import static com.sudokuprod.trythislanguagebot.telegram.state.State.RESULT_PYTHON;

@Slf4j
@Component
public class ResultPython implements TelegramCommand {

    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;
    private final KeyboardBuilder keyboardBuilder;

    public ResultPython(ResponseCreator responseCreator,
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
        final String pythonResult =
                "Byte of python - https://wombat.org.ua/AByteOfPython/#id10 \n" +
                "stepik  Курс для начинающих-  https://stepik.org/course/58852/promo\n" +
                "stepik Основы и применение -  https://stepik.org/course/512/syllabus\n" +
                "\n" +
                "Сайты где можно порешать задачки Python:\n" +
                "https://checkio.org/\n" +
                "https://www.codewars.com/\n" +
                "https://leetcode.com/\n" +
                "http://euler.jakumo.org/\n" +
                "http://pythontutor.ru/\n" +
                "https://acmp.ru/index.asp?main=tasks \n" +
                "\n" +
                "Канал Computer science center : https://www.youtube.com/playlist?list=PLlb7e2G7aSpTTNp7HBYzCBByaE1h54ruW \n" +
                "Курс от Яндекса:\n" +
                "https://habr.com/ru/company/yandex/blog/498856/\n" +
                "\n" +
                "Так же эти ресурсы могут быть вам полезны:\n" +
                "\n" +
                "Книга: Изучаем Python. Том 1 | Лутц Марк\n" +
                "Книга: Изучаем Python. Том 2 | Лутц Марк\n" +
                "Или\n" +
                "Книга: Доусон М. Программируем на Python.\n" +
                "\n" +
                "Ютуб канал Олега Молчанова  - https://www.youtube.com/channel/UCD5_waDcGBhof9xuA1qovTQ \n" +
                "Лекции от Тимофея Хирьянова -  https://www.youtube.com/playlist?list=PLRDzFCPr95fLuusPXwvOPgXzBL3ZTzybY \n" +
                "\n" +
                "Книга: Автоматизация рутинных задач с помощью Python: практическое руководство для начинающих \n\n\n" +
                 "За предоставленные материалы выражаю благодарность https://www.youtube.com/c/DataScienceGuy";
        bot.execute(responseCreator.createMessage(message.getChatId(),  pythonResult));
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
        return Collections.singletonList(RESULT_PYTHON);
    }
}