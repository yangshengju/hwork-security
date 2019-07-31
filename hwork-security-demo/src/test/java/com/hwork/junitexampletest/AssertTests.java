package com.hwork.junitexampletest;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by yangshengju on 2019-7-24.
 */
public class AssertTests {
    @Test
    public void testAssertArrayEquals() {
        byte[] expected = "trial".getBytes();
        byte[] actual = "trial".getBytes();
        Assert.assertArrayEquals("failure - byte arrays not same",expected,actual);
    }

    @Test
    public void testAssertEquals() {
        Assert.assertEquals("failure - strings are not equal","text","text1");
    }

    @Test
    public void testAssertFalse() {
        Assert.assertFalse("failure - should be false!!",true);
    }

    @Test
    public void testAssertNotNull() {
        Assert.assertNotNull("should not be null",null);
    }

    @Test
    public void testAssertNotSame() {
        Object a = new Object();
        Assert.assertNotSame("should not be same object ",a,a);
    }

    @Test
    public void testAssertThatBothContainsString() {
        Assert.assertThat("albumen", CoreMatchers.both(CoreMatchers.containsString("a")).and(CoreMatchers.containsString("l")));
    }

    @Test
    public void testAssertThatHasItems() {
        Assert.assertThat(Arrays.asList("one", "two", "three"),CoreMatchers.hasItems("one","four"));
    }
}
