package com.sudokuprod.trythislanguagebot.telegram.profile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
@ToString
public class UserProfile {

    private final Map<String, Integer> resultProfile;
    private final long chatId;
    private static int java;
    private static int javaScript;
    private static int typeScript;
    private static int python;
    private static int csharp;
    private static int plusPlus;
    private static int go;
    private static int kotlin;
    private String gap;
    private String name;

    public UserProfile(final long chatId) {
        this.chatId = chatId;
        resultProfile = new LinkedHashMap<>();
    }


    public Map<String, Integer> getResultProfileMap() {
        resultProfile.put("Java", java);
        resultProfile.put("JavaScript", javaScript);
        resultProfile.put("C#", csharp);
        resultProfile.put("Golang", go);
        resultProfile.put("Kotlin", kotlin);
        resultProfile.put("C++", plusPlus);
        resultProfile.put("Python", python);
        resultProfile.put("TypeScript", typeScript);
        return resultProfile;
    }

    public void removeResultProfile() {
        java = 0;
        javaScript = 0;
        csharp = 0;
        go = 0;
        kotlin = 0;
        plusPlus = 0;
        python = 0;
        typeScript = 0;
    }

    public void changeParams(final String call) {
        switch (call) {
            case ("call1"):
            case ("call5"):
            case ("call12"):
            case ("call18"):
                javaScript += 2;
                typeScript += 2;
                break;
            case ("call2"):
                java += 2;
                python += 2;
                go += 2;
                break;
            case ("call3"):
                csharp += 2;
                kotlin += 2;
                break;
            case ("call4"):
            case ("call7"):
            case ("call11"):
            case ("call15"):
            case ("call19"):
            case ("call25"):
                plusPlus += 2;
                break;
            case ("call6"):
                java += 2;
                python += 2;
                go += 2;
                csharp += 2;
                kotlin += 2;
                break;
            case ("call8"):
            case ("call13"):
            case ("call16"):
                java += 2;
                python += 2;
                csharp += 2;
                break;
            case ("call9"):
            case ("call14"):
            case ("call17"):
                go += 2;
                kotlin += 2;
                typeScript += 2;
                break;
            case ("call10"):
                javaScript += 2;
                break;
            case ("call20"):
                kotlin += 3;
                java += 2;
                break;
            case ("call21"):
                java += 2;
                csharp += 3;
                javaScript += 1;
                python += 1;
                go += 1;
                break;
            case ("call22"):
                python += 3;
                plusPlus += 2;
                break;
            case ("call23"):
                javaScript += 3;
                typeScript += 3;
                break;
            case ("call24"):
                java += 2;
                python += 1;
                go += 2;
                break;
            case ("call26"):
                java += 2;
                plusPlus += 2;
                csharp += 2;
                kotlin += 2;
                break;
            case ("call27"):
                go += 2;
                typeScript += 2;
                javaScript += 2;
                break;
            case ("call28"):
                python += 2;
                java += 1;
                break;
            case ("call29"):
            case ("call30"):
                csharp += 2;
                java += 2;
                go += 2;
                break;
            case ("call31"):
            case ("call32"):
                javaScript += 2;
                python += 2;
                plusPlus += 2;
                kotlin += 2;
                typeScript += 2;
                break;
            case ("Java"):
            case ("C++"):
            case ("JavaScript"):
            case ("TypeScript"):
            case ("Kotlin"):
            case ("Golang"):
            case ("Python"):
            case ("C#"):
                gap = call;
                break;
            default:
                name = call;
                break;
        }
    }
}
