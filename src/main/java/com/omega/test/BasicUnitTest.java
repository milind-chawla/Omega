package com.omega.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasicUnitTest {
	public static class DummyDao {
		private final List<String> database;

		public DummyDao(List<String> database) {			
			this.database = database;
		}

		public boolean add(String element) {
			return database.add(element);
		}

		public boolean remove(String element) {
			return database.remove(element);
		}
		
		public List<String> find(String queryWord) {
			final List<String> result = new ArrayList<String>();
			
			for(String element: database) {
				if(element.contains(queryWord)) {
					result.add(element);
				}
			}
			
			return result;
		}
	}
	
	private List<String> database;
	private DummyDao dummyDao;
	
	@Before
	public void dataSetup() {
		database = new ArrayList<String>();
		
		for (int i = 0; i < 20; i++) {
			database.add("test" + i);
		}
		
		dummyDao = new DummyDao(database);
	}
	
	@Test
	public void testDeleteQuery() {
		assertEquals(1, dummyDao.find("test0").size());
		
		dummyDao.remove("test0");
		
		assertEquals(19, database.size());
		assertEquals(0, dummyDao.find("test0").size());
	}
	
	@Test
	public void testAddQuery() {
		assertEquals(0, dummyDao.find("test20").size());
		
		dummyDao.add("test20");
		
		assertEquals(21, database.size());
		assertEquals(1, dummyDao.find("test20").size());
	}
	
	@Test
	public void testFindQuery() {
		List<String> results = dummyDao.find("2");
		
		assertEquals(2, results.size());
		
		for (String result: results) {
			assertTrue(result.equals("test2") || result.equals("test12"));
		}
	}
}
