package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.Products;

import java.util.List;

public interface IProductservice {
    public Products ajouterProducts(Products p);
    public void updateProducts(Long idProducts , Products p);
    public Products getProductsbyId(Long idProducts);
    public List<Products> chercherTousProducts();
    public void supprimerproducts(Long idProducts);
}
