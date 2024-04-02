package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.ERole;
import tn.esprit.vitanova.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(Long id);

    User updateUser(Long userId, User userDTO);

    String deleteUser(Long id);

    String banUser(Long id);
    List<User> findByRolesName(ERole roleName) ;

    long countBannedUsers();
}
