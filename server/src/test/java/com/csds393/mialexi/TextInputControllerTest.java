package com.csds393.mialexi;

import org.junit.jupiter.api.Test;

import com.csds393.mialexi.controller.TextInputController;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;



public class TextInputControllerTest {
	
	@Test
	void testWiki() {
		TextInputController ti = new TextInputController();
		assertEquals(ti.getWikipedia("polka").trim(), 
		"The polka is originally a Czech dance and genre of dance music familiar throughout all of Europe and the Americas. It originated in the middle of the nineteenth century in Bohemia, now part of the Czech Republic. The polka remains a popular folk music genre in many European and American countries, and is performed by many folk artists.");
	}
	
	@Test
	void testFailWiki() {
		TextInputController ti = new TextInputController();
		assertEquals(ti.getWikipedia(""), "An error occured while getting the Wikipedia page");
	}
	
	@Test
	void testPdf() throws IOException {
		TextInputController ti = new TextInputController();
		assertEquals(ti.pdfToString(System.getProperty("user.dir")+File.separator+"Test2.pdf").trim(), "Test one two three");
	}
	
	@Test
	void testTxt() throws FileNotFoundException {
		TextInputController ti = new TextInputController();
		
		
		assertEquals(ti.txtToString(System.getProperty("user.dir")+File.separator+"test1").trim(), "Test one two three");
	}
}
