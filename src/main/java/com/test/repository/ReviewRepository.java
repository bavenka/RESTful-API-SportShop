package com.test.repository;

import com.test.model.entity.product.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Павел on 23.11.2016.
 */
@Service
public interface ReviewRepository extends CrudRepository<Review, Long> {
}
