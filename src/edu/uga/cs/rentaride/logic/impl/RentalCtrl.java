package edu.uga.cs.rentaride.logic.impl;

import java.util.List;

import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;


public class RentalCtrl {
	
	private ObjectLayer objectLayer = null;
	private Rental modelRental = null;
	private Rental rental = null;
	private List<Rental> rentals = null;
	
	public RentalCtrl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
	public List<Rental> findRentals( int id ) throws RARException{
		modelRental = objectLayer.createRental();
		if(id < 0)
			return objectLayer.findRental(null);
		
		modelRental.setId(id);
		return objectLayer.findRental(modelRental);
	}
	
}
