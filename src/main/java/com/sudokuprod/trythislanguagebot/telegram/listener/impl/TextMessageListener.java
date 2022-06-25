package com.sudokuprod.trythislanguagebot.telegram.listener.impl;

import com.sudokuprod.trythislanguagebot.telegram.command.TelegramCommand;
import com.sudokuprod.trythislanguagebot.telegram.command.impl.common.BackCommand;
import com.sudokuprod.trythislanguagebot.telegram.command.impl.common.CircleStartCommand;
import com.sudokuprod.trythislanguagebot.telegram.command.impl.common.StartCommand;
import com.sudokuprod.trythislanguagebot.telegram.listener.TelegramListener;
import com.sudokuprod.trythislanguagebot.telegram.profile.ProfileProvider;
import com.sudokuprod.trythislanguagebot.telegram.profile.UserProfile;
import com.sudokuprod.trythislanguagebot.telegram.response.ResponseCreator;
import com.sudokuprod.trythislanguagebot.telegram.state.State;
import com.sudokuprod.trythislanguagebot.telegram.state.StateProvider;
import com.sudokuprod.trythislanguagebot.telegram.state.UserState;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sudokuprod.trythislanguagebot.telegram.Bot.UNKNOWN_COMMAND_MSG;

@Slf4j
@Service
public class TextMessageListener implements TelegramListener {
    private final LinkedList<TelegramCommand> commands;
    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;
    private final ProfileProvider profileProvider;

    @Autowired
    public TextMessageListener(ResponseCreator responseCreator,
                               StateProvider stateProvider,
                               ProfileProvider profileProvider,
                               List<TelegramCommand> commands) {
        this.responseCreator = responseCreator;
        this.stateProvider = stateProvider;
        this.profileProvider = profileProvider;

        this.commands = new LinkedList<>();
        // Основные команды
        this.commands.add(commands
                .stream()
                .filter(c -> c.getClass().isAssignableFrom(StartCommand.class))
                .findFirst().orElse(null));
        this.commands.add(commands
                .stream()
                .filter(c -> c.getClass().isAssignableFrom(BackCommand.class))
                .findFirst().orElse(null));
        this.commands.add(commands
                .stream()
                .filter(c -> c.getClass().isAssignableFrom(CircleStartCommand.class))
                .findFirst().orElse(null));
        // Шаги
        this.commands.addAll(commands
                .stream()
                .filter(c -> c.getClass().getSimpleName().contains("Step"))
                .peek(c -> log.debug("Register command {}", c.getClass().getSimpleName()))
                .collect(Collectors.toList()));
        // Результаты
        this.commands.addAll(commands
                .stream()
                .filter(c -> c.getClass().getSimpleName().contains("Result"))
                .peek(c -> log.debug("Register command {}", c.getClass().getSimpleName()))
                .collect(Collectors.toList()));
    }

    @Override
    public void onUpdate(@NonNull final TelegramLongPollingBot bot,
                         @NonNull final Update update) throws Exception {
        String text = null;
        long chatId = 0;
        Message message = null;

        if (update.hasMessage() && update.getMessage().hasText()) {
            text = update.getMessage().getText();
            chatId = update.getMessage().getChatId();
            message = update.getMessage();
        }

        if (update.hasCallbackQuery()) {
            text = update.getCallbackQuery().getData();
            chatId = update.getCallbackQuery().getMessage().getChatId();
            message = update.getCallbackQuery().getMessage();
        }

        try {
            if (chatId != 0 && Objects.nonNull(message)) {
                final UserState state = stateProvider.getByChatId(chatId);
                State currentState = state.getCurrentState();
                final UserProfile profile;
                if (text.equals("Начнём заного?")) {
                    profileProvider.removeUserProfileByChatId(chatId);
                    profile = profileProvider.getUserProfileByChatId(chatId);
                    profile.removeResultProfile();
                } else profile = profileProvider.changeParamsByChatId(chatId, text);

                final String fText = text;
                for (TelegramCommand command : commands) {
                    if (command.getCommands().stream().anyMatch(s -> s.startsWith(fText)) || command.getCommands().isEmpty()) {
                        if (command.availCurrentStates().contains(currentState)) {
                            command.execute(bot, message, state, profile);
                            return;
                        }
                    }
                }
                rollback(bot, chatId, UNKNOWN_COMMAND_MSG);
            }

        } catch (final Exception e) {
            log.warn("Exception while processing telegram update {}", update, e);
            rollback(bot, chatId, e.getMessage());
            throw e;
        }
    }

    private void rollback(final TelegramLongPollingBot bot,
                          final long chatId,
                          final String text) throws TelegramApiException {
        stateProvider.removeByChatId(chatId);
        stateProvider.createForChatId(chatId);
        profileProvider.removeUserProfileByChatId(chatId);
        bot.execute(responseCreator.createMessage(chatId, text));
    }
}
