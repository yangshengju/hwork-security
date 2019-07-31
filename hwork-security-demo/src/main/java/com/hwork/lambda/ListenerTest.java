package com.hwork.lambda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by yangshengju on 2019-7-26.
 */
public class ListenerTest {
    public static void main(String[] args) {
        JButton testButton = new JButton("Test Button");
        testButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                System.out.println("Click Detected by Anon Class");
            }
        });

        testButton.addActionListener(e->System.out.println("click lambda manner"));
        JFrame frame = new JFrame("Listener Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(testButton, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
