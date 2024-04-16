package tn.esprit.vitanova.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.vitanova.entities.ProductType;
import tn.esprit.vitanova.entities.Products;
import tn.esprit.vitanova.services.CloudinaryService;
import tn.esprit.vitanova.services.IProductservice;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/product")
public class ProductController {
    IProductservice productservice;
    CloudinaryService cloudinaryService;
    @PostMapping("/addProduct")
    public Products addProduct(@RequestParam("file") MultipartFile file, @RequestParam("prodName") String prodName, @RequestParam("typeProd") ProductType typeProd, @RequestParam("price") Long price, @RequestParam("quantityP") int quantityP, @RequestParam("descriptionP") String descriptionP, @RequestParam("expiration") String expiration) throws IOException, IOException {
        String imageUrl = cloudinaryService.uploadFile(file);
        Products product = new Products();
        product.setProdName(prodName);
        product.setTypeProd(typeProd);
        product.setPrice(price);
        product.setQuantityP(quantityP);
        product.setDescriptionP(descriptionP);
        product.setImageUrl(imageUrl);
        product.setExpiration(expiration);
        return productservice.ajouterProducts(product);
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

    @GetMapping("/getproductId/{idProducts}")
    public Products getproductId(@PathVariable Long idProducts){
        Products product = productservice.getproductId(idProducts);
        return product;

    }



}
