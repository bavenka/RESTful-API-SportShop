package com.test.dao;

import com.test.model.entity.category.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Павел on 22.08.2016.
 */
@Service
public interface CategoryDao {
    List<Category> getRootCategories();
    List<Category> getChildren(Long parentId);
}
