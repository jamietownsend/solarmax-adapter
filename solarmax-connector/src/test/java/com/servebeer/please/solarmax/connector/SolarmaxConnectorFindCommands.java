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
    public void testForCommands() {

        List<String> validCommands = new ArrayList<>();
        List<String> commandsToCheck = new ArrayList<>();
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

        commandsToCheck.addAll(Arrays.asList("FFK", "FRT", "GCP", "ITN", "PLD", "PLE", "PLF", "PLS", "PPO", "TV2",
                "VLE", "VLI", "VLO"));

        // commandsToCheck.addAll(Arrays.asList("cx.", "cx-", "cx_", "cya", "ent", "enu", "env", "enw", "enx", "eny",
        //         "enz", "enA", "enB", "enC", "enD", "enE", "enF", "enG", "enH", "enI", "enJ", "enK", "enL", "enM", "enN",
        //         "enO", "enP", "enQ", "enR", "enS", "enT", "enU", "enV", "enW", "enX", "enY", "enZ", "en0", "en1", "en2",
        //         "en3", "en4", "en5", "en6", "en7", "e-L", "e-M", "e-N", "e-O", "e-P", "e-Q", "e-R", "e-S", "e-T", "e-U",
        //         "e-V", "e-W", "e-X", "e-Y", "e-Z", "e-0", "e-1", "e-2", "e-3", "e-4", "e-5", "e-6", "e-7", "e-8", "e-9",
        //         "e-.", "e--", "e-_", "e_a", "e_b", "e_c", "e_d", "e_e", "e_f", "e_g", "e_h", "e_i", "e_j", "e_k", "e_l",
        //         "e_m", "e_n", "e_o", "e_p", "g-G", "g-H", "g-I", "nm-", "nm_", "nna", "nnb", "nnc", "nnd", "nne", "nnf",
        //         "nng", "nnh", "nni", "nnj", "nnk", "nnl", "nnm", "nnn", "nno", "nnp", "nnq", "nnr", "nns", "nnt", "nnu",
        //         "nnv", "nnw", "nnx", "nny", "nnz", "nnA", "nnB", "nnC", "nnD", "nnE", "nnF", "nnG", "nnH", "nnI", "nnJ",
        //         "nnK", "nnL", "nnM", "nnN", "nNH", "nNI", "nNJ", "nNK", "nNL", "nNM", "nNN", "nNO", "nNP", "nNQ", "nNR",
        //         "tnc", "tnd", "tne", "tnf", "tng", "tnh", "tni", "tnj", "tnk", "tnl", "tnm", "tnn", "tno", "tnp", "tnq",
        //         "tnr", "tns", "tnt", "tnu", "tnv", "tnw", "tnx", "tny", "tnz", "tnA", "tnB", "tnC", "tnD", "tnE", "tnF",
        //         "tnG", "tnH", "tnI", "tnJ", "tnK", "tnL", "tnM", "tnN", "tnO", "tnP", "tnQ", "tnR", "tnS", "tnT", "tnU",
        //         "tnV", "tnW", "tnX", "tnY", "t0p", "t0q", "uit", "uiu", "uiv", "uiw", "uix", "uiy", "uiz", "uiA", "uiB",
        //         "uiC", "uiD", "uiE", "uiF", "uiG", "uiH", "uiI", "uiJ", "uiK", "uiL", "uiM", "uiN", "uiO", "uiP", "uiQ",
        //         "uiR", "uiS", "uiT", "uiU", "uiV", "uiW", "uiX", "uiY", "uiZ", "ui0", "ui1", "ui2", "ui3", "ui4", "ui5",
        //         "ui6", "ui7", "ui8", "ui9", "ui.", "ui-", "ui_", "uja", "ujb", "ujc", "ujd", "uje", "ujf", "Eeb", "Eec",
        //         "Eed", "Eee", "Eef", "FFK", "FRT", "GCP", "ITN", "Kzp", "Kzq", "Kzr", "Kzs", "Kzt", "Kzu", "Kzv", "Kzw",
        //         "Kzx", "Kzy", "Kzz", "KzA", "KzB", "KzC", "KzD", "KzE", "KzF", "KzG", "KzH", "KzI", "KzJ", "KzK", "KzL",
        //         "KzM", "KzN", "KzO", "KzP", "KzQ", "KzR", "KzS", "KzT", "KzU", "KzV", "KzW", "KzX", "KzY", "KzZ", "Kz0",
        //         "Kz1", "Kz2", "Kz3", "Kz4", "Kz5", "Kz6", "Kz7", "Kz8", "Kz9", "Kz.", "Kz-", "Kz_", "KAa", "KAb", "KAc",
        //         "KAd", "KAe", "KAf", "KAg", "KAh", "KAi", "KAj", "KAk", "KAl", "KAm", "KAn", "KAo", "KAp", "KAq", "KAr",
        //         "KAs", "KAt", "KAu", "KAv", "KAw", "KAx", "KAy", "KAz", "KAA", "KAB", "KAC", "KAD", "KAE", "KAF", "KAG",
        //         "KAH", "KAI", "KAJ", "KAK", "KAL", "KAM", "KAN", "KAO", "KAP", "KAQ", "KAR", "KAS", "KAT", "KAU", "KAV",
        //         "KAW", "KAX", "KAY", "KAZ", "KA0", "KA1", "KA2", "KA3", "KA4", "KA5", "KA6", "KA7", "KA8", "KA9", "KA.",
        //         "KA-", "KA_", "KBa", "KBb", "KBc", "KBd", "KBe", "KBf", "KBg", "KBh", "KBi", "KBj", "KBk", "KBl", "KBm",
        //         "KBn", "KBo", "KBp", "KBq", "KBr", "KBs", "KBt", "KBu", "KBv", "KBw", "KBx", "KBy", "KBz", "KBA", "KBB",
        //         "KBC", "KBD", "KBE", "KBF", "KBG", "KBH", "KBI", "KBJ", "KBK", "KBL", "KBM", "KBN", "KBO", "KBP", "KBQ",
        //         "KBR", "KBS", "KBT", "KBU", "KBV", "KBW", "KBX", "KBY", "KBZ", "KB0", "PLD", "PLE", "PLF", "PLS", "PPO",
        //         "TV2", "VLE", "VLI", "VLO", "V4A", "V4B", "V4C", "V4D", "V4E", "V4F", "V4G", "V4H", "V4I", "V4J", "V4K",
        //         "V4L", "V4M", "V4N", "V4O", "V4P", "V4Q", "V4R", "V4S", "V4T", "V4U", "V4V", "V4W", "V4X", "V4Y", "XTw",
        //         "XTx", "XTy", "16e", "16f", "16g", "16h", "16i", "16j", "16k", "16l", "16m", "16n", "16o", "16p", "16q",
        //         "16r", "16s", "16t", "16u", "16v", "16w", "16x", "16y", "16z", "16A", "16B", "16C", "16D", "16E", "16F",
        //         "16G", "16H", "16I", "16J", "16K", "16L", "16M", "16N", "16O", "16P", "2a5", "2a6", "2a7", "2a8", "7OJ",
        //         "7OK", "7OL", "RL1", "RL2"));

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
                    Thread.sleep(20 * failedCommandRetry);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            try {
                Thread.sleep(20);
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
            System.out.println(failedCommand);
        }
    }

    private boolean checkIsValidCommand(String command)
            throws InterruptedException, UnknownHostException, SolarmaxException {

        List<String> commands = new ArrayList<String>();
        commands.add(command);

        Map<String, String> responseMap = null;

        responseMap = SolarmaxConnector.getValuesFromSolarmax(host, port, deviceId, commands);

        if (responseMap.containsKey(command)) {
            System.out.print("Response: " + responseMap.get(command));
            return true;
        }
        return false;

    }
}
