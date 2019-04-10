package com.hacker.mail.repository;

import org.springframework.data.repository.CrudRepository;

import com.hacker.mail.dao.Event;


	public interface EventRepository extends CrudRepository<Event,Integer>  {
}
