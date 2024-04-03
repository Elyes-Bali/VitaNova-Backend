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
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.*;
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
    QuestionRrepo qr;
    AnswersRepo an;



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
        if (consultation.getPsychologue() != null) {
            Integer test = isConsultationSlotAvailable(consultation.getConsultationdate(), consultation.getStartTime(), consultation.getPsychologue().getPsychologueId());
            if (test == 0) {
                return cr.save(consultation);
            } else {
                // Handle the case where consultation slot is not available
                System.out.println("shiit ");
                return null;
            }
        } else {
            // Handle the case where psychologue is null
            throw new IllegalArgumentException("Psychologue is null. Cannot add consultation.");
        }
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
                document.add(new Paragraph("psychologue: " + rapportPsy.getPsychologue().getNom()));

                document.add(new Paragraph("client: " + rapportPsy.getClient().getNom()));
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
    public Map<LocalDate, Integer> consultationsPerDayInMonth(Long psychologueId, YearMonth yearMonth) {
        Psychologue psychologue = pr.findById(psychologueId).orElse(null);

        if (psychologue == null) {
            return new HashMap<>(); // Handle the case where the Psychologue is not found
        }

        List<Consultation> consultations = psychologue.getConsultations();

        Map<LocalDate, Integer> consultationsPerDay = consultations.stream()
                .filter(consultation -> {
                    LocalDate consultationDate = consultation.getConsultationdate();
                    return consultationDate.getMonth() == yearMonth.getMonth() && consultationDate.getYear() == yearMonth.getYear();
                })
                .collect(Collectors.groupingBy(Consultation::getConsultationdate, Collectors.collectingAndThen(Collectors.toList(), List::size)));

        // Fill in days with zero consultations
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
        while (!firstDayOfMonth.isAfter(lastDayOfMonth)) {
            consultationsPerDay.putIfAbsent(firstDayOfMonth, 0);
            firstDayOfMonth = firstDayOfMonth.plusDays(1);
        }

        return consultationsPerDay;
    }
    public Integer isConsultationSlotAvailable(LocalDate date, LocalTime debut, Long psychologueid) {
        // Calculate the end time by adding one hour to the start time

        LocalTime endTime = debut.plusHours(1);

        // Retrieve existing consultations for the selected date and psychologist
        List<Consultation> consultations = cr.findByConsultationdateAndPsychologueId(date,psychologueid);
        List<Consultation> con = new ArrayList<>();
        // Check if the new consultation overlaps with any existing consultations
        for (Consultation consultation : consultations) {
            LocalTime existingStartTime = consultation.getStartTime().plusHours(1);
            LocalTime existingEndTime = existingStartTime;

            if ((existingStartTime.isBefore(endTime) && existingEndTime.isAfter(debut)) ||
                    (existingStartTime.equals(debut) && existingEndTime.isAfter(debut)) ||
                    (existingStartTime.isBefore(endTime) && existingEndTime.equals(endTime)))  {
                con.add(consultation);
            }


        }
        return con.size();


        // Slot does not overlap with any existing consultation for the psychologist

    }
    /////
    public List<LocalTime> getAvailableConsultationSlots(LocalDate date, Long psychologueid) {
        // Assuming working hours are from 9 AM to 5 PM
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 0);

        // Initial slot start time
        LocalTime slotStartTime = startTime;

        // Duration for each consultation slot (1 hour)
        Duration slotDuration = Duration.ofHours(1);

        List<LocalTime> availableSlots = new ArrayList<>();

        // Fetch existing consultation start times for the given date and psychologist
        List<LocalTime> existingConsultationStartTimes = cr.findStartTimesByDateAndPsychologueId(date, psychologueid);

        for (LocalTime existingStartTime : existingConsultationStartTimes) {
            // Adjust slot start time if it overlaps with an existing consultation
            if (slotStartTime.isBefore(existingStartTime)) {
                // There is an available slot before the existing consultation
                availableSlots.add(slotStartTime);
            }
            slotStartTime = existingStartTime.plusHours(1); // Move slot start time to the end of this consultation
        }

        // Add the last available slot if there's any remaining time in the day
        if (slotStartTime.isBefore(endTime)) {
            availableSlots.add(slotStartTime);
        }

        return availableSlots;
    }

    //////
    public List<Consultation> con(LocalDate date, LocalTime startTime, Long psychologueid) {
        // Calculate the end time by adding one hour to the start time

        LocalTime endTime = startTime.plusHours(1);

        // Retrieve existing consultations for the selected date and psychologist
        List<Consultation> consultations = cr.findByConsultationdateAndPsychologueId(date,psychologueid);

        // Check if the new consultation overlaps with any existing consultations

        return  consultations;


    }

    @Override
    public List<Question> allquestion() {
        return qr.findAll();
    }

    @Override
    public Answers getAnswerByQuestionId(Long questionId) {
        Question question = qr.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + questionId));

        // Get the associated answer
        Answers answer = question.getAnswer();

        if (answer != null) {
            return answer;
        } else {
            return null;
        }

    }

    @Override
    public List<Consultation> getConsultationsByUserId(Long userId) {
        return cr.findByClientId(userId);
    }


//        public List<String> recommendPsychologists(String genderPreference, char specializeDepression,
//                                                   char specializeRelationship, char specializeAnxiety) {
//            // Example list of psychologists (replace with your actual data)
//            List<Psychologue> psychologists = new ArrayList<>();
//
//            List<String> recommendedPsychologists = new ArrayList<>();
//
//            // Iterate over each psychologist
//            for (Psychologue psychologist : psychologists) {
//                // Check gender preference
//                if (!"none".equals(genderPreference) && !psychologist.getGender().equalsIgnoreCase(genderPreference)){
//                    // If the user has specified a gender preference and the psychologist's gender does not match,
//                    // skip to the next psychologist
//                    continue;
//                }
//
//                // Check specialties based on user's preferences
//                if (specializeDepression == 'Y' && !psychologist.getSpecialty().equals("Depression")) {
//                    // If the user is interested in depression specialty and the psychologist doesn't specialize in it,
//                    // skip to the next psychologist
//                    continue;
//                }
//
//                if (specializeRelationship == 'Y' && !psychologist.getSpecialty().equals("Relationship")) {
//                    // If the user is interested in relationship issues specialty and the psychologist doesn't specialize in it,
//                    // skip to the next psychologist
//                    continue;
//                }
//
//                if (specializeAnxiety == 'Y' && !psychologist.getSpecialty().equals("Anxiety")) {
//                    // If the user is interested in anxiety specialty and the psychologist doesn't specialize in it,
//                    // skip to the next psychologist
//                    continue;
//                }
//
//                // If all criteria are met, add the psychologist to the list of recommended psychologists
//                recommendedPsychologists.add(psychologist.getNom());
//            }
//
//            // Return the list of recommended psychologists
//            return recommendedPsychologists;
//        }
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



