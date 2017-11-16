package edu.uga.cs.rentaride.logic.impl;

import java.util.List;

import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;


public class ReservationCtrl {
	
	private ObjectLayer objectLayer = null;
	private Reservation modelReservation = null;
	private Reservation reservation = null;
	private List<Reservation> reservations = null;
	
	public ReservationCtrl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
	public List<Reservation> findReservation( int id ) throws RARException{
		modelReservation = objectLayer.createReservation();
		if(id < 0)
			return objectLayer.findReservation(null);
		
		modelReservation.setId(id);
		return objectLayer.findReservation(modelReservation);
	}
	
}
