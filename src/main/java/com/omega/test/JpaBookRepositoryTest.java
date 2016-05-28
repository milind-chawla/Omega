package com.omega.test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.omega.config.OmegaCoreConfig;
import com.omega.domain.Book;
import com.omega.repository.BookDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { OmegaCoreConfig.class })
@Transactional
public class JpaBookRepositoryTest {
	
	@PersistenceContext(unitName = "OmegaUnit1")
	private EntityManager entityManager;
	
	@Autowired
	private BookDao bookDao;
	
	private Book book;
	
/*	@Before
	public void setupData() {
		book = new Book(1, "Book 1");
	}
	
	@Test
	public void testFindById() {
		Book book = bookDao.findById(this.book.getId()).get();
		
		assertEquals(this.book.getId(), book.getId());
		assertEquals(this.book.getName(), book.getName());
	}
	
	@Test
	public void testStoreBookFull() {
		Book book = new Book("Book xyz");
		bookDao.save(book);
		
		entityManager.flush();
	}
	
	@Test
	public void testStoreBookEmpty() {
		Book book = new Book();
		bookDao.save(book);
		
		entityManager.flush();
	}
*/}
