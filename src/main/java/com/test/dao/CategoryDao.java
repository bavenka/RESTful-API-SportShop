package com.test.dao;

import com.test.model.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Павел on 22.08.2016.
 */
@Service
public interface CategoryDao extends GenericDao<Category> {
    List<Category> getRootCategories();
    List<Category> getSubcategories(Long parentId);
}
