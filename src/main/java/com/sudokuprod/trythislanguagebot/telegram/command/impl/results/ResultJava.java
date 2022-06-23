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
import static com.sudokuprod.trythislanguagebot.telegram.state.State.RESULT_JAVA;

@Slf4j
@Component
public class ResultJava implements TelegramCommand {

    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;
    private final KeyboardBuilder keyboardBuilder;

    public ResultJava(ResponseCreator responseCreator,
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
        final String javaResult = "Java Core:\n" +
                "Основы Java:  https://clck.ru/aj6Bc\n" +
                "Углубленная Java: https://clck.ru/atH8r\n" +
                "Упражнения:  https://codingbat.com/java (eng),\n https://habr.com/ru/post/440436/,\n https://github.com/allicen/Java-1000\n" +
                "Платный курс: https://javarush.ru\n" +
                "\n" +
                "Книги:\n" +
                "Начинающий: Изучаем Java, Берт Бейтс и Кэти Сьерра\n" +
                "Обычный: Философия Java, Брюс Эккель\n" +
                "Опытный: Java Эффективное программирование, Джошуа Блох; Чистый код, Роберт Мартин; OCP Oracle Certified Professional Java SE 11\n" +
                "\n" +
                "Алгоритмы и структуры данных:\n" +
                "Книги: Грокаем алгоритмы, Бхаргава Адитья; \n" +
                "Структуры данных и алгоритмы на Java, Роберт Лафоре\n" +
                "Упражнения: упражнения из Java Core, codewars.com (eng),\n https://www.hackerrank.com/domains/algorithms (eng)\n" +
                "\n" +
                "SQL + JDBC\n" +
                "Видео-курс SQL: https://clck.ru/ZS96v\n" +
                "Видео-курс SQL 2: https://clck.ru/atHAz\n" +
                "Упражнения: www.sql-ex.ru, https://sql-academy.org/ru\n" +
                "Видео-курс JDBC: https://clck.ru/atHBf\n" +
                "Видео-курс JDBC 2: https://clck.ru/atHC6\n" +
                "\n" +
                "HTTP, Servlets\n" +
                "Java EE для начинающих от alishev: https://clck.ru/atHCL\n" +
                "\n" +
                "Maven, Gradle\n" +
                "Видео-курс Maven: https://clck.ru/atHCW\n" +
                "Статьи по Maven: https://habr.com/ru/post/77382/, https://clck.ru/atHCe\n" +
                "Видео-курс Gradle (eng): https://clck.ru/atHCs\n" +
                "Статьи по Gradle: https://clck.ru/atHDB,\n http://spring-projects.ru/guides/gradle/,\n https://clck.ru/atHDQ,\n https://clck.ru/atHDg\n" +
                "\n" +
                "Spring\n" +
                "Видео-курс по Spring: https://clck.ru/atHDp\n" +
                "Документация (eng): https://clck.ru/YkRdA\n" +
                "Статьи: https://clck.ru/atHRj,\n https://habr.com/ru/post/490586/\n" +
                "\n" +
                "JPA/Hibernate\n" +
                "Видео-курс 1: https://clck.ru/atHRw\n" +
                "Видео-курс 2: https://clck.ru/atHS8\n" +
                "Статьи: \n" +
                "https://easyjava.ru/data/jpa/,\n https://clck.ru/atHSQ,\n https://www.baeldung.com/learn-jpa-hibernate (eng)\n" +
                "\n" +
                "Тесты\n" +
                "Статьи по JUnit: \n" +
                "https://javarush.ru/groups/posts/605-junit, https://habr.com/ru/company/otus/blog/596033/, https://habr.com/ru/post/120101/\n" +
                "Видео:\n" +
                "https://clck.ru/atHSq, https://clck.ru/atHTG\n\n\n" +
                "За предоставленные материалы выражаю благодарность https://www.youtube.com/channel/UCYqzL7JJjpvSL8P0GxsuBLQ";
        bot.execute(responseCreator.createMessage(message.getChatId(),  javaResult));
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
        return Collections.singletonList(RESULT_JAVA);
    }
}