package com.test.rest.controller;

import com.test.model.dto.CategoryDto;
import com.test.model.dto.product.ProductDto;
import com.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Павел on 19.11.2016.
 */
@RestController
@RequestMapping("${route.categories}")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getRootCategories() throws Exception {

        List<CategoryDto> categoryDtos;
        try {
            categoryDtos = categoryService.getRootCategories();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSubcategories(@PathVariable Long id) throws Exception {

        List<CategoryDto> categoryDtos;
        try {
            categoryDtos = categoryService.getSubcategories(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto,
                                            @RequestHeader(name = "Authorization") String token) throws Exception {
        CategoryDto existingCategoryDto;
        try {
            existingCategoryDto = categoryService.editCategory(categoryDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingCategoryDto, HttpStatus.OK);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto,
                                         @RequestHeader(name = "Authorization") String token) throws Exception {
        CategoryDto existingCategoryDto;
        try {
            existingCategoryDto = categoryService.addCategory(categoryDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingCategoryDto, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable Long id,
                                            @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            categoryService.deleteCategory(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "{childId}/add/parent/{parentId}", method = RequestMethod.PUT)
    public ResponseEntity<?> setParentToChild(@PathVariable("childId") Long childId,
                                              @PathVariable("parentId") Long parentId,
                                              @RequestHeader(name = "Authorization") String token) throws Exception {
        CategoryDto existingCategoryDto;
        try {
            existingCategoryDto = categoryService.setParentToChild(childId, parentId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingCategoryDto, HttpStatus.OK);
    }

    @RequestMapping(value = "{childId}/delete/parent", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteParentFromChild(@PathVariable("childId") Long childId,
                                                   @RequestHeader(name = "Authorization") String token) throws Exception {
        CategoryDto existingCategoryDto;
        try {
            existingCategoryDto = categoryService.deleteParentFromChild(childId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(existingCategoryDto, HttpStatus.OK);
    }
}
