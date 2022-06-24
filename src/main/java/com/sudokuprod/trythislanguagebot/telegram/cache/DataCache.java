package com.sudokuprod.trythislanguagebot.telegram.cache;

import com.sudokuprod.trythislanguagebot.telegram.profile.UserProfile;
import com.sudokuprod.trythislanguagebot.telegram.state.State;
import com.sudokuprod.trythislanguagebot.telegram.state.UserState;

import java.util.Map;

public interface DataCache {
    void setUserState(long chatId, State state);

    UserState getUserStateByChatId(long chatId);

    void removeByChatId(long chatId);

    UserProfile changeParamsByChatId(long chatId, String call);

    Map<String, Integer> getResultProfileMap(long chatId);

    UserProfile getUserProfileByChatId(long chatId);

    void removeUserProfileByChatId(long chatId);

}
