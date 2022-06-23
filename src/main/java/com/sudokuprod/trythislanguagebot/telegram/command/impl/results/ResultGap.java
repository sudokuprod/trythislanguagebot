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

import java.util.*;

import static com.sudokuprod.trythislanguagebot.telegram.state.State.*;

@Slf4j
@Component
public class ResultGap implements TelegramCommand {

    private final KeyboardBuilder keyboardBuilder;
    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;

    public ResultGap(KeyboardBuilder keyboardBuilder,
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
                        @NonNull final UserProfile profile) throws TelegramApiException, InterruptedException {
        bot.execute(responseCreator.createMessage(message.getChatId(), "Принято, обрабатываю!"));
        switch (profile.getGap()) {
            case ("Java"):
                stateProvider.changeStateByChatId(message.getChatId(), RESULT_JAVA);
                break;
            case ("C++"):
                stateProvider.changeStateByChatId(message.getChatId(), RESULT_C_PLUS_PLUS);
                break;
            case ("Kotlin"):
                stateProvider.changeStateByChatId(message.getChatId(), RESULT_KOTLIN);
                break;
            case ("C#"):
                stateProvider.changeStateByChatId(message.getChatId(), RESULT_CSHARP);
                break;
            case ("JavaScript"):
                stateProvider.changeStateByChatId(message.getChatId(), RESULT_JAVASCRIPT);
                break;
            case ("TypeScript"):
                stateProvider.changeStateByChatId(message.getChatId(), RESULT_TYPESCRIPT);
                break;
            case ("Golang"):
                stateProvider.changeStateByChatId(message.getChatId(), RESULT_GO);
                break;
            case ("Python"):
                stateProvider.changeStateByChatId(message.getChatId(), RESULT_PYTHON);
                break;
        }
        Thread.sleep(1000);
        bot.execute(responseCreator.createPhoto(message.getChatId(), correctPhotoPath(profile.getGap()), "Готово!\nЗабирай знания, " + profile.getName() + ";)",
                keyboardBuilder.createSingleButtonInlineKeyboard(profile.getGap(), profile.getGap())));
    }

    private String correctPhotoPath(final String profileChoice) {
        final StringBuilder builder = new StringBuilder();
        builder.append("static/images/");
        switch (profileChoice) {
            case("Java"):
                builder.append("java.jpg");
                break;
            case("C++"):
                builder.append("cplusplus.jpg");
                break;
            case("C#"):
                builder.append("csharp.jpg");
                break;
            case("Kotlin"):
                builder.append("kotlin.jpg");
                break;
            case("JavaScript"):
                builder.append("javascript.jpg");
                break;
            case("TypeScript"):
                builder.append("typescript.jpg");
                break;
            case("Python"):
                builder.append("python.jpg");
                break;
            case("Golang"):
                builder.append("golang.jpg");
                break;
        }
        return builder.toString();
    }

    @Override
    public List<String> getCommands() {
        return Collections.emptyList();
    }

    @Override
    public List<State> availCurrentStates() {
        return Collections.singletonList(GAP);
    }
}