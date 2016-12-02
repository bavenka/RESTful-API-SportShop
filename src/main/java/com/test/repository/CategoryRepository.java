package com.test.repository;

import com.test.model.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Павел on 19.11.2016.
 */
@Service
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
