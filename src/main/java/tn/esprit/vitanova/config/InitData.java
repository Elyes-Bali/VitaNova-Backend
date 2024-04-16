package tn.esprit.vitanova.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.vitanova.entities.Achievement;
import tn.esprit.vitanova.entities.AchievementSlug;
import tn.esprit.vitanova.entities.ERole;
import tn.esprit.vitanova.entities.Role;
import tn.esprit.vitanova.repository.AchievementRepository;
import tn.esprit.vitanova.repository.RoleRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
public class InitData {

//    @Bean
//    CommandLineRunner usersINIT(UserRepository repository) {
//        return args -> {
//            if (repository.count() == 0) {
//                log.info("populating users table");
//                List<User> users = new ArrayList<>();
//                User u1 = new User();
//                u1.setNom("Test");
//                u1.setEmail("test@vita.com");
//                u1.setPassword("mypassword");
//                u1.setGender("female");
//                u1.setPrenom("User");
//
//
//                User u2 = new User();
//                u2.setNom("Test");
//                u2.setEmail("test2@vita.com");
//                u2.setPassword("mypassword");
//                u2.setGender("female");
//                u2.setPrenom("User2");
//
//                users.add(u1);
//                users.add(u2);
//                repository.saveAll(users);
//            }
//        };
//    }

    @Bean
    CommandLineRunner achievementsINIT(AchievementRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                List<Achievement> achievementsList = new ArrayList<>();
                log.info("populating achievements table");
                for (AchievementSlug slug : AchievementSlug.values()) {
                    if (slug.equals(AchievementSlug.ADD_10_COMMENT)) {
                        Achievement a = new Achievement();
                        a.setCriteria(10);
                        a.setSlug(slug);
                        a.setPublicName("Add 10 comments");
                        achievementsList.add(a);
                    }else if(slug.equals(AchievementSlug.SUBSCRIBE)){
                        Achievement a = new Achievement();
                        a.setCriteria(5);
                        a.setSlug(slug);
                        a.setPublicName("Subscribe");
                        achievementsList.add(a);
                    }else {
                        Achievement a = new Achievement();
                        a.setCriteria(15);
                        a.setSlug(slug);
                        a.setPublicName("Add 15 comments");
                        achievementsList.add(a);
                    }
                }
                repository.saveAll(achievementsList);
            }
        };
    }

    @Bean
    CommandLineRunner rolesInit(RoleRepo repository) {
        return args -> {
            if (repository.count() == 0) {
                List<ERole> roles = Arrays.stream(ERole.values()).toList();
                for (ERole eRole : roles) {
                    repository.save(new Role(eRole));
                }

            }
        };
    }
}