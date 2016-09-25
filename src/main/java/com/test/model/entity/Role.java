package com.test.model.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Павел on 18.09.2016.
 */
@Entity
@Table(name = "roles")
public class Role extends BasicEntity{
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
