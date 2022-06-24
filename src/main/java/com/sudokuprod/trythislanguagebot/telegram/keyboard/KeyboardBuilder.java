package com.sudokuprod.trythislanguagebot.telegram.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;
import java.util.Map;

public interface KeyboardBuilder {

    ReplyKeyboardMarkup getBackReplyKeyboard();

    ReplyKeyboardMarkup createSingleButtonReplyKeyboard(String text);

    ReplyKeyboardMarkup createSingleRowReplyKeyboard(List<String> buttons);

    ReplyKeyboardMarkup createReplyKeyboard(List<List<String>> buttons);

    InlineKeyboardMarkup createSingleButtonInlineKeyboard(String text,
                                                          String callbackData);

    InlineKeyboardMarkup createSingleRowInlineKeyboard(Map<String, String> buttons);

    InlineKeyboardMarkup createInlineKeyboard(List<Map<String, String>> buttons);


}
