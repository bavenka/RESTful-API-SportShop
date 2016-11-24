package com.test.rest.controller;

import com.test.model.dto.product.ProductDto;
import com.test.model.dto.product.ReviewDto;
import com.test.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Павел on 23.11.2016.
 */
@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "users/{userId}/reviews/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody ReviewDto reviewDto,
                                        @PathVariable Long userId,
                                        @PathVariable Long productId,
                                        @RequestHeader(name = "Authorization") String token) throws Exception {
        ReviewDto existingReviewDto;
        try {
            existingReviewDto = reviewService.addReview(userId, reviewDto, productId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingReviewDto, HttpStatus.OK);
    }

    @RequestMapping(value = "reviews/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable Long id,
                                           @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            reviewService.deleteReview(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }
}
