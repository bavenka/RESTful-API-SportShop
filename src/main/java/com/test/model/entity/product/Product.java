package com.test.model.entity.product;

import com.test.model.entity.BasicEntity;
import com.test.model.entity.auth.User;
import com.test.model.entity.category.Category;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Павел on 13.11.2016.
 */
@Entity
@Table(name = "products")
public class Product extends BasicEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String image;
    @Column
    private String color;
    @Column
    private String country;
    @Column
    private String code;
    @Column
    private String brand;
    @Column
    private String sizes;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Image> productImages;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Review> productReviews;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "wishProducts")
    private Set<User> wishUsers;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public Set<Image> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<Image> productImages) {
        this.productImages = productImages;
    }

    public Set<Review> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(Set<Review> productReviews) {
        this.productReviews = productReviews;
    }

    public Set<User> getWishUsers() {
        return wishUsers;
    }

    public void setWishUsers(Set<User> wishUsers) {
        this.wishUsers = wishUsers;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
