package com.servebeer.please.solarmax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // define default constructor as protected to hide Sonar error
    protected Application() {
    }

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
