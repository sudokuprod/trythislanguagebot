package com.sudokuprod.trythislanguagebot.telegram.command.impl.results;

import com.sudokuprod.trythislanguagebot.telegram.command.TelegramCommand;
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

import static com.sudokuprod.trythislanguagebot.telegram.state.State.*;

@Slf4j
@Component
public class ResultAuthor implements TelegramCommand {

    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;

    public ResultAuthor(ResponseCreator responseCreator,
                        StateProvider stateProvider) {
        this.responseCreator = responseCreator;
        this.stateProvider = stateProvider;
    }

    @Override
    public void execute(@NonNull final TelegramLongPollingBot bot,
                        @NonNull final Message message,
                        @NonNull final UserState currentState,
                        @NonNull final UserProfile profile) throws TelegramApiException {
        final String contacts = "GitHub: https://github.com/sudokuprod \n" +
                "Моя красивая фоточка";
        bot.execute(responseCreator.createPhoto(message.getChatId(),"static/images/my.jpg", contacts));
        stateProvider.changeStateByChatId(message.getChatId(), NO_ONE);

    }

    @Override
    public List<String> getCommands() {
        return Collections.emptyList();
    }

    @Override
    public List<State> availCurrentStates() {
        return Collections.singletonList(AUTHOR_PAGE);
    }
}
