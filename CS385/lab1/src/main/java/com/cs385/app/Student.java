package com.cs385.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Class to represent student objects, provide a list of students, and provide
 * helper methods for manipulating student data.
 * 
 * @author Kyle Jon Aure
 *
 */
public class Student {
	// Lists
	private static ArrayList<Student> studentList = new ArrayList<Student>();
	private static ArrayList<Student> sortedList = new ArrayList<Student>();

	// Student fields
	private String firstName;
	private String lastName;
	private String favoriteColor;
	private String pets;
	private String hometown;
	private String favoriteMovie;
	private String shoeSize;

	// Helper variables
	private boolean isSortedListAccurate = false;

	/* ********************
	 * CONSTRUCTORS
	 * ********************/

	/**
	 * All argument constructor.
	 * 
	 * @param firstName     String this student's first name
	 * @param lastName      String this student's last name
	 * @param favoriteColor String this student's favorite color
	 * @param pets          String this student's pet(s)
	 * @param hometown      String this student's hometown
	 * @param favoriteMovie String this student's favorite movie(s)
	 * @param shoeSize      String this student's shoe size
	 */
	public Student(String firstName, String lastName, String favoriteColor, String pets, String hometown,
			String favoriteMovie, String shoeSize) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.favoriteColor = favoriteColor;
		this.pets = pets;
		this.hometown = hometown;
		this.favoriteMovie = favoriteMovie;
		this.shoeSize = shoeSize;

		studentList.add(this);

		isSortedListAccurate = false;
	}

	/**
	 * No argument constructor. Use this constructor to create a 'helper' student
	 * that can use the helper methods listed below without being on the class list.
	 */
	public Student() {
		// DO NOTHING
	}

	/* ********************
	 * HELPER METHODS
	 * ********************/

	/**
	 * Sorts student list by last name and queries data for students who's favorite
	 * color is either green or black
	 */
	@SuppressWarnings("unchecked")
	public void sortAndQuery() {
		sortedList = (ArrayList<Student>) studentList.clone();
		Collections.sort(sortedList, new SortByLastName());

		for (Iterator<Student> iterator = sortedList.iterator(); iterator.hasNext();) {
			Student s = iterator.next();
			String color = s.getFavoriteColor().toLowerCase();
			if (!(color.contains("green") || color.contains("black"))) {
				iterator.remove();
			}
		}

		isSortedListAccurate = true;
	}

	/**
	 * Creates a header row for student data if you plan on using a JTable to
	 * visually represent the data from this class.
	 * 
	 * @return String[] student header
	 */
	public String[] createHeader() {
		return new String[] { "FirstName", "LastName", "FavoriteColor", "Pets", "Hometown", "Favorite Movie(s)",
				"Shoe size" };
	}

	public boolean removeStudentFromList(Student s) {
    	if( !studentList.isEmpty() ) {
	    	if (studentList.contains(s)) {
	    		studentList.remove(s);
	    		isSortedListAccurate = false;
	    		return true;
	    	} else {
	    		throw new IllegalStateException("Student is not in the student list.");
	    	}
    	} else {
    		throw new IllegalStateException("Student list is empty.");
    	}
    }

	/* ********************
	 * GETTERS AND SETTERS
	 * ********************/

	public ArrayList<Student> getStudentList() {
		if (studentList.isEmpty()) {
			throw new IllegalStateException("Student List is empty");
		}
		return studentList;
	}

	public ArrayList<Student> getSortedList() {
		if (studentList.isEmpty() || !isSortedListAccurate) {
			throw new IllegalStateException("Sorted List is empty or is not accurate.");
		}
		return sortedList;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		studentList.remove(this);
		this.firstName = firstName;
		studentList.add(this);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		studentList.remove(this);
		this.lastName = lastName;
		studentList.add(this);
	}

	public String getFavoriteColor() {
		return favoriteColor;
	}

	public void setFavoriteColor(String favoriteColor) {
		studentList.remove(this);
		this.favoriteColor = favoriteColor;
		studentList.add(this);
	}

	public String getPets() {
		return pets;
	}

	public void setPets(String pets) {
		studentList.remove(this);
		this.pets = pets;
		studentList.add(this);
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		studentList.remove(this);
		this.hometown = hometown;
		studentList.add(this);
	}

	public String getFavoriteMovie() {
		return favoriteMovie;
	}

	public void setFavoriteMovie(String favoriteMovie) {
		studentList.remove(this);
		this.favoriteMovie = favoriteMovie;
		studentList.add(this);
	}

	public String getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(String shoeSize) {
		studentList.remove(this);
		this.shoeSize = shoeSize;
		studentList.add(this);
	}

	/*
	 * ******************
	 * OUTPUT
	 ********************/

	/**
	 * Returns this student's data as a row for use in a JTable
	 * 
	 * @return Object[] Row of student data
	 */
	public Object[] toRow() {
		Object[] results = new Object[] { firstName, lastName, favoriteColor, pets, hometown, favoriteMovie, shoeSize };
		return results;
	}

	/*
	 * ******************
	 * COMPARATOR
	 ********************/

	/**
	 * Subclass that implements the Comparator class and indicates that students
	 * should be sorted by last name.s
	 */
	class SortByLastName implements Comparator<Student> {
		public int compare(Student a, Student b) {
			return a.getLastName().compareToIgnoreCase(b.getLastName());
		}
	}
}
