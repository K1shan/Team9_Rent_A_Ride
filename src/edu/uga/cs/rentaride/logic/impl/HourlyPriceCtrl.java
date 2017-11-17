package edu.uga.cs.rentaride.logic.impl;

import java.util.List;

import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;


public class HourlyPriceCtrl {
	
	private ObjectLayer objectLayer = null;
	
	public HourlyPriceCtrl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
	public List<HourlyPrice> findHourlyPrices( int id ) throws RARException{
    	HourlyPrice modelPrice = objectLayer.createHourlyPrice();
		if(id < 0)
			return objectLayer.findHourlyPrice(null);
		
		modelPrice.setId(id);
		return objectLayer.findHourlyPrice(modelPrice);
	}
	
	public void createHourlyPrice (int vehicleTypeId, int maxHrs, int price) throws RARException {
		
		// check if type already exists
		//
		VehicleType modelVehicleType = objectLayer.createVehicleType();
		modelVehicleType.setId(vehicleTypeId);
		List<VehicleType> vehicleTypes = objectLayer.findVehicleType(modelVehicleType);
		VehicleType vehicleType = null;
		if(vehicleTypes.size() > 0){
			vehicleType = vehicleTypes.get(0);
			if(vehicleType == null)
				throw new RARException( "This vehicle type does not exist" );
		}
		
		// check if hourly price already exists
		//
		HourlyPrice modelPrice = objectLayer.createHourlyPrice();
		modelPrice.setMaxHours(maxHrs);
		modelPrice.setPrice(price);
		modelPrice.setVehicleType(vehicleType);
		List<HourlyPrice> hourlyPrices = objectLayer.findHourlyPrice(modelPrice);
		HourlyPrice hourlyPrice = null;
		if(hourlyPrices.size() > 0){
			hourlyPrice = hourlyPrices.get(0);
			if(hourlyPrice != null)
				throw new RARException( "An hourly price with these parameters already exist" );
		}
		// store hourly price
		//
		hourlyPrice = objectLayer.createHourlyPrice(maxHrs, price, vehicleType);
		objectLayer.storeHourlyPrice(hourlyPrice);
	}

	public void updateHourlyPrice(int hourlyPriceId, int vehicleTypeId, int maxHrs, int price) {
		// TODO Auto-generated method stub
		long typeId = 0;
		
		HourlyPrice modelPrice = objectLayer.createHourlyPrice();
		modelPrice.setId(hourlyPriceId);
		List<HourlyPrice> hourlyPrices = objectLayer.findHourlyPrice(modelPrice);
		HourlyPrice hourlyPrice = null;
		if(hourlyPrices.size() > 0){
			hourlyPrice = hourlyPrices.get(0);
			typeId = hourlyPrice.getId();
		}
		if(hourlyPrice == null) {
			throw new RARException( "A location with this name does not exist" );
		}

		hourlyPrice = null;
		hourlyPrice = objectLayer.createHourlyPrice(vehicleTypeId, maxHrs, price);
		hourlyPrice.setId(typeId);
		objectLayer.storeHourlyPrice(hourlyPrice);
	}
	
	public void deleteHourlyPrice(int id) throws RARException {
		
		// check if hourly price already exists
		//
		HourlyPrice modelHourlyPrice = objectLayer.createHourlyPrice();
		modelHourlyPrice.setId(id);
		List<HourlyPrice> hourlyPrices = objectLayer.findHourlyPrice(modelHourlyPrice);
		HourlyPrice hourlyPrice = null;
		if(hourlyPrices.size() > 0){
			hourlyPrice = hourlyPrices.get(0);
		}
		
		// check if hourly price found
		//
		if(hourlyPrice == null)
			throw new RARException( "A hourly price with this id does not exist." );
	
		objectLayer.deleteHourlyPrice(hourlyPrice);
	}
}