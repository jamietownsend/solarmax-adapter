package ch.townsend.jamie.electro.solarmax;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class SolarmaxConnectorTest {

    final private static String host = SolarmaxTestDefaults.defaultHost;
    final private static int port = SolarmaxTestDefaults.defaultPort;

    @Test
    public void connectionTest() throws Exception {
        assertTrue("Could not connect to host '" + host + "' on port '" + port + "'", SolarmaxConnector.connectionTest(host, port));
    }
}