package com.servebeer.please.solarmax.communicator;

import com.servebeer.please.solarmax.PowerGenerationDetail;
import com.servebeer.please.solarmax.SolarmaxTestDefaults;

import org.junit.Test;

public class SolarmaxCommunicatorIT {

    static final String host = SolarmaxTestDefaults.getTestHost();
    static final int port = SolarmaxTestDefaults.getTestPort();
    static final int deviceId = SolarmaxTestDefaults.getTestDeviceId();

    @Test
    public void getPowerGenerationDetailsTest() throws Exception {
        PowerGenerationDetail details = SolarmaxCommunicator.getPowerGenerationDetails(host, port, deviceId);
        System.out.println(details);
    }
}
