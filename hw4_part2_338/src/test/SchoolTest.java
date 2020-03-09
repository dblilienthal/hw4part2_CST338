package test;

import static org.junit.jupiter.api.Assertions.*;

import hw4.*;

import org.junit.jupiter.api.Test;

class SchoolTest {

	@Test
	void test() {
		School school = new School("SCD");
		//  tom teaches CST338
		boolean rc = school.addInstructor(1,  "tom", "tom@csumb.edu", "813-111-1212");
		assertTrue(rc);
		rc = school.addCourse(338, "CST338 Software Design", 1, "BIT105"); 
		assertTrue(rc);
		school.addCourse(438,  "Software Engineering", 1,  "BIT104");
		assertTrue(rc);
		rc = school.addStudent(1001, "Grace", 338, 68, "D"); 
		assertTrue(rc);

	}
	

	@Test
	// duplicate instructor
	public void test5() {
		School school = new School("CSUMB");
		boolean rc = school.addInstructor(1,  "tom",  "tom@csumb.edu",  "813-111-1011");
		assertTrue(rc);
		
		// duplicate instructor should fail
		rc = school.addInstructor(1,  "alice",  "alice@csumb.edu", "813-888-1313");
		assertFalse(rc);
	}
	
	@Test 
	// add Students
	public void test6() {
		School school = new School("CSUMB");
		school.addInstructor(1,  "Bob",  "bob@csumb.edu",  "813-211-1212");
		school.addCourse(301,  "CST301 - Python",  1,  "BIT104");
		school.addCourse(338,  "CST338 Software Design",  1,  "BIT105");
		boolean rc = school.addStudent(1001,  "Alice",  301,  92.0,  "A");
		assertTrue(rc);
		
		// student with same id but different name should fail
		rc = school.addStudent(1001,  "Alison",  301,  85, "B");
		assertFalse(rc);
		
		// student with same id and name, different course
		rc = school.addStudent(1001,  "Alice", 338, 85, "B");
		assertTrue(rc);
		
	}
	
	@Test
	public void test7() {
		School SCD = new School("SCD");
		SCD.readData("test1.txt");

		boolean rc = SCD.addInstructor(700, "E. Tao", "tao@csumb.edu", "777-777-1234");
		assertTrue(rc);
		
		// addCourse should fail. invalid instructor id.
		rc = SCD.addCourse(300, "CST300 – ProSem", 70, "BIT 110");
		assertFalse(rc);
		
		// invalid instructor id
		rc = SCD.addCourse(499, "CST499 – iOS Dev", 15, "BIT 104");
		assertFalse(rc);

		// duplicate courseid and invalid instructor id
		rc = SCD.addCourse(306, "CST306 – GUI Dev", 25, "BIT 120");
		assertFalse(rc);
	}
	
}
