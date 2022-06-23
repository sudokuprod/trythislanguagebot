package com.sudokuprod.trythislanguagebot.telegram.command.impl.steps;

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

import java.util.*;

import static com.sudokuprod.trythislanguagebot.telegram.state.State.FIRST_Q;
import static com.sudokuprod.trythislanguagebot.telegram.state.State.SECOND_Q;

@Slf4j
@Component
public class FirstStep implements TelegramCommand {

    private final KeyboardBuilder keyboardBuilder;
    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;

    public FirstStep(KeyboardBuilder keyboardBuilder,
                     ResponseCreator responseCreator,
                     StateProvider stateProvider) {
        this.keyboardBuilder = keyboardBuilder;
        this.responseCreator = responseCreator;
        this.stateProvider = stateProvider;
    }

    @Override
    public void execute(@NonNull final TelegramLongPollingBot bot,
                        @NonNull final Message message,
                        @NonNull final UserState currentState,
                        @NonNull final UserProfile profile) throws TelegramApiException {
        List<Map<String, String>> keyboard = new LinkedList<>();
        Map<String, String> inlineRow1 = new LinkedHashMap<>();
        Map<String, String> inlineRow2 = new LinkedHashMap<>();
        Map<String, String> inlineRow3 = new LinkedHashMap<>();
        Map<String, String> inlineRow4 = new LinkedHashMap<>();
        inlineRow1.put("Мой выбор - сказка!", "call1");
        inlineRow2.put("Конечно с массажем спины!", "call2");
        inlineRow3.put("Есть третий стул?", "call3");
        inlineRow4.put("При чем здесь стулья вообще?", "call4");
        keyboard.add(inlineRow1);
        keyboard.add(inlineRow2);
        keyboard.add(inlineRow3);
        keyboard.add(inlineRow4);

        final String firstQuestion = profile.getName() + ", представь, что перед тобой два стула.\n" +
                "Да-да.\n" +
                "Один выглядит как сказка, красочный, аккуратный, обшит кожей.\n" +
                "Второй выглядит так себе, но с волшебной кнопкой - \"массаж спины\".\n" +
                "Какой стул выберешь?";

        bot.execute(responseCreator.createPhoto(message.getChatId(), "static/images/1.jpg",
                firstQuestion, keyboardBuilder.createInlineKeyboard(keyboard)));
        stateProvider.changeStateByChatId(message.getChatId(), SECOND_Q);
    }

    @Override
    public List<String> getCommands() {
        return Collections.emptyList();
    }

    @Override
    public List<State> availCurrentStates() {
        return Collections.singletonList(FIRST_Q);
    }
}
