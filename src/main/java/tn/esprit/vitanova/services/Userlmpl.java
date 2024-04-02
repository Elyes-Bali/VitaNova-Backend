package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.ERole;
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

        existingUser.setNom(userDTO.getNom());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setGender(userDTO.getGender());
        existingUser.setRoles(userDTO.getRoles());
        existingUser.setAge(userDTO.getAge());
        existingUser.setPrenom(userDTO.getPrenom());
        return userRepo.save(existingUser);


    }


    @Override
    public String deleteUser(Long id)
    {
        User user = getUser(id);
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
    public List<User> findByRolesName(ERole roleName) {
        return userRepo.findByRolesName(roleName) ;
    }

    @Override
    public long countBannedUsers() {
        return userRepo.countByBannedTrue();
    }

}