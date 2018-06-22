package homeoffice.application.basket;

/**
 * Represents a customer's shopping basket.
 * 
 * @author Richard Innocent
 */
public interface Basket extends Iterable<BasketEntry> {
	
	/**
	 * Adds a single specified item to the basket.
	 * @param item The item to be added to the basket.
	 * @throws ArithmeticException Thrown if the new number of items exceeds the {@code
	 * 		Integer.MAX_VALUE}.
	 */
	public int addItem(Item item) throws ArithmeticException;
	
	/**
	 * Adds the specified item to the basket in the given quantity.
	 * @param item The item to be added to the basket.
	 * @param quantity The quantity.
	 * @throws IllegalArgumentException Thrown if {@code quantity < 1}.
	 * @throws ArithmeticException Thrown if the new number of items exceeds the {@code
	 * 		Integer.MAX_VALUE}.
	 * @return The new quantity of the item in the basket.
	 */
	public int addItem(Item item, int amount) throws IllegalArgumentException, ArithmeticException;
	
	/**
	 * Reduces the quantity of the given item in the basket, and removes it entirely if the
	 * quantity if now 0. If the item is not in the basket, this method has no effect.
	 * @param item The item to be removed.
	 * @return The new quantity of the item in the basket. 
	 */
	public int removeItem(Item item);
	
	/**
	 * Removes the item from the basket completely, regardless of the initial quantity. If the item
	 * is not in the basket, this method has no effect.
	 * @param item The item to be removed.
	 */
	public void removeAll(Item item);
	
	/**
	 * Removes all items from the basket.
	 */
	public void clear();
	
	/**
	 * Returns whether or not a particular item is in the basket.
	 * @param item The item.
	 * @return {@code true} if the item is in the basket.
	 */
	public boolean contains(Item item);
	
	/**
	 * Returns the quantity of the given item currently in the basket.
	 * @param item The item.
	 * @return The quantity of the given item, or {@code 0} if the item is not in the basket.
	 */
	public int getQuantity(Item item);
	
	/**
	 * Returns the total cost for all items in the basket.
	 * @return The total cost for all items in the basket.
	 */
	public double getTotalCost();
}
