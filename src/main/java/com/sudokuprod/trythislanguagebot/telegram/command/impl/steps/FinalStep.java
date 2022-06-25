package com.sudokuprod.trythislanguagebot.telegram.command.impl.steps;

import com.sudokuprod.trythislanguagebot.telegram.command.impl.ParentByStepAndResult;
import com.sudokuprod.trythislanguagebot.telegram.profile.ProfileProvider;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

import static com.sudokuprod.trythislanguagebot.telegram.state.State.FINAL;
import static com.sudokuprod.trythislanguagebot.telegram.state.State.GAP;

@Slf4j
@Component
@RequiredArgsConstructor
public class FinalStep extends ParentByStepAndResult {

    private final ResponseCreator responseCreator;
    private final StateProvider stateProvider;
    private final ProfileProvider profileProvider;

    @Override
    public void execute(@NonNull final TelegramLongPollingBot bot,
                        @NonNull final Message message,
                        @NonNull final UserState currentState,
                        @NonNull final UserProfile profile) throws TelegramApiException {
        final Map<String, Integer> resultProfile = profileProvider.resultProfileMap(message.getChatId());

        bot.execute(responseCreator.createPhoto(message.getChatId(), "static/images/9.jpg",
                resultBuilder(resultProfile),
                resultKeyboardBuilder(resultProfile)));
        stateProvider.changeStateByChatId(message.getChatId(), GAP);
        profile.removeResultProfile();
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
            case (1):
            case (21):
                return " балл!";
            case (2):
            case (3):
            case (4):
            case (22):
                return " балла!";
            default:
                return " баллов!";
        }
    }

    // Собирает линии в одну клавиатуру, исходя из результатов опроса
    private InlineKeyboardMarkup resultKeyboardBuilder(final Map<String, Integer> mapProfile) {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        final List<InlineKeyboardButton> keyboardButtons = new LinkedList<>();
        mapProfile.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(c -> keyboardButtons.add(InlineKeyboardButton.builder()
                        .text(c.getKey())
                        .callbackData(c.getKey()).build()));
        keyboardMarkup.setKeyboard(keyboardRowsBuilder(keyboardButtons));
        return keyboardMarkup;
    }

    // Распределяет кнопки по линиям
    private List<List<InlineKeyboardButton>> keyboardRowsBuilder(final List<InlineKeyboardButton> buttons) {
        final int[] scheme = new int[]{1, 1, 3, 3};
        final List<List<InlineKeyboardButton>> keyboardRows = new LinkedList<>();
        int tempCounter = 0;
        for (int i = 0; i < scheme.length; i++) {
            List<InlineKeyboardButton> keyboardRow = new ArrayList<>();
            for (int j = 0; j < scheme[i]; j++) {
                keyboardRow.add(buttons.get(tempCounter++));
            }
            keyboardRows.add(keyboardRow);
        }
        return keyboardRows;
    }
}