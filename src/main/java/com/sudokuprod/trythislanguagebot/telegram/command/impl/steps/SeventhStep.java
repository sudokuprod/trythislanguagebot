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

import static com.sudokuprod.trythislanguagebot.telegram.state.State.EIGHTH_Q;
import static com.sudokuprod.trythislanguagebot.telegram.state.State.SEVENTH_Q;
import static com.sudokuprod.trythislanguagebot.telegram.utils.ConstantText.SEVENTH_QUESTION;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeventhStep extends ParentByStepAndResult {

    private final KeyboardBuilder keyboardBuilder;
    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;

    @Override
    public void execute(@NonNull final TelegramLongPollingBot bot,
                        @NonNull final Message message,
                        @NonNull final UserState currentState,
                        @NonNull final UserProfile profile) throws TelegramApiException {
        final String[][] buttons = new String[][]{{"В самые недра, без сомнений", "call26"},
                {"Мне хватит пары алмазиков", "call27"}, {"Главное не копать под себя!", "call28"}};
        List<Map<String, String>> keyboard = createInlineKeyboard(buttons);


        bot.execute(responseCreator.createPhoto(message.getChatId(), "static/images/7.jpg",
                SEVENTH_QUESTION, keyboardBuilder.createInlineKeyboard(keyboard)));
        stateProvider.changeStateByChatId(message.getChatId(), EIGHTH_Q);
    }

    @Override
    public List<State> availCurrentStates() {
        return Collections.singletonList(SEVENTH_Q);
    }
}