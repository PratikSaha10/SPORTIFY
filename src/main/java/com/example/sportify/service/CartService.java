// package com.example.sportify.service;

// import com.example.sportify.model.Cart;
// import com.example.sportify.model.CartItem;
// import com.example.sportify.model.Product;
// import com.example.sportify.model.User;
// import com.example.sportify.repository.CartItemRepository;
// import com.example.sportify.repository.CartRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.Optional;

// @Service
// public class CartService {
//     @Autowired
//     private CartRepository cartRepository;
//     @Autowired
//     private CartItemRepository cartItemRepository;

//     public Cart getCartByUser(User user) {
//         Cart cart = cartRepository.findByUserId(user.getId());
//         if (cart == null) {
//             cart = new Cart();
//             cart.setUser(user);
//             cart = cartRepository.save(cart);
//         }
//         return cart;
//     }

//     public void addToCart(User user, Product product, int quantity) {
//         Cart cart = getCartByUser(user);
//         Optional<CartItem> existingItem = cart.getItems().stream()
//                 .filter(item -> item.getProduct().getId().equals(product.getId()))
//                 .findFirst();
//         if (existingItem.isPresent()) {
//             CartItem item = existingItem.get();
//             item.setQuantity(item.getQuantity() + quantity);
//             cartItemRepository.save(item);
//         } else {
//             CartItem newItem = new CartItem();
//             newItem.setProduct(product);
//             newItem.setQuantity(quantity);
//             newItem.setCart(cart);
//             cartItemRepository.save(newItem);
//             cart.getItems().add(newItem);
//         }
//         cartRepository.save(cart);
//     }

//     public void removeFromCart(Long cartItemId) {
//         cartItemRepository.deleteById(cartItemId);
//     }

//     public double getTotal(Cart cart) {
//         return cart.getItems().stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
//     }
// }
package com.example.sportify.service;

import com.example.sportify.model.Cart;
import com.example.sportify.model.CartItem;
import com.example.sportify.model.Product;
import com.example.sportify.model.User;
import com.example.sportify.repository.CartItemRepository;
import com.example.sportify.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    // Get or create cart for the user
    public Cart getCartByUser(User user) {
        Cart cart = cartRepository.findByUserId(user.getId());
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }
        return cart;
    }

    // Add product to cart
    public void addToCart(User user, Product product, int quantity) {
        Cart cart = getCartByUser(user);

        // Check if product already exists in cart
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            item.setSubtotal(item.getProduct().getPrice() * item.getQuantity());
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setName(product.getName()); // snapshot
            newItem.setQuantity(quantity);
            newItem.setSubtotal(product.getPrice() * quantity);

            cart.getItems().add(newItem);
            cartItemRepository.save(newItem);
        }

        // Save cart with updated items
        cartRepository.save(cart);
    }

    // Remove item from cart
    public void removeFromCart(Long cartItemId) {
        CartItem item = cartItemRepository.findById(cartItemId).orElse(null);
        if (item != null) {
            Cart cart = item.getCart();
            cart.getItems().remove(item); // for orphanRemoval to work
            cartItemRepository.delete(item);
            cartRepository.save(cart); // save updated cart
            
        }
        
    }

    // Get total cost of cart
    public double getTotal(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }

    // // Optional: clear entire cart
    // public void clearCart(User user) {
    //     Cart cart = getCartByUser(user);
    //     cart.getItems().clear();
    //     cartRepository.save(cart); // orphanRemoval will delete cart items
    // }
}
