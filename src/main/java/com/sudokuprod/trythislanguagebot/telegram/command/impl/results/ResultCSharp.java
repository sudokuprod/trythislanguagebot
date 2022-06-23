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
import static com.sudokuprod.trythislanguagebot.telegram.state.State.RESULT_CSHARP;

@Slf4j
@Component
public class ResultCSharp implements TelegramCommand {

    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;
    private final KeyboardBuilder keyboardBuilder;

    public ResultCSharp(ResponseCreator responseCreator,
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
        final String cSharpResult =
                "Книга \"Герберт Шилдт - C# 4.0: полное руководство\"\n" +
                "Книга \"Эндрю Троелсен - Язык программирования C# 7 и платформы .NET и .NET Core\"\n" +
                "Курс thenewboston - https://www.youtube.com/playlist?list=PL0EE421AE8BCEBA4A\n" +
                "https://metanit.com/\n" +
                "YouTube канал Senior C# программиста - https://www.youtube.com/channel/UCeObZv89Stb2xLtjLJ0De3Q\n" +
                "Еще книги/курсы https://vk.com/topic-84392011_37640354 \n" +
                "Канал по C# CodeBlog: https://www.youtube.com/channel/UCKCUvcAFFR4UxI9XWVERSkA\n" +
                "Уроки C# для новичков:https://www.youtube.com/playlist?list=PLIIXgDT0bKw4OmiZ9yGmShKsY0XncViZ8\n" +
                "Пишем настоящую программу на C#: https://www.youtube.com/playlist?list=PLIIXgDT0bKw7AOFNyc2_FGYDj_52AclvV";
        bot.execute(responseCreator.createMessage(message.getChatId(),  cSharpResult));
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
        return Collections.singletonList(RESULT_CSHARP);
    }
}