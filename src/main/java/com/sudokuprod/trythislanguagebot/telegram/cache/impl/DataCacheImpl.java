package com.sudokuprod.trythislanguagebot.telegram.cache.impl;

import com.sudokuprod.trythislanguagebot.telegram.cache.DataCache;
import com.sudokuprod.trythislanguagebot.telegram.profile.UserProfile;
import com.sudokuprod.trythislanguagebot.telegram.state.State;
import com.sudokuprod.trythislanguagebot.telegram.state.UserState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class DataCacheImpl implements DataCache {
    private Map<Long, UserState> userStates = new HashMap<>();
    private Map<Long, UserProfile> userProfile = new HashMap<>();


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
    public UserProfile changeParamsByChatId(final long chatId, String call) {
        UserProfile profile = getUserProfileByChatId(chatId);
        switch (call) {
            case ("call1"):
            case ("call5"):
            case ("call12"):
            case ("call18"):
                profile.setJavaScript(profile.getJavaScript() + 2);
                profile.setTypeScript(profile.getTypeScript() + 2);
                break;
            case ("call2"):
                profile.setJava(profile.getJava() + 2);
                profile.setPython(profile.getPython() + 2);
                profile.setGo(profile.getGo() + 2);
                break;
            case ("call3"):
                profile.setCsharp(profile.getCsharp() + 2);
                profile.setKotlin(profile.getKotlin() + 2);
                break;
            case ("call4"):
            case ("call7"):
            case ("call11"):
            case ("call15"):
            case ("call19"):
            case ("call25"):
                profile.setPlusPlus(profile.getPlusPlus() +2);
                break;
            case ("call6"):
                profile.setJava(profile.getJava() + 2);
                profile.setPython(profile.getPython() + 2);
                profile.setGo(profile.getGo() + 2);
                profile.setCsharp(profile.getCsharp() + 2);
                profile.setKotlin(profile.getKotlin() + 2);
                break;
            case ("call8"):
            case ("call13"):
            case ("call16"):
                profile.setJava(profile.getJava() + 2);
                profile.setPython(profile.getPython() + 2);
                profile.setCsharp(profile.getCsharp() + 2);
                break;
            case ("call9"):
            case ("call14"):
            case ("call17"):
                profile.setGo(profile.getGo() + 2);
                profile.setKotlin(profile.getKotlin() + 2);
                profile.setTypeScript(profile.getTypeScript() + 2);
                break;
            case ("call10"):
                profile.setJavaScript(profile.getJavaScript() + 2);
                break;

            case ("call20"):
                profile.setKotlin(profile.getKotlin() + 3);
                profile.setJava(profile.getJava() + 2);
                break;
            case ("call21"):
                profile.setJava(profile.getJava() + 1);
                profile.setCsharp(profile.getCsharp() + 3);
                profile.setJavaScript(profile.getJavaScript() + 1);
                profile.setPython(profile.getPython() + 1);
                profile.setGo(profile.getGo() + 1);
                break;
            case ("call22"):
                profile.setPython(profile.getPython() + 3);
                profile.setPlusPlus(profile.getPlusPlus() +2);
                break;
            case ("call23"):
                profile.setJavaScript(profile.getJavaScript() + 3);
                profile.setTypeScript(profile.getTypeScript() + 3);
                break;
            case ("call24"):
                profile.setJava(profile.getJava() + 2);
                profile.setPython(profile.getPython() + 1);
                profile.setGo(profile.getGo() + 2);
                break;
            case ("call26"):
                profile.setJava(profile.getJava() + 2);
                profile.setPlusPlus(profile.getPlusPlus() +2);
                profile.setCsharp(profile.getCsharp() + 2);
                profile.setKotlin(profile.getKotlin() + 2);
                break;
            case ("call27"):
                profile.setGo(profile.getGo() + 2);
                profile.setTypeScript(profile.getTypeScript() + 2);
                profile.setJavaScript(profile.getJavaScript() + 2);
                break;
            case ("call28"):
                profile.setPython(profile.getPython() + 2);
                profile.setJava(profile.getJava() + 1);
                break;
            case ("call29"):
            case ("call30"):
                profile.setCsharp(profile.getCsharp() + 2);
                profile.setJava(profile.getJava() + 2);
                profile.setGo(profile.getGo() + 2);
                break;
            case ("call31"):
            case ("call32"):
                profile.setJavaScript(profile.getJavaScript() + 2);
                profile.setPython(profile.getPython() + 2);
                profile.setPlusPlus(profile.getPlusPlus() +2);
                profile.setKotlin(profile.getKotlin() + 2);
                profile.setTypeScript(profile.getTypeScript() + 2);
                break;
            case("Java"):
            case("C++"):
            case("JavaScript"):
            case("TypeScript"):
            case("Kotlin"):
            case("Golang"):
            case("Python"):
            case("C#"):
                profile.setGap(call);
                break;
            default:
                profile.setName(call);
                break;

        }
        userProfile.put(chatId, profile);
        return userProfile.get(chatId);
    }

    @Override
    public Map<String, Integer> getResultProfileMap(final UserProfile profile) {
        Map<String, Integer> resultProfile = new LinkedHashMap<>();
        resultProfile.put("Java", profile.getJava());
        resultProfile.put("C#", profile.getCsharp());
        resultProfile.put("Golang", profile.getGo());
        resultProfile.put("Kotlin", profile.getKotlin());
        resultProfile.put("C++", profile.getPlusPlus());
        resultProfile.put("JavaScript", profile.getJavaScript());
        resultProfile.put("Python", profile.getPython());
        resultProfile.put("TypeScript", profile.getTypeScript());
        return resultProfile;
    }

    @Override
    public UserProfile getUserProfileByChatId(long chatId) {
        UserProfile profile = userProfile.get(chatId);
        if (profile == null) {
            profile = new UserProfile(chatId);
            userProfile.put(chatId,profile);
        }
        return profile;
    }

    @Override
    public void removeUserProfileByChatId(long chatId) {
        userProfile.remove(chatId);
    }


}
