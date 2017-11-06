package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.persistence.impl.Persistent;
import edu.uga.cs.rentaride.RARException;

//Getters and Setters for Rent A Ride Params 

public class RentARideParamsImpl 
	extends Persistent
	implements RentARideParams 
{

	private int membershipPrice;
	private int lateFee;
	
	public RentARideParamsImpl() {
		this.membershipPrice = 0;
		this.lateFee = 0;
	}

	public RentARideParamsImpl(int membershipPrice, int lateFee) {
		this.membershipPrice = membershipPrice;
		this.lateFee = lateFee;
	}
	
	@Override
	public int getMembershipPrice() {
		return this.membershipPrice;
	}

	@Override
	public void setMembershipPrice(int membershipPrice) throws RARException {
		this.membershipPrice = membershipPrice;
	}

	@Override
	public int getLateFee() {
		return this.lateFee;
	}

	@Override
	public void setLateFee(int lateFee) throws RARException {
		this.lateFee = lateFee;
	}
}