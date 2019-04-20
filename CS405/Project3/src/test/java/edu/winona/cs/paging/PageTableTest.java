package edu.winona.cs.paging;

import java.util.Arrays;
import java.util.LinkedList;
import static org.junit.Assert.assertEquals;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.junit.Test;

public class PageTableTest {
	private PageTable pt;
	private String jsonString = "{\"pages\":[1,2,3]}";
	private LinkedList<Integer> pages = new LinkedList<Integer>(Arrays.asList(1,2,3));
	
	@Test
	public void testSerialization() {
		pt = new PageTable();
		pt.setPages(pages);
		
		Jsonb jsonb = JsonbBuilder.create();
		String result = jsonb.toJson(pt);
		
		assertEquals("Generated JSON strings should match:", jsonString, result);
		assertEquals("Pop value should match:", pt.getPages().pop().intValue(), 1);
	}
	
	@Test
	public void testDeserialization() {
		Jsonb jsonb = JsonbBuilder.create();
		pt = jsonb.fromJson(jsonString, PageTable.class);
		
		assertEquals("Generated list objects should match:", pt.getPages(), pages);
	}
}
