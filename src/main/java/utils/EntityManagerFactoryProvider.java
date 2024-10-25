package utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryProvider {
	private static EntityManagerFactory factory;

	public static EntityManagerFactory getFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("trinhminhkha21091031");
		}
		return factory;
	}
}


