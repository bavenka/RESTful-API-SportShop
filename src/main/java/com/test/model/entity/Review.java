package com.test.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Павел on 13.11.2016.
 */
@Entity
@Getter
@Setter
@Table(name = "reviews")
public class Review extends BasicEntity {
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
}
