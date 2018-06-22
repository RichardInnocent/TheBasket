package homeoffice.application.basket;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple DAO implementation using a {@code List}.
 * 
 * @author Richard Innocent
 */
public class ItemListDAO implements ItemDAO {
	
	private final List<Item> items;
	
	public ItemListDAO(Item... allItems) {
		items = new ArrayList<Item>(allItems.length);
		addItems(allItems);
	}
	
	private void addItems(Item... allItems) {
		for (Item item : allItems)
			items.add(item);
	}
	
	public Item getItem(String id) {
		for (Item item : items)
			if (item.hasId(id))
				return item;
		return null;
	}

	public boolean updateItem(Item updatedItem) {
		int itemIndex = items.indexOf(updatedItem);
		if (itemIndex < 0)
			return false;
		items.set(itemIndex, updatedItem);
		return true;
	}

	public boolean addItem(Item item) {
		if (items.contains(item))
			return false;
		items.add(item);
		return true;
	}

	public boolean removeItem(Item item) {
		return items.remove(item);
	}
	
}
