package com.test.dao.impl;


import com.test.dao.GenericDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Павел on 28.07.2016.
 */
@Component
@Transactional(noRollbackFor = Exception.class)
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private Class<T> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    protected abstract Class<T> getPersistenceClass();

    public GenericDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    public T save(T entity) {
        this.entityManager.persist(entity);
        return entity;
    }

    public T find(Long id) {
        return (T) this.entityManager.find(entityClass, id);
    }

    public T update(T entity) {
        return this.entityManager.merge(entity);
    }

    public void delete(Long id) {
        this.entityManager.remove(this.entityManager.getReference(entityClass, id));
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected CriteriaBuilder getBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }

}
