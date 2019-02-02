package com.servebeer.please.solarmax.array;

import junit.framework.TestCase;
import org.junit.Test;

public class ArrayManagerIT {

    @Test
    public void ping() throws Exception {

        System.out.println("ping response: " + ArrayManager.ping());
    }

    @Test
    public void currentlyGeneratedPowerIT() throws Exception {
        int currentlyGeneratedPower = ArrayManager.getCurrentlyGeneratedPower();

        System.out.println("currentlyGeneratedPower [W]:    " + currentlyGeneratedPower);
        TestCase.assertTrue(currentlyGeneratedPower > 0);
    }
}
