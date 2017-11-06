package edu.uga.cs.rentaride.entity.impl;



import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;

//Getters and Setters for Comment 

public class CommentImpl
	extends Persistent
	implements Comment 
{

	private String text;
	private Date date;
	private Rental rental;
	
	public CommentImpl(){
		super( -1 );
		this.date = null;
		this.rental = null;
		this.text = null;
	}
	
	public CommentImpl(String text, Date date, Rental rental){
		super( -1 );
		this.date = date;
		this.rental = rental;
		this.text = text;
	}
	
	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Rental getRental() {
		return rental;
	}

	@Override
	public void setRental(Rental rental) throws RARException {
		this.rental = rental;
		
	}

	@Override
	public Customer getCustomer() {
		return this.getCustomer();
	}

	@Override
	public String toString() {
		return "CommentImpl [text=" + text + ", date=" + date + ", rentalId=" + rental.getId() + "]";
	}
	
	
}