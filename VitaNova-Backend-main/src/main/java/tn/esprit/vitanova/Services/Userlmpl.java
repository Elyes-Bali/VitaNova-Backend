package tn.esprit.vitanova.Services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.User;
import tn.esprit.vitanova.repository.RoleRepo;
import tn.esprit.vitanova.repository.UserRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class Userlmpl implements UserService {
    private UserRepo userRepo;

    private RoleRepo roleRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public User updateUser(Long userId, User userDTO) {
        User existingUser = getUser(userId);

        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setCreationDate(userDTO.getCreationDate());
        existingUser.setNom(userDTO.getNom());
        existingUser.setPrenom(userDTO.getPrenom());
        existingUser.setGender(userDTO.getGender());
        existingUser.setDesctiption(userDTO.getDesctiption());
        existingUser.setAchievments(userDTO.getAchievments());
        existingUser.setFears(userDTO.getFears());
        existingUser.setAge(userDTO.getAge());
        existingUser.setActivated(userDTO.getActivated());
        existingUser.setBanned(userDTO.getBanned());
        existingUser.setRoles(userDTO.getRoles());

        return userRepo.save(existingUser);
    }


    @Override
    public String deleteUser(Long id)
    {
        userRepo.deleteById(id);
        return "User with id " + id + " has been successfully deleted !";
    }

    @Override
    public String banUser(Long id) {
        User user = getUser(id);
        if (user.getBanned()) {
            user.setBanned(false);
            userRepo.save(user);
            return "User with id " + id + " has been unbanned!";
        } else {
            user.setBanned(true);
            userRepo.save(user);
            return "User with id " + id + " has been banned!";
        }
    }

    @Override
    public long countBannedUsers() {
        return userRepo.countByBannedTrue();
    }

}