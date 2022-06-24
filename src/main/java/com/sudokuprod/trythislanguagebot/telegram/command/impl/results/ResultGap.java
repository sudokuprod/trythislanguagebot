package com.sudokuprod.trythislanguagebot.telegram.command.impl.results;

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

import static com.sudokuprod.trythislanguagebot.telegram.state.State.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResultGap extends ParentByStepAndResult {

    private final KeyboardBuilder keyboardBuilder;
    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;

    @Override
    public void execute(@NonNull final TelegramLongPollingBot bot,
                        @NonNull final Message message,
                        @NonNull final UserState currentState,
                        @NonNull final UserProfile profile) throws TelegramApiException, InterruptedException {

        bot.execute(responseCreator.createMessage(message.getChatId(), "Принято, обрабатываю!"));
        Thread.sleep(1000);
        bot.execute(responseCreator.createPhoto(message.getChatId(), correctPhotoPath(profile.getGap()), "Готово!\nЗабирай знания, " + profile.getName() + ";)",
                keyboardBuilder.createSingleButtonInlineKeyboard(profile.getGap(), profile.getGap())));
        stateProvider.changeStateByChatId(message.getChatId(), correctState(profile));
    }

    private String correctPhotoPath(final String profileChoice) {
        final StringBuilder builder = new StringBuilder();
        builder.append("static/images/");
        switch (profileChoice) {
            case ("Java"):
                builder.append("java.jpg");
                break;
            case ("C++"):
                builder.append("cplusplus.jpg");
                break;
            case ("C#"):
                builder.append("csharp.jpg");
                break;
            case ("Kotlin"):
                builder.append("kotlin.jpg");
                break;
            case ("JavaScript"):
                builder.append("javascript.jpg");
                break;
            case ("TypeScript"):
                builder.append("typescript.jpg");
                break;
            case ("Python"):
                builder.append("python.jpg");
                break;
            case ("Golang"):
                builder.append("golang.jpg");
                break;
        }
        return builder.toString();
    }

    private State correctState(final UserProfile profile) {
        switch (profile.getGap()) {
            case ("Java"):
                return RESULT_JAVA;
            case ("C++"):
                return RESULT_C_PLUS_PLUS;
            case ("Kotlin"):
                return RESULT_KOTLIN;
            case ("C#"):
                return RESULT_CSHARP;
            case ("JavaScript"):
                return RESULT_JAVASCRIPT;
            case ("TypeScript"):
                return RESULT_TYPESCRIPT;
            case ("Golang"):
                return RESULT_GO;
            case ("Python"):
                return RESULT_PYTHON;
            default:
                return GAP;
        }
    }

    @Override
    public List<State> availCurrentStates() {
        return Collections.singletonList(GAP);
    }
}