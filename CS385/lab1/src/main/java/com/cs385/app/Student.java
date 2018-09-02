package com.cs385.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Student {
	private static ArrayList<Student> studentList = new ArrayList<Student>();
	private static ArrayList<Student> sortedList = new ArrayList<Student>();

	private String firstName;
	private String lastName;
	private String favoriteColor;
	private String pets;
	private String hometown;
	private String favoriteMovie;
	private String shoeSize;

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
	}
	
	public Student() {
		//Do nothing
	}

	public static ArrayList<Student> getStudentList() {
		if(studentList.isEmpty()) {
			throw new IllegalStateException("Student List is empty");
		}
		return studentList;
	}
	
	public static ArrayList<Student> getSortedList() {
		if(studentList.isEmpty()) {
			throw new IllegalStateException("Sorted List is empty");
		}
		return sortedList;
	}

	@SuppressWarnings("unchecked")
	public void sortAndQuery() {
		sortedList = (ArrayList<Student>) studentList.clone();
		Collections.sort(sortedList, new SortByLastName());

		for (Student s : sortedList) {
			String color = s.getFavoriteColor().toLowerCase();
			if (!(color.contains("green") || color.contains("black"))) {
				sortedList.remove(s);
			}
		}
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

	class SortByLastName implements Comparator<Student> {
		public int compare(Student a, Student b) {
			return a.getLastName().compareToIgnoreCase(b.getLastName());
		}
	}
}
