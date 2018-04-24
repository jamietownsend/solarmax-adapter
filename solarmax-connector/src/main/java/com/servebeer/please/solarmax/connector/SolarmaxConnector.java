package com.servebeer.please.solarmax.connector;

import com.servebeer.please.solarmax.connector.exception.SolarmaxConnectionException;
import com.servebeer.please.solarmax.connector.exception.SolarmaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

/**
 * With a little help from https://github.com/sushiguru/solar-pv/blob/master/solmax/pv.php
 */
public class SolarmaxConnector {

    /**
     * default port number of Solarmax devices is...
     */
    final private static int defaultPort = 12345;

    private static Logger log = LoggerFactory.getLogger(SolarmaxConnector.class);

    /**
     * default timeout for socket connections is 1 second
     */
    private static int connectionTimeout = 1000;

    /**
     * default timeout for socket responses is 10 second
     */
    private static int responseTimeout = 10000;

//    public static List<SolarmaxCommands.SolarmaxCommandKey, String> getCurrentValuesFromSolarmax(final String host, int port, final int deviceAddress) throws SolarmaxException, UnknownHostException {
//
//        List<SolarmaxCommands.SolarmaxCommandKey> commandList = new ArrayList<SolarmaxCommands.SolarmaxCommandKey>();
//        commandList = Arrays.asList(SolarmaxCommands.SolarmaxCommandKey.values());
//        return getValuesFromSolarmax(host, port, deviceAddress, commandList);
//    }

    /**
     * gets values from the SolarMax device addressable at host:port (at the default location "0")
     */
    public static Map<SolarmaxCommands.SolarmaxCommandKey, String> getValuesFromSolarmax(final String host, int port, final List<SolarmaxCommands.SolarmaxCommandKey> commandList) throws SolarmaxException, UnknownHostException {
        return getValuesFromSolarmax(host, port, 0, commandList);
    }

    /**
     * gets values from the SolarMax device addressable at host:port
     */
    public static Map<SolarmaxCommands.SolarmaxCommandKey, String> getValuesFromSolarmax(final String host, int port, final int deviceAddress, final List<SolarmaxCommands.SolarmaxCommandKey> commandList) throws SolarmaxException, UnknownHostException {

        Socket socket;
        String valueFromSolarmax;

        port = (port == 0) ? defaultPort : port;
        socket = getSocketConnection(host, port);

        log.debug("    Requesting data from {}:{}({}) with timeout of {}ms", host, port, deviceAddress, responseTimeout);
        return getValuesFromSolarmax(socket, deviceAddress, commandList);

    }

    private static String getCommandString(List<SolarmaxCommands.SolarmaxCommandKey> commandList) {
        String commandString = null;
        for (SolarmaxCommands.SolarmaxCommandKey command : commandList) {
            if (commandString == null) {
                commandString = command.toString();
            } else {
                commandString = commandString + ";" + command.toString();
            }
        }
        return commandString;
    }

    private static Map<SolarmaxCommands.SolarmaxCommandKey, String> getValuesFromSolarmax(final Socket socket, final int deviceAddress, final List<SolarmaxCommands.SolarmaxCommandKey> commandList) throws SolarmaxException {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();

            return getValuesFromSolarmax(outputStream, inputStream, deviceAddress, commandList);

        } catch (final Exception e) {
            throw new SolarmaxException("Error getting input/output streams from socket", e);
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
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

    private static Map<SolarmaxCommands.SolarmaxCommandKey, String> getValuesFromSolarmax(final OutputStream outputStream, final InputStream inputStream, final int deviceAddress, final List<SolarmaxCommands.SolarmaxCommandKey> commandList)
            throws SolarmaxException {

        Map<SolarmaxCommands.SolarmaxCommandKey, String> returnedValues = new HashMap<SolarmaxCommands.SolarmaxCommandKey, String>();
        String commandString = getCommandString(commandList);
        String request = SolarmaxCommands.contructRequest(deviceAddress, commandString);
        try {

            // hard code it for now
            // request = "{FB;01;46|64:KDY;KMT;KYR;KT0;TNF;TKK;PAC;PRL;IL1;IDC;UL1;UDC;SYS|1199}";

            // send the message out
            log.debug("    ==>: {}", request);

            outputStream.write(request.getBytes());
//            outputStream.flush();


            String response = "";
            byte[] responseByte = new byte[1];

            // get everything from the stream
            while (true) {
                // read one byte from the stream
                int bytesRead = inputStream.read(responseByte);
                if (bytesRead < 1) {
                    break;
                }
                response = response + new String(responseByte);
            }

            // read the header, start by removing the "{"
            //String inputStreamString = new Scanner(inputStream,"UTF-8").useDelimiter("\\|").next();

            // get the whole response
            //String response = new Scanner(inputStream, "UTF-8").useDelimiter("\\}").next() + "}";

            if (!SolarmaxCommands.validateResponse(response)) {
                throw new SolarmaxException("Invalid response received: " + response);
            }

            log.debug("    <==: {}", response);
            returnedValues = extractValuesFromResponse(response);

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

//            return response.toString();
            return returnedValues;

        } catch (IOException e) {
            log.debug("Error communicating via input/output streams " + e.getMessage());
            throw new SolarmaxException(e);
        }

    }

    /**
     * @param response e.g. "{01;FB;6D|64:KDY=82;KMT=8F;KYR=23F7;KT0=72F1;TNF=1386;TKK=28;PAC=1F70;PRL=28;IL1=236;UL1=8F9;SYS=4E28,0|19E5}"
     * @return a map of keys and values
     */
    static Map<SolarmaxCommands.SolarmaxCommandKey, String> extractValuesFromResponse(String response) {

        Map<SolarmaxCommands.SolarmaxCommandKey, String> responseMap = new HashMap<SolarmaxCommands.SolarmaxCommandKey, String>();

        // extract the body first
        // start by getting the part of the response between the two pipes
        String body = response.substring(response.indexOf("|") + 1, response.lastIndexOf("|"));

        // the name/value pairs now lie after the ":"
        body = body.substring(body.indexOf(":") + 1);

        // split into an array of name=value pairs
        String[] entries = body.split(";");
        for (String entry : entries) {

            SolarmaxCommands.SolarmaxCommandKey key = SolarmaxCommands.SolarmaxCommandKey.valueOf(entry.substring(0, 3));

            responseMap.put(key, entry.substring(4));
        }

        return responseMap;
    }

    private static Socket getSocketConnection(final String host, int port) throws SolarmaxConnectionException, UnknownHostException {

        port = (port == 0) ? defaultPort : port;

        Socket socket;

        try {
            socket = new Socket();
            log.debug("    Connecting to " + host + ":" + port + " with a timeout of " + connectionTimeout);
            socket.connect(new InetSocketAddress(host, port), connectionTimeout);
            log.debug("    Connected.");
            socket.setSoTimeout(responseTimeout);
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
    public static int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * @param connectionTimeout timeout for connections in milliseconds
     */
    public static void setConnectionTimeout(int connectionTimeout) {
        SolarmaxConnector.connectionTimeout = connectionTimeout;
    }

    /**
     * @return timeout for responses in milliseconds
     */
    public static int getResponseTimeout() {
        return responseTimeout;
    }

    /**
     * @param responseTimeout timeout for responses in milliseconds
     */
    public static void setResponseTimeout(int responseTimeout) {
        SolarmaxConnector.responseTimeout = responseTimeout;
    }


}
