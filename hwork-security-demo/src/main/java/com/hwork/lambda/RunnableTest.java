package com.hwork.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yangshengju on 2019-7-25.
 */
public class RunnableTest {
    static Logger logger = LoggerFactory.getLogger(RunnableTest.class);
    public static void main(String[] args) {
        logger.info("===========Runnable Test========");
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                logger.info("runnable1");
            }
        };

        Runnable runnable2 = ()->logger.info("runnable2");
        runnable1.run();
        runnable2.run();
    }
}
