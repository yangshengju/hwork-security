package com.hwork.junitexample;

/**
 * Created by yangshengju on 2019-7-24.
 */
public class Calculator {
    public int evaluate(String expression) {
        int sum = 0;
        for(String summand:expression.split("\\+")) {
            sum+=Integer.valueOf(summand);
        }
        return sum;
    }
}
