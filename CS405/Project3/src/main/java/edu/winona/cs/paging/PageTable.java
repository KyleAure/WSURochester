package edu.winona.cs.paging;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.json.bind.annotation.JsonbProperty;

public class PageTable {
	@JsonbProperty
	private LinkedList<Integer> pages;

	public PageTable() {
		//Intentionally Empty JSONB will use getters and setters
	}

	public LinkedList<Integer> getPages() {
		return pages;
	}

	public void setPages(LinkedList<Integer> pages) {
		this.pages = pages;
	}
	
	/**
	 * Returns the next page in page table.
	 * Return -1 if page table does not exist
	 * Return 0 if page table is empty
	 * @return integer
	 */
	public int pop() {
		try {
			return pages == null ? -1 : pages.pop().intValue();
		} catch (NoSuchElementException e) {
			return 0;
		}
	}
	
	public boolean arePagesCreated() {
		return pages != null;
	}

	@Override
	public String toString() {
		return "PageTable [pages=" + pages + "]";
	}
}
