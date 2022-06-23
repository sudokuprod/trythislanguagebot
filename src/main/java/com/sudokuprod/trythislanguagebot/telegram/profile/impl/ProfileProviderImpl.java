package com.sudokuprod.trythislanguagebot.telegram.profile.impl;

import com.sudokuprod.trythislanguagebot.telegram.cache.DataCache;
import com.sudokuprod.trythislanguagebot.telegram.profile.ProfileProvider;
import com.sudokuprod.trythislanguagebot.telegram.profile.UserProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ProfileProviderImpl implements ProfileProvider {
    private final DataCache dataCache;

    public ProfileProviderImpl(final DataCache dataCache) {
        this.dataCache = dataCache;
    }

    @Override
    public UserProfile changeParamsByChatId(final long chatId, final String call) {
        return dataCache.changeParamsByChatId(chatId, call);
    }

    @Override
    public UserProfile getUserProfileByChatId(final long chatId) {
        return dataCache.getUserProfileByChatId(chatId);
    }

    @Override
    public Map<String, Integer> resultProfileMap(final UserProfile profile) {
        return dataCache.getResultProfileMap(profile);
    }

    @Override
    public void removeUserProfileByChatId(long chatId) {
        dataCache.removeUserProfileByChatId(chatId);
    }

}
