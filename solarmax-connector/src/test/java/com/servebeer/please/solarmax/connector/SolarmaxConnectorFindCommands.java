package com.servebeer.please.solarmax.connector;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.servebeer.please.solarmax.SolarmaxTestDefaults;
import com.servebeer.please.solarmax.connector.exception.SolarmaxException;

import org.junit.Test;

public class SolarmaxConnectorFindCommands {

    static final String host = SolarmaxTestDefaults.getTestHost();
    static final int port = SolarmaxTestDefaults.getTestPort();
    static final int deviceId = SolarmaxTestDefaults.getTestDeviceId();

    private List<String> getCharacters() {
        List<String> characters = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            characters.add(Character.toString(c));
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            characters.add(Character.toString(c));
        }
        characters.add("0");
        characters.add("1");
        characters.add("2");
        characters.add("3");
        characters.add("4");
        characters.add("5");
        characters.add("6");
        characters.add("7");
        characters.add("8");
        characters.add("9");

        characters.add(".");
        characters.add("-");
        characters.add("_");

        return characters;
    }

    @Test
    public void testForCommands() throws UnknownHostException, SolarmaxException {

        List<String> validCommands = new ArrayList<>();
        List<String> commandsToCheck = new ArrayList<String>();
        List<String> failedCommands = new ArrayList<>();
        int failedCommandRetry = 0;
        String lastFailedCommand = "";

        // for (String first : getCharacters()) {
        //     for (String second : getCharacters()) {
        //         for (String third : getCharacters()) {
        //             // commandsToCheck.add(first + second + third);

        //             // specifically searching for "E" errors
        //             // commandsToCheck.add("E" + first + second + third);
        //         }
        //         commandsToCheck.add("E" + first + second);
        //     }
        // }

        commandsToCheck.addAll(Arrays.asList("RH1", "RH2", "RH3", "TP1", "TP2", "TP3", "UL1", "UL2", "UL3", "UMX",
                "UM1", "UM2", "UM3", "UPD", "TCP"));

        while (!commandsToCheck.isEmpty()) {
            if (commandsToCheck.size() % 100 == 0) {
                System.out.println(commandsToCheck.size() + " left to check");
            }
            try {
                if (checkIsValidCommand(commandsToCheck.get(0))) {
                    validCommands.add(commandsToCheck.get(0));
                    commandsToCheck.remove(0);
                } else {
                    commandsToCheck.remove(0);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("Sleeping after Exception: " + e.getLocalizedMessage());
                if (lastFailedCommand.equals(commandsToCheck.get(0))) {
                    failedCommandRetry = failedCommandRetry + 1;
                    if (failedCommandRetry >= 5) {
                        failedCommands.add(commandsToCheck.get(0));
                        commandsToCheck.remove(0);
                    }
                } else {
                    failedCommandRetry = 0;
                    lastFailedCommand = commandsToCheck.get(0);
                }
                try {
                    Thread.sleep(2 * failedCommandRetry * failedCommandRetry * failedCommandRetry);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException e1) {
                // do nothing
            }

        }

        System.out.println();
        System.out.println("Valid commands:");

        for (String validCommand : validCommands) {
            System.out.println(validCommand);
        }

        System.out.println();
        System.out.println("Failed commands:");

        for (String failedCommand : failedCommands) {
            System.out.println(failedCommand + "\", \"");
        }
    }

    private boolean checkIsValidCommand(String command)
            throws InterruptedException, UnknownHostException, SolarmaxException {

        List<String> commands = new ArrayList<String>();
        commands.add(command);

        Map<String, String> responseMap = null;

        responseMap = SolarmaxConnector.getValuesFromSolarmax(host, port, deviceId, commands);

        if (responseMap.containsKey(command)) {
            System.out.println("Request: " + command + " Valid Response: " + responseMap.get(command));
            return true;
        }
        return false;

    }
}
