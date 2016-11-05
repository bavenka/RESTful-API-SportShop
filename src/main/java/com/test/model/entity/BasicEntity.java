package com.test.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Павел on 17.09.2016.
 */
@MappedSuperclass
@Data
public abstract class BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
