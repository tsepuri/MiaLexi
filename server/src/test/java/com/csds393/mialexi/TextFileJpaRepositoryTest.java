package com.csds393.mialexi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.csds393.mialexi.model.TextFile;
import com.csds393.mialexi.model.User;
import com.csds393.mialexi.repository.TextFileJpaRepository;
import com.csds393.mialexi.repository.UserJpaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
class TextFileJpaRepositoryTest {

	@Autowired
	  private TestEntityManager entityManager;
	
	 @Autowired
	  private TextFileJpaRepository textFileJpaRepository;
	
	private TextFile textFile = new TextFile();
	
	  @BeforeEach
	  public void fillSomeDataIntoOurDb() {
	    // Add new Users to Database
		textFile.setUsername("User1");
		textFile.setFileName("File1");
	    entityManager.persist(textFile);
	  }

	  @Test
	  public void testFindByUsername() throws Exception {
	    
		List<TextFile> textfile1 = new ArrayList<TextFile>();
		textfile1.add(textFile);
		List<TextFile> textfiles = textFileJpaRepository.findByUsername("User1");
	    assertEquals(textfile1, textfiles);
	  }
	  
	  @Test
	  public void testFindByUsernameAndFileName() throws Exception {
		    
		    TextFile textFile1 = textFileJpaRepository.findByUsernameAndFileName("User1", "File1");
		    assertEquals(textFile, textFile1);
		  }
	  
	
	
	

}
