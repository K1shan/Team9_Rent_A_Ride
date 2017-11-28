package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.rentaride.RARException;

//Getters and Setters for Customer 

public class CustomerImpl 
	extends Persistent
	implements Customer
{
	private Date createDate;
	private Date memberUntil;
	private Date cardExpiration;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;
	private String address;
	private String state;
	private String licenseNumber;
	private String cardNumber;
	private UserStatus userStatus;
	private List<Reservation> reservations;
	private List<Comment> comments;
	private List<Rental> rentals;
	
	public CustomerImpl(){
	
		
		super( -1 );
		this.createDate = null;
		this.memberUntil = null;
		this.cardExpiration = null;
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.email =  null;
		this.password =  null;
		this.address =  null;
		this.state =  null;
		this.licenseNumber = null;
		this.cardNumber = null;
		this.userStatus = null;
		this.reservations = null;
		this.comments = null;
		this.rentals = null;
		this.reservations = null;
		this.comments = null;
		this.rentals = null;
	}
	
	public CustomerImpl(String firstName, String lastName, String userName, String password, String email,
			String address, Date createDate, Date membershipExpiration, String licenseState, String licenseNumber,
			String cardNumber, Date cardExpiration){
		
		super( -1 );
		this.createDate = createDate;
		this.memberUntil = membershipExpiration;
		this.cardExpiration = cardExpiration;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email =  email;
		this.password =  password;
		this.address =  address;
		this.state =  licenseState;
		this.licenseNumber = licenseNumber;
		this.cardNumber = cardNumber;
		this.userStatus = UserStatus.ACTIVE;
		this.reservations = null;
		this.comments = null;
		this.rentals = null;
	}
  
	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public void setUserName(String userName) throws RARException {
		this.userName = userName;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Date getCreatedDate() {
		return this.createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String getAddress() {
		return this.address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public UserStatus getUserStatus() {
		return this.userStatus;
	}

	@Override
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public Date getMemberUntil() {
		return this.memberUntil;
	}

	@Override
	public void setMemberUntil(Date memberUntil) throws RARException {
		this.memberUntil = memberUntil;
	}

	@Override
	public String getLicenseState() {
		return this.state;
	}

	@Override
	public void setLicenseState(String state) {
		this.state = state;
	}

	@Override
	public String getLicenseNumber() {
		return this.licenseNumber;
	}

	@Override
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	@Override
	public String getCreditCardNumber() {
		return this.cardNumber;
	}

	@Override
	public void setCreditCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public Date getCreditCardExpiration() {
		return this.cardExpiration;
	}

	@Override
	public void setCreditCardExpiration(Date cardExpiration) {
		this.cardExpiration = cardExpiration;
	}

	@Override
	public List<Reservation> getReservations() throws RARException{
		
		// if customer has made a reservation
		//
		if(reservations == null){
			if(isPersistent() ){
				reservations = getPersistenceLayer().restoreCustomerReservation( this );
			}else{
                throw new RARException( "This Customer object is not persistent" );
			}
		}
		
		// if customer is terminated
		//
		if(userStatus.equals(UserStatus.TERMINATED)){
			int i = 0;
			if(reservations != null){
				for(Reservation reservation : reservations){
					reservation.setCancelled(true);
					getPersistenceLayer().storeCustomerReservation(this, reservation);
					reservations.set(i, reservation);
					i++;
				}
			}
		}
        return reservations;
	}

	@Override
	public List<Comment> getComments() throws RARException{
		if(comments == null){
			if(rentals != null){
				if(isPersistent()){
					Comment comment = new CommentImpl();
					for(Rental rental : rentals){
						comment = getPersistenceLayer().restoreRentalComment(rental);
						comments.add(comment);	
					}
				}else
					throw new RARException( "This Customer object is not persistent" );
			}else
				throw new RARException( "This Customer object has no rentals.");
		}
        return comments;
	}

	@Override
	public List<Rental> getRentals() throws RARException {
		if(rentals == null){
			if(reservations != null){
				if(isPersistent() ){
					Rental rental = new RentalImpl();
					for(Reservation reservation : reservations){
						rental = getPersistenceLayer().restoreRentalReservation(reservation);
						rentals.add(rental);
					}
				}else
	                throw new RARException( "This Customer object is not persistent" );
			}else
				throw new RARException( "This Customer object has no reservations." );
		}
        return rentals;
	}

	@Override
	public String toString() {
		return "CustomerImpl ["
				+ "firstName=" + firstName 
				+ ", lastName=" + lastName 
				+ ", userName=" + userName
				+ ", password=" + password 
				+ ", email=" + email 
				+ ", address=" + address 
				+ ", createDate=" + createDate 
				+ ", memberUntil=" + memberUntil 
				+ ", state=" + state
				+ ", licenseNumber=" + licenseNumber 
				+ ", cardNumber=" + cardNumber 
				+ ", cardExpiration=" + cardExpiration
				+ ", userStatus=" + userStatus
				+ "]";
	}

	@Override
	public void setIsAdmin(boolean isAdmin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsAdmin() {
		// TODO Auto-generated method stub
		return false;
	}

	
}