package com.example.sportify.controller;

import com.example.sportify.model.User;
import com.example.sportify.service.CartService;
import com.example.sportify.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String viewCart(@AuthenticationPrincipal User user, Model model) {
        var cart = cartService.getCartByUser(user);
        model.addAttribute("cart", cart);
        model.addAttribute("total", cartService.getTotal(cart));
        return "cart";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@AuthenticationPrincipal User user, @PathVariable Long productId, @RequestParam int quantity) {
        var product = productService.getProductById(productId);
        if (product != null) {
            cartService.addToCart(user, product, quantity);
        }
        return "redirect:/shop";
    }

    @GetMapping("/cart/remove/{itemId}")
    public String removeFromCart(@PathVariable Long itemId) {
        cartService.removeFromCart(itemId);
        return "redirect:/cart";
    }
    // @GetMapping("/cart/clear")
    // public String clearCart(@AuthenticationPrincipal User user) {
    //     cartService.clearCart(user);
    //     return "redirect:/cart";
    // }

}