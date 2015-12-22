/**
 * 
 */
package fr.dc.iamcore.launcher;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.dc.iamcore.datamodel.Identity;
import fr.dc.iamcore.services.dao.IdentityDAOInterface;

/**
 * This is the main class which runs the console application
 * 
 * @author DEEP
 */
public class Main {

	/**
	 * This is an instance of the data access object interface that is autowired
	 * in the applicationContext xml file
	 */
	@Autowired
	IdentityDAOInterface dao;

	/**
	 * This is an instance of the data source that is autowired in the
	 * applicationContext xml file
	 */
	@Autowired
	DataSource ds;

	/**
	 * This is an instance of the session factory that is autowired in the
	 * applicationContext xml file
	 */
	@Autowired
	SessionFactory sf;

	/**
	 * This is the main method with parameter args which creates an application
	 * context based on the applicationContext xml file and calls the run method
	 * by creating a runner from the bean of the Main class
	 *
	 * @param args
	 *            Unused
	 * @throws ParseException
	 *             Signals that an error has been reached unexpectedly while
	 *             parsing
	 */
	public static void main(String[] args) throws ParseException {

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		Main runner = applicationContext.getBean(Main.class);
		try {
			runner.selfCheck();
			runner.run();
		} catch (Exception e) {
			System.out.println(e);
		}
		((ClassPathXmlApplicationContext) applicationContext).close();
	}

	/**
	 * This method is called by the main function to run the console application
	 * by creating a session and a transaction
	 * 
	 * @throws ParseException
	 *             Signals that an error has been reached unexpectedly while
	 *             parsing
	 */
	public void run() throws ParseException {

		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Identity identity = new Identity(0, "quentin", "broussard",
				"tbr@tbr.com", null);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date birthDate = formatter.parse("2015/05/05");
		identity.setBirthDate(birthDate);

		System.out.println(identity);

		dao.write(identity);

		session.saveOrUpdate(identity);
		tx.commit();

		System.out.println(dao.search(identity));

		Identity newIdentity = new Identity(1, "quentin", "decayeux",
				"qd@qd.com", null);
		Date newBirthDate = formatter.parse("2015/05/05");
		newIdentity.setBirthDate(newBirthDate);
		dao.update(newIdentity);

		System.out.println(dao.readAll());

		dao.delete(identity);

		System.out.println(dao.readAll());
	}

	/**
	 * This method is used to connect to the database through a datasource
	 * object which is autowired in the applicationContext xml file
	 * 
	 * @throws SQLException
	 *             Signals that a database error has been reached unexpectedly
	 */
	public void selfCheck() throws SQLException {
		ds.getConnection();
	}

}
