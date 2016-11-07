package ch.townsend.jamie.electro.solarmax;

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

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public void setConnectionStatus(ConnectionStatusValue connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public enum ConnectionStatusValue {
        ok, error
    }

}

