package com.servebeer.please.solarmax.connector;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.servebeer.please.solarmax.SolarmaxTestDefaults;

import org.junit.Test;

public class SolarmaxConnectorFindCommands {

    static final String host = SolarmaxTestDefaults.getTestHost();
    static final int port = SolarmaxTestDefaults.getTestPort();
    static final int deviceId = SolarmaxTestDefaults.getTestDeviceId();

    @Test
    public void readSoftwareVersionIT() throws Exception {
        // sample request {FB;01;16|64:SWV|462}
        // sample response {FB;01;46|64:KDY;KMT;KYR;KT0;TNF;TKK;PAC;PRL;IL1;IDC;UL1;UDC;SYS|1199}
        List<SolarmaxCommands.SolarmaxCommandKey> commands = new ArrayList<SolarmaxCommands.SolarmaxCommandKey>();
        commands.add(SolarmaxCommands.SolarmaxCommandKey.SWV);

        Map<SolarmaxCommands.SolarmaxCommandKey, String> responseMap = null;

        System.out.println("readSoftwareVersionIT...");
        System.out.println("Host:    " + host);
        System.out.println("Port:    " + port);
        System.out.println("DeviceId:    " + deviceId);

        try {
            responseMap = SolarmaxConnector.getValuesFromSolarmax(host, port, deviceId, commands);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        if (responseMap.containsKey(SolarmaxCommands.SolarmaxCommandKey.SWV)) {
            System.out.println("Version: " + responseMap.get(SolarmaxCommands.SolarmaxCommandKey.SWV));
        } else {
            fail("Could not read the software version from host '" + host + "' on port '" + port + "'");
        }
    }
}
