package com.servebeer.please.solarmax.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.servebeer.please.solarmax.SolarmaxTestDefaults;

/**
 * used for logging and debugging communication between clients and the SolarMax device
 */
public class Proxy {

    private static final String PROXIED_HOST = SolarmaxTestDefaults.getRealHost();
    private static final int PROXIED_PORT = SolarmaxTestDefaults.getRealPort();
    private static final int PROXY_PORT = SolarmaxTestDefaults.getTestPort();

    public static void main(String[] args) throws IOException {
        ServerSocket proxyServerSocket = new ServerSocket(PROXY_PORT); // proxy port
        Socket proxySocket = proxyServerSocket.accept();
        Socket solarMaxSocket = new Socket(PROXIED_HOST, PROXIED_PORT); // server address
        new ProxyThread("proxy->SolarMax", proxySocket.getInputStream(), solarMaxSocket.getOutputStream()).start();
        new ProxyThread("SolarMax->proxy", solarMaxSocket.getInputStream(), proxySocket.getOutputStream()).start();
    }
}

class ProxyThread extends Thread {
    private final String description;
    private final InputStream inputStream;
    private final OutputStream outputStream;

    ProxyThread(String description, InputStream inputStream, OutputStream outputStream) {
        this.description = description;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
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