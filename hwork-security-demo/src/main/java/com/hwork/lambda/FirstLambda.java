package com.hwork.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.*;

/**
 * Created by yangshengju on 2019-7-25.
 */
public class FirstLambda {
    static Logger logger = LoggerFactory.getLogger(FirstLambda.class);

    public static void main(String[] args) {

        FirstLambda firstLambda = new FirstLambda();
        String welcomeMsg = "Welcome to Beijing,";
        Thread t=new Thread(() -> System.out.println("thread"));
        t.start();
        // 1. 不需要参数,返回值为 5
        Supplier doubleSupplier = () -> 5;
        // 2. 接收一个参数(数字类型),返回其2倍的值
        IntToLongFunction intToLongFunction = x -> 2 * x;
        // 3. 接受2个参数(数字),并返回他们的差值
        MathOperation subtraction = (x, y) -> x-y;
//        MathOperation subtraction = (a, b) -> a - b;
        // 4. 接收2个int型整数,返回他们的和
        MathOperation addition  = (int x, int y) -> x + y;

        MathOperation multiply = (x,y)->x*y;

        MathOperation divide = (x,y)->x/y;

        // 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)
        GreetingService greetingService=(String s) -> System.out.println(s);

        logger.info("10+5="+firstLambda.operate(10,5,addition));
        logger.info("10-5="+firstLambda.operate(10,5,subtraction));
        logger.info("10*5="+firstLambda.operate(10,5,multiply));
        logger.info("10/5="+firstLambda.operate(10,5,divide));

        GreetingService greetingServiceWelcome=(String userName)->System.out.println(welcomeMsg+userName);
//        welcomeMsg = "xingxingdiandeng";
        greetingService.sayMessage("test");
        greetingServiceWelcome.sayMessage("Shengju");

    }


    private int operate(int a,int b,MathOperation mathOperation) {
        return mathOperation.operation(a,b);
    }


}
