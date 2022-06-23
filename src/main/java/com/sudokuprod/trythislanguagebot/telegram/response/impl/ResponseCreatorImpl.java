package com.sudokuprod.trythislanguagebot.telegram.response.impl;

import com.sudokuprod.trythislanguagebot.telegram.response.ResponseCreator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResponseCreatorImpl implements ResponseCreator {

    @Override
    public SendMessage createMessage(final long chatId,
                                     final String text) {
        return createMessage(chatId, text, true, false, false, false, null);
    }

    @Override
    public SendMessage createMessage(final long chatId,
                                     final String text,
                                     final ReplyKeyboard keyboard) {
        return createMessage(chatId, text, true, false, false, false, keyboard);
    }

    @Override
    public SendMessage createMessage(final long chatId,
                                     final String text,
                                     final boolean enableNotification,
                                     final boolean enableHtml,
                                     final boolean enableWebPagePreview,
                                     final boolean enableMarkdown,
                                     final ReplyKeyboard replyKeyboard) {
        final SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        message.setReplyMarkup(replyKeyboard);
        message.enableHtml(enableHtml);
        message.enableMarkdown(enableMarkdown);

        if (enableNotification) {
            message.enableNotification();
        }

        if (enableWebPagePreview) {
            message.enableWebPagePreview();
        }

        return message;
    }

    @Override
    public SendPhoto createPhoto(long chatId, String photoPath, String caption) {
        return createPhoto(chatId, photoPath, caption, true, null);
    }

    @Override
    public SendPhoto createPhoto(final long chatId,
                                 final String photoPath,
                                 final String caption,
                                 final ReplyKeyboard keyboard) {
        return createPhoto(chatId, photoPath, caption, true, keyboard);
    }

    @SneakyThrows
    @Override
    public SendPhoto createPhoto(final long chatId,
                                 final String photoPath,
                                 final String caption,
                                 final boolean enableNotification,
                                 final ReplyKeyboard keyboard) {
        final SendPhoto file = new SendPhoto();
        InputFile inputFile = new InputFile(ResourceUtils.getFile("classpath:" + photoPath));
        file.setChatId(String.valueOf(chatId));
        file.setPhoto(inputFile);
        file.setCaption(caption);
        file.setReplyMarkup(keyboard);

        if (enableNotification) {
            file.enableNotification();
        }
        return file;
    }

}
