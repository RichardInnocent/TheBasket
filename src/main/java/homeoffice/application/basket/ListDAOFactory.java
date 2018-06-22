package homeoffice.application.basket;

/**
 * Factory object for retrieving all ListDAOs.
 *  
 * @author Richard Innocent
 */
public class ListDAOFactory extends DAOFactory {
	
	private static ListDAOFactory self;

	private final ItemDAO itemDAO = new ItemListDAO();
	
	private ListDAOFactory() {}
	
	/**
	 * Returns the {@code ListDAOFactory} object.
	 * @return The {@code ListDAOFactory} object.
	 */
	public static ListDAOFactory getInstance() {
		if (self == null)
			self = new ListDAOFactory();
		return self;
	}
	
	public ItemDAO getItemDAO() {
		return itemDAO;
	}
}
