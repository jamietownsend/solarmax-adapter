package ch.townsend.jamie.electro.solarmax;

public class SolarmaxConnectionStatus {

    private String host;
    private int port;
    private String status;

    // hide the default constructor
    private SolarmaxConnectionStatus() {
    }

    public SolarmaxConnectionStatus(final String host, final int port, final boolean status) {
        this.host = host;
        this.port = port;
        this.status = (status) ? "ok" : "error";
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getStatus() {
        return status;
    }
}
