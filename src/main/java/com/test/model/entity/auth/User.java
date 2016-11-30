package com.test.model.entity.auth;

import com.test.model.entity.BasicEntity;
import com.test.model.entity.product.Product;
import com.test.model.entity.product.Review;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Павел on 17.09.2016.
 */

@Entity
@Table(name = "users")
public class User extends BasicEntity {
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Embedded
    private Address address;
    @Column(nullable = false)
    private String phone;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PasswordResetToken passwordResetToken;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> productReviews;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_wishProduct", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> wishProducts;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_cartProduct", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> cartProducts;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public PasswordResetToken getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(PasswordResetToken passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Review> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(List<Review> productReviews) {
        this.productReviews = productReviews;
    }

    public Set<Product> getWishProducts() {
        return wishProducts;
    }

    public void setWishProducts(Set<Product> wishProducts) {
        this.wishProducts = wishProducts;
    }

    public Set<Product> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(Set<Product> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
