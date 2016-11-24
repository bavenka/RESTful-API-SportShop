package com.test.dao.impl;

import com.test.dao.ProductDao;
import com.test.model.entity.product.AttributeName;
import com.test.model.entity.product.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Павел on 19.11.2016.
 */
@Component
@Transactional
public class ProductDaoImpl implements ProductDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Product> getProducts(int offset, int limit) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root);
        return em.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
    }

    @Override
    public List<Product> findProductsByFilters(Map<String, String> filters, int offset, int limit) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            AttributeName attributeName = AttributeName.valueOf((filter.getKey()));
            switch (attributeName.toString()) {
                case "name": {
                    predicates.add(builder.like(root.get("name"), "%" + filter.getValue() + "%"));
                    break;
                }
                case "color": {
                    predicates.add(builder.equal(root.get("color"), filter.getValue()));
                    break;
                }
                case "brand": {
                    predicates.add(builder.equal(root.get("brand"), filter.getValue()));
                    break;
                }
                case "size": {
                    predicates.add(builder.like(root.get("sizes"), "%" + filter.getValue() + "%"));
                    break;
                }
                case "minPrice": {
                    predicates.add(builder.greaterThanOrEqualTo(root.get("price"), filter.getValue()));
                    break;
                }
                case "maxPrice": {
                    predicates.add(builder.lessThanOrEqualTo(root.get("price"), filter.getValue()));
                    break;
                }
                case "productCategory": {
                    predicates.add(builder.equal(root.get("productCategory"), filter.getValue()));
                    break;
                }
            }
            query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        }
        return em.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
    }
}
