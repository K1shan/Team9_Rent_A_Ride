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
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class CustomerManager{

	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public CustomerManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	@SuppressWarnings("resource")
	public void store(Customer customer) throws RARException{
		
    	//Queries
		
		String insertUserQuery = 
				"INSERT INTO USER "
				+ "( fname, lname, uname, pword, email, address, create_date ) "
				+ "VALUES "
				+ "( ?, ?, ?, ?, ?, ?, ? )"; 
		
		String insertCustomerQuery = 
				"INSERT INTO CUSTOMER "
				+ "( user_id, member_until, lic_state, lic_num, cc_num, cc_exp, status ) "
				+ "VALUES "
				+ "( ?, ?, ?, ?, ?, ?, ? )";
		
		String insertAdminQuery =
				"INSERT INTO ADMIN "
				+ "( user_id ) "
				+ "VALUES "
				+ "( ? )";
		
		String updateUserQuery = 
				"UPDATE USER SET "
				+ "fname=?, "
				+ "lname=?, "
				+ "uname=?, "
				+ "pword=?, "
				+ "email=?, "
				+ "address=?, "
				+ "create_date=? "
				+ "WHERE email=?";               
		
		String updateCustomerQuery = 
				"UPDATE CUSTOMER SET "
				+ "user_id=?, "
				+ "member_until=?, "
				+ "lic_state=?, "
				+ "lic_num=?, "
				+ "cc_num=?, "
				+ "cc_exp=?, "
				+ "status=? "
				+ "WHERE customer_id=?";
		
		String selectUserIdQuery = 
				"SELECT user_id "
				+ "FROM USER "
				+ "WHERE USER.email=?";
		
		PreparedStatement 	pstmt;
		int 				inscnt;
		long 				customerId;
		long 				userId = 0;
	    
		/*
		 * USER
		 */
		try {
			// check if persistent
			//
			if( !customer.isPersistent() ){
                pstmt = (PreparedStatement) con.prepareStatement( insertUserQuery );
			}else{
                pstmt = (PreparedStatement) con.prepareStatement( updateUserQuery );
			}
			
			if( customer.getFirstName() != null )
                pstmt.setString( 1, customer.getFirstName().toLowerCase() );
            else{
                throw new RARException( "CustomerManager.save: can't save a user: FirstName undefined" );
            }if( customer.getLastName() != null )
                pstmt.setString( 2, customer.getLastName().toLowerCase() );
            else
                throw new RARException( "CustomerManager.save: can't save a user: LastName undefined" );
            if( customer.getUserName() != null )
                pstmt.setString( 3, customer.getUserName() );
            else
                throw new RARException( "CustomerManager.save: can't save a user: UserName undefined" );
            if( customer.getPassword() != null )
                pstmt.setString( 4, customer.getPassword());
            else
                throw new RARException( "CustomerManager.save: can't save a user: Password undefined" );
            if( customer.getEmail() != null )
                pstmt.setString( 5, customer.getEmail());
            else
                throw new RARException( "CustomerManager.save: can't save a user: Email undefined" );
            if( customer.getAddress() != null )
                pstmt.setString( 6, customer.getAddress());
            else
                throw new RARException( "CustomerManager.save: can't save a user: Address undefined" );
         
            if( customer.getCreatedDate() != null ){
            	java.util.Date myDate = customer.getCreatedDate();
            	java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
            	pstmt.setDate(7, sqlDate);
            }else
                throw new RARException( "CustomerManager.save: can't save a user: CreatedDate undefined" );
            if( customer.isPersistent() )
                pstmt.setString( 8, customer.getEmail() );

			System.out.println("query: "+pstmt.asSql());
            inscnt = pstmt.executeUpdate();
            
            // retrieve last incremented id
            //
            if( !customer.isPersistent() ) {
	            if( inscnt > 0 ) {
	                String sql = "select last_insert_id()";
	                if( pstmt.execute( sql ) ) {
	                    ResultSet r = pstmt.getResultSet();
	                    while( r.next() ) {
	                        userId = r.getLong( 1 );
	                    }
	                    pstmt.close();
	                }
	            }
            }
		}catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "CustomerManager.save: failed to save a user: " + e );
		}
		try {
			pstmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		/*
		 * Get userId
		 */
		if( customer.isPersistent() ){
			try {
				pstmt = (PreparedStatement) con.prepareStatement( selectUserIdQuery );
				pstmt.setString(1, customer.getEmail());
				System.out.println("query: "+pstmt.asSql());
				ResultSet rs = pstmt.executeQuery();
				while( rs.next() )
	                userId = rs.getLong( 1 );
				
			} catch (SQLException e){
				e.printStackTrace();
			}
		}


		/*
		 * CUSTOMER
		 */
		try {
			// check if persistent
			//
			if( !customer.isPersistent() ){
				pstmt = (PreparedStatement) con.prepareStatement( insertCustomerQuery );
			}else{
				pstmt = (PreparedStatement) con.prepareStatement( updateCustomerQuery );
			}
			
			if( userId > 0 )
                pstmt.setLong( 1, userId );
            else{
                throw new RARException( "CustomerManager.save: can't save a customer: userId undefined" );
            }
			if( customer.getMemberUntil() != null ){
				java.util.Date myDate = customer.getMemberUntil();
            	java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        		pstmt.setDate(2, sqlDate);
            }
            else
                throw new RARException( "CustomerManager.save: can't save a customer: MemberUntil undefined" );
            if( customer.getLicenseState() != null )
                pstmt.setString( 3, customer.getLicenseState() );
            else
                throw new RARException( "CustomerManager.save: can't save a customer: LicenseState undefined" );
            if( customer.getLicenseNumber() != null )
                pstmt.setString( 4, customer.getLicenseNumber());
            else
                throw new RARException( "CustomerManager.save: can't save a customer: LicenseNumber undefined" );
            if( customer.getCreditCardNumber() != null )
                pstmt.setString( 5, customer.getCreditCardNumber());
            else
                throw new RARException( "CustomerManager.save: can't save a customer: CreditCardNumber undefined" );
         
            if( customer.getCreditCardExpiration() != null ){
            	java.util.Date myDate = customer.getCreditCardExpiration();
            	java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        		pstmt.setDate(6, sqlDate);
            } else
                throw new RARException( "CustomerManager.save: can't save a customer: CreditCardExpiration undefined" );
         
        	if(customer.getUserStatus().equals(UserStatus.ACTIVE))
        		pstmt.setLong( 7, 0 );
        	else if(customer.getUserStatus().equals(UserStatus.CANCELLED))
        		pstmt.setLong( 7, 1 );
        	else if(customer.getUserStatus().equals(UserStatus.TERMINATED))
        		pstmt.setLong( 7, 2 );
        	else
        		throw new RARException( "CustomerManager.save: can't save a customer: UserStatus undefined" );
        
            if( customer.isPersistent() )
                pstmt.setLong( 8, customer.getId() );

            System.out.println("query: "+pstmt.asSql());
            inscnt = pstmt.executeUpdate();
            
            // retrieve last incremented value if persistent for pk
            //
            if( !customer.isPersistent() ) {
                if( inscnt == 1 ) {
                    String sql = "select last_insert_id()";
                    if( pstmt.execute( sql ) ) {
                        ResultSet r = pstmt.getResultSet();
                        while( r.next() ) {
                            customerId = r.getLong( 1 );
                            if( customerId > 0 )
                                customer.setId( customerId );
                        }
                    }
                }
            }
            else {
                if( inscnt < 1 )
                    throw new RARException( "CustomerManager.save: failed to save a customer" ); 
            }
		}catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "CustomerManager.save: failed to save a customer: " + e );
		}
	    
		System.out.println(customer.getIsAdmin());
		if( customer.getIsAdmin() ){
			
			try {
				pstmt = (PreparedStatement) con.prepareStatement( insertAdminQuery );
				pstmt.setLong( 1, userId );System.out.println("query: "+pstmt.asSql());
	            System.out.println("query: "+pstmt.asSql());
	            inscnt = pstmt.executeUpdate();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}

	public List<Customer> restore(Customer modelCustomer ) 
		throws RARException{
		
    	// Queries
		String selectCustomerQuery = 
				"SELECT "
				+ "USER.*, "
				+ "CUSTOMER.* "
				+ "FROM USER INNER JOIN CUSTOMER ON USER.user_id=CUSTOMER.user_id";
		
		StringBuffer query = new StringBuffer(1000);
		StringBuffer condition = new StringBuffer(1000);
		List<Customer> customers = new ArrayList<Customer>();
		Statement stmt = null;
		condition.setLength(0);
		query.append(selectCustomerQuery);
				
		// if we want just 1 row
		if (modelCustomer != null){
			if(modelCustomer.getId() >= 0){
				query.append( " where CUSTOMER.customer_id=" + modelCustomer.getId() );
			}else if(modelCustomer.getUserName() != null){
				query.append( " where USER.uname = '" + modelCustomer.getUserName() + "'");
			}else{
				if(modelCustomer.getPassword() != null){
					condition.append( " USER.pword = '" + modelCustomer.getPassword() + "'");
				}
				
				if(modelCustomer.getEmail() != null ) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.email = '" + modelCustomer.getEmail() + "'" );
                }
				
				if(modelCustomer.getFirstName() != null) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.fname = '" + modelCustomer.getFirstName() + "'" );
                }
				
				if(modelCustomer.getLastName() != null) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.lname = '" + modelCustomer.getLastName() + "'" );
                }
				
				if(modelCustomer.getAddress() != null) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.address = '" + modelCustomer.getAddress() + "'" );
                }        
				
				if(modelCustomer.getCreatedDate() != null) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.create_date = '" + modelCustomer.getCreatedDate() + "'" );
                }
				
				if(modelCustomer.getMemberUntil() != null ) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " CUSTOMER.member_until = '" + modelCustomer.getMemberUntil() + "'" );
                }
				
				if(modelCustomer.getLicenseState() != null ) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " CUSTOMER.lic_state = '" + modelCustomer.getLicenseState() + "'" );
                }
				
				if(modelCustomer.getLicenseNumber() != null ) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " CUSTOMER.lic_num = '" + modelCustomer.getLicenseNumber() + "'" );
                }
				
				if(modelCustomer.getCreditCardNumber() != null ) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " CUSTOMER.cc_num = '" + modelCustomer.getCreditCardNumber() + "'" );
                }
				
				if(modelCustomer.getCreditCardExpiration() != null ) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " CUSTOMER.cc_exp = '" + modelCustomer.getCreditCardExpiration() + "'" );
                }
				
				if(modelCustomer.getUserStatus() != null){
					if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " CUSTOMER.status='"+modelCustomer.getUserStatus()+"'");
				}
				
				if( condition.length() > 0 ) {
                    query.append(  " where " );
                    query.append( condition );
                }
			}
		}
		
		
		try {
			
			stmt = con.createStatement();
			System.out.println("query: " + query.toString());
			if( stmt.execute( query.toString() )){
				
				ResultSet rs = stmt.getResultSet();
				
				// USER
				//
				int		user_user_id;
				String 	user_fname;
	            String 	user_lname;
	            String 	user_uname;
	            String 	user_pword;
	            String 	user_email;
	            String 	user_address;
	            Date 	user_createDate;
				// CUSTOMER
	            //
	            int		customer_customer_id;
	            int 	customer_user_id;
	            Date 	customer_memberUntil;
	            String 	customer_licState;
	            String 	customer_licNum;
	            String 	customer_ccNum;
	            Date 	customer_ccExp;
	            int		customer_status = 0;
	            
	            UserStatus userStatus = UserStatus.ACTIVE;
	            Customer customer = null;
	            
	            while( rs.next() ) {
	            	
	            	// USER
					user_user_id	= rs.getInt(1);
	           	 	user_fname 		= rs.getString(2);
	           	 	user_lname 		= rs.getString(3);
	           	 	user_uname 		= rs.getString(4);
	           	 	user_pword 		= rs.getString(5);
	           	 	user_email 		= rs.getString(6);
	           	 	user_address 	= rs.getString(7);
	           	 	user_createDate = rs.getDate(8);
	           	 	
	           	 	// CUSTOMER
	                customer_customer_id= rs.getInt(9);
	                customer_user_id 	= rs.getInt(10);
	                customer_memberUntil= rs.getDate(11);
	                customer_licState 	= rs.getString(12);
	                customer_licNum 	= rs.getString(13);
	                customer_ccNum 		= rs.getString(14);
	                customer_ccExp 		= rs.getDate(15);
	                customer_status 	= rs.getInt(16);
	                if(customer_status == 0)
	                	userStatus		= UserStatus.ACTIVE;
	                if(customer_status == 1)
	                	userStatus 		= UserStatus.CANCELLED;
	                else if(customer_status == 2)
	                	userStatus 		= UserStatus.TERMINATED;
	                
	                customer = null;
	                customer = objectLayer.createCustomer(user_fname, user_lname, user_uname, user_pword, user_email, user_address, user_createDate, customer_memberUntil, customer_licState, customer_licNum, customer_ccNum, customer_ccExp);
	                customer.setId(customer_customer_id);
	                customer.setUserStatus(userStatus);
	                customers.add(customer);
	            }
			}
            return customers;
            
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "CustomerManager.get: failed to get any customers: " + e );
		}
	}

	public void delete(Customer customer) throws RARException{
		String deleteCustomerQuery = 
				"DELETE `USER` "
				+ "FROM `USER` "
				+ "INNER JOIN CUSTOMER ON USER.user_id=CUSTOMER.user_id";
		
		StringBuffer query = new StringBuffer(1000);
		StringBuffer condition = new StringBuffer(1000);
		Statement stmt = null;
		condition.setLength(0);
		query.append(deleteCustomerQuery);

		if ( customer != null ){
			query.append( " WHERE CUSTOMER.user_id=" + customer.getId() );
		}
		
		try {
			stmt = con.createStatement();
			System.out.println("query: " + query.toString());
			stmt.executeUpdate(query.toString());
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("CustomerManager.delete: failed to delete a customer" + e);
		}
//		String deleteCustomerSql = "UPDATE CUSTOMER INNER JOIN `USER` ON CUSTOMER.user_id = USER.user_id SET CUSTOMER.status = 2 WHERE USER.uname = ?";
//		PreparedStatement stmt;
//		int inscnt;
//		
//		if (!customer.isPersistent()) // checks if Customer object is persistent. If not, nothing to delete
//			return;
//		
//		try {
//			stmt = (PreparedStatement) con.prepareStatement(deleteCustomerSql);
//			stmt.setNString(1, customer.getUserName());
//			inscnt = stmt.executeUpdate();
//			if(inscnt == 1) {
//				return;
//			}
//			else
//				throw new RARException("CustomerManager.delete: failed to delete a customer");
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//			throw new RARException("CustomerManager.delete: failed to delete a customer" + e);
//		}
	}	
}