package com.test.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Павел on 19.11.2016.
 */
@Entity
@Getter
@Setter
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
}
