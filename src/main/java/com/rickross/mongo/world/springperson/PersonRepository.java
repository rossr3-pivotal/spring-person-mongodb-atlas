package com.rickross.mongo.world.springperson;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, String> {

	List<Person> findByEmailAddress(String emailAddress);
}
