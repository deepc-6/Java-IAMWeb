package fr.dc.iamcore.test.junit;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import junit.framework.TestCase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.dc.iamcore.datamodel.Identity;

/**
 * This is the test class which uses junit for testing
 * 
 * @author DEEP
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
public class JUnitTest extends TestCase {

	/**
	 * This is an instance of the session factory that is autowired in the
	 * applicationContext xml file
	 */
	@Autowired
	SessionFactory factory;

	/**
	 * This is an instance of the data source that is autowired in the
	 * applicationContext xml file
	 */
	@Autowired
	DataSource ds;

	/**
	 * This is the default constructor for the class JUnitTest with no
	 * parameters
	 */
	public JUnitTest() {

	}

	/**
	 * This is the test method that is used to connect to the database through a
	 * datasource object which is autowired in the applicationContext xml file
	 * 
	 * @throws SQLException
	 *             Signals that a database error has been reached unexpectedly
	 */
	@Test
	public void selfCheck() throws SQLException {
		ds.getConnection();
	}

	/**
	 * This is the test method to read all identities from the target path. This
	 * method overrides the readAll method in the data access object interface.
	 */
	@Test
	public void readAll() {
		Session session = factory.openSession();
		@SuppressWarnings("unchecked")
		List<Identity> idList = session.createQuery("from Identity").list();
		session.close();
		System.out.println(idList);
	}

	/**
	 * This is the test method to write an identity to the target path. This
	 * method overrides the write method in the data access object interface.
	 */
	@Test
	public void write() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		Identity identity = new Identity(0, "thomas", "broussard",
				"tbr@tbr.com", null);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date birthDate = null;
		try {
			birthDate = formatter.parse("2015/05/05");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		identity.setBirthDate(birthDate);

		session.saveOrUpdate(identity);
		tx.commit();
		session.close();
	}

	/**
	 * This is the test method to search for an identity from the target path
	 * whose first name, last name and email matches the user input. This method
	 * overrides the search method in the data access object interface.
	 */
	@Test
	public void search() {

		Session session = factory.openSession();
		Identity identity = new Identity(1, "thomas", "broussard",
				"tbr@tbr.com", null);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date birthDate = null;
		try {
			birthDate = formatter.parse("2015/05/05");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		identity.setBirthDate(birthDate);
		@SuppressWarnings("unchecked")
		List<Identity> searchedList = session
				.createQuery(
						"from Identity as i where i.firstName = :fname and i.lastName = :lname and i.email = :email")
				.setParameter("fname", identity.getFirstName())
				.setParameter("lname", identity.getLastName())
				.setParameter("email", identity.getEmail()).list();
		session.close();
		System.out.println(searchedList);
	}

	/**
	 * This is the test method that searches for an identity from the target
	 * path whose id matches the user input and updates it in the target path.
	 * This method overrides the update method in the data access object
	 * interface.
	 */
	@Test
	public void update() {
		Session session = factory.openSession();
		Identity identity = new Identity(1, "quentin", "decayeux", "qd@qd.com",
				null);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date birthDate = null;
		try {
			birthDate = formatter.parse("2015/05/05");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		identity.setBirthDate(birthDate);
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
	 * This is the test method that searches for an identity from the target
	 * path whose id matches the user input and deletes it from the target path.
	 * This method overrides the delete method in the data access object
	 * interface.
	 */
	@Test
	public void delete() {
		Session session = factory.openSession();
		Identity identity = new Identity(1, "thomas", "broussard",
				"tbr@tbr.com", null);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date birthDate = null;
		try {
			birthDate = formatter.parse("2015/05/05");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		identity.setBirthDate(birthDate);
		Query query = session.createQuery("delete Identity where id = :id")
				.setParameter("id", identity.getId());
		query.executeUpdate();
		session.close();
	}

}
