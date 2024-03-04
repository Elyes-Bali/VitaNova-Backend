package tn.esprit.vitanova.Services;

import tn.esprit.vitanova.entities.*;

import javax.management.Notification;
import java.util.List;

public interface Allservicesimpl {
    public Psychologue ajouterPsychologue(Psychologue p);
    public void updatepsychologue(Long idpsychologue , Psychologue psychologue);
    public Psychologue getpsychologuebyId(Long idpsychologue);
    public List<Psychologue> chercherTousForm();
    public void supprimerpsychologue(Long idPsychologue);
    public RapportPsy ajouterrapportpsychologue (RapportPsy rp) ;
    public void updateprapportpsychologue(Long idrapportpsychologue , RapportPsy rp);
    public RapportPsy getrapportpsychologuebyId(Long idrapportpsychologue);
    public List<RapportPsy> chercherTousrapport();
    public void supprimerrapportpsychologue(Long idrapportpsychologue);
    public Notifications ajouternotification (Notifications no);
    public void updatenotification (Long idnotification , Notifications notification);
    public Notifications getnotificationbyId (Long idnotication);
    public List<Notifications> cherchertousnotification();
    public void supprimernotification (Long idnotification);
    public Chat ajouterchat (Chat chat);
    public void updatechat (Long idchat , Chat chat);
    public Chat getchatbyId (Long idchat);
    public List<Chat> cherchertouschat();
    public void supprimerchat (Long idchat);

    public List<Client> cherchertousclient();


}
