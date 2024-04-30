package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.ERole;
import tn.esprit.vitanova.entities.PremuimV;
import tn.esprit.vitanova.entities.Products;
import tn.esprit.vitanova.entities.User;
import tn.esprit.vitanova.repository.PremuimVRepo;
import tn.esprit.vitanova.repository.ProductsRepo;
import tn.esprit.vitanova.repository.RoleRepo;
import tn.esprit.vitanova.repository.UserRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class Userlmpl implements UserService {
    private UserRepo userRepo;

    private RoleRepo roleRepo;

    private PremuimVRepo premuimVRepo;
    private ProductsRepo productsRepo;

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
        existingUser.setAnnualIncome(userDTO.getAnnualIncome());
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

    @Override
    public List<User> getClients()
    {
        return userRepo.findAllByRolesName(ERole.ROLE_CLIENT);
    }
    @Override
    public void calculateScores() {
        List<User> users = getClients();

        for (User user : users) {
            int purchasedProducts = 0;
            double paidProductsAmount = 0.0;
            long premiumPeriod = 0;
            long premiumPeriodInDays =0;
            double paidPremiumAmount = 0.0;

            for (Products product : user.getProducts()) {
                if (product.getPurchased()) {
                    purchasedProducts++;
                }
            }

            for (Products product : user.getProducts()) {
                if (product.getPaymentProduct() != null) {
                    paidProductsAmount += product.getPaymentProduct().getTotalPrice();
                }
            }

            if (user.getPremuimV() != null) {
                premiumPeriod = user.getPremuimV().getExpirationDate().getTime() - user.getPremuimV().getStartingDate().getTime();
                premiumPeriodInDays = premiumPeriod / (24 * 60 * 60 * 1000);
            }

            if (user.getPremuimV() != null && user.getPremuimV().getPaymentV() != null) {
                paidPremiumAmount = user.getPremuimV().getPaymentV().getTotalPrice();
            }

            double score = (purchasedProducts * 0.2) + (paidProductsAmount * 0.3) + (premiumPeriodInDays * 0.2) + (paidPremiumAmount * 0.3);
            System.out.println(score);
            user.setScore(score);
            userRepo.save(user);
        }
    }


    @Override
    public Map<Long, Integer> findUserWithMostPurchasedProducts() {
        User userWithMostPurchases = null;
        int maxPurchases = 0;

        List<User> users = getClients();

        for (User user : users) {
            int purchaseCount = 0;
            for (Products product : user.getProducts()) {
                if (product.getPurchased()) {
                    purchaseCount++;
                }
            }

            if (purchaseCount > maxPurchases) {
                maxPurchases = purchaseCount;
                userWithMostPurchases = user;
            }
        }

        Map<Long, Integer> result = new HashMap<>();
        result.put(userWithMostPurchases.getId(), maxPurchases);

        return result;
    }


    @Override
    public Map<Long, Double> findUserWithMostPaidProductsAmount() {
        User userWithMostPaidAmount = null;
        double maxPaidAmount = 0.0;

        List<User> users = getClients();

        for (User user : users) {
            double currentPaidAmount = 0.0;
            for (Products product : user.getProducts()) {
                if (product.getPaymentProduct() != null) {
                    currentPaidAmount += product.getPaymentProduct().getTotalPrice();
                }
            }

            if (currentPaidAmount > maxPaidAmount) {
                maxPaidAmount = currentPaidAmount;
                userWithMostPaidAmount = user;
            }
        }

        Map<Long, Double> result = new HashMap<>();
        result.put(userWithMostPaidAmount.getId(), maxPaidAmount);

        return result;
    }


    @Override
    public Map<Long, Long> findUserWithLongestPremiumPeriod() {
        User userWithLongestPremium = null;
        long maxPremiumPeriod = 0;

        List<User> users = getClients();

        for (User user : users) {
            PremuimV premuimV = user.getPremuimV();
            if (premuimV != null) {
                long currentPremiumPeriod = premuimV.getExpirationDate().getTime() - premuimV.getStartingDate().getTime();
                if (currentPremiumPeriod > maxPremiumPeriod) {
                    maxPremiumPeriod = currentPremiumPeriod;
                    userWithLongestPremium = user;
                }
            }
        }

        Map<Long, Long> result = new HashMap<>();
        result.put(userWithLongestPremium.getId(), maxPremiumPeriod  / (24 * 60 * 60 * 1000));

        return result;
    }


    @Override
    public Map<Long, Double> findUserWithMostPaidPremuimAmount() {
        User userWithMostPaidAmount = null;
        double maxPaidAmount = 0.0;

        List<User> users = getClients();

        for (User user : users) {
            double currentPaidAmount = 0.0;
            if (user.getPremuimV() != null && user.getPremuimV().getPaymentV() != null) {
                currentPaidAmount = user.getPremuimV().getPaymentV().getTotalPrice();
            }

            if (currentPaidAmount > maxPaidAmount) {
                maxPaidAmount = currentPaidAmount;
                userWithMostPaidAmount = user;
            }
        }

        Map<Long, Double> result = new HashMap<>();
        result.put(userWithMostPaidAmount.getId(), maxPaidAmount);

        return result;
    }

    @Override
    public List<Map<Long, Integer>> findUsersWithPurchasedProducts() {
        List<Map<Long, Integer>> results = new ArrayList<>();

        List<User> users = getClients();

        for (User user : users) {
            int purchaseCount = 0;
            for (Products product : user.getProducts()) {
                if (product.getPurchased()) {
                    purchaseCount++;
                }
            }

            if (purchaseCount > 0) {
                Map<Long, Integer> result = new HashMap<>();
                result.put(user.getId(), purchaseCount);
                results.add(result);
            }
        }

        return results;
    }


    @Override
    public List<Map<Long, Double>> findUsersWithPaidProductsAmount() {
        List<User> users = getClients();
        List<Map<Long, Double>> results = new ArrayList<>();

        for (User user : users) {
            double currentPaidAmount = 0.0;
            for (Products product : user.getProducts()) {
                if (product.getPaymentProduct() != null) {
                    currentPaidAmount += product.getPaymentProduct().getTotalPrice();
                }
            }

            if (currentPaidAmount > 0) {
                Map<Long, Double> result = new HashMap<>();
                result.put(user.getId(), currentPaidAmount);
                results.add(result);
            }
        }

        return results;
    }

    @Override
    public List<Map<Long, Long>> findUsersWithPremiumPeriod() {
        List<User> users = getClients();
        List<Map<Long, Long>> results = new ArrayList<>();

        for (User user : users) {
            PremuimV premuimV = user.getPremuimV();
            if (premuimV != null) {
                long currentPremiumPeriod = premuimV.getExpirationDate().getTime() - premuimV.getStartingDate().getTime();
                Map<Long, Long> result = new HashMap<>();
                result.put(user.getId(), currentPremiumPeriod / (24 * 60 * 60 * 1000));
                results.add(result);
            }
        }

        return results;
    }

    @Override
    public List<Map<Long, Double>> findUsersWithPaidPremiumAmount() {
        List<User> users = getClients();
        List<Map<Long, Double>> results = new ArrayList<>();

        for (User user : users) {
            double currentPaidAmount = 0.0;
            if (user.getPremuimV() != null && user.getPremuimV().getPaymentV() != null) {
                currentPaidAmount = user.getPremuimV().getPaymentV().getTotalPrice();
            }

            if (currentPaidAmount > 0) {
                Map<Long, Double> result = new HashMap<>();
                result.put(user.getId(), currentPaidAmount);
                results.add(result);
            }
        }

        return results;
    }


}