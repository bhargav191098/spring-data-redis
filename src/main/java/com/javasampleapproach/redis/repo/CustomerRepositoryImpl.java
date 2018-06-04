package com.javasampleapproach.redis.repo;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.redis.model.Customer;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	private static final String KEY = "Customer";

	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, Customer> hashOperations;

	@Autowired
	public CustomerRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(Customer customer) {
		hashOperations.put(KEY, customer.getEmail(), customer);
	}

	@Override
	public Customer find(Long id) {
		return hashOperations.get(KEY, id);
	}

	@Override
	public Customer findbyemail(String email){
		return hashOperations.get(KEY,email);
	}

	@Override
	public Map<String,Customer> findAll() {
		return hashOperations.entries(KEY);
	}

	@Override
	public void update(Customer customer) {
		hashOperations.put(KEY, customer.getEmail(), customer);
	}
	@Override
    public void update_email_id(Customer c){
	    hashOperations.put(KEY,c.getEmail(),c);
    }

	@Override
	public void delete(String e) {
		hashOperations.delete(KEY, e);
	}

	@Override
	public int login(String email,String passwd){
		Customer c = findbyemail(email);
		if(c==null)
			return 3;
		if(c.getPasswd().equals(passwd))
			return 1;
		else
			return 0;
	}

}
