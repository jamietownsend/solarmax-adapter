package com.servebeer.please.solarmax.communicator;

import com.servebeer.please.solarmax.connector.SolarmaxCommands;
import com.servebeer.please.solarmax.connector.SolarmaxConnector;
import com.servebeer.please.solarmax.connector.exception.SolarmaxException;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolarmaxCommunicator {

    public static int getCurrentlyGeneratedPower(final String host, final int port, final int deviceNumber) throws SolarmaxException, UnknownHostException {

        List<SolarmaxCommands.SolarmaxCommandKey> commandList = new ArrayList<SolarmaxCommands.SolarmaxCommandKey>();
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.PAC);

        Map<SolarmaxCommands.SolarmaxCommandKey, String> result = SolarmaxConnector.getValuesFromSolarmax(host, port, deviceNumber, commandList);

        return Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.PAC), 16) / 2;
    }

    // returns true if the device can be contacted
    public static boolean ping(final String host, final int port, final int deviceNumber) {

//        SolarmaxPowerStatus status = new SolarmaxPowerStatus();

        List<SolarmaxCommands.SolarmaxCommandKey> commandList = new ArrayList<SolarmaxCommands.SolarmaxCommandKey>();
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.SWV);

        Map<SolarmaxCommands.SolarmaxCommandKey, String> result;
        try {
            result = SolarmaxConnector.getValuesFromSolarmax(host, port, deviceNumber, commandList);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (SolarmaxException e) {
            e.printStackTrace();
            return false;
        }

        String softwareVersion;
        softwareVersion = result.get(SolarmaxCommands.SolarmaxCommandKey.SWV);

        // if the softwareVersion is not null, the device must be online and available
        return softwareVersion != null;
    }
}
