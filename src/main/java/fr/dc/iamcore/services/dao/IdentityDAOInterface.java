/**
 * 
 */
package fr.dc.iamcore.services.dao;

import java.util.List;

import fr.dc.iamcore.datamodel.Identity;

/**
 * This is an interface of the Data Access Object which links the main class to
 * the Data Access Object classes used to represent the Identity
 * 
 * @author DEEP
 */
public interface IdentityDAOInterface {

	/**
	 * This method is used to read all identities from the target path
	 * <p>
	 * Return type: <b>List</b>
	 * <p>
	 * 
	 * @return The list containing the identities read from the target path
	 */
	public List<Identity> readAll();

	/**
	 * This method is used to write an identity to the target path
	 * <p>
	 * Return type: <b>void</b>
	 * <p>
	 * 
	 * @param identity
	 *            The identity to write to the target path
	 */
	public void write(Identity identity);

	/**
	 * This method is used to search for an identity from the target path whose
	 * first name, last name and email matches the user input
	 * <p>
	 * Return type: <b>List</b>
	 * <p>
	 * 
	 * @param identity
	 *            The identity whose id is to be searched for in the target path
	 * 
	 * @return The list containing the identities in the target path which match
	 *         the first name, last name and email entered by the user input
	 */
	public List<Identity> search(Identity identity);

	/**
	 * This method searches for an identity from the target path whose id
	 * matches the user input and updates it in the target path
	 * <p>
	 * Return type: <b>void</b>
	 * <p>
	 * 
	 * @param identity
	 *            The identity whose id is to be searched for and updated in the
	 *            target path
	 */
	public void update(Identity identity);

	/**
	 * This method searches for an identity from the target path whose id
	 * matches the user input and deletes it from the target path
	 * <p>
	 * Return type: <b>void</b>
	 * <p>
	 * 
	 * @param identity
	 *            The identity whose id is to be searched for and deleted from
	 *            the target path
	 */
	public void delete(Identity identity);

}