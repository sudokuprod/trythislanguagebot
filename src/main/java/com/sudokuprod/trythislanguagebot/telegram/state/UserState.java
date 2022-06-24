package com.sudokuprod.trythislanguagebot.telegram.state;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserState {
    private final long chatId;
    private State currentState;

    public UserState(long chatId) {
        this(chatId, State.NO_ONE);
    }

    public UserState(long chatId,
                     State state) {
        this.chatId = chatId;
        this.currentState = state;
    }

}
