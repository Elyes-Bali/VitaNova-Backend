package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.Products;

import java.util.List;

public interface IProductservice {
     Products ajouterProducts(Products p);
     void updateProducts(Long idProducts , Products p);
     Products getProductsbyId(Long idProducts);
     List<Products> chercherTousProducts();
     void supprimerproducts(Long idProducts);
      Products getproductId(Long idProducts);

     List<Products> getChartData(String metric);
}
