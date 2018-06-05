package com.javasampleapproach.redis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.redis.model.Customer;
import com.javasampleapproach.redis.repo.CustomerRepository;

@RestController
public class WebController {

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping("/save")
	public String save() {
		// save a single Customer
		customerRepository.save(new Customer( 1, "Jack", "Smith","jack@gmail.com","test"));
		customerRepository.save(new Customer(2, "Adam", "Johnson","adam@gmail.com","test1"));
		customerRepository.save(new Customer(3, "Kim", "Smith","kim@gmail.com","test2"));
		customerRepository.save(new Customer(4, "David", "Williams","david@gmail.com","test3"));
		customerRepository.save(new Customer(5, "Peter", "Davis","Peter@gmail.com","test4"));

		return "Done";
	}

	@RequestMapping("/findall")
	public String findAll() {
		String result = "";
		Map<String, Customer> customers = customerRepository.findAll(); // Change Longs to Strings here, add log in, add find by email id, add Check by EMail, add

		for (Customer customer : customers.values()) {
			result += customer.toString() + "<br>";
		}

		return result;
	}

	@RequestMapping("/find")
	public String findById(@RequestParam("id") Long id) {
		String result = "";
		result = customerRepository.find(id).toString();
		return result;
	}

	@RequestMapping(value = "/uppercase")
	public String postCustomer(@RequestParam("id") Long id) {
		Customer customer = customerRepository.find(id);
		customer.setFirstName(customer.getFirstName().toUpperCase());
		customer.setLastName(customer.getLastName().toUpperCase());

		customerRepository.update(customer);

		return "Done";
	}

	@RequestMapping("/delete")
	public String deleteById(@RequestParam("email") String e) {
		customerRepository.delete(e);

		return "Done";
	}
	@RequestMapping("/updateFirstName")
	public String update1(@RequestParam("email")String e,@RequestParam("name")String n){
		Customer c=customerRepository.findbyemail(e);
		c.setFirstName(n);
		customerRepository.update(c);
		return "Name updated!";

	}
    @RequestMapping("/updateLastName")
    public String update2(@RequestParam("email")String e,@RequestParam("name")String n){
        Customer c=customerRepository.findbyemail(e);
        c.setLastName(n);
        customerRepository.update(c);
        return "Last Name updated!";

    }

	@RequestMapping("/add")
	public String add(@RequestParam("id") Long id,@RequestParam("fname")String fname,@RequestParam("lname")String lname,@RequestParam("email")String e,@RequestParam("passwd")String p){
		customerRepository.save(new Customer( id, fname,lname,e,p));
		return "Done";
	}
	@CrossOrigin
	@RequestMapping(value="/Login",method = RequestMethod.GET)
    public JSONObject log(@RequestParam("email")String e,@RequestParam("passwd")String p){
		JSONObject j1 = new JSONObject();
		JSONObject j2 = new JSONObject();
		j1.put("Login","Success");
		j2.put("Login","Failed");
	    int j = customerRepository.login(e,p);
	    if(j==1)
	        return j1;
	    else
	    	return j2;
    }


}
