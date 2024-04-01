package tn.esprit.vitanova.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.vitanova.entities.Achievement;
import tn.esprit.vitanova.entities.AchievementSlug;
import tn.esprit.vitanova.entities.User;
import tn.esprit.vitanova.repository.AchievementRepository;
import tn.esprit.vitanova.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class InitData {

    @Bean
    CommandLineRunner usersINIT(UserRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                log.info("populating users table");
                List<User> users = new ArrayList<>();
                User u1 = new User();
                u1.setNomUser("Test");
                u1.setEmail("test@vita.com");
                u1.setPassword("mypassword");
                u1.setGender("female");
                u1.setPrenomUser("User");


                User u2 = new User();
                u2.setNomUser("Test");
                u2.setEmail("test2@vita.com");
                u2.setPassword("mypassword");
                u2.setGender("female");
                u2.setPrenomUser("User2");

                users.add(u1);
                users.add(u2);
                repository.saveAll(users);
            }
        };
    }

    @Bean
    CommandLineRunner achievementsINIT(AchievementRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                List<Achievement> achievementsList = new ArrayList<>();
                log.info("populating achievements table");
                for (AchievementSlug slug : AchievementSlug.values()) {
                    Achievement a = new Achievement();
                    a.setCriteria(10);
                    a.setSlug(slug);
                    achievementsList.add(a);
                }
                repository.saveAll(achievementsList);
            }
        };
    }
}