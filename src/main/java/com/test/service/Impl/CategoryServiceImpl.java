package com.test.service.Impl;

import com.test.dao.CategoryDao;
import com.test.model.dto.CategoryDto;
import com.test.model.entity.category.Category;
import com.test.repository.CategoryRepository;
import com.test.service.CategoryService;
import com.test.utils.Constant;
import com.test.utils.Converter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Павел on 19.11.2016.
 */
@Component
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public CategoryDto addCategory(@NonNull CategoryDto categoryDto) throws Exception {
        Category category = Converter.toCategory(categoryDto);
        categoryRepository.save(category);
        categoryDto.setId(category.getId());
        return categoryDto;
    }

    @Override
    public CategoryDto editCategory(@NonNull CategoryDto categoryDto) throws Exception {
        Category category = categoryRepository.findOne(categoryDto.getId());
        if (category == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_CATEGORY);
        }
        category = Converter.toCategory(categoryDto);
        category.setId(categoryDto.getId());
        categoryRepository.save(category);
        return categoryDto;
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        categoryRepository.delete(id);
    }

    @Override
    public CategoryDto setParentToChild(Long childId, Long parentId) throws Exception {
        Category child = categoryRepository.findOne(childId);
        if (child == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_CHILD);
        }
        Category parent = categoryRepository.findOne(parentId);
        if (parent == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_PARENT);
        }
        if (!child.getId().equals(parent.getId())) {
            child.setParent(parent);
            categoryRepository.save(child);
            return Converter.toCategoryDto(child);
        }
        return null;
    }

    @Override
    public void deleteParentFromChild(Long childId) throws Exception {
        Category child = categoryRepository.findOne(childId);
        if (child == null) {
            throw new Exception(Constant.MESSAGE_NOT_FOUND_CHILD);
        }
        if (child.getParent() == null) {
            throw new Exception("Child has not a parent!");
        }
        child.setParent(null);
        categoryRepository.save(child);
    }

    @Override
    public List<CategoryDto> getRootCategories() throws Exception {
        List<Category> categories = categoryDao.getRootCategories();
        if (categories == null) {
            return null;
        }
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            categoryDtos.add(Converter.toCategoryWithoutChildrenDto(category));
        }
        return categoryDtos;
    }

    @Override
    public List<CategoryDto> getSubcategories(Long categoryId) throws Exception {
        List<Category> subcategories = categoryDao.getSubcategories(categoryId);
        if (subcategories == null) {
            return null;
        }
        List<CategoryDto> childrenDtos = new ArrayList<>();
        for (Category subcategory : subcategories) {
            childrenDtos.add(Converter.toCategoryWithoutChildrenDto(subcategory));
        }
        return childrenDtos;
    }
}
