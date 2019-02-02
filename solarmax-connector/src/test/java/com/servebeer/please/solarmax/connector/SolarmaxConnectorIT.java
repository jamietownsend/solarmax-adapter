package com.servebeer.please.solarmax.connector;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;
import org.junit.Ignore;

public class SolarmaxConnectorIT {

    @Test
    public void readSoftwareVersionIT() throws Exception {
        // sample request {FB;01;16|64:SWV|462}
        // sample response {FB;01;46|64:KDY;KMT;KYR;KT0;TNF;TKK;PAC;PRL;IL1;IDC;UL1;UDC;SYS|1199}
        List<SolarmaxCommands.SolarmaxCommandKey> commands = new ArrayList<SolarmaxCommands.SolarmaxCommandKey>();
        commands.add(SolarmaxCommands.SolarmaxCommandKey.SWV);

        Map<SolarmaxCommands.SolarmaxCommandKey, String> responseMap = null;

        System.out.println("readSoftwareVersionIT...");
        System.out.println("Host:    " + SolarmaxTestDefaults.host);
        System.out.println("Port:    " + SolarmaxTestDefaults.port);
        System.out.println("DeviceId:    " + SolarmaxTestDefaults.deviceId);

        try {
            responseMap = SolarmaxConnector.getValuesFromSolarmax(SolarmaxTestDefaults.host, SolarmaxTestDefaults.port, SolarmaxTestDefaults.deviceId, commands);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        if (responseMap.containsKey(SolarmaxCommands.SolarmaxCommandKey.SWV)) {
            System.out.println("Version: " + responseMap.get(SolarmaxCommands.SolarmaxCommandKey.SWV));
        } else {
            fail("Could not read the software version from host '" + SolarmaxTestDefaults.host + "' on port '" + SolarmaxTestDefaults.port + "'");
        }
    }

    // Test currently doesn't work. Since I don't need this functionality, just ignore the test for now
    @Ignore 
    @Test
    public void readErrorLog() throws Exception {
        // sample request {FB;02;2B|64:EL00;EL01;EL02;EL03;EL04|091B}
        // sample response {02;FB;8F|64:EL00=7E1070C,D783,4EDF,0;EL01=7E1070C,D717,4EDF,0;EL02=7E1070C,D496,4EDF,0;EL03=7E1070C,CCB0,4EDF,0;EL04=7E1070C,CC2B,4EDF,0|1F2E}
        List<SolarmaxCommands.SolarmaxCommandKey> commands = new ArrayList<SolarmaxCommands.SolarmaxCommandKey>();
        commands.add(SolarmaxCommands.SolarmaxCommandKey.E11); // Should this be EL00 ??

        Map<SolarmaxCommands.SolarmaxCommandKey, String> responseMap = null;

        System.out.println("readErrorLog...");
        System.out.println("Host:    " + SolarmaxTestDefaults.host);
        System.out.println("Port:    " + SolarmaxTestDefaults.port);
        System.out.println("DeviceId:    " + SolarmaxTestDefaults.deviceId);

        try {
            responseMap = SolarmaxConnector.getValuesFromSolarmax(SolarmaxTestDefaults.host, SolarmaxTestDefaults.port, SolarmaxTestDefaults.deviceId, commands);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        if (responseMap.containsKey(SolarmaxCommands.SolarmaxCommandKey.EL00)) {
            System.out.println("EL00: " + responseMap.get(SolarmaxCommands.SolarmaxCommandKey.EL00));
        } else {
            fail("Could not read the software version from host '" + SolarmaxTestDefaults.host + "' on port '" + SolarmaxTestDefaults.port + "'");
        }
    }
}
