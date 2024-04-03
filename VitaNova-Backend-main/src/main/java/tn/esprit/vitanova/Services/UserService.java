package tn.esprit.vitanova.Services;

import tn.esprit.vitanova.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(Long id);

    User updateUser(Long userId, User userDTO);

    String deleteUser(Long id);

    String banUser(Long id);

    long countBannedUsers();
    public List<User> getUsersWithPsychiatristSpecialty();
}
