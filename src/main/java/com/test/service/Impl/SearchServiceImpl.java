package com.test.service.Impl;

import com.test.dao.ProductDao;
import com.test.model.dto.product.ProductDto;
import com.test.model.entity.product.Product;
import com.test.service.SearchService;
import com.test.utils.Converter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Павел on 23.11.2016.
 */
@Component
@Transactional
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductDto> findProductsByFilters(@NonNull Map<String, String> filters, int offset, int limit) throws Exception {
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            if (filter.getValue().isEmpty()) {
                filters.remove(filter.getKey());
            }
        }
        if (filters.isEmpty()) {
            throw new Exception("Invalid filters data");
        }
        Set<Product> products = productDao.getProductsByFilters(filters, offset, limit).stream().collect(Collectors.toSet());
        if (products != null) {
            List<ProductDto> productDtos = new ArrayList<>();
            for (Product product : products) {
                productDtos.add(Converter.toProductWithoutSpecificationsDto(product));
            }
            return productDtos;
        }
        return null;
    }
}
