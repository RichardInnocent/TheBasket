package homeoffice.application.basket;

/**
 * Interface for providing different DAO types.
 *  
 * @author Richard Innocent
 */
public abstract class DAOFactory {
	
	private static ListDAOFactory listDaoFactory;
	
	/**
	 * Returns the {@code ItemDAO} for this DAO type.
	 * @return The {@code ItemDAO}.
	 */
	public abstract ItemDAO getItemDAO();
	
	/**
	 * Different types of data stores supported by this application.
	 * @author Richard Innocent
	 */
	public enum DAOType {
		/**
		 * @see homeoffice.application.basket.ListDAOFactory
		 */
		LIST
	}
	
	/**
	 * Gets the {@code DAOFactory} for the provided {@code DAOType}.
	 * @param type The type of data store that should be used.
	 * @return The appropriate {@code DAOFactory}.
	 * @see homeoffice.application.basket.DAOFactory.DAOType
	 */
	public static DAOFactory getDAOFactory(DAOType type) {
		DAOFactory daoFactory;
		
		switch (type) {
		case LIST:
			daoFactory = (listDaoFactory == null) ? ListDAOFactory.getInstance() : listDaoFactory;
			break;
		default:
			throw new UnsupportedOperationException("Type " + type + " has not been implemented.");
		}
		
		return daoFactory;
	}
	
}
