package com.cs385.app;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	private Student helper = new Student();
	
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( AppTest.class );
    }


    public void testStudentAddedToList() {
    	Student x = new Student("a", "b", "c", "d", "e", "f", "g");
        ArrayList<Student> list = helper.getStudentList();
        
        assertTrue("Student list should have contained student x.", list.contains(x));
    }
    
    public void testRemoveStudentFromList() {
    	Student x = new Student("a", "b", "c", "d", "e", "f", "g");
    	
    	try {
    		assertTrue(helper.removeStudentFromList(x));
    	} catch (IllegalStateException ise) {
    		fail("Removing student from the list should not have thrown an exception." + ise.toString());
    	}
    }
    
    @SuppressWarnings("unused")
	public void testSort() {
    	Student x = new Student("a", "Zebra", "black", "d", "e", "f", "g");
    	Student y = new Student("a", "Monkey", "black", "d", "e", "f", "g");
    	Student z = new Student("a", "Apple", "black", "d", "e", "f", "g");
    	
    	helper.sortAndQuery();
    	
    	ArrayList<Student> list = helper.getSortedList();
    	
    	assertTrue("Student z should have be at position 0", list.get(0) == z);
    }
    
    public void testQuery() {
    	Student x = new Student("a", "b", "black", "d", "e", "f", "g");
    	Student y = new Student("a", "b", "green", "d", "e", "f", "g");
    	Student z = new Student("a", "b", "purple", "d", "e", "f", "g");
    	
    	helper.sortAndQuery();
    	ArrayList<Student> list = helper.getSortedList();
    	
    	assertTrue("Sorted list should have contained student x.", list.contains(x));
    	assertTrue("Sorted list should have contained student y.", list.contains(y));
    	assertTrue("Sorted list should NOT have contained student z.", !list.contains(z));
    }
}
