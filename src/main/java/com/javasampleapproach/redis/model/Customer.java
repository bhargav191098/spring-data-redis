package com.javasampleapproach.redis.model;

import java.io.Serializable;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String passwd;

	protected Customer() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail(){
		return email;
	}
	public String getPasswd(){
		return passwd;
	}
	public void setEmail(String e){
		this.email=e;
	}
	public void setPasswd(String p){
		this.passwd=p;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Customer(long id, String firstName, String lastName, String email,String passwd) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passwd=passwd;
		this.email=email;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s' , email = '%s']", id, firstName, lastName,email);
	}
}
