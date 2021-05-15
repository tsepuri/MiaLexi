package com.csds393.mialexi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.csds393.mialexi.model.User;
import com.csds393.mialexi.repository.UserJpaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserJpaRepositoryTest {

	@Autowired
	  private TestEntityManager entityManager;
	
	 @Autowired
	  private UserJpaRepository userJpaRepository;
	
	private User user = new User();
	
	  @BeforeEach
	  public void fillSomeDataIntoOurDb() {
	    // Add new Users to Database
		user.setUsername("Test");
		user.setPassword("123456");
	    entityManager.persist(user);
	  }

	  @Test
	  public void testFindByUsername() throws Exception {
	    
	    User user1 = userJpaRepository.findByUsername("Test");
	    assertEquals(user1, user);
	  }
	  
	  @Test
	  public void testFindByUsernameandPassword() throws Exception {
		    User user1 = userJpaRepository.findByUsernameAndPassword("Test", "123456");
		    assertEquals(user1, user);
		  }
	  
	
	
	

}
