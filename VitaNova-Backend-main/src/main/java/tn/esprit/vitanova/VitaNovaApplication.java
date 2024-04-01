package tn.esprit.vitanova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@EnableScheduling
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"tn.esprit.vitanova"})
@EntityScan(basePackages = {"tn.esprit.vitanova.entities"})
@EnableJpaRepositories(basePackages = {"tn.esprit.vitanova.repository"})
public class VitaNovaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VitaNovaApplication.class, args);
        LocalDate today = LocalDate.now();
        System.out.println(today);
    }

}
