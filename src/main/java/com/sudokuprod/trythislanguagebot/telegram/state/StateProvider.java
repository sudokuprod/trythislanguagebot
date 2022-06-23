package com.sudokuprod.trythislanguagebot.telegram.state;


public interface StateProvider {
    UserState getByChatId(long chatId);

    void changeStateByChatId(long chatId, State state);

    UserState createForChatId(long chatId);

    void removeByChatId(long chatId);
}
