package homeoffice.application.basket;

/**
 * This class provides a go-between for the Java {@code Item}s, and the items in the data store.
 * 
 * @author Richard Innocent
 */
public interface ItemDAO {
	
	/**
	 * Gets the item from the data store with the specified ID.
	 * @param id The item ID.
	 * @return The item with the specified ID.
	 */
	public Item getItem(String id);
	
	/**
	 * Updates an item in the data store to reflect any changes made on the provided {@code
	 * updatedItem} object. Consider the following example:<br>
	 * {@code Item item = itemDao.getItem("001");}<br>
	 * {@code item.setName("QWERTY Keyboard");}<br>
	 * {@code // At this point, the data has been updated in the interface but not in the data}<br>
	 * {@code // store.}<br>
	 * {@code itemDao.updateItem(item); // Reflect changes in data store}
	 * @param updatedItem The item that has been updated.
	 * @return {@code true} if the item was added to the data store successfully.
	 */
	public boolean updateItem(Item updatedItem);
	
	/**
	 * Adds a new {@code Item} to the data store.
	 * @param item The new item.
	 * @return {@code true} if the data store was updated successfully.
	 */
	public boolean addItem(Item item);
	
	/**
	 * Removes an {@code Item} from the data store.
	 * @param item The item to be removed.
	 * @return {@code true} if the item was removed from the data store successfully.
	 */
	public boolean removeItem(Item item);

}
