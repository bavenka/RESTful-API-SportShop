package com.test.rest.controller;

import com.test.model.dto.ProductDto;
import com.test.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Павел on 23.11.2016.
 */
@RestController
@RequestMapping("${route.search}")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> getSearchedProducts(@RequestBody Map<String, String> filters,
                                                 @RequestParam("offset") int offset,
                                                 @RequestParam("limit") int limit) throws Exception {
        List<ProductDto> productDtos;
        try {
            productDtos = searchService.findProductsByFilters(filters, offset, limit);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

}
