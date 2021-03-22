package com.servebeer.please.solarmax.communicator;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.servebeer.please.solarmax.PowerGenerationDetail;
import com.servebeer.please.solarmax.connector.SolarmaxCommands;
import com.servebeer.please.solarmax.connector.SolarmaxConnector;
import com.servebeer.please.solarmax.connector.exception.SolarmaxException;

import org.slf4j.LoggerFactory;

public class SolarmaxCommunicator {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SolarmaxCommunicator.class);

    public static int getCurrentlyGeneratedPower(final String host, final int port, final int deviceNumber)
            throws SolarmaxException, UnknownHostException {

        List<SolarmaxCommands.SolarmaxCommandKey> commandList = new ArrayList<>();
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.PAC);

        Map<SolarmaxCommands.SolarmaxCommandKey, String> result = SolarmaxConnector.getValuesFromSolarmax(host, port,
                deviceNumber, commandList);

        return Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.PAC), 16) / 2;
    }

    public static PowerGenerationDetail getPowerGenerationDetails(final String host, final int port,
            final int deviceNumber) throws SolarmaxException, UnknownHostException {

        List<SolarmaxCommands.SolarmaxCommandKey> commandList = new ArrayList<>();
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.ADR);
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.KDY);
        // commandList.add(SolarmaxCommands.SolarmaxCommandKey.UDC);
        // commandList.add(SolarmaxCommands.SolarmaxCommandKey.IDC);
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.UL1);
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.UL2);
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.UL3);
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.IL1);
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.IL2);
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.IL3);
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.PAC);
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.TNF);
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.PRL);
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.TKK);
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.SYS);

        Map<SolarmaxCommands.SolarmaxCommandKey, String> result = SolarmaxConnector.getValuesFromSolarmax(host, port,
                deviceNumber, commandList);

        PowerGenerationDetail pgd = new PowerGenerationDetail().dataAtDateTime(LocalDateTime.now());
        pgd.address(result.get(SolarmaxCommands.SolarmaxCommandKey.ADR))
                .wattHoursToday(Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.KDY), 16) * 100)
                // .dcVolts((float) Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.UDC), 16))
                // .dcAmps((float) Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.IDC), 16))
                .acVolts1((float) Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.UL1), 16) / 10)
                .acVolts2((float) Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.UL2), 16) / 10)
                .acVolts3((float) Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.UL3), 16) / 10)
                .acAmps1((float) Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.IL1), 16) / 100)
                .acAmps2((float) Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.IL2), 16) / 100)
                .acAmps3((float) Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.IL3), 16) / 100)
                .acPowerWatts(Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.PAC), 16) / 2)
                .acFrequencyHerz(
                        (float) Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.TNF), 16) / 100)
                .acPowerPercent((float) Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.PRL), 16))
                .temperatureHeatSink((float) Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.TKK), 16))
        // .operationState(Integer.parseInt(result.get(SolarmaxCommands.SolarmaxCommandKey.SYS), 16))
        ;

        return pgd;
    }

    // returns true if the device can be contacted
    public static boolean ping(final String host, final int port, final int deviceNumber) {

        //        SolarmaxPowerStatus status = new SolarmaxPowerStatus();
        List<SolarmaxCommands.SolarmaxCommandKey> commandList = new ArrayList<>();
        commandList.add(SolarmaxCommands.SolarmaxCommandKey.SWV);

        Map<SolarmaxCommands.SolarmaxCommandKey, String> result;
        try {
            result = SolarmaxConnector.getValuesFromSolarmax(host, port, deviceNumber, commandList);
        } catch (UnknownHostException | SolarmaxException e) {
            log.error(e.getMessage(), e);
            return false;
        }

        String softwareVersion;
        softwareVersion = result.get(SolarmaxCommands.SolarmaxCommandKey.SWV);

        // if the softwareVersion is not null, the device must be online and available
        return softwareVersion != null;
    }
}
