package com.servebeer.please.solarmax.connector.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * used for logging and debugging communication between clients and the SolarMax device
 */
public class Proxy {

    private static final int PROXY_PORT = 12345;
    private static final int SOLARMAX_PORT = 12345;
    private static final String SOLARMAX_HOST = "192.168.1.151";

    public static void main(String[] args) throws IOException {
        ServerSocket proxyServerSocket = new ServerSocket(PROXY_PORT); // proxy port
        Socket proxySocket = proxyServerSocket.accept();
        Socket solarMaxSocket = new Socket(SOLARMAX_HOST, SOLARMAX_PORT); // server address
        new ProxyThread("proxy->SolarMax", proxySocket.getInputStream(), solarMaxSocket.getOutputStream()).start();
        new ProxyThread("SolarMax->proxy", solarMaxSocket.getInputStream(), proxySocket.getOutputStream()).start();
    }
}

class ProxyThread extends Thread {
    private String description;
    private InputStream inputStream;
    private OutputStream outputStream;

    ProxyThread(String description, InputStream inputStream, OutputStream outputStream) {
        this.description = description;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void run() {
        try {
            String line = "";
            int i;
            while ((i = inputStream.read()) != -1) {
                // pass through the recieved character
                outputStream.write(i);

                // keep the character for logging
                line = line + (char) i;

                // if we got '}' it's the end of the request or response, so log it
                if ((char) i == '}') {
                    System.out.println(this.description + ": " + line);
                    line = "";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}