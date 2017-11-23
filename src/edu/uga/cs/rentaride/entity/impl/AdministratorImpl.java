package edu.uga.cs.rentaride.entity.impl;



import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

import java.util.Date;


// Getters and Setters for Admin 

public class AdministratorImpl 
	extends Persistent
	implements Administrator 
{
	private String firstName; 
	private String lastName; 
	private String userName; 
	private String password; 
	private String email;
	private String address; 
	private Date createdDate;
	private UserStatus userStatus;
	
	public AdministratorImpl(){
		super( -1 );
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.email =  null;
		this.password =  null;
		this.address =  null;
		this.createdDate = null;
		this.userStatus = null;
	}
	
	public AdministratorImpl(String firstName, String lastName, String userName, String password, String email,
			String address, Date createdDate){
		super( -1 );
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email =  email;
		this.password =  password;
		this.createdDate = createdDate;
		this.address =  address;
		this.userStatus = UserStatus.ACTIVE;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public void setCreateDate(Date createDate) {
		this.createdDate = createDate;
	}

	@Override
	public String toString() {
		return "AdministratorImpl [adminId=" + this.getId() + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", email=" + email + ", address=" + address + ", createdDate="
				+ createdDate + ", userStatus=" + userStatus + "]";
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

	@Override
	public Date getMemberUntil() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMemberUntil(Date memberUntil) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLicenseState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLicenseState(String licenseState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLicenseNum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLicenseNum(String licenseNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCcNum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCcNum(String ccNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getCcExp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCcExp(Date ccExp) {
		// TODO Auto-generated method stub
		
	}


}