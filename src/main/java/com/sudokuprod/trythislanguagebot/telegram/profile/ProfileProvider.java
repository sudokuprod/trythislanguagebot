package com.sudokuprod.trythislanguagebot.telegram.profile;

import java.util.Map;

public interface ProfileProvider {
    UserProfile changeParamsByChatId(long chatId, String call);

    UserProfile getUserProfileByChatId(long chatId);

    Map<String, Integer> resultProfileMap(long chatId);

    void removeUserProfileByChatId(long chatId);
}
