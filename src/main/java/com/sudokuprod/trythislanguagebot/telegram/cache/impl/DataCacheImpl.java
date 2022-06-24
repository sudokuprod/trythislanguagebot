package com.sudokuprod.trythislanguagebot.telegram.cache.impl;

import com.sudokuprod.trythislanguagebot.telegram.cache.DataCache;
import com.sudokuprod.trythislanguagebot.telegram.profile.UserProfile;
import com.sudokuprod.trythislanguagebot.telegram.state.State;
import com.sudokuprod.trythislanguagebot.telegram.state.UserState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DataCacheImpl implements DataCache {
    private final Map<Long, UserState> userStates = new HashMap<>();
    private final Map<Long, UserProfile> userProfile = new HashMap<>();


    @Override
    public void setUserState(final long chatId, final State state) {
        UserState userState = getUserStateByChatId(chatId);
        userState.setCurrentState(state);
        userStates.put(chatId, userState);
    }

    @Override
    public UserState getUserStateByChatId(final long chatId) {
        UserState state = userStates.get(chatId);
        if (state == null) {
            state = new UserState(chatId);
        }
        return state;
    }

    @Override
    public void removeByChatId(final long chatId) {
        UserState state = getUserStateByChatId(chatId);
        state.setCurrentState(null);
    }

    @Override
    public UserProfile changeParamsByChatId(final long chatId, final String call) {
        UserProfile profile = getUserProfileByChatId(chatId);
        profile.changeParams(call);
        userProfile.put(chatId, profile);
        return userProfile.get(chatId);
    }

    @Override
    public Map<String, Integer> getResultProfileMap(final long chatId) {
        UserProfile profile = getUserProfileByChatId(chatId);
        return profile.getResultProfileMap();
    }

    @Override
    public UserProfile getUserProfileByChatId(long chatId) {
        UserProfile profile = userProfile.get(chatId);
        if (profile == null) {
            profile = new UserProfile(chatId);
            userProfile.put(chatId, profile);
        }
        return profile;
    }

    @Override
    public void removeUserProfileByChatId(long chatId) {
        userProfile.remove(chatId);
    }


}
