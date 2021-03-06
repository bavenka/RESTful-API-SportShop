package com.test.rest.controller;

import com.test.model.dto.ReviewDto;
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

    @RequestMapping(value = "user/{userId}/add/reviews/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> addReview(@RequestBody ReviewDto reviewDto,
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

    @RequestMapping(value = "user/{userId}/delete/reviews/{reviewId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReview(@PathVariable Long userId,
                                           @PathVariable Long reviewId,
                                           @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            reviewService.deleteReview(userId, reviewId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }
}
