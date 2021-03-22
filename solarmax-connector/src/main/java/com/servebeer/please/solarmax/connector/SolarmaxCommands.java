package com.servebeer.please.solarmax.connector;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolarmaxCommands {

        // final Map<SolarmaxCommandKey, SolarmaxCommand> solarmaxCommands = new HashMap<SolarmaxCommandKey, SolarmaxCommand>() {
        //         {
        //                 put(SolarmaxCommandKey.ADR, new SolarmaxCommand(0, SolarmaxCommandKey.ADR, "Address",
        //                                 "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.TYP,
        //                                 new SolarmaxCommand(1, SolarmaxCommandKey.TYP, "Type", "return \"0x\" . $i;"));
        //                 put(SolarmaxCommandKey.SWV, new SolarmaxCommand(2, SolarmaxCommandKey.SWV, "Software version",
        //                                 "return sprintf(\"%1.1f\", hexdec($i) / 10 );"));
        //                 put(SolarmaxCommandKey.DDY, new SolarmaxCommand(3, SolarmaxCommandKey.DDY, "Date day",
        //                                 "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.DMT, new SolarmaxCommand(4, SolarmaxCommandKey.DMT, "Date month",
        //                                 "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.DYR, new SolarmaxCommand(5, SolarmaxCommandKey.DYR, "Date year",
        //                                 "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.THR, new SolarmaxCommand(6, SolarmaxCommandKey.THR, "Time hours",
        //                                 "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.TMI, new SolarmaxCommand(7, SolarmaxCommandKey.TMI, "Time minutes",
        //                                 "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E11, new SolarmaxCommand(8, SolarmaxCommandKey.E11,
        //                                 "???Error 1, number???", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E1D, new SolarmaxCommand(9, SolarmaxCommandKey.E1D, "???Error 1, day???",
        //                                 "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E1M, new SolarmaxCommand(10, SolarmaxCommandKey.E1M,
        //                                 "???Error 1, month???", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E1h, new SolarmaxCommand(11, SolarmaxCommandKey.E1h,
        //                                 "???Error 1, hour???", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E1m, new SolarmaxCommand(12, SolarmaxCommandKey.E1m,
        //                                 "???Error 1, minute???", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E21, new SolarmaxCommand(13, SolarmaxCommandKey.E21,
        //                                 "???Error 2, number???", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E2D, new SolarmaxCommand(14, SolarmaxCommandKey.E2D,
        //                                 "???Error 2, day???", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E2M, new SolarmaxCommand(15, SolarmaxCommandKey.E2M,
        //                                 "???Error 2, month???", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E2h, new SolarmaxCommand(16, SolarmaxCommandKey.E2h,
        //                                 "???Error 2, hour???", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E2m, new SolarmaxCommand(17, SolarmaxCommandKey.E2m,
        //                                 "???Error 2, minute???", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E31, new SolarmaxCommand(18, SolarmaxCommandKey.E31,
        //                                 "???Error 3, number???", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E3D, new SolarmaxCommand(19, SolarmaxCommandKey.E3D,
        //                                 "???Error 3, day???", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E3M, new SolarmaxCommand(20, SolarmaxCommandKey.E3M,
        //                                 "???Error 3, month???", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E3h, new SolarmaxCommand(21, SolarmaxCommandKey.E3h,
        //                                 "???Error 3, hour???", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.E3m, new SolarmaxCommand(22, SolarmaxCommandKey.E3m,
        //                                 "???Error 3, minute???", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.KHR, new SolarmaxCommand(23, SolarmaxCommandKey.KHR, "Operating hours",
        //                                 "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.KDY, new SolarmaxCommand(24, SolarmaxCommandKey.KDY, "Energy today [Wh]",
        //                                 "return hexdec($i) * 100;"));
        //                 put(SolarmaxCommandKey.KLD, new SolarmaxCommand(25, SolarmaxCommandKey.KLD,
        //                                 "Energy yesterday [kWh]", "return hexdec($i) * 100;"));
        //                 put(SolarmaxCommandKey.KMT, new SolarmaxCommand(26, SolarmaxCommandKey.KMT,
        //                                 "Energy this month [kWh]", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.KLM, new SolarmaxCommand(27, SolarmaxCommandKey.KLM,
        //                                 "Energy last monh [kWh]", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.KYR, new SolarmaxCommand(28, SolarmaxCommandKey.KYR,
        //                                 "Energy this year [kWh]", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.KLY, new SolarmaxCommand(29, SolarmaxCommandKey.KLY,
        //                                 "Energy last year [kWh]", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.KT0, new SolarmaxCommand(30, SolarmaxCommandKey.KT0,
        //                                 "Energy total [kWh]", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.LAN, new SolarmaxCommand(31, SolarmaxCommandKey.LAN, "Language",
        //                                 "return hexdec($i);"));
        //                 // put(SolarmaxCommandKey.UDC,
        //                 //         new SolarmaxCommand(32, SolarmaxCommandKey.UDC, "DC voltage [mV]", "return hexdec($i) * 100;"));
        //                 put(SolarmaxCommandKey.UL1, new SolarmaxCommand(33, SolarmaxCommandKey.UL1, "AC voltage [mV]",
        //                                 "return hexdec($i) * 100;"));
        //                 put(SolarmaxCommandKey.UL2, new SolarmaxCommand(33, SolarmaxCommandKey.UL2, "AC voltage [mV]",
        //                                 "return hexdec($i) * 100;"));
        //                 put(SolarmaxCommandKey.UL3, new SolarmaxCommand(33, SolarmaxCommandKey.UL3, "AC voltage [mV]",
        //                                 "return hexdec($i) * 100;"));
        //                 // put(SolarmaxCommandKey.IDC,
        //                 //         new SolarmaxCommand(34, SolarmaxCommandKey.IDC, "DC current [mA]", "return hexdec($i) * 10;"));
        //                 put(SolarmaxCommandKey.IL1, new SolarmaxCommand(35, SolarmaxCommandKey.IL1, "AC current [mA]",
        //                                 "return hexdec($i) * 10;"));
        //                 put(SolarmaxCommandKey.IL2, new SolarmaxCommand(35, SolarmaxCommandKey.IL1, "AC current [mA]",
        //                                 "return hexdec($i) * 10;"));
        //                 put(SolarmaxCommandKey.IL3, new SolarmaxCommand(35, SolarmaxCommandKey.IL1, "AC current [mA]",
        //                                 "return hexdec($i) * 10;"));
        //                 put(SolarmaxCommandKey.PAC, new SolarmaxCommand(36, SolarmaxCommandKey.PAC, "AC power [mW]",
        //                                 "return hexdec($i) * 500;"));
        //                 put(SolarmaxCommandKey.PIN, new SolarmaxCommand(37, SolarmaxCommandKey.PIN,
        //                                 "Power installed [mW]", "return hexdec($i) * 500;"));
        //                 put(SolarmaxCommandKey.PRL, new SolarmaxCommand(38, SolarmaxCommandKey.PRL, "AC power [%]",
        //                                 "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.CAC, new SolarmaxCommand(39, SolarmaxCommandKey.CAC, "Start ups",
        //                                 "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.FRD,
        //                                 new SolarmaxCommand(40, SolarmaxCommandKey.FRD, "???", "return \"0x\" . $i;"));
        //                 put(SolarmaxCommandKey.SCD,
        //                                 new SolarmaxCommand(41, SolarmaxCommandKey.SCD, "???", "return \"0x\" . $i;"));
        //                 put(SolarmaxCommandKey.SE1,
        //                                 new SolarmaxCommand(42, SolarmaxCommandKey.SE1, "???", "return \"0x\" . $i;"));
        //                 put(SolarmaxCommandKey.SE2,
        //                                 new SolarmaxCommand(43, SolarmaxCommandKey.SE2, "???", "return \"0x\" . $i;"));
        //                 put(SolarmaxCommandKey.SPR,
        //                                 new SolarmaxCommand(44, SolarmaxCommandKey.SPR, "???", "return \"0x\" . $i;"));
        //                 put(SolarmaxCommandKey.TKK, new SolarmaxCommand(45, SolarmaxCommandKey.TKK,
        //                                 "Temerature Heat Sink", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.TNF, new SolarmaxCommand(46, SolarmaxCommandKey.TNF, "AC Frequency",
        //                                 "return hexdec($i / 100);"));
        //                 put(SolarmaxCommandKey.SYS, new SolarmaxCommand(47, SolarmaxCommandKey.SYS, "Operation State",
        //                                 "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.BDN, new SolarmaxCommand(48, SolarmaxCommandKey.BDN, "Build number",
        //                                 "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.EC00, new SolarmaxCommand(49, SolarmaxCommandKey.EC00,
        //                                 "Error-Code(?) 00", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.EC01, new SolarmaxCommand(50, SolarmaxCommandKey.EC01,
        //                                 "Error-Code(?) 01", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.EC02, new SolarmaxCommand(51, SolarmaxCommandKey.EC02,
        //                                 "Error-Code(?) 02", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.EC03, new SolarmaxCommand(52, SolarmaxCommandKey.EC03,
        //                                 "Error-Code(?) 03", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.EC04, new SolarmaxCommand(53, SolarmaxCommandKey.EC04,
        //                                 "Error-Code(?) 04", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.EC05, new SolarmaxCommand(54, SolarmaxCommandKey.EC05,
        //                                 "Error-Code(?) 05", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.EC06, new SolarmaxCommand(55, SolarmaxCommandKey.EC06,
        //                                 "Error-Code(?) 06", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.EC07, new SolarmaxCommand(56, SolarmaxCommandKey.EC07,
        //                                 "Error-Code(?) 07", "return hexdec($i);"));
        //                 put(SolarmaxCommandKey.EC08, new SolarmaxCommand(57, SolarmaxCommandKey.EC08,
        //                                 "Error-Code(?) 08", "return hexdec($i);"));
        //         }
        // };

        /**
         * @param destinationDevice device number - used if devices are daisy-chained. Normally it will be "1"
         * @param questions         appears to be able to handle multiple commands. For now, one at a time is good fishing
         * @return the request to be sent to the Solarmax device
         */
        static String contructRequest(final int destinationDevice, final String questions) {
                String src = "FB";
                String dstHex = String.format("%02X", destinationDevice);
                String len = "00";
                String cs = "0000";
                // String msg = is_array(questions) ? "64:" + implode(';', questions) : "64:" + questions;
                String msg = "64:" + questions;
                int lenInt = ("{" + src + ";" + dstHex + ";" + len + "|" + msg + "|" + cs + "}").length();

                // given the following, I'd expect problems if the request is longer than 255 characters. Since I'm not sure though, I won't fixe what isn't (yet) broken
                String lenHex = String.format("%02X", lenInt);

                String checksum = calculateChecksum16(src + ";" + dstHex + ";" + lenHex + "|" + msg + "|");

                return "{" + src + ";" + dstHex + ";" + lenHex + "|" + msg + "|" + checksum + "}";

        }

        /**
         * calculates the "checksum16" of the given string argument
         */
        static String calculateChecksum16(String str) {

                byte[] bytes = str.getBytes();
                int sum = 0;

                // loop through each of the bytes and add them together
                for (byte aByte : bytes) {
                        sum = sum + aByte;
                }

                // calculate the "checksum16"
                sum = sum % (int) Math.pow(2, 16);

                // return Integer.toHexString(sum);
                return String.format("%04X", sum);

        }

        static boolean validateResponse(final String header) {

                // // FIXME: 12.11.2016 whole response will be passed in
                final String patternString = "/\\{([0-9A-F]{2});FB;([0-9A-F]{2})/";
                final Pattern pattern = Pattern.compile(patternString);

                final Matcher matcher = pattern.matcher(header);

                boolean matches = matcher.matches();
                // return matches;
                // FIXME: 10.11.2016
                return true;
        }

        // public enum SolarmaxCommandKey {
        //         ADR, TYP, SWV, DDY, DMT, DYR, THR, TMI, E11, E1D, E1M, E1h, E1m, E21, E2D, E2M, E2h, E2m, E31, E3D, E3M,
        //         E3h, E3m, KHR, KDY, KLD, KMT, KLM, KYR, KLY, KT0, LAN,
        //         // UDC,
        //         UL1, UL2, UL3,
        //         // IDC,
        //         IL1, IL2, IL3, PAC, PIN, PRL, CAC, FRD, SCD, SE1, SE2, SPR, TKK, TNF, SYS, BDN, EC00, EC01, EC02, EC03,
        //         EC04, EC05, EC06, EC07, EC08, EL00
        // }

}
