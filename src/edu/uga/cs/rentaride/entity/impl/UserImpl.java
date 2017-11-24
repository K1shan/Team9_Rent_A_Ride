package edu.uga.cs.rentaride.entity.impl;



import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;


//Getters and Setters for User 

public class UserImpl extends Persistent implements User {

		private boolean isAdmin;
		private String firstName;
		private String lastName;
		private String userName;
		private String password;
		private String email;
		private String address;
		private Date createdDate;
		private Date memberUntil;
		private String licenseState;
		private String licenseNum;
		private String ccNum;
		private Date ccExp;
		private UserStatus userStatus;
	
	public UserImpl() {
		
		super(-1);
		this.firstName = null;
		this.lastName = null;
		this.userName = null;
		this.email = null;
		this.password = null;
		this.createdDate = null;
		this.address = null;
		this.memberUntil = null;
		this.licenseState = null;
		this.licenseNum = null;
		this.ccNum = null;
		this.ccExp = null;
		this.userStatus = null;
		this.isAdmin = false;
	}
	
	public UserImpl(String firstName, String lastName, String userName, String email, String password, Date createdDate, String address, UserStatus userStatus) {
		super(-1);
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.createdDate = createdDate;
		this.address = address;
		this.userStatus = userStatus;
		this.memberUntil = null;
		this.licenseState = null;
		this.licenseNum = null;
		this.ccNum = null;
		this.ccExp = null;
		this.isAdmin = false;
	}
	
	public boolean getIsAdmin(){
		return isAdmin;
	}
	
	public void setIsAdmin(boolean isAdmin){
		this.isAdmin = isAdmin;
	}
	
	@Override
	public String getFirstName() {

		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		
		this.firstName = firstName;
		
	}

	@Override
	public String getLastName() {

		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		
		this.lastName = lastName;
	}

	@Override
	public String getUserName() {

		return userName;
	}

	@Override
	public void setUserName(String userName) throws RARException {
		
		this.userName = userName;
	}

	@Override
	public String getEmail() {

		return email;
	}

	@Override
	public void setEmail(String email) {

		this.email = email;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public void setPassword(String password) {

		this.password = password;
	}

	@Override
	public Date getCreatedDate() {

		return createdDate;
	}

	@Override
	public void setCreateDate(Date createdDate) {
		
		this.createdDate = createdDate;
	}

	@Override
	public String getAddress() {

		return address;
	}

	@Override
	public void setAddress(String address) {

		this.address = address;
	}

	@Override
	public UserStatus getUserStatus() {

		return userStatus;
	}

	@Override
	public void setUserStatus(UserStatus userStatus) {

		this.userStatus = userStatus;
	}
	
	@Override
	public Date getMemberUntil(){
		return memberUntil;
	}
	
	@Override
	public void setMemberUntil(Date memberUntil) throws RARException{
		this.memberUntil = memberUntil;
	}

	@Override
	public String getLicenseState() {
		return licenseState;
	}

	@Override
	public void setLicenseState(String licenseState) {
		this.licenseState = licenseState;
	}
	
	@Override
	public String getLicenseNum() {
		return licenseNum;
	}

	@Override
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	@Override
	public String getCcNum() {
		return ccNum;
	}

	@Override
	public void setCcNum(String ccNum) {
		this.ccNum = ccNum;
	}

	@Override
	public Date getCcExp() {
		return ccExp;
	}

	@Override
	public void setCcExp(Date ccExp) {
		this.ccExp = ccExp;
	}

	@Override
	public String toString() {
		return "UserImpl ["
				+ "firstName=" + firstName 
				+ ", lastName=" + lastName 
				+ ", userName=" + userName 
				+ ", email=" + email 
				+ ", password=" + password 
				+ ", address=" + address 
				+ ", createdDate=" + createdDate
				+ ", memberUntil=" + memberUntil
				+ ", licenseState=" + licenseState
				+ ", licenseNum=" + licenseNum
				+ ", ccNum=" + ccNum
				+ ", ccExp=" + ccExp
				+ ", userStatus=" + userStatus 
				+ ", isAdmin=" + isAdmin 
				+ "]";
	}	
}