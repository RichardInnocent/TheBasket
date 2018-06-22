package homeoffice.application.basket;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemTest {
	
	private final String itemId = "itemCode123";
	private final String name = "Sports bottle";
	private final double price = 3.45;
	
	private final Item item = new Item(itemId, name, price);
	
	@Test
	public void testConstructor() {
		assertEquals(itemId, item.getId());
		assertEquals(name, item.getName());
		assertEquals(price, item.getPrice(), 1e-5);
	}
	
	/**
	 * Two items with the same ID should be regarded as the same object
	 */
	@Test
	public void testEquals() {
		Item sameItem = new Item(itemId, name, price);
		Item sameItemId = new Item(itemId, "Some other name", 100e-5);
		Item differentItemId = new Item("differentItemId", name, price);
		
		assertTrue(item.equals(sameItem));
		assertTrue(item.equals(sameItemId));
		assertFalse(item.equals(differentItemId));
	}
	
	@Test
	public void testToString() {
		assertEquals(itemId, item.toString());
	}
	
}
