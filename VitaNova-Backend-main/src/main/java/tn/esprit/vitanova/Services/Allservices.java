package tn.esprit.vitanova.Services;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import tn.esprit.vitanova.entities.*;
import tn.esprit.vitanova.repository.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class Allservices implements Allservicesimpl{
    PsychologueRepo pr;
    NotificationssRepo np;
    ChatRepo chatRepo;
    RapportPsyRepo rapportPsyRepo;
    ClientRepo cl;
    ConsultationRepo cr;
    TemplateEngine templateEngine;




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

    @Override
    public Consultation addconsultation(Consultation consultation) {
        return cr.save(consultation);
    }

    @Override
    public void updateconsultation(Long id, Consultation consultation) {
        consultation.setIdConsultation(id);
        cr.save(consultation);
    }

    @Override
    public Consultation showconsultation(Long idconsulation) {
        return cr.findById(idconsulation).orElse(null);
    }

    @Override
    public List<Consultation> getallconsultation() {
        return cr.findAll();
    }

    @Override
    public void deleteconsultationbyid(Long id) {
        cr.deleteById(id);
    }
    @Override
    public Integer numberconsultation(Long psychologueId) {
        Psychologue psychologue = pr.findById(psychologueId).orElse(null);

        List<Consultation> consultations=psychologue.getConsultations();

        LocalDate today = LocalDate.now();
        List<Consultation> todaysConsultations = psychologue.getConsultations().stream()
                .filter(consultation -> consultation.getConsultationdate().equals(today))
                .collect(Collectors.toList());

        return todaysConsultations.size();
    }
    public Integer numberConsultationPerMonth(Long psychologueId, YearMonth yearMonth) {
        Psychologue psychologue = pr.findById(psychologueId).orElse(null);

        if (psychologue == null) {
            return 0; // Handle the case where the Psychologue is not found
        }

        List<Consultation> consultations = psychologue.getConsultations();

        List<Consultation> consultationsInMonth = psychologue.getConsultations().stream()
                .filter(consultation -> {
                    LocalDate consultationDate = consultation.getConsultationdate();
                    return consultationDate.getMonth() == yearMonth.getMonth() && consultationDate.getYear() == yearMonth.getYear();
                })
                .collect(Collectors.toList());

        return consultationsInMonth.size();
    }
    public void generatePdf(Long rapportPsyId) {
        Optional<RapportPsy> rapportPsyOptional = rapportPsyRepo.findById(rapportPsyId);
        if (rapportPsyOptional.isPresent()) {
            RapportPsy rapportPsy = rapportPsyOptional.get();
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("rapport_psy.pdf"));
                document.open();

                // Add content to the PDF
                document.add(new Paragraph("Rapport Psy"));
                document.add(new Paragraph("Description: " + rapportPsy.getDescription()));
                document.add(new Paragraph("Date: " + rapportPsy.getDateRappPs()));

                document.close();
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("RapportPsy not found with ID: " + rapportPsyId);
        }
    }


    }




//    public long countConsultationsPerDay(Long psychologueId) {
//        Psychologue psychologue = pr.findById(psychologueId)
//                .orElseThrow(() -> new EntityNotFoundException("Psychologue not found with id: " + psychologueId));
//
//        List<Consultation> consultations = psychologue.getConsultations();
//
//        LocalDate systemDate = LocalDate.now();
//
//        long result = consultations.stream()
//                .filter(consultation -> {
//                    Date consultationDate = consultation.getConsultationdate();
//                    if (consultationDate != null) {
//                        LocalDate dateFromConsultation = consultationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                        return dateFromConsultation.isEqual(systemDate);
//                    }
//                    return false;
//                })
//                .count();
//
//        System.out.println("Total consultations per day: " + result);
//
//        return result;
//



//    public long countConsultationsPerWeek(Long psychologueId, LocalDate date) {
//        Psychologue psychologue = pr.findById(psychologueId)
//                .orElseThrow(() -> new EntityNotFoundException("Psychologue not found with id: " + psychologueId));
//
//        long result = psychologue.countConsultationsPerWeek(date);
//        System.out.println("Consultations per week: " + result);
//
//        return result;
//    }
//
//    public long countConsultationsPerMonth(Long psychologueId, LocalDate date) {
//        Psychologue psychologue = pr.findById(psychologueId)
//                .orElseThrow(() -> new EntityNotFoundException("Psychologue not found with id: " + psychologueId));
//
//        long result = psychologue.countConsultationsPerMonth(date);
//        System.out.println("Consultations per month: " + result);
//
//        return result;
//    }



