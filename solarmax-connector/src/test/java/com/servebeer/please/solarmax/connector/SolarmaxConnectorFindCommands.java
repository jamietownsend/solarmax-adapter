package com.servebeer.please.solarmax.connector;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.servebeer.please.solarmax.SolarmaxTestDefaults;
import com.servebeer.please.solarmax.connector.exception.SolarmaxException;

import org.junit.Test;

public class SolarmaxConnectorFindCommands {

    static final String host = SolarmaxTestDefaults.getTestHost();
    static final int port = SolarmaxTestDefaults.getTestPort();
    static final int deviceId = SolarmaxTestDefaults.getTestDeviceId();

    // private List<String> getCharacters() {
    //     List<String> characters = new ArrayList<>();
    //     for (char c = 'a'; c <= 'z'; c++) {
    //         characters.add(Character.toString(c));
    //     }
    //     for (char c = 'A'; c <= 'Z'; c++) {
    //         characters.add(Character.toString(c));
    //     }
    //     characters.add("0");
    //     characters.add("1");
    //     characters.add("2");
    //     characters.add("3");
    //     characters.add("4");
    //     characters.add("5");
    //     characters.add("6");
    //     characters.add("7");
    //     characters.add("8");
    //     characters.add("9");

    //     characters.add(".");
    //     characters.add("-");
    //     characters.add("_");

    //     return characters;
    // }

    @Test
    public void testForCommands() throws UnknownHostException, SolarmaxException {

        List<String> validCommands = new ArrayList<>();
        Set<String> commandsToCheck = new HashSet<String>();
        List<String> failedCommands = new ArrayList<>();
        int failedCommandRetry = 0;
        String lastFailedCommand = "";

        // for (String first : getCharacters()) {
        //     for (String second : getCharacters()) {
        //         for (String third : getCharacters()) {
        //             commandsToCheck.add(first + second + third);
        //         }
        //     }
        // }

        commandsToCheck.addAll(Arrays.asList("ADR", "AMM", "BDN", "CAC", "CID", "CPG", "CPL", "CP0", "CP1", "CP2",
                "CP3", "CP4", "CP5", "CYC", "DIN", "DMO", "ETH", "FH2", "FL2", "FQR", "FWV", "IAA", "IED", "IEE", "IEM",
                "ILM", "IL1", "IL2", "IL3", "IP4", "ISL", "ITS", "KDY", "KFS", "KHR", "KHS", "KLD", "KLM", "KLY", "KMT",
                "KTS", "KT0", "KYR", "LAN", "MAC", "PAC", "PAE", "PAM", "PDA", "PDC", "PFA", "PIN", "PLR", "PPC", "PRL",
                "PSF", "PSR", "PSS", "QAC", "QMO", "QUC", "RA1", "RA2", "RB1", "RB2", "REL", "RH1", "RH2", "RPR", "RSD",
                "SAC", "SAL", "SAM", "SCH", "SNM", "SPS", "SRD", "SRS", "SWV", "SYS", "TCP", "TI1", "TKK", "TL1", "TL3",
                "TND", "TNF", "TNH", "TNL", "TP1", "TP2", "TP3", "TV0", "TV1", "TYP", "UA2", "UB2", "UGD", "UI1", "UI2",
                "UI3", "ULH", "ULL", "UL1", "UL2", "UL3", "UMX", "UM1", "UM2", "UM3", "UPD", "UZK", "VCM"));

        Map<String, String> responseMap = SolarmaxConnector.getValuesFromSolarmax(host, port, deviceId,
                commandsToCheck);

        System.out.println(responseMap.toString());

        // while (!commandsToCheck.isEmpty()) {
        //     if (commandsToCheck.size() % 100 == 0) {
        //         System.out.println(commandsToCheck.size() + " left to check");
        //     }
        //     try {
        //         if (checkIsValidCommand(commandsToCheck.get(0))) {
        //             validCommands.add(commandsToCheck.get(0));
        //             commandsToCheck.remove(0);
        //         } else {
        //             commandsToCheck.remove(0);
        //         }
        //     } catch (Exception e) {
        //         // TODO Auto-generated catch block
        //         System.out.println("Sleeping after Exception: " + e.getLocalizedMessage());
        //         if (lastFailedCommand.equals(commandsToCheck.get(0))) {
        //             failedCommandRetry = failedCommandRetry + 1;
        //             if (failedCommandRetry >= 5) {
        //                 failedCommands.add(commandsToCheck.get(0));
        //                 commandsToCheck.remove(0);
        //             }
        //         } else {
        //             failedCommandRetry = 0;
        //             lastFailedCommand = commandsToCheck.get(0);
        //         }
        //         try {
        //             Thread.sleep(20 * failedCommandRetry);
        //         } catch (InterruptedException e1) {
        //             // do nothing
        //         }
        //     }
        //     try {
        //         Thread.sleep(20);
        //     } catch (InterruptedException e1) {
        //         // do nothing
        //     }

        // }

        // System.out.println();
        // System.out.println("Valid commands:");

        // for (String validCommand : validCommands) {
        //     System.out.println(validCommand);
        // }

        // System.out.println();
        // System.out.println("Failed commands:");

        // for (String failedCommand : failedCommands) {
        //     System.out.println(failedCommand);
        // }
    }

    // private boolean checkIsValidCommand(String command)
    //         throws InterruptedException, UnknownHostException, SolarmaxException {

    //     List<String> commands = new ArrayList<String>();
    //     commands.add(command);

    //     Map<String, String> responseMap = null;

    //     responseMap = SolarmaxConnector.getValuesFromSolarmax(host, port, deviceId, commands);

    //     if (responseMap.containsKey(command)) {
    //         System.out.print("Response: " + responseMap.get(command));
    //         return true;
    //     }
    //     return false;

    // }
}
