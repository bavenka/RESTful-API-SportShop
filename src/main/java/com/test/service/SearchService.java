package com.test.service;

import com.test.model.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Павел on 23.11.2016.
 */
@Service
public interface SearchService {
    List<ProductDto> findProductsByFilters(Map<String, String> filters, int offset, int limit) throws Exception;
}
