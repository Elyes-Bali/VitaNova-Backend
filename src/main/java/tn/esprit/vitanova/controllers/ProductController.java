package tn.esprit.vitanova.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vitanova.entities.Products;
import tn.esprit.vitanova.services.IProductservice;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ProductController {
    IProductservice productservice;
    @PostMapping("/addprod")
    public Products ajouterProducts(@RequestBody Products p) {
        return productservice.ajouterProducts(p) ;
    }

    @PutMapping("/update/{idProducts}")
    public void updateProducts(@PathVariable Long idProducts,@RequestBody Products p){
        productservice.updateProducts(idProducts,p);
    }
    @GetMapping("/getproducts")
    public Products getProductsbyId(Long idProducts){
        return productservice.getProductsbyId(idProducts);
    }
    @GetMapping("/getallprods")
    public List<Products> getAllProds(){
        return productservice.chercherTousProducts();
    }
    @DeleteMapping("/deleteprod/{idProducts}")
    public  void  supprimerproducts(@PathVariable Long idProducts) {
        productservice.supprimerproducts(idProducts);
    }
}
