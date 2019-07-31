package com.hwork.junitexampletest;

import com.hwork.junitexample.Calculator;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by yangshengju on 2019-7-24.
 */
public class CalculatorTest {
    @Test
    public void evalutesExpression() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        Assert.assertEquals(6,sum);
    }

}
