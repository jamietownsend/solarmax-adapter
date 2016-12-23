package com.servebeer.please.solarmax.connector;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

public class SolarmaxConnectorIT {

    @Test
    public void readSoftwareVersionIT() throws Exception {
        // sample request {FB;01;16|64:SWV|462}
        // sample response {FB;01;46|64:KDY;KMT;KYR;KT0;TNF;TKK;PAC;PRL;IL1;IDC;UL1;UDC;SYS|1199}
        List<SolarmaxCommands.SolarmaxCommandKey> commands = new ArrayList<SolarmaxCommands.SolarmaxCommandKey>();
        commands.add(SolarmaxCommands.SolarmaxCommandKey.SWV);

        Map<SolarmaxCommands.SolarmaxCommandKey, String> responseMap = null;

        try {
            responseMap = SolarmaxConnector.getValuesFromSolarmax(SolarmaxTestDefaults.host, SolarmaxTestDefaults.port, SolarmaxTestDefaults.deviceId, commands);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        System.out.println("Host:    " + SolarmaxTestDefaults.host);
        System.out.println("Port:    " + SolarmaxTestDefaults.port);
        if (responseMap.containsKey(SolarmaxCommands.SolarmaxCommandKey.SWV)) {
            System.out.println("Version: " + responseMap.get(SolarmaxCommands.SolarmaxCommandKey.SWV));
        } else {
            fail("Could not read the software version from host '" + SolarmaxTestDefaults.host + "' on port '" + SolarmaxTestDefaults.port + "'");
        }
    }
}
