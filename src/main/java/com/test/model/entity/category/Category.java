package com.test.model.entity.category;

import com.test.model.entity.BasicEntity;
import com.test.model.entity.product.Product;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Павел on 19.11.2016.
 */
@Entity
@Table(name = "categories")
public class Category extends BasicEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String image;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent", cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private Set<Category> children;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Product> products;

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
