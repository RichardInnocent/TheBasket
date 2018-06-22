package homeoffice.application.basket;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ItemListDAOTest {
	
	private ItemDAO itemDAO;
	private final Item [] initialItems = new Item [7];
	
	private final Item item1 = new Item("id01", "Water bottle", 3.45);
	private final Item item2 = new Item("id02", "Desktop fan", 10d);
	private final Item item3 = new Item("id03", "Coffee beans", 3.6);
	private final Item item4 = new Item("id04", "Thermal flask", 5.7);
	private final Item item5 = new Item("id05", "Computer mouse", 8.5);
	private final Item item6 = new Item("id06", "Wallet", 12d);
	private final Item item7 = new Item("id07", "Pillow", 14.95);
	
	// Not added to the DAO initially
	private final Item item8 = new Item("id08", "Fax machine", 40.2);
	
	@Before
	public void populateDAO() {
		initialItems[0] = item1;
		initialItems[1] = item2;
		initialItems[2] = item3;
		initialItems[3] = item4;
		initialItems[4] = item5;
		initialItems[5] = item6;
		initialItems[6] = item7;
		itemDAO = new ItemListDAO(initialItems);
	}
	
	@Test
	public void getItemTest() {
		assertEquals(item1, itemDAO.getItem(item1.getId()));
		assertEquals(item4, itemDAO.getItem(item4.getId()));
		assertEquals(item7, itemDAO.getItem(item7.getId()));
	}
	
	@Test
	public void getNonExistentItemShouldReturnNull() {
		assertNull(itemDAO.getItem("invalidId"));
	}
	
	@Test
	public void updateItemTest() {
		Item item5Updated = new Item(item5.getId(), "Wireless mouse", 8.95);
		assertTrue(itemDAO.updateItem(item5Updated));
		assertEquals(item5Updated.getName(), itemDAO.getItem(item5Updated.getId()).getName());
		assertEquals(item5Updated.getPrice(), itemDAO.getItem(item5Updated.getId()).getPrice(),
				1e-5);
	}
	
	@Test
	public void updateNonExistentItemShouldReturnFalse() {
		Item nonExistentItem = new Item("invalidId", "Backpack", 20d);
		assertFalse(itemDAO.updateItem(nonExistentItem));
	}
	
	@Test
	public void addItemTest() {
		assertNull(itemDAO.getItem(item8.getId()));
		assertTrue(itemDAO.addItem(item8));
		assertEquals(item8, itemDAO.getItem(item8.getId()));
	}
	
	@Test
	public void addAlreadyExistentItemShouldReturnFalse() {
		assertEquals(item3, itemDAO.getItem(item3.getId()));
		assertFalse(itemDAO.addItem(new Item(item3.getId(), "Croydon mug", 2.2)));
	}
	
	@Test
	public void removeItemTest() {
		assertEquals(item1, itemDAO.getItem(item1.getId()));
		assertEquals(item4, itemDAO.getItem(item4.getId()));
		assertEquals(item7, itemDAO.getItem(item7.getId()));
		assertTrue(itemDAO.removeItem(item1));
		assertTrue(itemDAO.removeItem(item4));
		assertTrue(itemDAO.removeItem(item7));
		assertNull(itemDAO.getItem(item1.getId()));
		assertNull(itemDAO.getItem(item4.getId()));
		assertNull(itemDAO.getItem(item7.getId()));
	}
	
	@Test
	public void removeNonExistentItemShouldReturnFalse() {
		assertNull(itemDAO.getItem(item8.getId()));
		assertFalse(itemDAO.removeItem(item8));
	}
	
}
