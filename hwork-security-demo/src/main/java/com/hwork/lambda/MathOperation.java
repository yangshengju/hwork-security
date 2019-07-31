package com.hwork.lambda;

/**
 * Created by yangshengju on 2019-7-25.
 */
@FunctionalInterface
public interface MathOperation {
    abstract int operation(int x, int y);
}