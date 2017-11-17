package edu.uga.cs.rentaride.logic.impl;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;


public class CommentCtrl {
	
	private ObjectLayer objectLayer = null;
	private Comment modelComment = null;
	private Comment comment = null;
	private List<Comment> comments = null;
	
	public CommentCtrl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
	public List<Comment> findComments( int id ) throws RARException{
		modelComment = objectLayer.createComment();
		if(id < 0)
			return objectLayer.findComment(null);
		
		modelComment.setId(id);
		return objectLayer.findComment(modelComment);
	}
	
	public void createComment(int rentalId, String text, Date commentDate) throws RARException {
		
		// check if rental already exists
		//
		Rental modelRental = objectLayer.createRental();
		modelRental.setId(rentalId);
		List<Rental> rentals = objectLayer.findRental(modelRental);
		Rental rental = null;
		if(rentals.size() > 0) 
			rental = rentals.get(0);
		
		// check if rental is found
		//
		if(rental == null) 
			throw new RARException("A rental with this id does not exist exist");
		
		comment = objectLayer.createComment(text, commentDate, rental);
		objectLayer.storeComment(comment);
	}
}