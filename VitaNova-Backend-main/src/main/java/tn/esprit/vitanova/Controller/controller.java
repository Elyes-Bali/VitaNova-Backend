package tn.esprit.vitanova.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vitanova.Services.Allservices;
import tn.esprit.vitanova.entities.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor


public class controller {

    Allservices allservices;

    @PostMapping("/psychologue/addpsychologue")
    public Psychologue ajouterPsychologue(@RequestBody Psychologue p) {
        return allservices.ajouterPsychologue(p) ;
    }

    @PutMapping("/psychologue/updatepsychologue/{idpsychologue}")
    public void updatepsychologue(@PathVariable Long idpsychologue,@RequestBody Psychologue psychologue){
         allservices.updatepsychologue(idpsychologue,psychologue);
    }
    @GetMapping("/psychologue/getpsychologueId/{idpsychologue}")
    public Psychologue getpsychologuebyId(@PathVariable Long idpsychologue){
        return allservices.getpsychologuebyId(idpsychologue);
    }
    @GetMapping("/psychologue/getAll")
    public List<Psychologue> getAllForm(){
        return allservices.chercherTousForm();
    }
    @DeleteMapping("/psychologue/deletepsychologue/{idpsychologue}")
    public  void  supprimerpsychologue(@PathVariable Long idpsychologue) {
        allservices.supprimerpsychologue(idpsychologue);}
        @PostMapping("/rapportpsy/addrapportpsy")
        public RapportPsy ajouterrapportpsychologue(@RequestBody RapportPsy rp) {
            return allservices.ajouterrapportpsychologue(rp);
        }
    @PutMapping("/rapportpsy/updaterapportpsy/{idrapportpsychologue}")
    public void updateprapportpsychologue(@PathVariable Long idrapportpsychologue,@RequestBody RapportPsy rp){
        allservices.updateprapportpsychologue(idrapportpsychologue,rp);
    }
    @GetMapping("/rapportpsy/getrapportpsychologueId/{idrapportpsychologue}")
    public RapportPsy getrapportpsychologuebyId(@PathVariable Long idrapportpsychologue){
        return allservices.getrapportpsychologuebyId(idrapportpsychologue);
    }
    @GetMapping("/rapportpsy/getAll")
    public List<RapportPsy> chercherTousrapport() {
    return allservices.chercherTousrapport();}
    @DeleteMapping("/rapportpsy/deleterapportpsychologue/{idrapportpsychologue}")
    public void supprimerrapportpsychologue(@PathVariable Long idrapportpsychologue) {
        allservices.supprimerrapportpsychologue(idrapportpsychologue);
    }

    @PostMapping("/notification/addnotification")
    public Notifications ajouternotification(@RequestBody Notifications no) {

        return allservices.ajouternotification(no);
    }
    @PutMapping("/notification/updatenotification/{idnotification}")
    public void updatenotification(@PathVariable Long idnotification,@RequestBody Notifications notification) {
        allservices.updatenotification(idnotification,notification);
    }
    @GetMapping("/notification/getnotificationbyid/{idnotication}")
    public Notifications getnotificationbyId(@PathVariable Long idnotication){
       return  allservices.getnotificationbyId(idnotication);
    }
    @GetMapping("/notification/getall")
            public List<Notifications> cherchertousnotification(){return allservices.cherchertousnotification();}

    @DeleteMapping("/notification/deletenotification/{idnotification}")
    public void supprimernotification(@PathVariable Long idnotification){allservices.supprimernotification(idnotification);}
    @PostMapping("/chat/addchat")
    public Chat ajouterchat(@RequestBody Chat chat){return allservices.ajouterchat(chat);}
    @PutMapping("/chat/updatechat/{idchat}")
    public void updatechat(@PathVariable Long idchat, @RequestBody Chat chat){allservices.updatechat(idchat,chat);}
    @GetMapping("/chat/getchatbyid/{idchat}")
    public Chat getchatbyId(@PathVariable Long idchat){return allservices.getchatbyId(idchat);}
    @GetMapping("/chat/getall")
    public List<Chat> cherchertouschat(){return allservices.cherchertouschat();}
    @DeleteMapping("/chat/deletechat/{idchat}")
    public void supprimerchat(@PathVariable Long idchat){allservices.supprimerchat(idchat);}
    @GetMapping("/client/getall")
    public List<Client> cherchertousclient(){
        return allservices.cherchertousclient();
    }

}


