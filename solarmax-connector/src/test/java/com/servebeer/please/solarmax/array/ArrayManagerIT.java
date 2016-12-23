package com.servebeer.please.solarmax.array;

import com.servebeer.please.solarmax.array.ArrayManager;
import com.servebeer.please.solarmax.connector.SolarmaxCommands;
import com.servebeer.please.solarmax.connector.SolarmaxConnector;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

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
