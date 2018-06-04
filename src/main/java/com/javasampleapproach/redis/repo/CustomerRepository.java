package com.javasampleapproach.redis.repo;

import java.util.Map;

import com.javasampleapproach.redis.model.Customer;

public interface CustomerRepository {
	int login(String email,String passwd);
	void save(Customer customer);
	Customer find(Long id);
	Customer findbyemail(String email);
	Map<String, Customer> findAll();
	void update(Customer customer);
	void delete(String e);
	void update_email_id(Customer c);

}

