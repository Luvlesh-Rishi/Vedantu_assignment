package com.assignment.Vedantu.Repository;


import org.springframework.data.repository.CrudRepository;

import com.assignment.Vedantu.Entity.Item;

public interface InvRepo extends CrudRepository<Item, Integer>{


}
