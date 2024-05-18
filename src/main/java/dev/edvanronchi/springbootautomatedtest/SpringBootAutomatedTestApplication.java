package dev.edvanronchi.springbootautomatedtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dev.edvanronchi.springbootautomatedtest")
public class SpringBootAutomatedTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAutomatedTestApplication.class, args);
    }

}
