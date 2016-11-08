package ch.townsend.jamie.electro.solarmax;

import static junit.framework.TestCase.assertEquals;


import org.junit.Test;

public class SolarmaxConnectorTest {

    final private static String host = SolarmaxTestDefaults.host;
    final private static int port = SolarmaxTestDefaults.port;

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
}