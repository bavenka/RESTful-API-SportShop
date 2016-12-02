package com.test.dao;

import com.test.model.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Павел on 19.11.2016.
 */
@Service
public interface ProductDao extends GenericDao<Product> {
    List<Product> getProducts(int offset, int limit);
    List<Product> getProductsByFilters(Map<String, String> filters, int offset, int limit);
}
