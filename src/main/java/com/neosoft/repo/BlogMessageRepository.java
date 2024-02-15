package com.neosoft.repo;

import org.springframework.data.repository.CrudRepository;

import com.neosoft.model.BlogMessage;

public interface BlogMessageRepository extends CrudRepository<BlogMessage, Integer> {

}
