package homeoffice.application.basket;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import homeoffice.application.basket.DAOFactory.DAOType;

public class DAOFactoryTest {
	
	@Test
	public void testGetListDAOFactory() {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOType.LIST);
		assertTrue(daoFactory instanceof ListDAOFactory);
		DAOFactory daoFactory2 = DAOFactory.getDAOFactory(DAOType.LIST);
		assertTrue(daoFactory == daoFactory2);
	}

}
