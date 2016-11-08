package ch.townsend.jamie.electro.solarmax;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class SolarmaxConnectorIT {

    final private static String host = SolarmaxTestDefaults.host;
    final private static int port = SolarmaxTestDefaults.port;

    @Test
    public void readSoftwareVersionTest() throws Exception {
        String result = SolarmaxConnector.getValueFromSolarmax(SolarmaxTestDefaults.host, SolarmaxTestDefaults.port, 1, SolarmaxCommands.SolarmaxCommandKey.SWV);
        assertTrue("Could not read the software version from host '" + host + "' on port '" + port + "'", result.length() > 0);
    }
}