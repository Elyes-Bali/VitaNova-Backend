package tn.esprit.vitanova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableAspectJAutoProxy
public class VitaNovaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VitaNovaApplication.class, args);
    }

}
