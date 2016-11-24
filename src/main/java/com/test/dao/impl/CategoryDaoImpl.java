package com.test.dao.impl;

import com.test.dao.CategoryDao;
import com.test.model.entity.category.Category;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Павел on 19.11.2016.
 */
@Component
@Transactional
public class CategoryDaoImpl implements CategoryDao {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Category> getRootCategories() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        query.where(
                builder.isNull(root.get("parent"))
        );
        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Category> getChildren(Long parentId) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        query.where(
                builder.equal(root.get("parent"), parentId)
        );
        return em.createQuery(query).getResultList();
    }
}
