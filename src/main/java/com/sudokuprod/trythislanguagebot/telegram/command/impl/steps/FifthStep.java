package com.sudokuprod.trythislanguagebot.telegram.command.impl.steps;

import com.sudokuprod.trythislanguagebot.telegram.command.impl.ParentByStepAndResult;
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
import java.util.Map;

import static com.sudokuprod.trythislanguagebot.telegram.state.State.FIFTH_Q;
import static com.sudokuprod.trythislanguagebot.telegram.state.State.SIX_Q;
import static com.sudokuprod.trythislanguagebot.telegram.utils.ConstantText.FIFTH_QUESTION;

@Slf4j
@Component
@RequiredArgsConstructor
public class FifthStep extends ParentByStepAndResult {

    private final KeyboardBuilder keyboardBuilder;
    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;

    @Override
    public void execute(@NonNull final TelegramLongPollingBot bot,
                        @NonNull final Message message,
                        @NonNull final UserState currentState,
                        @NonNull final UserProfile profile) throws TelegramApiException {
        final String[][] buttons = new String[][]{{"Возьму книжку, по программированию, конечно", "call16"},
                {"Оторвусь в клубе с друзьями!", "call17"}, {"Отличная возможность поехать за город!", "call18"},
                {"Буду спать до обеда", "call19"}};
        List<Map<String, String>> keyboard = createInlineKeyboard(buttons);


        bot.execute(responseCreator.createPhoto(message.getChatId(), "static/images/5.jpg",
                FIFTH_QUESTION, keyboardBuilder.createInlineKeyboard(keyboard)));
        stateProvider.changeStateByChatId(message.getChatId(), SIX_Q);
    }

    @Override
    public List<State> availCurrentStates() {
        return Collections.singletonList(FIFTH_Q);
    }
}