package com.test.service.Impl;

import com.test.model.dto.ReviewDto;
import com.test.model.entity.User;
import com.test.model.entity.Product;
import com.test.model.entity.Review;
import com.test.repository.ProductRepository;
import com.test.repository.ReviewRepository;
import com.test.repository.UserRepository;
import com.test.service.ReviewService;
import com.test.utils.Constant;
import com.test.utils.Converter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Павел on 23.11.2016.
 */
@Component
@Transactional
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public ReviewDto addReview(Long userId, @NonNull ReviewDto reviewDto, Long productId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_PRODUCT);
        }
        Review review = Converter.toReview(reviewDto);
        review.setUser(user);
        review.setProduct(product);
        reviewRepository.save(review);
        reviewDto.setId(review.getId());
        reviewDto.setAuthor(user.getName());
        return reviewDto;
    }

    @Override
    public void deleteReview(Long userId, Long reviewId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_USER);
        }
        List<Review> reviews = user.getProductReviews();
        if (reviews == null) {
            throw new Exception("User has not reviews");
        }
        Review searchedReview = null;
        for (Review existingReview : reviews) {
            if (existingReview.getId().equals(reviewId)) {
                searchedReview = existingReview;
                break;
            }
        }
        if (searchedReview == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_REVIEW);
        }
        reviewRepository.delete(searchedReview.getId());
    }
}
