package tn.esprit.vitanova.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.*;
import tn.esprit.vitanova.repository.*;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class Allservices implements Allservicesimpl{
    PsychologueRepo pr;
    NotificationssRepo np;
    ChatRepo chatRepo;
    RapportPsyRepo rapportPsyRepo;
    ClientRepo cl;
    @Override
    public Psychologue ajouterPsychologue(Psychologue p) {
        return pr.save(p) ;
    }

    @Override
    public void updatepsychologue(Long idpsychologue, Psychologue psychologue) {
     psychologue.setPsychologueId(idpsychologue);
     pr.save(psychologue);
    }

    @Override
    public Psychologue getpsychologuebyId(Long idpsychologue) {
        return pr.findById(idpsychologue).orElse(null);
    }

    @Override
    public List<Psychologue> chercherTousForm() {
        return pr.findAll();
    }

    @Override
    public void supprimerpsychologue(Long idPsychologue) {
        pr.deleteById(idPsychologue);

    }

    @Override
    public RapportPsy ajouterrapportpsychologue(RapportPsy rp) {
        return rapportPsyRepo.save(rp);
    }

    @Override
    public void updateprapportpsychologue(Long idrapportpsychologue, RapportPsy rp) {
        rp.setIdRapportPsy(idrapportpsychologue);
        rapportPsyRepo.save(rp);

    }

    @Override
    public RapportPsy getrapportpsychologuebyId(Long idrapportpsychologue) {
        return rapportPsyRepo.findById(idrapportpsychologue).orElse(null);
    }

    @Override
    public List<RapportPsy> chercherTousrapport() {
        return rapportPsyRepo.findAll();
    }

    @Override
    public void supprimerrapportpsychologue(Long idrapportpsychologue) {
        rapportPsyRepo.deleteById(idrapportpsychologue);

    }

    @Override
    public Notifications ajouternotification(Notifications no) {
        return np.save(no);
    }

    @Override
    public void updatenotification(Long idnotification, Notifications notification) {
     notification.setIdNotifications(idnotification);
     np.save(notification);

    }

    @Override
    public Notifications getnotificationbyId(Long idnotication) {
        return np.findById(idnotication).orElse(null);
    }

    @Override
    public List<Notifications> cherchertousnotification() {
        return np.findAll();
    }

    @Override
    public void supprimernotification(Long idnotification) {
        np.deleteById(idnotification);

    }

    @Override
    public Chat ajouterchat(Chat chat) {
        return chatRepo.save(chat);
    }

    @Override
    public void updatechat(Long idchat, Chat chat) {
        chat.setIdChat(idchat);
        chatRepo.save(chat);
    }

    @Override
    public Chat getchatbyId(Long idchat) {
        return chatRepo.findById(idchat).orElse(null);
    }

    @Override
    public List<Chat> cherchertouschat() {
        return chatRepo.findAll();
    }

    @Override
    public void supprimerchat(Long idchat) {
         chatRepo.deleteById(idchat);
    }

    @Override
    public List<Client> cherchertousclient() {
        return cl.findAll();
    }


}
