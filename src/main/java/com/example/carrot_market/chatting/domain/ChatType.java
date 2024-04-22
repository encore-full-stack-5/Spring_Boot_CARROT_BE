package com.example.carrot_market.chatting.domain;

public enum ChatType {
    MESSAGE(1),
    ENTER(2),
    EXIT(3),
    SYSTEM(4);

    private int value;
    private ChatType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ChatType valueOf(int value) {
        switch (value) {
            case 1:
                return MESSAGE;
            case 2:
                return ENTER;
            case 3:
                return EXIT;
            case 4:
                return SYSTEM;
            default:
                throw new AssertionError("Unknown value: " + value);
        }
    }
}
