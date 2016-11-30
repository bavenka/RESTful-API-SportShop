package com.test.dao.impl;

import com.test.dao.CategoryDao;
import com.test.model.entity.category.Category;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Павел on 19.11.2016.
 */
@Component
@Transactional(noRollbackFor = Exception.class)
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {

    @Override
    public List<Category> getRootCategories() {
        CriteriaBuilder builder = getBuilder();
        CriteriaQuery<Category> query = builder.createQuery(getPersistenceClass());
        Root<Category> root = query.from(getPersistenceClass());
        query.where(
                builder.isNull(root.get("parent"))
        );
        return getEntityManager().createQuery(query).getResultList();
    }

    @Override
    public List<Category> getSubcategories(Long parentId) {
        CriteriaBuilder builder = getBuilder();
        CriteriaQuery<Category> query = builder.createQuery(getPersistenceClass());
        Root<Category> root = query.from(getPersistenceClass());
        query.where(
                builder.equal(root.get("parent"), parentId)
        );
        return getEntityManager().createQuery(query).getResultList();
    }

    @Override
    protected Class<Category> getPersistenceClass() {
        return Category.class;
    }
}
