package com.hacker.mail.repository;

import org.springframework.data.repository.CrudRepository;

import com.hacker.mail.dao.Feedback;

public interface FeedbackRepository extends CrudRepository<Feedback,Integer>  {

}
