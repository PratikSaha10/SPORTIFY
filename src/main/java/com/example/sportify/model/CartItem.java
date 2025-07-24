// package com.example.sportify.model;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;

// @Entity

// public class CartItem {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     @ManyToOne
//     private Product product;
//     private int quantity;
//     @ManyToOne
//     private Cart cart;

//      public Product getProduct() {
//         return product;
//     }
//     public void setProduct(Product product) {
//         this.product = product;
//     }
//     public int getQuantity() {
//         return quantity;
//     }
//     public void setQuantity(int quantity) {
//         this.quantity = quantity;
//     }
//     public Long getId() {
//         return id;
//     }
//     public void setId(Long id) {
//         this.id = id;
//     }
//     public Cart getCart() {
//         return cart;
//     }
//     public void setCart(Cart cart) {
//         this.cart = cart;
//     }
// }

package com.example.sportify.model;

import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many items belong to one cart
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String name;
    private int quantity;
    private double subtotal;

    public CartItem() {}

    public CartItem(Cart cart, Product product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.name = product.getName();
        this.quantity = quantity;
        this.subtotal = product.getPrice() * quantity;
    }

    // Getters and setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Cart getCart() { return cart; }

    public void setCart(Cart cart) { this.cart = cart; }

    public Product getProduct() { return product; }

    public void setProduct(Product product) {
        this.product = product;
        this.name = product.getName();
        recalculateSubtotal();
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        recalculateSubtotal();
    }

    public double getSubtotal() { return subtotal; }

    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    private void recalculateSubtotal() {
        if (this.product != null) {
            this.subtotal = this.product.getPrice() * this.quantity;
        }
    }
}
