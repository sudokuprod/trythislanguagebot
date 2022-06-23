package com.sudokuprod.trythislanguagebot.telegram.command.impl.steps;

import com.sudokuprod.trythislanguagebot.telegram.command.TelegramCommand;
import com.sudokuprod.trythislanguagebot.telegram.keyboard.KeyboardBuilder;
import com.sudokuprod.trythislanguagebot.telegram.profile.ProfileProvider;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

import static com.sudokuprod.trythislanguagebot.telegram.state.State.FINAL;
import static com.sudokuprod.trythislanguagebot.telegram.state.State.GAP;

@Slf4j
@Component
public class FinalStep implements TelegramCommand {

    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;
    private final ProfileProvider profileProvider;

    public FinalStep(ResponseCreator responseCreator,
                     StateProvider stateProvider,
                     ProfileProvider profileProvider) {
        this.responseCreator = responseCreator;
        this.stateProvider = stateProvider;
        this.profileProvider = profileProvider;
    }

    @Override
    public void execute(@NonNull final TelegramLongPollingBot bot,
                        @NonNull final Message message,
                        @NonNull final UserState currentState,
                        @NonNull final UserProfile profile) throws TelegramApiException {
        final Map<String, Integer> resultProfile = profileProvider.resultProfileMap(profile);

        bot.execute(responseCreator.createPhoto(message.getChatId(), "static/images/9.jpg",
                resultBuilder(resultProfile),
                resultKeyboardBuilder(resultProfile)));
        stateProvider.changeStateByChatId(message.getChatId(), GAP);
    }

    @Override
    public List<String> getCommands() {
        return Collections.emptyList();
    }

    @Override
    public List<State> availCurrentStates() {
        return Collections.singletonList(FINAL);
    }

    private String resultBuilder(final Map<String, Integer> mapProfile) {
        final StringBuilder builder = new StringBuilder();

        mapProfile.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(c -> builder.append(c.getKey())
                        .append(" - ")
                        .append(c.getValue())
                        .append(correctWord(c.getValue()))
                        .append("\n\n"));
        builder.append("Теперь ты знаешь какие языки подходят тебе больше всего!\n")
                .append("Дальше выбор только за тобой.\n\n")
                .append("Выбирай любой язык и получай стартовый набор рекомендаций по изучению, удачи!");
        return builder.toString();
    }

    private String correctWord(final Integer value) {
        switch (value) {
            case(1):
                return " балл!";
            case(2):
            case(3):
            case(4):
                return " балла!";
            default:
                return " баллов!";
        }
    }

    private InlineKeyboardMarkup resultKeyboardBuilder(final Map<String, Integer> mapProfile) {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        final List<InlineKeyboardButton> keyboardRows1 = new LinkedList<>();
        final List<InlineKeyboardButton> keyboardRows2 = new LinkedList<>();
        final List<InlineKeyboardButton> keyboardRows3 = new LinkedList<>();
        final List<InlineKeyboardButton> keyboardRows4 = new LinkedList<>();
        final List<List<InlineKeyboardButton>> finalKeyboardRow = new LinkedList<>();
        final List<InlineKeyboardButton> keyboardButtons = new LinkedList<>();
        mapProfile.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(c -> keyboardButtons.add(InlineKeyboardButton.builder()
                        .text(c.getKey())
                        .callbackData(c.getKey()).build()));
            keyboardRows1.add(keyboardButtons.get(0));
            keyboardRows2.add(keyboardButtons.get(1));
            keyboardRows3.add(keyboardButtons.get(2));
            keyboardRows3.add(keyboardButtons.get(3));
            keyboardRows3.add(keyboardButtons.get(4));
            keyboardRows4.add(keyboardButtons.get(5));
            keyboardRows4.add(keyboardButtons.get(6));
            keyboardRows4.add(keyboardButtons.get(7));
            finalKeyboardRow.add(keyboardRows1);
            finalKeyboardRow.add(keyboardRows2);
            finalKeyboardRow.add(keyboardRows3);
            finalKeyboardRow.add(keyboardRows4);
            keyboardMarkup.setKeyboard(finalKeyboardRow);
            return keyboardMarkup;

    }
}