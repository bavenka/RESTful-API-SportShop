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
@Table(name = "images")
public class Image extends BasicEntity {
    @Column(nullable = false)
    private String image;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Image() {
    }

}
