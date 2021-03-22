package com.servebeer.please.solarmax.array;

import java.net.UnknownHostException;

import com.servebeer.please.solarmax.PowerGenerationDetail;
import com.servebeer.please.solarmax.communicator.SolarmaxCommunicator;
import com.servebeer.please.solarmax.connector.SolarmaxConnector;
import com.servebeer.please.solarmax.connector.exception.SolarmaxException;

import org.slf4j.LoggerFactory;

/**
 * contains details about the SolarMax device. Currently only supporting HTTP connections
 */
class SolarmaxDevice {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SolarmaxConnector.class);

    /**
     * hostname or ip address of the SolarMax device
     */
    private final String host;

    /**
     * port number of the SolarMax device. Defaults to 12345
     */
    private final int port;

    /**
     * device number if multiple devices are chained together. Defaults to 0
     */
    private final int deviceNumber;

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
        } catch (UnknownHostException | SolarmaxException e) {
            // the host is unknown. log it and return 0.
            log.debug(e.getMessage());
            return 0;
        }
        // there was some kind of exception. log it and return 0.

    }

    public PowerGenerationDetail getPowerGenerationDetails() {
        try {
            return SolarmaxCommunicator.getPowerGenerationDetails(host, port, deviceNumber);
        } catch (UnknownHostException | SolarmaxException e) {
            // the host is unknown. log it and return 0.
            log.debug(e.getMessage());
            return null;
        }

    }

    @Override
    public String toString() {
        return "Host: " + host + System.lineSeparator() + "Port: " + port + System.lineSeparator() + "Device Number: "
                + deviceNumber + System.lineSeparator() + "Online: " + ping() + System.lineSeparator();
    }
}
