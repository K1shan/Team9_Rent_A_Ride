package edu.uga.cs.rentaride.logic.impl;

import java.util.List;

import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;


public class VehicleTypeCtrl {
	
	private ObjectLayer objectLayer = null;
	private VehicleType modelVehicleType = null;
	private VehicleType vehicleType = null;
	private List<VehicleType> vehicleTypes = null;
	
	public VehicleTypeCtrl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
	public List<VehicleType> findVehicleTypes( int typeId ) throws RARException{
		modelVehicleType = objectLayer.createVehicleType();
		if(typeId < 0)
			return objectLayer.findVehicleType(null);
		
		modelVehicleType.setId(typeId);
		return objectLayer.findVehicleType(modelVehicleType);
	}
	
	public void createType(String name) throws RARException{
		
		// check if type already exists
		//
		modelVehicleType = objectLayer.createVehicleType();
		modelVehicleType.setName(name);
		vehicleTypes = objectLayer.findVehicleType(modelVehicleType);
		if(vehicleTypes.size() > 0) {
			vehicleType = vehicleTypes.get(0);
		}
		if(vehicleType != null) {
			throw new RARException("A vehicle type with this name already exists");
		}
		
		vehicleType = objectLayer.createVehicleType(name);
		objectLayer.storeVehicleType(modelVehicleType);		
	}
	
	public void updateVehicleType (int id, String name) throws RARException{
		long vehicleTypeId = 0;
		
		modelVehicleType = objectLayer.createVehicleType();
		modelVehicleType.setId(id);
		vehicleTypes = objectLayer.findVehicleType(modelVehicleType);
		if(vehicleTypes.size() > 0){
			vehicleType = vehicleTypes.get(0);
			vehicleTypeId = vehicleType.getId();
		}
		if(vehicleType == null) {
			throw new RARException( "A location with this name does not exist" );
		}

		vehicleType = null;
		vehicleType = objectLayer.createVehicleType(name);
		vehicleType.setId(vehicleTypeId);
		objectLayer.storeVehicleType(vehicleType);
	}
	
public void deleteVehicleType(int id) throws RARException {
		
		// check if vehicle type already exists
		//

		modelVehicleType = objectLayer.createVehicleType();
		modelVehicleType.setId(id);
		vehicleTypes = objectLayer.findVehicleType(modelVehicleType);
		if(vehicleTypes.size() > 0){
			vehicleType = vehicleTypes.get(0);
		}
		
		// check if vehicle type found
		//
		if(vehicleType == null)
			throw new RARException( "A vehicle type with this id does not exist." );
	
		objectLayer.deleteVehicleType(vehicleType);
	}
}

