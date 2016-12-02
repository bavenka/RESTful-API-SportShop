package com.test.repository;

import com.test.model.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Pavel on 30.11.2016.
 */
@Service
public interface OrderRepository extends CrudRepository<Order, Long> {
}
