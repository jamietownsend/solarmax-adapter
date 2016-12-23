package com.servebeer.please.solarmax.connector;

public class SolarmaxResponse {

    private String host;
    private int port;
    private ConnectionStatusValue connectionStatus;

    // hide the default constructor
    private SolarmaxResponse() {
    }

    public SolarmaxResponse(final String host, final int port, final boolean connectionStatus) {
        this.host = host;
        this.port = port;
        this.connectionStatus = (connectionStatus) ? ConnectionStatusValue.ok : ConnectionStatusValue.error;
    }

    public ConnectionStatusValue getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(ConnectionStatusValue connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    enum ConnectionStatusValue {
        ok, error
    }

}

