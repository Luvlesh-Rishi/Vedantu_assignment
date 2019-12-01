package com.assignment.Vedantu.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.assignment.Vedantu.Entity.ItemOrdered;

public interface ItemOrderedRepo extends CrudRepository<ItemOrdered, Integer>{

	List<ItemOrdered> findByOrderid(int id);
}
