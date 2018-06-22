package homeoffice.application.basket;

/**
 * Data structure to hold an {@code Item} and its quantity. The public interface of this class is
 * immutable, and can only be created or modified from via the {@code Basket}.<br>
 * <br>
 * Note that this class is <b>not</b> synchronised.
 *  
 * @author Richard Innocent
 * @see homeoffice.application.basket.Basket
 */
public class BasketEntry {
	
	protected Item item;
	protected int quantity;
	
	/**
	 * Creates a new {@code BasketEntry} of the given item and specified quantity.
	 * @param item The item.
	 * @param quantity The quantity of the item.
	 * @throws IllegalArgumentException Thrown if {@code quantity < 0}.
	 */
	protected BasketEntry(Item item, int quantity) throws IllegalArgumentException {
		assertNotNegative(quantity, IllegalArgumentException.class, "Quantity must be >= 0");
		this.item = item;
		this.quantity = quantity;
	}
	
	/**
	 * Gets the {@code Item}.
	 * @return The {@code Item}.
	 */
	public Item getItem() {
		return item;
	}
	
	/**
	 * gets the quantity of the {@code Item}.
	 * @return The quantity.
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Returns whether or not this {@code BasketEntry} contains the specified {@code Item}.
	 * @param item The {@code Item} to be searched for.
	 * @return {@code true} if the specified {@code Item} matches the item in this {@code
	 * 		BasketEntry}.
	 */
	public boolean contains(Item item) {
		return this.item.equals(item);
	}
	
	/**
	 * Returns the price of a single unit of the specified item.
	 * @return The price of a single unit of the specified item.
	 */
	public double getIndividualItemCost() {
		return item.getPrice();
	}
	
	/**
	 * Returns the total price of all items in this {@code BasketEntry}.
	 * @return {@code getIndividualItemCost() * getQuantity()}.
	 */
	public double getTotalCost() {
		return getIndividualItemCost() * quantity; 
	}
	
	/**
	 * Verifies that {@code amount >= 0}. If not, this method will create a new {@code
	 * RuntimeException} of the specified type, with the provided {@code failMessage}.
	 * @param amount The amount to verify is greater than 0.
	 * @param errorType The type of {@code RuntimeException} that should be thrown if {@code amount
	 * 		< 0}. If the particular exception type has no constructor that takes a single {@code
	 * 		String} as an argument, a generic {@code RuntimeException} is created with the given
	 * 		message. As a result, ensure that the provided exception type specifies the required
	 * 		constructor.
	 * @param failMessage The message that the exception should be create with.
	 */
	protected void assertNotNegative(int amount, Class<? extends RuntimeException> errorType,
			String failMessage) {
		if (amount < 0) {
			RuntimeException exception;
			try {
				exception = createExceptionWithMessage(errorType, failMessage);
			} catch (Exception e) {
				exception = new RuntimeException(failMessage);
			}
			throw exception;
		}
	}
	
	private RuntimeException createExceptionWithMessage(
			Class<? extends RuntimeException> errorType, String failMessage) throws Exception {
		return errorType.getConstructor(String.class).newInstance(failMessage);
	}
	
	@Override
	public String toString() {
		return item + ": " + quantity;
	}
	
}
