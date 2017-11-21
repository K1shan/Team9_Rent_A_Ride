package edu.uga.cs.rentaride.entity;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.Persistable;


/** This is the base class of all known users of the Rent-A-Ride system.
 *
 */
public interface User
    extends Persistable
{
    /** Return the user's first name.
     * @return the user's first name
     */
    public String getFirstName();
    
    /** Set the user's first name.
     * @param firstName the new first name
     */
    public void setFirstName( String firstName );
    
    /** Return the user's last name.
     * @return the user's last name
     */
    public String getLastName();
    
    /** Set the user's last name.
     * @param firstName the new last name
     */
    public void setLastName( String lastName );
    
    /** Return the user's user (login) name.
     * @return the user's user (login) name
     */
    public String getUserName();
    
    /** Set the user's user (login) name.
     * @param userName the new user (login) name
     * @throws RARException in case userName is non-unique
     */
    public void setUserName( String userName ) throws RARException;
    
    /** Return the user's email address.
     * @return the user's email address
     */
    public String getEmail();
    
    /** Set the user's email address.
     * @param email the new email address
     */
    public void setEmail( String email );
    
    /** Return the user's password.
     * @return the user's password
     */
    public String getPassword();
    
    /** Set the user's password.
     * @param password the new password
     */
    public void setPassword( String password );
    
    /** Return the user's creation date.
     * @return the user's creation date
     */
    public Date getCreatedDate();
    
    /** Set the user's creation date.
     * @param createDate the new creation date
     */
    public void setCreateDate( Date createDate );
    
    /** Return the residence address of this user.
     * @return the address of this user's residence
     */
    public String getAddress();
    
    /** Set the new residence address of this user.
     * @param address the new residence address of this user
     */
    public void setAddress( String address );
    
    /** Return the current status of this user (ACTIVE, REMOVED, or TERMINATED)
     * @return the current status of this user
     */
    public UserStatus getUserStatus();
    
    /** Set the current status of this user (must be ACTIVE, CANCELLED, or TERMINATED)
     * @param userStatus the new status of this user (must be ACTIVE, CANCELLED, or TERMINATED)
     */
    public void setUserStatus( UserStatus userStatus );

	public void setIsAdmin(boolean isAdmin);
	
	public boolean getIsAdmin();
	
	public Date getMemberUntil();
	
	public void setMemberUntil(Date memberUntil) throws RARException;
	
	public String getLicenseState();
    
    /** Set the new license state for this customer.
     * @param state the new state of this customer's license
     */
    public void setLicenseState( String licenseState );
    
    /** Return the license number of this customer.
     * @return the license number of this customer
     */
    public String getLicenseNum();
    
    /** Set the new license number of this customer.
     * @param licenseNumber the new license number of this customer
     */
    public void setLicenseNum( String licenseNum );
    
    /** Return the credit card number of this customer.
     * @return the credit card number of this customer
     */
    public String getCcNum();
    
    /** Set the new credit card number of this customer.
     * @param cardNumber the new credit card number of this customer
     */
    public void setCcNum( String ccNum );
    
    /** Return the expiration date of this customer's credit card.
     * @return the expiration date of this customer's credit card
     */
    public Date getCcExp();
    
    /** Set the new expiration date of this customer's credit card.
     * @param cardExpiration the new expiration date of this customer's credit card
     */
    public void setCcExp( Date ccExp);
}
