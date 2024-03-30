package tn.esprit.vitanova.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.vitanova.entities.Cart;
import tn.esprit.vitanova.entities.Products;
import tn.esprit.vitanova.services.ICartService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cart")
public class CartController {
    ICartService iCartService;

    @PostMapping("/addtocart/{ownerId}")
    public Cart ajouterCart(@PathVariable Long ownerId,@RequestBody Cart c) {
        return iCartService.ajouterCart(ownerId,c) ;
    }

    @PutMapping("/updatecart/{idProducts}")
    public void updateCart(@PathVariable Long idProducts,@RequestBody Cart c){
        iCartService.updateCart(idProducts,c);
    }
    @GetMapping("/getcartbyid")
    public Cart getCartbyId(Long idCart){
        return iCartService.getCartbyId(idCart);
    }
    @GetMapping("/getallcart")
    public List<Cart> getAllCart(){
        return iCartService.chercherTousCart();
    }
    @DeleteMapping("/deletecart/{idCart}")
    public  void  supprimerprodCart(@PathVariable Long idCart) {
        iCartService.supprimerprodCart(idCart);
    }

    @GetMapping("/getCartId/{idCart}")
    public Cart getCartId(@PathVariable Long idCart){
        return iCartService.getCartId(idCart);
    }

    @GetMapping("/getCartByOwnerId/{ownerId}")
    public List<Cart> getCartByOwnerId(@PathVariable Long ownerId) {
        return iCartService.getCartByOwnerId(ownerId);
    }

}
