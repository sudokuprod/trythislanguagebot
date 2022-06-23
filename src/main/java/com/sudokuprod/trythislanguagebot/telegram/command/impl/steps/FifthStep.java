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

import static com.sudokuprod.trythislanguagebot.telegram.state.State.*;

@Slf4j
@Component
public class FifthStep implements TelegramCommand {

    private final KeyboardBuilder keyboardBuilder;
    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;

    public FifthStep(KeyboardBuilder keyboardBuilder,
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
        inlineRow1.put("Возьму книжку, по программированию, конечно", "call16");
        inlineRow2.put("Оторвусь в клубе с друзьями!", "call17");
        inlineRow3.put("Отличная возможность поехать за город!", "call18");
        inlineRow4.put("Буду спать до обеда", "call19");
        keyboard.add(inlineRow1);
        keyboard.add(inlineRow2);
        keyboard.add(inlineRow3);
        keyboard.add(inlineRow4);

        final String fifthQuestion = "Давай немного серьезнее.\n" +
                "Мы тут вообще-то будущую профессию выбираем.\n" +
                "У тебя сегодня выходной, чем займемся?";

        bot.execute(responseCreator.createPhoto(message.getChatId(), "static/images/5.jpg",
                fifthQuestion, keyboardBuilder.createInlineKeyboard(keyboard)));
        stateProvider.changeStateByChatId(message.getChatId(), SIX_Q);
    }

    @Override
    public List<String> getCommands() {
        return Collections.emptyList();
    }

    @Override
    public List<State> availCurrentStates() {
        return Collections.singletonList(FIFTH_Q);
    }
}