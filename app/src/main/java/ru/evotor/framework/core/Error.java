package ru.evotor.framework.core;

/**
 * Created by a.kuznetsov on 18/05/2017.
 */

public final class Error {
    private final int code;
    private final String message;

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
