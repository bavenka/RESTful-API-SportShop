package com.test.service;

import com.test.model.dto.product.ReviewDto;
import org.springframework.stereotype.Service;

/**
 * Created by Павел on 23.11.2016.
 */
@Service
public interface ReviewService {
    ReviewDto addReview(Long userId, ReviewDto reviewDto, Long productId) throws Exception;
    void deleteReview(Long userId, Long reviewId) throws Exception;
}
