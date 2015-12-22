package fr.dc.iamcore.services.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import fr.dc.iamcore.datamodel.Identity;
import fr.dc.iamcore.services.dao.IdentityDAOInterface;

/**
 * This is the implementation class for the data access object which implements
 * all the methods in the data access object interface
 * 
 * @author DEEP
 */
public class IdentityHibernateDAO implements IdentityDAOInterface {

	/**
	 * This is a session factory that has been autowired in the
	 * applicationContext xml file
	 */
	@Autowired
	private SessionFactory factory;

	/**
	 * This method is used to read all identities from the target path. This
	 * method overrides the readAll method in the data access object interface.
	 * <p>
	 * Return type: <b>List</b>
	 * <p>
	 * 
	 * @return The list containing the identities read from the target path
	 */
	@Override
	public List<Identity> readAll() {
		Session session = factory.openSession();
		@SuppressWarnings("unchecked")
		List<Identity> idList = session.createQuery("from Identity").list();
		session.close();
		return idList;
	}

	/**
	 * This method is used to write an identity to the target path. This method
	 * overrides the write method in the data access object interface.
	 * <p>
	 * Return type: <b>void</b>
	 * <p>
	 * 
	 * @param identity
	 *            The identity to write to the target path
	 */
	@Override
	public void write(Identity identity) {
		if (identity == null)
			return;
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(identity);
		tx.commit();
		session.close();
	}

	/**
	 * This method is used to search for an identity from the target path whose
	 * first name, last name and email matches the user input. This method
	 * overrides the search method in the data access object interface.
	 * <p>
	 * Return type: <b>List</b>
	 * <p>
	 * 
	 * @return The list containing the identities in the target path which match
	 *         the first name, last name and email entered by the user input
	 */
	@Override
	public List<Identity> search(Identity identity) {

		Session session = factory.openSession();
		@SuppressWarnings("unchecked")
		List<Identity> searchedList = session
				.createQuery(
						"from Identity as i where i.firstName = :fname and i.lastName = :lname")
				.setParameter("fname", identity.getFirstName())
				.setParameter("lname", identity.getLastName()).list();
		session.close();
		return searchedList;
	}

	/**
	 * This method searches for an identity from the target path whose id
	 * matches the user input and updates it in the target path. This method
	 * overrides the update method in the data access object interface.
	 * <p>
	 * Return type: <b>void</b>
	 * <p>
	 * 
	 * @param identity
	 *            The identity whose id is to be searched for and updated in the
	 *            target path
	 */
	@Override
	public void update(Identity identity) {
		Session session = factory.openSession();
		Query query = session
				.createQuery(
						"update Identity set firstName = :newFirstName, lastName = :newLastName, email = :newEmail, birthDate = :newBirthDate"
								+ " where id = :id")
				.setParameter("newFirstName", identity.getFirstName())
				.setParameter("newLastName", identity.getLastName())
				.setParameter("newEmail", identity.getEmail())
				.setParameter("newBirthDate", identity.getBirthDate())
				.setParameter("id", identity.getId());
		query.executeUpdate();
		session.close();
	}

	/**
	 * This method searches for an identity from the target path whose id
	 * matches the user input and deletes it from the target path. This method
	 * overrides the delete method in the data access object interface.
	 * <p>
	 * Return type: <b>void</b>
	 * <p>
	 * 
	 * @param identity
	 *            The identity whose id is to be searched for and deleted from
	 *            the target path
	 */
	@Override
	public void delete(Identity identity) {
		Session session = factory.openSession();
		Query query = session.createQuery("delete Identity where id = :id")
				.setParameter("id", identity.getId());
		query.executeUpdate();
		session.close();
	}

}
