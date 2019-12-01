package com.assignment.Vedantu.Repository;

import org.springframework.data.repository.CrudRepository;

import com.assignment.Vedantu.Entity.ProductOrder;

public interface OrderRepo extends CrudRepository<ProductOrder, Integer>{

}
