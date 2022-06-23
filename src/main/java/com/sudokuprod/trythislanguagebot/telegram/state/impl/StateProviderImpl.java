package com.sudokuprod.trythislanguagebot.telegram.state.impl;

import com.sudokuprod.trythislanguagebot.telegram.cache.DataCache;
import com.sudokuprod.trythislanguagebot.telegram.state.State;
import com.sudokuprod.trythislanguagebot.telegram.state.StateProvider;
import com.sudokuprod.trythislanguagebot.telegram.state.UserState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StateProviderImpl implements StateProvider {

    private final DataCache dataCache;

    public StateProviderImpl(final DataCache dataCache) {
        this.dataCache = dataCache;
    }

    @Override
    public UserState getByChatId(long chatId) {
        return dataCache.getUserStateByChatId(chatId);
    }

    @Override
    public void changeStateByChatId(long chatId, State state) {
        dataCache.setUserState(chatId, state);
    }

    @Override
    public UserState createForChatId(long chatId) {
        return dataCache.getUserStateByChatId(chatId);
    }

    @Override
    public void removeByChatId(long chatId) {
        dataCache.removeByChatId(chatId);
    }


}
