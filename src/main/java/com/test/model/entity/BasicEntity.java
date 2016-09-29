package com.test.model.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.IncrementGenerator;

import javax.persistence.*;

/**
 * Created by Павел on 17.09.2016.
 */
@MappedSuperclass
public abstract class BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
