package edu.uga.cs.rentaride.test.object;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.object.impl.ObjectLayerImpl;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.DbUtils;
import edu.uga.cs.rentaride.persistence.impl.PersistenceLayerImpl;

public class RentARideTester
{
    public static void main(String[] args)
    {
         Connection  con = null;
         ObjectLayer objectLayer = null;
         PersistenceLayer persistence = null;

         Customer  		adminWayne;
         Customer  		adminRafael;
         Customer  		adminKishan;
         Customer  		adminAlex;
         
         Customer       customerWayne;
         Customer       customerRafael;
         Customer       customerKishan;
         Customer       customerAlex;
         
         VehicleType    truckVehicleType;
         VehicleType	convertibleVehicleType;
         
         String			truckPath = "cars/Truck.png";
         String			convertiblePath = "cars/Sedan.png";
         String			minivanPath = "cars/Minivan.png";
         String			suvPath = "cars/Suv.png";
         
         List<VehicleType> vehicleTypes;
         
         HourlyPrice	truckHourlyPrice1;
         HourlyPrice	truckHourlyPrice2;
         HourlyPrice	truckHourlyPrice3;

         HourlyPrice	convertibleHourlyPrice1;
         HourlyPrice	convertibleHourlyPrice2;
         HourlyPrice	convertibleHourlyPrice3;
         
         RentalLocation	rentalLocationAtlanta;
         RentalLocation	rentalLocationAthens;
         
         Vehicle 		truck1;
         Vehicle		truck2;
         Vehicle		convertible1;
         Vehicle		convertible2;
         
         Reservation 	reservationWayne1;
         Reservation 	reservationRafael1;
         Reservation 	reservationKishan1;
         Reservation 	reservationAlex1;

         Rental			rentalWayne1;
         Rental			rentalRafael1;
         
         Comment		commentWayne1;
         Comment		commentRafael1;
         
         RentARideParams params;
         
         // get a database connection
         try {
             con = DbUtils.connect();
         } 
         catch (Exception seq) {
             System.err.println( "RentARideTester: Unable to obtain a database connection" );
         }
         
         if( con == null ) {
             System.out.println( "RentARideTester: failed to connect to the database" );
             return;
         }
         
         // obtain a reference to the ObjectModel module      
         objectLayer = new ObjectLayerImpl();
         // obtain a reference to Persistence module and connect it to the ObjectModel        
         persistence = new PersistenceLayerImpl( con, objectLayer ); 
         // connect the ObjectModel module to the Persistence module
         objectLayer.setPersistence( persistence ); 
         
         try {
        	 Date createDate = new Date();
        	 Date dateMemberTill = new Date();
        	 Calendar cal = Calendar.getInstance();
        	 createDate = cal.getTime();
             cal.add(Calendar.MONTH, 6);
             dateMemberTill = cal.getTime();
        	 
             Date serviceDate = new Date();
             
        	 Date ccExp = new Date();
        	 Date dateReservation1 = new Date();
        	 Date dateReservation2 = new Date();
        	 Date dateReservation3 = new Date();
        	 Date dateReservation4 = new Date();
        	 DateFormat df1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        	 DateFormat ccFormat = new SimpleDateFormat( "MM-yy" );
        	 ccExp = ccFormat.parse( "11-19" );
        	 
        	 dateReservation1 = df1.parse( "2017-11-28 12:00:00" );
        	 dateReservation2 = df1.parse( "2017-11-28 13:15:45" );
        	 dateReservation3 = df1.parse( "2017-11-28 14:30:30" );
        	 dateReservation4 = df1.parse( "2017-11-28 14:45:15" );
        	 
        	 Date rentalPickup1 = new Date();
        	 Date rentalPickup2 = new Date();
        	 DateFormat df2 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        	 rentalPickup1 = df2.parse( "2017-12-20 12:00:00" );
        	 rentalPickup2 = df2.parse( "2017-12-22 13:15:45" );
        	 
        	 Date commentDate1 = new Date();
        	 Date commentDate2 = new Date();
        	 commentDate1 = df2.parse( "2017-10-21 15:00:00 " );
        	 commentDate2 = df2.parse( "2017-10-26 06:00:00 " );
        	 
        	 int lengthReservation1 = 24;
        	 int lengthReservation2 = 48;
        	 int lengthReservation3 = 72;
        	 
        	 int hourRange1 = 24;
        	 int hourRange2 = 48;
        	 int hourRange3 = 72;

        	 // DELETE everything
        	 objectLayer.deleteEverything();

             
             // 4 ADMINS
        	 //
             adminWayne = objectLayer.createCustomer("Wayne", "Kung", "wayneadmin@uga.edu", "w", "wayneadmin@uga.edu", "000 Hello St., Small Town, GA. 30129", createDate, dateMemberTill, "GA", "111111111", "1111111111111111", ccExp);
             adminWayne.setIsAdmin(true);
             persistence.storeCustomer(adminWayne);
             
             adminRafael = objectLayer.createCustomer("Rafael", "Caraballo", "rbbadmin@uga.edu", "r", "rbbadmin@uga.edu", "111 Goodbye St., Big Town, GA. 30129", createDate, dateMemberTill, "GA", "222222222", "2222222222222222", ccExp);
             adminRafael.setIsAdmin(true);
             persistence.storeCustomer(adminRafael);
             
             adminKishan = objectLayer.createCustomer("Kishan", "Patel", "kp021995admin@uga.edu", "k", "kp021995admin@uga.edu", "000 Hello St., Small Town, GA. 30129", createDate, dateMemberTill, "GA", "333333333", "3333333333333333", ccExp);
             adminKishan.setIsAdmin(true);
             persistence.storeCustomer(adminKishan);
             
             adminAlex = objectLayer.createCustomer( "Alex", "White", "alexadmin@uga.edu", "a", "alexadmin@uga.edu", "133 Maple St., Big Town, AZ. 87888", createDate, dateMemberTill, "GA", "444444444", "4444444444444444", ccExp );
             adminAlex.setIsAdmin(true);
             persistence.storeCustomer(adminAlex);
        	 
             // 4 CUSTOMERS
             //
             customerWayne = objectLayer.createCustomer("Wayne", "Kung", "waynecustomer@uga.edu", "w", "waynecustomer@uga.edu", "000 Hello St., Small Town, GA. 30129", createDate, dateMemberTill, "GA", "999999999", "999999999", ccExp);
             persistence.storeCustomer(customerWayne);
             
             customerRafael = objectLayer.createCustomer("Rafael", "Caraballo", "rbbcustomer@uga.edu", "r", "rbbcustomer@uga.edu", "111 Goodbye St., Big Town, GA. 30129", createDate, dateMemberTill, "GA", "888888888", "888888888", ccExp);
             persistence.storeCustomer(customerRafael);
             
             customerKishan = objectLayer.createCustomer("Kishan", "Patel", "kp021995customer@uga.edu", "k", "kp021995customer@uga.edu", "000 Hello St., Small Town, GA. 30129", createDate, dateMemberTill, "GA", "777777777", "777777777", ccExp);
             persistence.storeCustomer(customerKishan);
             
             customerAlex = objectLayer.createCustomer( "Alex", "White", "alexcustomer@uga.edu", "a", "alexcustomer@uga.edu", "133 Maple St., Big Town, AZ. 87888", createDate, dateMemberTill, "GA", "66666666", "66666666", ccExp );
             persistence.storeCustomer(customerAlex);
             
        	 // 2 LOCATIONS
             rentalLocationAtlanta = objectLayer.createRentalLocation("Atlanta", "999 Broad St.", "Atlanta", "GA", "30301", "city/Atlanta.png", 500);
             persistence.storeRentalLocation( rentalLocationAtlanta );
             rentalLocationAthens = objectLayer.createRentalLocation("Athens", "123 Broad St.", "Athens", "GA", "30605", "city/Athens.png", 250);
             persistence.storeRentalLocation( rentalLocationAthens );
        	 
        	 // 2 VEHICLE_TYPES
             truckVehicleType = objectLayer.createVehicleType("truck");
             persistence.storeVehicleType( truckVehicleType );
             vehicleTypes = objectLayer.findVehicleType(truckVehicleType);
             truckVehicleType = vehicleTypes.get(0);
             objectLayer.storeTypePath(truckVehicleType, truckPath);
             
             convertibleVehicleType = objectLayer.createVehicleType("convertible");
             persistence.storeVehicleType( convertibleVehicleType );
             vehicleTypes = objectLayer.findVehicleType(convertibleVehicleType);
             convertibleVehicleType = vehicleTypes.get(0);
             objectLayer.storeTypePath(convertibleVehicleType, convertiblePath);
        	 
             // 6 HOURLY PRICES
             truckHourlyPrice1 = objectLayer.createHourlyPrice(hourRange1, 2, truckVehicleType);
             persistence.storeHourlyPrice( truckHourlyPrice1 );
             truckHourlyPrice2 = objectLayer.createHourlyPrice(hourRange2, 3, truckVehicleType);
             persistence.storeHourlyPrice( truckHourlyPrice2 );
             truckHourlyPrice3 = objectLayer.createHourlyPrice(hourRange3, 4, truckVehicleType);
             persistence.storeHourlyPrice( truckHourlyPrice3 );
             
             convertibleHourlyPrice1 = objectLayer.createHourlyPrice(hourRange1, 3, convertibleVehicleType);
             persistence.storeHourlyPrice( convertibleHourlyPrice1 );
             convertibleHourlyPrice2 = objectLayer.createHourlyPrice(hourRange2, 4, convertibleVehicleType);
             persistence.storeHourlyPrice( convertibleHourlyPrice2 );
             convertibleHourlyPrice3 = objectLayer.createHourlyPrice(hourRange3, 5, convertibleVehicleType);
             persistence.storeHourlyPrice( convertibleHourlyPrice3 );
        	 
        	 // 4 VEHICLES
             truck1 = objectLayer.createVehicle("Chevrolet", "Avalanche", 2013, "111111111", 20000, serviceDate, truckVehicleType, rentalLocationAtlanta, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
             persistence.storeVehicle( truck1 );
             
             truck2 = objectLayer.createVehicle("Toyota", "Tacoma", 2017, "222222222", 1000, serviceDate, truckVehicleType, rentalLocationAthens, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
             persistence.storeVehicle( truck2) ;
        	 
             convertible1 = objectLayer.createVehicle("Honda", "Del Sol", 1997, "333333333", 120000, serviceDate, convertibleVehicleType, rentalLocationAtlanta, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
             persistence.storeVehicle( convertible1 );
        	 
             convertible2 = objectLayer.createVehicle("Ford", "Mustang", 2017, "444444444", 2000, serviceDate, convertibleVehicleType, rentalLocationAthens, VehicleCondition.GOOD, VehicleStatus.INLOCATION);
             persistence.storeVehicle( convertible2 );
        	 
        	 // 4 RESERVATIONS
             reservationWayne1 = objectLayer.createReservation(dateReservation1, lengthReservation1, truckVehicleType, rentalLocationAtlanta, customerWayne);
             persistence.storeReservation(reservationWayne1);
             persistence.storeCharges(reservationWayne1, null, true);
             
             reservationRafael1 = objectLayer.createReservation(dateReservation2, lengthReservation2, convertibleVehicleType, rentalLocationAthens, customerRafael);
             persistence.storeReservation(reservationRafael1);
             persistence.storeCharges(reservationRafael1, null, true);

             reservationKishan1 = objectLayer.createReservation(dateReservation3, lengthReservation3, truckVehicleType, rentalLocationAtlanta, customerKishan);
             persistence.storeReservation(reservationKishan1);
             persistence.storeCharges(reservationKishan1, null, true);
             
             reservationAlex1 = objectLayer.createReservation(dateReservation4, lengthReservation3, convertibleVehicleType, rentalLocationAthens, customerAlex);
             persistence.storeReservation(reservationAlex1);
             persistence.storeCharges(reservationAlex1, null, true);
        	 
//        	 // 2 RENTALS
//             //
//             rentalWayne1 = objectLayer.createRental(dateReservation1, reservationWayne1, truck1);
//             truck1.setStatus(VehicleStatus.INRENTAL);
//             persistence.storeVehicle(truck1);
//             persistence.storeRental(rentalWayne1);
//             
//             rentalRafael1 = objectLayer.createRental(dateReservation2, reservationRafael1, convertible1);
//             convertible1.setStatus(VehicleStatus.INRENTAL);
//             persistence.storeVehicle(convertible1);
//             persistence.storeRental(rentalRafael1);
//             
//             // 2 COMMENTS
//             //
//             commentWayne1 = objectLayer.createComment("great experience", commentDate1, rentalWayne1);
//             rentalWayne1.setComment(commentWayne1);
//             persistence.storeComment(commentWayne1);
//             		
//             commentRafael1 = objectLayer.createComment("horrible experience", commentDate2, rentalRafael1);
//             rentalRafael1.setComment(commentRafael1);
//             persistence.storeComment(commentRafael1);
             
             // PARAMS
             //
             params = objectLayer.createRentARideParams();
             params.setMembershipPrice(60);
             params.setLateFee(30);
             persistence.storeRentARideConfig(params);
             
         }
         
         catch (RARException re) {
        	 System.err.println("RARException: " + re);
        	 re.printStackTrace();
         }
         catch (Exception e) {
        	 System.err.println("Exception: " + e);
        	 e.printStackTrace();
         }
         finally {
        	 // close connection
        	 try {
        		 con.close();
                 System.out.println( "\n\nRentARideTester: Connection closed successfully.\n\n" );
        	 }
        	 catch( Exception e ) {
                 System.err.println( "Exception: " + e );
             }
         }
    }
}
