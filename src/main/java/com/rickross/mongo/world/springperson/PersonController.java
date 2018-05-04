package com.rickross.mongo.world.springperson;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

	private PersonRepository repository;

	@Autowired
	public PersonController(PersonRepository repository)
	{
		this.repository = repository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Person> persons()
	{
		return repository.findAll();
	}

	@RequestMapping(method = RequestMethod.PUT)
    public Person add(@RequestBody @Valid Person person) {
        return repository.save(person);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Person update(@RequestBody @Valid Person person) {
        return repository.save(person);
    }
    
    @RequestMapping(value = "/email/{emailAddress:.+}", method = RequestMethod.GET) 
    public List<Person> getByEmailAddress(@PathVariable String emailAddress) {
    	return repository.findByEmailAddress(emailAddress);
    }

    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    public Optional<Person> getById(@PathVariable String id) {
        return repository.findById(id);
    }

    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable String id) {
        repository.deleteById(id);
    }
}
