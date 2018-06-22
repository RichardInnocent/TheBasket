package homeoffice.application.basket;

import static org.junit.Assert.*;

import org.junit.Test;

public class BasketEntryTest {
	
	private static final Item ITEM_1 = new Item("id1", "Violin bow", 14.65);
	private static final Item ITEM_2 = new Item("id2", "Arduino", 34.60);
	
	private BasketEntry basketEntry = new BasketEntry(ITEM_1, 3);
	
	@Test
	public void testToString() {
		assertEquals(ITEM_1.toString() + ": 3", basketEntry.toString());
	}
	
	@Test
	public void testContains() {
		assertTrue(basketEntry.contains(ITEM_1));
		assertFalse(basketEntry.contains(ITEM_2));
	}
	
	@Test
	public void testGetTotalCost() {
		assertEquals(14.65*3, basketEntry.getTotalCost(), 1e-5);
	}

}
