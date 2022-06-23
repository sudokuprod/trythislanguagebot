package com.sudokuprod.trythislanguagebot.telegram.profile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserProfile {

    private final long chatId;
    private int java;
    private int javaScript;
    private int typeScript;
    private int python;
    private int csharp;
    private int plusPlus;
    private int go;
    private int kotlin;
    private String gap;
    private String name;

    public UserProfile(final long chatId) {
        this.chatId = chatId;
        java = 0;
        javaScript = 0;
        typeScript = 0;
        python = 0;
        csharp = 0;
        plusPlus = 0;
        go = 0;
        kotlin = 0;
        gap = "";
        name = "";
    }
}
