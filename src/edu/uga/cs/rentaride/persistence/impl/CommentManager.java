package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class CommentManager {

	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public CommentManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
    public void store( Comment comment ) throws RARException{
    	
    	//Queries
    	
    	String insertCommentQuery =
				"INSERT INTO COMMENT "
				+ "( rental_id, customer_id, text, comment_date ) "
				+ "VALUES "
				+ "( ?, ?, ?, ? )";
		
		String updateCommentQuery =
				"UPDATE COMMENT SET "
				+ "( rental_id, customer_id, text, comment_date ) "
				+ "VALUES "
				+ "( ?, ?, ?, ? )";
		
		PreparedStatement pstmt;
		int inscnt;
		long commentId;
		
		try {
			
			// check persist
			if( !comment.isPersistent() ){
				pstmt = (PreparedStatement) con.prepareStatement( insertCommentQuery );
			}else{
				pstmt = (PreparedStatement) con.prepareStatement( updateCommentQuery );
			}
			
			// update pstmt
			if( comment.getRental().getId() > 0 ){
				pstmt.setLong( 1, comment.getRental().getId() );
			}else{
				throw new RARException( "Comment.save: can't save a comment: rental undefined" );
			}
			
			if( comment.getCustomer().getId() > 0 ){
				pstmt.setLong( 2, comment.getRental().getCustomer().getId() );
			}else{
				throw new RARException( "Comment.save: can't save a comment: customer undefined" );
			}
			
			if( comment.getText() != null ){
				pstmt.setString( 3, comment.getText() );
			}else{
				throw new RARException( "Comment.save: can't save a comment: text undefined" );
			}
			
			if( comment.getDate() != null ){
				java.util.Date myDate = comment.getDate();
	    		pstmt.setTimestamp( 4, new java.sql.Timestamp(myDate.getTime()));
			}else{
				throw new RARException( "Comment.save: can't save a comment: date undefined" );
			}
			
			System.out.println("query: " + pstmt.asSql());
            inscnt = pstmt.executeUpdate();
            
            // auto_inc pk to object pk
            if ( !comment.isPersistent() ){
            	if( inscnt == 1 ){
            		String sql = "select last_insert_id()";
            		if( pstmt.execute( sql ) ){
            			ResultSet rs = pstmt.getResultSet();
            			while( rs.next() ){
            				commentId = rs.getLong( 1 );
            				if( commentId > 0 ){
            					comment.setId( commentId );
            				}
            			}
            		}
            	}
            }else{
            	if( inscnt < 1 ){
            		throw new RARException( "CommentManager.save: failed to save a comment" );
            	}
            }
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "CommentManager.store: failed to store a comment: " + e );
		}
    }
    
    public List<Comment> restore( Comment modelComment ) throws RARException{
    	
    	// Queries
    	//
    	String selectCommentQuery =
    			"SELECT "
				+ "VEHICLE_TYPE.type_id, VEHICLE_TYPE.name, "
				+ "VEHICLE.*, "
				+ "RESERVATION.*, "
				//+ "HOURLY_PRICE.*, "
				+ "LOCATION.*, "
				+ "USER.*, "
				+ "CUSTOMER.*, "
				+ "RENTAL.*, "
				+ "COMMENT.* "
				+ "FROM COMMENT "
<<<<<<< HEAD
				+ "INNER JOIN RENTAL ON COMMENT.rental_id=RENTAL.rental_id "
				+ "INNER JOIN RESERVATION ON RENTAL.reservation_id=RESERVATION.reservation_id "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=RESERVATION.type_id "
				+ "INNER JOIN VEHICLE ON VEHICLE.type_id=VEHICLE_TYPE.type_id "
				+ "INNER JOIN HOURLY_PRICE ON HOURLY_PRICE.type_id=VEHICLE_TYPE.type_id "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=VEHICLE.location_id "
				+ "INNER JOIN CUSTOMER ON CUSTOMER.customer_id=RESERVATION.customer_id "
=======
				+ "INNER JOIN RENTAL ON RENTAL.rental_id=COMMENT.rental_id "
				+ "INNER JOIN CUSTOMER ON CUSTOMER.customer_id=COMMENT.customer_id "
				+ "INNER JOIN RESERVATION ON RESERVATION.reservation_id=RENTAL.reservation_id "
				+ "INNER JOIN VEHICLE ON VEHICLE.vehicle_id=RENTAL.vehicle_id "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=VEHICLE.type_id "
				//+ "INNER JOIN HOURLY_PRICE ON HOURLY_PRICE.type_id=VEHICLE_TYPE.type_id "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=VEHICLE.location_id "
>>>>>>> master
				+ "INNER JOIN USER ON USER.user_id=CUSTOMER.user_id";
		
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<Comment> comments = new ArrayList<Comment>();
		Statement stmt = null;
		condition.setLength(0);
		query.append(selectCommentQuery);
		
		// NULL CHECK
		if( modelComment != null ){
			
			if( modelComment.getId() >= 0 ){
				query.append( " where LOCATION.location_id = " + modelComment.getId() );
			}else if( modelComment.getRental() != null ){  // name is unique
				query.append( " where LOCATION.rental='" + modelComment.getRental() + "'" );
			}else{
				if( modelComment.getText() != null ){
					condition.append( " where LOCATION.text='" + modelComment.getText() + "'" );
				}
				if( modelComment.getDate() != null ){
					condition.append( " where LOCATION.date='" + modelComment.getDate() );
				}
				if( condition.length() > 0 ){
					query.append( condition );
				}
			}
		}

		
		try {
			stmt = con.createStatement();
			System.out.println("query: " + query.toString());
			if( stmt.execute(query.toString()) ){
				ResultSet rs = stmt.getResultSet();

				// RENTAL
				int		rental_rental_id;
				int		rental_reservation_id;
				int		rental_vehicle_id;
				Date 	rental_pickupTime = null;
				Date	rental_returnTime = null;
				int 	rental_late;
				int 	rental_charges;
				
				// COMMENT
				int		comment_comment_id;
				int		comment_rental_id;
				int		comment_customer_id;
				String 	comment_text;
				Date	comment_date;
				
				// VEHICLE_TYPE
                int 	type_type_id;
                String 	type_name;
                
                // VEHICLE
                int 	vehicle_vehicle_id;
                int		vehicle_type_id;
                int 	vehicle_location_id;
				String 	vehicle_make;
				String 	vehicle_model;
				int 	vehicle_year;
				int 	vehicle_mileage;
				String 	vehicle_tag;
				Date 	vehicle_service_date;
				int 	vehicle_status = 0;
				int 	vehicle_cond = 0;
                
				// RESERVATION
				int 	reservation_reservation_id;
				int 	reservation_location_id;
				int		reservation_type_id;
				int 	reservation_customer_id;
				Date 	reservation_pickupTime;
				int 	reservation_rentalLength;
				int		reservation_cancelled;
				
				// HOURLY_PRICE
				int		hourly_hourly_id;
				int		hourly_type_id;
				int		hourly_max_hrs;
				int		hourly_price;
				
				// LOCATION
				int 	location_location_id;
				String 	location_name;
				String 	location_addr;
				String 	location_addr_city;
				String 	location_addr_state;
				String 	location_addr_zip;
				String 	location_image_path;
				int 	location_capacity;
				
				// USER
				int		user_user_id;
				String 	user_fname;
	            String 	user_lname;
	            String 	user_uname;
	            String 	user_pword;
	            String 	user_email;
	            String 	user_address;
	            Date 	user_createDate;
	            
	            // CUSTOMER
	            int		customer_customer_id;
	            int 	customer_user_id;
	            Date 	customer_memberUntil;
	            String 	customer_licState;
	            String 	customer_licNum;
	            String 	customer_ccNum;
	            Date 	customer_ccExp;
	            int		customer_status = 0;
	            
	            // OBJECTS
	            Vehicle vehicle = null;
                Reservation reservation = null;
                Customer customer = null;
                RentalLocation rentalLocation = null;
                VehicleType vehicleType = null;
                Comment comment = null;
                Rental rental = null;
                VehicleStatus vehicleStatus = VehicleStatus.INLOCATION;
				VehicleCondition vehicleCondition = VehicleCondition.GOOD;
				UserStatus userStatus = UserStatus.ACTIVE;
				
				while( rs.next() ){
					
					// VEHICLE_TYPE
	                type_type_id = rs.getInt(1);
	                type_name = rs.getString(2);
	                
	                // VEHICLE
	                vehicle_vehicle_id 	= rs.getInt(3);
					vehicle_type_id		= rs.getInt(4);
					vehicle_location_id = rs.getInt(5);
					vehicle_make 		= rs.getString(6);
					vehicle_model 		= rs.getString(7);
					vehicle_year 		= rs.getInt(8);
					vehicle_mileage 	= rs.getInt(9);
					vehicle_tag 		= rs.getString(10);
					vehicle_service_date= rs.getDate(11);
					vehicle_status 		= rs.getInt(12);
					if(vehicle_status == 0)
						vehicleStatus	= VehicleStatus.INLOCATION;
					else if(vehicle_status == 1)
						vehicleStatus 	= VehicleStatus.INRENTAL;
					vehicle_cond = rs.getInt(13);
					if(vehicle_cond == 0)
						vehicleCondition= VehicleCondition.GOOD;
					else if(vehicle_cond == 1)
						vehicleCondition= VehicleCondition.NEEDSMAINTENANCE;
					
					// RESERVATION
					reservation_reservation_id 	= rs.getInt(14);
					reservation_location_id		= rs.getInt(15);
					reservation_type_id 		= rs.getInt(16);
					reservation_customer_id		= rs.getInt(17);
					reservation_pickupTime 		= rs.getDate(18);
					reservation_rentalLength 	= rs.getInt(19);
					reservation_cancelled		= rs.getInt(20);
					
//					// HOURLY_PRICE
//					hourly_hourly_id			= rs.getInt(21);
//					hourly_type_id				= rs.getInt(22);
//					hourly_max_hrs 				= rs.getInt(23);
//					hourly_price 				= rs.getInt(24);
					
					// LOCATION
					location_location_id		= rs.getInt(21);
					location_name 				= rs.getString(22);
					location_addr	 			= rs.getString(23);
					location_addr_city			= rs.getString(24);
					location_addr_state			= rs.getString(25);
					location_addr_zip			= rs.getString(26);
					location_image_path			= rs.getString(27);
					location_capacity 			= rs.getInt(28);

					// USER
					user_user_id 	= rs.getInt(29);
	           	 	user_fname 		= rs.getString(30);
	           	 	user_lname 		= rs.getString(31);
	           	 	user_uname 		= rs.getString(32);
	           	 	user_pword 		= rs.getString(33);
	           	 	user_email 		= rs.getString(34);
	           	 	user_address 	= rs.getString(35);
	           	 	user_createDate = rs.getDate(36);
	           	 	
	           	 	// CUSTOMER
	                customer_customer_id= rs.getInt(37);
	                customer_user_id 	= rs.getInt(38);
	                customer_memberUntil= rs.getDate(39);
	                customer_licState 	= rs.getString(40);
	                customer_licNum 	= rs.getString(41);
	                customer_ccNum 		= rs.getString(42);
	                customer_ccExp 		= rs.getDate(43);
	                customer_status 	= rs.getInt(44);
	                if(customer_status == 0)
	                	userStatus 		= UserStatus.ACTIVE;
	                else if(customer_status == 1)
	                	userStatus 		= UserStatus.CANCELLED;
	                else if(customer_status == 2)
	                	userStatus 		= UserStatus.TERMINATED;
	                
	                // RENTAL
					rental_rental_id = rs.getInt(45);
					rental_reservation_id = rs.getInt(46);
					rental_vehicle_id = rs.getInt(47);
					rental_pickupTime = rs.getDate(48);
					rental_returnTime = rs.getDate(49);
					rental_late = rs.getInt(50);
					rental_charges = rs.getInt(51);
	                
	                // COMMENT
					comment_comment_id = rs.getInt(52);
					comment_rental_id = rs.getInt(53);
					comment_customer_id = rs.getInt(54);
					comment_text = rs.getString(55);
					comment_date = rs.getDate(56);
					
					// VEHICLE_TYPE
                    vehicleType = objectLayer.createVehicleType(type_name);
                    vehicleType.setId( type_type_id );
                    
                    // LOCATION
                    rentalLocation = objectLayer.createRentalLocation(location_name, location_addr, location_addr_city, location_addr_state, location_addr_zip, location_image_path, location_capacity);
                    rentalLocation.setId(location_location_id);
                    
                    // VEHICLE
					vehicle = objectLayer.createVehicle(vehicle_make, vehicle_model, vehicle_year, vehicle_tag, vehicle_mileage, vehicle_service_date, vehicleType, rentalLocation, vehicleCondition, vehicleStatus);
					vehicle.setId(vehicle_vehicle_id);
                    //vehicleType.getVehicles();
                    
                    // CUSTOMER
                    customer = objectLayer.createCustomer(user_fname, user_lname, user_uname, user_pword, user_email, user_address, user_createDate, customer_memberUntil, customer_licState, customer_licNum, customer_ccNum, customer_ccExp);
                    customer.setId(customer_customer_id);
                    
                    // RESERVATION
					reservation = objectLayer.createReservation(reservation_pickupTime, reservation_rentalLength, vehicleType, rentalLocation, customer);
					reservation.setId(reservation_reservation_id);
                    //vehicleType.getReservations();
                    
                    //objectLayer.createHourlyPrice(hourly_max_hrs, hourly_price, vehicleType);
                    //vehicleType.getHourlyPrices();
                    
                    // RENTAL
					rental = objectLayer.createRental(rental_pickupTime, reservation, vehicle);
					rental.setId(rental_rental_id);
					
					// COMMENT
					comment = objectLayer.createComment(comment_text, comment_date, rental);
					comment.setId(comment_comment_id);
					rental.setComment(comment);
					comments.add(comment);
				}
			}
			return comments;
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new RARException( "CommentManager.get: failed to get any comments: " + e );
		}
	}
    
    public void delete( Comment comment ) throws RARException{
    	
    	//Queries
    	
		String delteComment = "DELETE FROM COMMENT WHERE comment_id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
        if( !comment.isPersistent() ) 
            return;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement( delteComment );         
            stmt.setLong( 1, comment.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RARException( "CommentManager.delete: failed to delete a Comment" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "CommentManager.delete: failed to delete a Comment: " + e );       
            }
    }
}