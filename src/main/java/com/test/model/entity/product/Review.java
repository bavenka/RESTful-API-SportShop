package com.test.model.entity.product;

import com.test.model.entity.BasicEntity;
import com.test.model.entity.auth.User;

import javax.persistence.*;

/**
 * Created by Павел on 13.11.2016.
 */
@Entity
@Table(name = "reviews")
public class Review extends BasicEntity{
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private Integer stars;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Review() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
