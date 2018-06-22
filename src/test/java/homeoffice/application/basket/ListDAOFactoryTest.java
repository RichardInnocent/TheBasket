package homeoffice.application.basket;

import static org.junit.Assert.assertTrue;

import org.junit.*;

public class ListDAOFactoryTest {
	
	private static final ListDAOFactory listDaoFactory = ListDAOFactory.getInstance();
	
	@Test
	public void testGetInstance() {
		assertTrue(listDaoFactory == ListDAOFactory.getInstance());
		assertTrue(listDaoFactory == ListDAOFactory.getInstance());
	}
	
	@Test
	public void testGetItemDAOIsItemListDAO() {
		assertTrue(listDaoFactory.getItemDAO() instanceof ItemListDAO);
	}

}
