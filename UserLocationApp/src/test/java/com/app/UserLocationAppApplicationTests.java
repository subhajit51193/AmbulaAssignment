package com.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.exception.EndUserException;
import com.app.model.Authority;
import com.app.model.EndUser;
import com.app.repository.EndUserRepository;
import com.app.service.EndUserService;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserLocationAppApplicationTests {

	@Autowired
	private EndUserService endUserService;
	
	@MockBean
	private EndUserRepository endUserRepository;

	/*
	 * Test case for save method which is supposed to
	 * return exception
	 */
	@Test
	public void saveUserDetailsWithExceptionTest() {
		
		EndUser endUser = new EndUser();
		endUser.setUserId("abcd");
		endUser.setName("Demo");
		endUser.setEmail("demo@gmail.com");
		endUser.setPassword("1234");
		endUser.getAuthorities().add(new Authority(1, "ROLE_READER", endUser));
		
		when(endUserRepository.save(endUser)).thenReturn(null);
		assertThrows(EndUserException.class,
				() ->{
					endUserService.registerEndUser(endUser);
				});
	}
	
	/*
	 * Test case for save method which is not supposed to
	 * return exception
	 */
	@Test
	public void saveUserDetailsTest() throws EndUserException {
		
		EndUser endUser = new EndUser();
		endUser.setUserId("abcd");
		endUser.setName("Demo");
		endUser.setEmail("demo@gmail.com");
		endUser.setPassword("1234");
		endUser.getAuthorities().add(new Authority(1, "ROLE_READER", endUser));
		
		when(endUserRepository.save(endUser)).thenReturn(endUser);
		assertEquals(endUser, endUserService.registerEndUser(endUser));
	}
	
	/*
	 * Test case for get All users method which is supposed to
	 * return empty list as exception
	 */
	@Test
	public void getAllUserDetailsExceptionTest() {
		
		when(endUserRepository.findAll()).thenReturn(Collections.emptyList());
		assertThrows(EndUserException.class,
				() ->{
					endUserService.getAllUsers();
				});
	}
	
	/*
	 * Test case for getAllusers method which is supposed to
	 * return empty list
	 */
	@Test
	public void getAllUserDetailsTest() throws EndUserException {
		
		EndUser endUser = new EndUser();
		endUser.setUserId("abcd");
		endUser.setName("Demo");
		endUser.setEmail("demo@gmail.com");
		endUser.setPassword("1234");
		endUser.getAuthorities().add(new Authority(1, "ROLE_READER", endUser));
		
		when(endUserRepository.findAll()).thenReturn(Stream
				.of(endUser).collect(Collectors.toList()));
		assertEquals(1, endUserService.getAllUsers().size());
	}
	
	/*
	 * Test case for get User by id method which is supposed to
	 * return exception
	 */
	@Test
	public void getUserByIdExceptionTest() {
		
		when(endUserRepository.findById(anyString())).thenReturn(null);
		
		assertThrows(EndUserException.class, 
				() ->{
					endUserService.getUserdetailsById(anyString());
				});
	}
	
	/*
	 * Test case for get User by id method which is not supposed to
	 * return exception
	 */
	@Test
	public void getUserByIdTest() throws EndUserException {
		
		EndUser endUser = new EndUser();
		endUser.setUserId("abcd");
		endUser.setName("Demo");
		endUser.setEmail("demo@gmail.com");
		endUser.setPassword("1234");
		endUser.getAuthorities().add(new Authority(1, "ROLE_READER", endUser));
		
		when(endUserRepository.findById(anyString())).thenReturn(Optional.of(endUser));
		
		assertEquals(Optional.of(endUser).get(), endUserService.getUserdetailsById(anyString()));
		
	}
	
	/*
	 * Test case for delete method to verify
	 * how many itmes will be deleted
	 * if it is implemented
	 * 
	 */
	@Test
	public void deleteUserTest() throws EndUserException {
		
		EndUser endUser = new EndUser();
		endUser.setUserId("abcd");
		endUser.setName("Demo");
		endUser.setEmail("demo@gmail.com");
		endUser.setPassword("1234");
		endUser.getAuthorities().add(new Authority(1, "ROLE_READER", endUser));
		
		endUserService.deleteUser(endUser);
		verify(endUserRepository,times(1)).delete(endUser);
	}
	
	
	

}
