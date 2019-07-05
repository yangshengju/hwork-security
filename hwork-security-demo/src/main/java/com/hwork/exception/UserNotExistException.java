package com.hwork.exception;

/**
 * Created by yangshengju on 2019-7-5.
 */
public class UserNotExistException extends RuntimeException {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserNotExistException(String userId, String message) {
        super(userId + message);
        this.setUserId(userId);
    }
}
