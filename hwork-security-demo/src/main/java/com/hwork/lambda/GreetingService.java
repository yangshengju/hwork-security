package com.hwork.lambda;

/**
 * Created by yangshengju on 2019-7-25.
 */
@FunctionalInterface
public interface GreetingService {
    void sayMessage(String message);
    default boolean isTom() {return true;};
}
