package com.test.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Павел on 18.09.2016.
 */
@Entity
@Table(name = "roles")
@Data
public class Role extends BasicEntity{
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }
}
