package com.servebeer.please.solarmax.connector;

import static junit.framework.TestCase.assertEquals;

import static com.servebeer.please.solarmax.connector.SolarmaxCommands.SolarmaxCommandKey.*;
import static junit.framework.TestCase.failNotEquals;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SolarmaxConnectorTest {

    @Test
    public void mkmsgTest() throws Exception {

        final String validCommand1 = "{FB;05;4E|64:E1D;E11;E1h;E1m;E1M;E2D;E21;E2h;E2m;E2M;E3D;E31;E3h;E3m;E3M|1270}";

        String msg = SolarmaxCommands.contructRequest(5, "E1D;E11;E1h;E1m;E1M;E2D;E21;E2h;E2m;E2M;E3D;E31;E3h;E3m;E3M");
        assertEquals("Error trying to recreate a valid command", validCommand1, msg);
    }

    @Test
    public void checksum16Test() throws Exception {

        final String input = "FB;05;4E|64:E1D;E11;E1h;E1m;E1M;E2D;E21;E2h;E2m;E2M;E3D;E31;E3h;E3m;E3M|";
        final String expected = "1270";

        String result = SolarmaxCommands.calculateChecksum16(input);
        assertEquals("Error trying to calculate checksum16 ", expected, result);
    }

    @Test
    public void extractValuesFromResponse() {
        // is there a ",0" or not??
        // final String sampleResponse = "{01;FB;6D|64:KDY=82;KMT=8F;KYR=23F7;KT0=72F1;TNF=1386;TKK=28;PAC=1F70;PRL=28;IL1=236;UL1=8F9;SYS=4E28,0|19E5}";
        final String sampleResponse = "{01;FB;6D|64:KDY=82;KMT=8F;KYR=23F7;KT0=72F1;TNF=1386;TKK=28;PAC=1F70;PRL=28;IL1=236;UL1=8F9;SYS=4E28|19E5}";
        final Map<SolarmaxCommands.SolarmaxCommandKey, String> expectedResponseMap = new HashMap<SolarmaxCommands.SolarmaxCommandKey, String>();

        // TODO: 14.11.2016 these probably need to be converted to strings or ints or whatever...
        expectedResponseMap.put(KDY, "82");
        expectedResponseMap.put(KMT, "8F");
        expectedResponseMap.put(KYR, "23F7");
        expectedResponseMap.put(KT0, "72F1");
        expectedResponseMap.put(TNF, "1386");
        expectedResponseMap.put(TKK, "28");
        expectedResponseMap.put(PAC, "1F70");
        expectedResponseMap.put(PRL, "28");
        expectedResponseMap.put(IL1, "236");
        expectedResponseMap.put(UL1, "8F9");
        expectedResponseMap.put(SYS, "4E28");

        Map<SolarmaxCommands.SolarmaxCommandKey, String> responseMap = SolarmaxConnector.extractValuesFromResponse(sampleResponse);

        if (!expectedResponseMap.equals(responseMap)) {
            failNotEquals("The expected response doesn't equal the response received", expectedResponseMap, responseMap);
        }
    }
}