package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.Products;
import tn.esprit.vitanova.repository.ProductRepo;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService implements IProductservice{
    ProductRepo productRepo;

    @Override
    public Products ajouterProducts(Products p) {
        return productRepo.save(p);
    }

    @Override
    public void updateProducts(Long idProducts, Products p) {
        p.setIdProducts(idProducts);
        productRepo.save(p);

    }

    @Override
    public Products getProductsbyId(Long idProducts) {
        return productRepo.findById(idProducts).get();
    }

    @Override
    public List<Products> chercherTousProducts() {
        return productRepo.findAll();
    }

    @Override
    public void supprimerproducts(Long idProducts) {
    productRepo.deleteById(idProducts);
    }
}
