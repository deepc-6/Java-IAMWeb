/**
 * 
 */
package fr.dc.iamcore.datamodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This is the datamodel class to represent the Identity
 * 
 * @author DEEP
 */

@Entity
@Table(name = "IDENTITIES")
public class Identity {

	/**
	 * This is the default constructor for the class Identity with no parameters
	 */
	public Identity() {

	}

	/**
	 * This is the id for each identity in the database. It is auto generated
	 * when an identity is created.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * This is the first name of the identity in the database.
	 */
	private String firstName;

	/**
	 * This is the first name of the identity in the database.
	 */
	private String lastName;

	/**
	 * This is the email of the identity in the database. It is unique and two
	 * identities cannot have the same email.
	 */
	@Column(unique = true)
	private String email;

	/**
	 * This is the date of birth of the identity in the database.
	 */
	private Date birthDate;

	/**
	 * This is a constructor for the class Identity with parameters firstName,
	 * lastName, email, birthDate
	 * 
	 * @param id
	 *            The id of the identity. Data type: <b>int</b>
	 * @param firstName
	 *            The first name of the identity. Data type: <b>String</b>
	 * @param lastName
	 *            The last name of the identity. Data type: <b>String</b>
	 * @param email
	 *            The email of the identity. Data type: <b>String</b>
	 * @param birthDate
	 *            The birth date of the identity. Data type: <b>Date</b>
	 */

	public Identity(int id, String firstName, String lastName, String email,
			Date birthDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
	}

	/**
	 * This method returns the output to the console in the following format:
	 * <p>
	 * [id [First Name, Last Name, E-mail address, Date of birth]
	 * <p>
	 * This method overrides the {@link java.lang.Object#toString()} method.
	 * <p>
	 * 
	 * @return <b>firstName</b> The first name of the identity. Data type:
	 *         <b>String</b>
	 *         <p>
	 *         <b>lastName</b> The last name of the identity. Data type:
	 *         <b>String</b>
	 *         <p>
	 *         <b>email</b> The e-mail of the identity. Data type: <b>String</b>
	 *         <p>
	 *         <b>birthDate</b> The birth date of the identity. Data type:
	 *         <b>Date</b>
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "\n" + id + " First Name=" + firstName + ", Last Name="
				+ lastName + ", E-mail address=" + email + ", Date of birth="
				+ birthDate;
	}

	/**
	 * This method returns the first name of the identity
	 * <p>
	 * Return type: <b>String</b>
	 * 
	 * @return The first name of the identity
	 */
	public final String getFirstName() {
		return firstName;
	}

	/**
	 * This method sets the first name of the identity
	 * <p>
	 * Return type: <b>void</b>
	 * 
	 * @param firstName
	 *            The first name of the identity. Data type: <b>String</b>
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * This method returns the last name of the identity
	 * <p>
	 * Return type: <b>String</b>
	 * 
	 * @return The last name of the identity
	 */
	public final String getLastName() {
		return lastName;
	}

	/**
	 * This method sets the last name of the identity
	 * <p>
	 * Return type: <b>void</b>
	 * 
	 * @param lastName
	 *            The last name of the identity. Data type: <b>String</b>
	 */
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * This method returns the e-mail address of the identity
	 * <p>
	 * Return type: <b>String</b>
	 * 
	 * @return The email of the identity
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * This method sets the e-mail of the identity
	 * <p>
	 * Return type: <b>void</b>
	 * 
	 * @param email
	 *            The e-mail of the identity. Data type: <b>String</b>
	 */
	public final void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This method returns the birth date of the identity
	 * <p>
	 * Return type: <b>Date</b>
	 * 
	 * @return The birth date of the identity
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * This method sets the birth date of the identity
	 * <p>
	 * Return type: <b>void</b>
	 * 
	 * @param birthDate
	 *            The birth date of the identity. Data type: <b>Date</b>
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * This method returns the id of the identity
	 * <p>
	 * Return type: <b>int</b>
	 * 
	 * @return id The id of the identity.
	 */
	public int getId() {
		return id;
	}

	/**
	 * This method sets the id of the identity.
	 * <p>
	 * Return type: <b>void</b>
	 * 
	 * @param id
	 *            The id of the identity. Data type: <b>int</b>
	 */
	public void setId(int id) {
		this.id = id;
	}

}