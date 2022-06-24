package com.sudokuprod.trythislanguagebot.telegram.response;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface ResponseCreator {

    SendMessage createMessage(long chatId,
                              String text);

    SendMessage createMessage(long chatId,
                              String text,
                              ReplyKeyboard keyboard);

    SendMessage createMessage(long chatId,
                              String text,
                              boolean enableNotification,
                              boolean enableHtml,
                              boolean enableWebPagePreview,
                              boolean enableMarkdown,
                              ReplyKeyboard replyKeyboard);


    SendPhoto createPhoto(long chatId,
                          String photoPath,
                          String caption);

    SendPhoto createPhoto(long chatId,
                          String photoPath,
                          String caption,
                          ReplyKeyboard keyboard);

    SendPhoto createPhoto(long chatId,
                          String photoPath,
                          String caption,
                          boolean enableNotification,
                          ReplyKeyboard keyboard);
}
