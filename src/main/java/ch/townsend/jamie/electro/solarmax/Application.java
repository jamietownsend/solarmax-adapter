package ch.townsend.jamie.electro.solarmax;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration
public class Application {

    // define default constructor as protected to hide Sonar error
    protected Application() {
    }

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
