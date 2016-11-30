package com.test.service;

import com.test.model.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Павел on 19.11.2016.
 */
@Service
public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto) throws Exception;

    CategoryDto editCategory(CategoryDto categoryDto) throws Exception;

    void deleteCategory(Long id) throws Exception;

    CategoryDto setParentToChild(Long childId, Long parentId) throws Exception;

    void deleteParentFromChild(Long childId) throws Exception;

    List<CategoryDto> getRootCategories() throws Exception;

    List<CategoryDto> getSubcategories(Long categoryId) throws Exception;
}
