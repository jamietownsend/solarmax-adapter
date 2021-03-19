package com.servebeer.please.solarmax;

public class SolarmaxTestDefaults {

    // to use the proxy host, Proxy.java must be started first
    private static boolean USE_PROXY = false;
    private static String REAL_HOST = "192.168.1.151";
    private static int REAL_PORT = 12345;
    private static String PROXY_HOST = "localhost";
    private static int PROXY_PORT = 12345;
    private static int DEVICE_ID = 1;

    public static String getTestHost() {
        return USE_PROXY ? PROXY_HOST : REAL_HOST;
    }

    public static int getTestPort() {
        return USE_PROXY ? PROXY_PORT : REAL_PORT;
    }

    public static int getTestDeviceId() {
        return DEVICE_ID;
    }

    public static String getRealHost() {
        return REAL_HOST;
    }

    public static int getRealPort() {
        return REAL_PORT;
    }

}
