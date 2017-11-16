package edu.uga.cs.rentaride.logic.impl;

import java.util.List;

import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;


public class VehicleCtrl {
	
	private ObjectLayer objectLayer = null;
	private Vehicle modelVehicle = null;
	private Vehicle vehicle = null;
	private List<Vehicle> vehicles = null;
	
	public VehicleCtrl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
	public List<Vehicle> findVehicles( int id ) throws RARException{
		modelVehicle = objectLayer.createVehicle();
		if(id < 0)
			return objectLayer.findVehicle(null);
		
		modelVehicle.setId(id);
		return objectLayer.findVehicle(modelVehicle);
	}
	
}
