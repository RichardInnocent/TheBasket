package homeoffice.application.basket;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class BasketTest {
	
	private final SimpleBasket basket = new SimpleBasket();
	
	private static final double ITEM_1_COST = 3.45;
	private static final double ITEM_2_COST = 1.23;
	private static final double ITEM_3_COST = 13.45;
	
	private static final Item ITEM_1 = new Item("id01", "Water bottle", ITEM_1_COST);
	private static final Item ITEM_2 = new Item("id02", "Desktop fan", ITEM_2_COST);
	private static final Item ITEM_3 = new Item("id03", "Coffee beans", ITEM_3_COST);
	
	@Test
	public void testAddSingleItem() {
		assertFalse(basket.contains(ITEM_1));
		assertFalse(basket.contains(ITEM_2));
		assertFalse(basket.contains(ITEM_3));
		basket.addItem(ITEM_1);
		basket.addItem(ITEM_2);
		basket.addItem(ITEM_3);
		assertTrue(basket.contains(ITEM_1));
		assertTrue(basket.contains(ITEM_2));
		assertTrue(basket.contains(ITEM_3));
		assertEquals(1, basket.getQuantity(ITEM_1));
		assertEquals(1, basket.getQuantity(ITEM_2));
		assertEquals(1, basket.getQuantity(ITEM_3));
	}
	
	@Test
	public void testAddMultipleItems() {
		assertFalse(basket.contains(ITEM_1));
		assertFalse(basket.contains(ITEM_2));
		assertFalse(basket.contains(ITEM_3));
		basket.addItem(ITEM_1, 5);
		basket.addItem(ITEM_2, 6);
		basket.addItem(ITEM_3, 3);
		assertTrue(basket.contains(ITEM_1));
		assertTrue(basket.contains(ITEM_2));
		assertTrue(basket.contains(ITEM_3));
		assertEquals(5, basket.getQuantity(ITEM_1));
		assertEquals(6, basket.getQuantity(ITEM_2));
		assertEquals(3, basket.getQuantity(ITEM_3));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddNegativeNumberOfItems() {
		basket.addItem(ITEM_1, -3);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddNegativeNumberOfItemsWhenItemAlreadyExists() {
		basket.addItem(ITEM_1, 3);
		basket.addItem(ITEM_1, -1);
	}
	
	@Test (expected = ArithmeticException.class)
	public void testAddSingleItemOverflows() {
		basket.addItem(ITEM_1, Integer.MAX_VALUE);
		basket.addItem(ITEM_1);
	}
	
	@Test (expected = ArithmeticException.class)
	public void testAddMutlipleItemsOverflows() {
		basket.addItem(ITEM_1, Integer.MAX_VALUE-3);
		basket.addItem(ITEM_1, 4);
	}
	
	@Test
	public void testRemoveItem() {
		basket.addItem(ITEM_1, 4);
		assertEquals(4, basket.getQuantity(ITEM_1));
		basket.removeItem(ITEM_1);
		assertEquals(3, basket.getQuantity(ITEM_1));
	}
	
	@Test
	public void testRemoveAll() {
		basket.addItem(ITEM_1, 4);
		assertTrue(basket.contains(ITEM_1));
		basket.removeAll(ITEM_1);
		assertFalse(basket.contains(ITEM_1));
	}
	
	@Test
	public void testClear() {
		basket.addItem(ITEM_1, 5);
		basket.addItem(ITEM_2, 6);
		basket.addItem(ITEM_3, 3);
		assertTrue(basket.contains(ITEM_1));
		assertTrue(basket.contains(ITEM_2));
		assertTrue(basket.contains(ITEM_3));
		basket.clear();
		assertFalse(basket.contains(ITEM_1));
		assertFalse(basket.contains(ITEM_2));
		assertFalse(basket.contains(ITEM_3));
	}
	
	@Test
	public void testGetQuantityOfNonExistentObject() {
		assertEquals(0, basket.getQuantity(ITEM_1));
	}
	
	@Test
	public void testGetTotalCost() {
		basket.addItem(ITEM_1, 5);
		basket.addItem(ITEM_2, 6);
		basket.addItem(ITEM_3, 3);
		double expectedCost = ITEM_1_COST * 5 + ITEM_2_COST * 6 + ITEM_3_COST * 3;
		assertEquals(expectedCost, basket.getTotalCost(), 1e-5);
	}
	
	@Test
	public void testGetTotalCostWhenEmpty() {
		assertEquals(0d, basket.getTotalCost(), 1e-5);
	}
	
	@Test
	public void testGetTotalCostOfEmptyBasket() {
		assertEquals(0d, basket.getTotalCost(), 1e-5);
	}
	
	@Test
	public void testIteratorForEach() {
		 basket.addItem(ITEM_1, 4);
		 basket.addItem(ITEM_2, 3);
		 basket.addItem(ITEM_3);
		 
		 boolean item1Success = false;
		 boolean item2Success = false;
		 boolean item3Success = false;
		 
		 for (BasketEntry basketEntry : basket) {
			 Item item = basketEntry.getItem();
			 if (item.equals(ITEM_1)) {
				 item1Success = basketEntry.getQuantity() == 4 ? true : false;
			 } else if (item.equals(ITEM_2)) {
				 item2Success = basketEntry.getQuantity() == 3 ? true : false;
			 } else if (item.equals(ITEM_3)) {
				 item3Success = basketEntry.getQuantity() == 1 ? true : false;
			 } else {
				 fail("Unexpected item detected");
			 }
		 }
		 
		 assertTrue("One or more of the items was failed to be found with the correct quantity",
				 item1Success && item2Success && item3Success);
	}
	
	@Test
	public void testHasNext() {
		Iterator<BasketEntry> basketEntries = basket.iterator();
		assertFalse(basketEntries.hasNext());
		assertFalse(basketEntries.hasNext());
		basket.addItem(ITEM_1);
		basketEntries = basket.iterator();
		assertTrue(basketEntries.hasNext());
		assertTrue(basketEntries.hasNext());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testIteratorNextThrowsExceptionWhenBasketEmpty() {
		Iterator<BasketEntry> basketEntries = basket.iterator();
		basketEntries.next();
	}
	
}
