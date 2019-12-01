package com.assignment.Vedantu.Repository;

import org.springframework.data.repository.CrudRepository;

import com.assignment.Vedantu.Entity.Account;

public interface AccountRepo extends CrudRepository<Account, String>{

}
