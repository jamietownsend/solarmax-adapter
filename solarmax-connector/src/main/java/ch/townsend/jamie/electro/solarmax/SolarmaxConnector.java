package ch.townsend.jamie.electro.solarmax;

import ch.townsend.jamie.electro.solarmax.exception.SolarmaxConnectionException;
import ch.townsend.jamie.electro.solarmax.exception.SolarmaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * With a little help from https://github.com/sushiguru/solar-pv/blob/master/solmax/pv.php
 */
public class SolarmaxConnector {

    /**
     * default port number of Solarmax devices is...
     */
    final private static int defaultPort = 12345;

    private static final Logger logger = LoggerFactory.getLogger(SolarmaxConnector.class);

    /**
     * default timeout for connections is 1 second
     */

    private static int timeout = 1000;

    public static String getValueFromSolarmax(final String host, int port, final int deviceAddress, final SolarmaxCommands.SolarmaxCommandKey commandKey) throws SolarmaxException, UnknownHostException {

        Socket socket = null;
        String valueFromSolarmax = null;

        port = (port == 0) ? defaultPort : port;

        try {
            socket = getSocketConnection(host, port);

            valueFromSolarmax = getValueFromSolarmax(socket, deviceAddress, commandKey);

        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (final IOException e) {
                /* ignore the exception, we're dying anyway */
            }
        }

        return valueFromSolarmax;

    }

    private static String getValueFromSolarmax(final Socket socket, final int deviceAddress, final SolarmaxCommands.SolarmaxCommandKey commandKey) throws SolarmaxException {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();

            return getValueFromSolarmax(outputStream, inputStream, deviceAddress, commandKey);

        } catch (final IOException e) {
            throw new SolarmaxException("Error getting input/output streams from socket", e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException e) {
                // ignore the error, we're dying anyway...
            }
        }
    }

    private static String getValueFromSolarmax(final OutputStream outputStream, final InputStream inputStream, final int deviceAddress, final SolarmaxCommands.SolarmaxCommandKey commandKey)
            throws SolarmaxException {
        String msg = SolarmaxCommands.contructRequest(deviceAddress, commandKey.toString());
        String msgString = new String(msg);
        try {

            // send the message out
            outputStream.write(msg.getBytes());

            // Read the first 8 bytes
            byte[] response = new byte[8];
            inputStream.read(response, 0, 8);

            if (!SolarmaxCommands.checkValidResponseHeader(response)) {
                throw new SolarmaxException("Invalid response header: " + response);
            }

            // Read the next bytes - the deviceAddress
            inputStream.read(response, 0, 1);
            response = new byte[1];

            if (Integer.parseInt(String.valueOf(response)) != deviceAddress) {
                throw new SolarmaxException("Invalid response device address: " + response);
            }

            // just get everything else for now...
            response = new byte[]{};
            inputStream.read(response);

            logger.info(response.toString());

            /*
             * $V_LEN = hexdec($matches[2]);
             * $V_LEN -= 9; # header is already in
             * $V_MSG = fread($P_HANDLE, $V_LEN);
             * #Logic required here to separately test OPSTATES and return that value
             * if(!preg_match('/^\|64:(\w{3})=([0-9A-F]+)\|([0-9A-F]{4})}$/',$V_MSG,$matches))
             * {
             * flush();
             * fclose($P_HANDLE);
             * die("invalid response");
             * }
             * if($matches[1]!=$P_COMMAND['name'])
             * {
             * flush();
             * fclose($P_HANDLE);
             * die("wrong response");
             * }
             * $retval = $P_COMMAND['convert']($matches[2]);
             * return $retval;
             */

            return response.toString();

        } catch (IOException e) {
            logger.info("Error communicating via input/output streams " + e.getMessage());
            throw new SolarmaxException(e);
        }

    }

    private static Socket getSocketConnection(final String host, int port) throws SolarmaxConnectionException, UnknownHostException {

        port = (port == 0) ? defaultPort : port;

        Socket socket;

        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), timeout);
        } catch (final UnknownHostException e) {
            throw e;
        } catch (final Exception e) {
            throw new SolarmaxConnectionException("Error connecting to port '" + port + "' on host '" + host + "'", e);
        }

        return socket;
    }

    public static boolean connectionTest(final String host, int port) throws UnknownHostException {

        Socket socket = null;

        try {
            socket = getSocketConnection(host, port);
        } catch (SolarmaxConnectionException e) {
            return false;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // ignore any error while trying to close the socket
                }
            }
        }

        return true;
    }

    /**
     * @return timeout for connections in milliseconds
     */
    public static int getTimeout() {
        return timeout;
    }

    /**
     * @param timeout timeout for connections in milliseconds
     */
    public static void setTimeout(int timeout) {
        SolarmaxConnector.timeout = timeout;
    }


}
