package com.test.repository;

import com.test.model.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Павел on 13.11.2016.
 */
@Service
public interface ProductRepository extends CrudRepository<Product, Long> {
}
