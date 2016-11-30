package com.test.model.entity.product;

import com.test.model.entity.BasicEntity;

import javax.persistence.*;

/**
 * Created by Павел on 13.11.2016.
 */
@Entity
@Table(name = "images")
public class Image extends BasicEntity {
    @Column(nullable = false)
    private String image;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Image() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
