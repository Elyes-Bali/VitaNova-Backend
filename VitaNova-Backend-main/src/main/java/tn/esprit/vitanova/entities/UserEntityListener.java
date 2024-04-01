package tn.esprit.vitanova.entities;

import jakarta.persistence.PostPersist;
import tn.esprit.vitanova.repository.ClientRepo;

public class UserEntityListener {
    private ClientRepo clientRepository;

    @PostPersist
    public void onCreate(User user) {
        if (user.getRoles().equals("ROLE_CLIENT")) {
            Client client = new Client();
            client.setClientId(user.getId());
            client.setNom(user.getNom());
            clientRepository.save(client);
        }
    }
}
