package homeoffice.application.basket;

/**
 * Represents an item as part of a shopping basket.
 *  
 * @author Richard Innocent
 */
public class Item {

	private String id;
	private String name;
	private double price;
	
	/**
	 * Creates a new item.
	 * @param id The item ID.
	 * @param name The item name.
	 * @param price The item price.
	 */
	public Item(String id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	/**
	 * Returns the item's ID.
	 * @return The item ID.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the item's ID.
	 * @param id The new item ID.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Returns the item's price. Units not specified.
	 * @return The item price.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Sets the item's price.
	 * @param price The new price. Note that price is best suited to non-atomic units, e.g. USD and
	 * 		GBP over cents and pence respectively.
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Returns the item's name.
	 * @return The item's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the item's name.
	 * @param name The new item name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns whether or not this {@code Item} matches the given ID.
	 * @param id The ID.
	 * @return {@code true} if this {@code Item} matches the given ID.
	 */
	public boolean hasId(String id) {
		return this.id.equals(id);
	}
	
	/**
	 * Will return {@code true} if the item IDs are equal.
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Item))
			return false;
		
		Item otherItem = (Item) object;
		return id.equals(otherItem.getId());
	}
	
	@Override
	public String toString() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}

}
