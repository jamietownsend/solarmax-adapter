package ch.townsend.jamie.electro.solarmax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SolarmaxConnector {

    /**
     * default port number of Solarmax devices is...
     */
    final private static int defaultPort = 12345;

    /**
     * connection timeout of 1 second
     */
    final private static int timeout = 1000;

    private static final Logger logger = LoggerFactory.getLogger(SolarmaxConnector.class);

    public static boolean connectionTest(final String host, int port) {

        port = (port == 0) ? defaultPort : port;

        try {
            final Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), timeout);
        } catch (final UnknownHostException e) {
            logger.debug("Unknown Host: '" + host + "'");
            return false;
        } catch (final Exception e) {
            logger.debug("Error connecting to port '" + port + "' on host '" + host + "'");
            return false;
        }

        return true;
    }
}
