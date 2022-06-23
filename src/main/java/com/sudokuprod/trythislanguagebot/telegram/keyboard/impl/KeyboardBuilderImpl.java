package com.sudokuprod.trythislanguagebot.telegram.keyboard.impl;

import com.sudokuprod.trythislanguagebot.telegram.keyboard.KeyboardBuilder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableList;

@Slf4j
@Service
public class KeyboardBuilderImpl implements KeyboardBuilder {
    private ReplyKeyboardMarkup backReplyKeyboard;
    private List<String> replyBackButton;

    public KeyboardBuilderImpl() {
        buildBackKeyboards();
    }

    private void buildBackKeyboards() {
        this.replyBackButton = Collections.singletonList("Начнём заного?");
        this.backReplyKeyboard = createReplyKeyboard(new LinkedList<>());
    }

    @Override
    public ReplyKeyboardMarkup getBackReplyKeyboard() {
        return backReplyKeyboard;
    }

    @Override
    public ReplyKeyboardMarkup createSingleButtonReplyKeyboard(@NonNull final String text) {
        final List<List<String>> rows = new LinkedList<>();
        rows.add(Collections.singletonList(text));
        return createReplyKeyboard(rows);
    }

    @Override
    public ReplyKeyboardMarkup createSingleRowReplyKeyboard(@NonNull final List<String> buttons) {
        final List<List<String>> rows = new LinkedList<>();
        rows.add(buttons);
        return createReplyKeyboard(rows);
    }

    @Override
    public ReplyKeyboardMarkup createReplyKeyboard(@NonNull final List<List<String>> buttons) {
        buttons.add(replyBackButton);

        final ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        final List<KeyboardRow> rows = buttons.stream()
                .map(this::createReplyKeyboardRow)
                .collect(toUnmodifiableList());
        keyboard.setKeyboard(rows);
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);

        return keyboard;
    }

    @Override
    public InlineKeyboardMarkup createSingleButtonInlineKeyboard(@NonNull final String text,
                                                                 @NonNull final String callbackData) {
        final List<Map<String, String>> rows = new LinkedList<>();
        rows.add(Collections.singletonMap(text, callbackData));
        return createInlineKeyboard(rows);
    }

    @Override
    public InlineKeyboardMarkup createSingleRowInlineKeyboard(@NonNull final Map<String, String> buttons) {
        final List<Map<String, String>> rows = new LinkedList<>();
        rows.add(buttons);
        return createInlineKeyboard(rows);
    }

    @Override
    public InlineKeyboardMarkup createInlineKeyboard(@NonNull final List<Map<String, String>> buttons) {
        final InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        final List<List<InlineKeyboardButton>> inlineRows = buttons.stream()
                .map(this::createInlineKeyboardRow)
                .collect(Collectors.toList());
        keyboard.setKeyboard(inlineRows);

        return keyboard;
    }


    private List<InlineKeyboardButton> createInlineKeyboardRow(@NonNull final Map<String, String> buttons) {
        return buttons.entrySet()
                .stream()
                .map(e -> createInlineButton(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    private InlineKeyboardButton createInlineButton(@NonNull final String text,
                                                    @NonNull final String callbackData) {
        final InlineKeyboardButton button = new InlineKeyboardButton();
        button.setCallbackData(callbackData);
        button.setText(text);
        return button;
    }

    private KeyboardRow createReplyKeyboardRow(@NonNull final List<String> buttons) {
        val row = new KeyboardRow();
        row.addAll(buttons);
        return row;
    }

}
