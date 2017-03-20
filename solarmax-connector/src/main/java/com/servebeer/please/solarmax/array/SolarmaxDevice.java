package com.servebeer.please.solarmax.array;

import com.servebeer.please.solarmax.communicator.SolarmaxCommunicator;
import com.servebeer.please.solarmax.connector.SolarmaxConnector;
import com.servebeer.please.solarmax.connector.exception.SolarmaxException;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;

/**
 * contains details about the SolarMax device. Currently only supporting HTTP connections
 */
class SolarmaxDevice {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SolarmaxConnector.class);

    /**
     * hostname or ip address of the SolarMax device
     */
    private String host;

    /**
     * port number of the SolarMax device. Defaults to 12345
     */
    private int port = 12345;

    /**
     * device number if multiple devices are chained together. Defaults to 0
     */
    private int deviceNumber = 0;

    SolarmaxDevice(String host, int port, int deviceNumber) {
        this.host = host;
        this.port = port;
        this.deviceNumber = deviceNumber;
    }

    // returns true if the device can be contacted
    boolean ping() {
        return SolarmaxCommunicator.ping(host, port, deviceNumber);
    }

    int getCurrentlyGeneratedPower() {
        try {
            return SolarmaxCommunicator.getCurrentlyGeneratedPower(host, port, deviceNumber);
        } catch (UnknownHostException e) {
            // the host is unknown. log it and return 0.
            log.debug(e.getMessage());
            return 0;
        } catch (SolarmaxException e) {
            // there was some kind of exception. log it and return 0.
            log.debug(e.getMessage());
            return 0;
        }
    }

    public String toString() {
        return "Host: " + host + System.lineSeparator() + "Port: " + port + System.lineSeparator() +
                "Device Number: " + deviceNumber + System.lineSeparator() + "Online: " + ping() + System.lineSeparator();
    }
}
