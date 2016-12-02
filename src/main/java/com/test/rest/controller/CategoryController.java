package com.test.rest.controller;

import com.test.model.dto.CategoryDto;
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
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "categories", method = RequestMethod.GET)
    public ResponseEntity<?> getRootCategories() throws Exception {

        List<CategoryDto> categoryDtos;
        try {
            categoryDtos = categoryService.getRootCategories();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "category/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSubcategories(@PathVariable Long id) throws Exception {

        List<CategoryDto> categoryDtos;
        try {
            categoryDtos = categoryService.getSubcategories(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "admin/category/update", method = RequestMethod.PUT)
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

    @RequestMapping(value = "admin/category/add", method = RequestMethod.POST)
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

    @RequestMapping(value = "admin/category/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable Long id,
                                            @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            categoryService.deleteCategory(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "admin/subcategory/{childId}/add/category/{parentId}", method = RequestMethod.PUT)
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

    @RequestMapping(value = "admin/subcategory/{childId}/delete/category", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteParentFromChild(@PathVariable("childId") Long childId,
                                                   @RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            categoryService.deleteParentFromChild(childId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().build();
    }
}
