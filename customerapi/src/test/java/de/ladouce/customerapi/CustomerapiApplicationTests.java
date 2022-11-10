package de.ladouce.customerapi;

import de.ladouce.customerapi.controller.CustomerController;
import  de.ladouce.customerapi.entity.Customer;
import de.ladouce.customerapi.repository.CustomerRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerapiApplicationTests {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CustomerController customerController = new CustomerController(customerRepository);

	@Test
	@Order(1)
	public void numberOfCustumerTest(){
		assertThat(customerController.customerNumber()).isEqualTo(0);
	}

	@Test
	@Order(2)
	public void createCustomerTest(){
		Customer customer = new Customer(13L,"New", "New");
		customerController.createNewCustomer(customer);
		assertNotNull(customerRepository.findById(13L).get());

	}

	@Test
	@Order(3)
	public void findAllCustomerTest(){
		List<Customer> customerList = customerController.findAll();
		assertThat(customerList.size()).isEqualTo(customerController.customerNumber());
	}

	@Test
	@Order(4)
	public void findOneCustomerTest(){
		Customer customer = customerController.findOne(13L);
		assertThat(customer.getId()).isEqualTo(13L);
		assertThat(customer.getFirstname()).isEqualTo("New");
		assertThat(customer.getLastname()).isEqualTo("New");
	}

	@Test
	@Order(5)
	public void deleteCustomerTest(){
		customerController.deleteCustomer(13L);
		assertThat(customerRepository.existsById(13L)).isFalse();
	}


}
