package homeoffice.application.basket;

import java.util.*;

/**
 * Represents a customer's shopping basket.<br>
 * <br>
 * Note that this implementation of {@code Basket} is <b>not</b> synchronised.
 * 
 * @author Richard Innocent
 */
public class SimpleBasket implements Basket {
	
	private final List<EditableBasketEntry> basketEntries = new ArrayList<>();
	
	@Override
	public int addItem(Item item) throws ArithmeticException {
		return addItem(item, 1);
	}
	
	@Override
	public int addItem(Item item, int quantity)
			throws IllegalArgumentException, ArithmeticException {
		try {
			return addItemThrowException(item, quantity);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(
					"Should not try to add a negative number of items. Please see removeItem()",
					e);
		}
	}
	
	private int addItemThrowException(Item item, int quantity)
			throws IllegalArgumentException, ArithmeticException {
		EditableBasketEntry basketEntry = getBasketEntry(item);
		if (basketEntry == null) {
			basketEntries.add(new EditableBasketEntry(item, quantity));
			return quantity;
		}
		return basketEntry.incrementQuantity(quantity);
	}
	
	private EditableBasketEntry getBasketEntry(Item item) {
		for (EditableBasketEntry basketEntry : basketEntries) {
			if (basketEntry.contains(item))
				return basketEntry;
		}
		return null;
	}
	
	@Override
	public int removeItem(Item item) {
		EditableBasketEntry basketEntry = getBasketEntry(item);

		if (basketEntry == null)
			return 0;
		
		int newQuantity = basketEntry.decrementQuantity(1);
		
		if (newQuantity == 0)
			remove(basketEntry);
		
		return newQuantity;
	}
	
	@Override
	public void removeAll(Item item) {
		remove(getBasketEntry(item));
	}
	
	private void remove(EditableBasketEntry basketEntry) {
		basketEntries.remove(basketEntry);
	}
	
	@Override
	public void clear() {
		basketEntries.clear();
	}
	
	@Override
	public boolean contains(Item item) {
		return getBasketEntry(item) != null;
	}
	
	@Override
	public int getQuantity(Item item) {
		EditableBasketEntry basketEntry = getBasketEntry(item);
		return basketEntry == null ? 0 : basketEntry.getQuantity();
	}
	
	@Override
	public double getTotalCost() {
		double total = 0d;
		for (EditableBasketEntry basketEntry : basketEntries)
			total += basketEntry.getTotalCost();
		return total;
	}

	@Override
	public Iterator<BasketEntry> iterator() {
		return new BasketIterator();
	}
	
	
	private class EditableBasketEntry extends BasketEntry {
		
		public EditableBasketEntry(Item item, int quantity) throws IllegalArgumentException {
			super(item, quantity);
		}
		
		/**
		 * Increments the quantity of the item in the basket by the given amount.
		 * @param amount The number to increment the quantity by.
		 * @return The new quantity of the item.
		 * @throws IllegalArgumentException Thrown if {@code amount <= 0}.
		 * @throws ArithmeticException Thrown if {@code basketEntry.getAmount() + amount >
		 * 		Integer.MAX_VALUE}.
		 */
		public int incrementQuantity(int amount) throws IllegalArgumentException, ArithmeticException {
			assertNotNegative(amount, IllegalArgumentException.class, "Quantity must be >= 0");
			int newQuantity = this.quantity + amount;
			assertNotNegative(newQuantity, ArithmeticException.class, "Quantity overflowed");
			return this.quantity += amount;
		}
		
		/**
		 * Decrements the quantity of the item in the basket by the given amount.
		 * @param amount The number to decrement the quantity by.
		 * @return The new quantity of the item. Note that this method will not allow the item quantity
		 * 		to fall below 0 - calling this method when {@code amount > basketEntry.getQuantity()}
		 * 		will set the item quantity to 0.
		 */
		public int decrementQuantity(int amount) throws IllegalArgumentException {
			assertNotNegative(amount, IllegalArgumentException.class, "Quantity must be >= 0");
			int newQuantity = quantity - amount;
			return quantity = newQuantity <= 0 ? 0 : newQuantity;
		}
		
	}
	
	
	private class BasketIterator implements Iterator<BasketEntry> {

		private int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < basketEntries.size();
		}

		@Override
		public BasketEntry next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return basketEntries.get(index++);
		}
	}
	
}

