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
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.object.ObjectLayer;


public class AdministratorManager {
	
	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public AdministratorManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	@SuppressWarnings("resource")
	public void store(Administrator administrator) throws RARException{
		
		// Queries 
		//
		String insertUserQuery = 
				"INSERT INTO USER "
				+ "( fname, lname, uname, pword, email, address, create_date ) "
				+ "VALUES "
				+ "( ?, ?, ?, ?, ?, ?, ?)";
		
		String insertAdministratorQuery = 
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
		
		String updateAdministratorQuery = 
				"UPDATE ADMIN SET "
				+ "user_id=? "
				+ "WHERE admin_id=?";
		
		PreparedStatement	pstmt;
		int 				inscnt;
		long 				administratorId;
		long 				userId = 0;
		
		try {
			//check if persistent
			//
			if(!administrator.isPersistent()){
				pstmt = (PreparedStatement) con.prepareStatement(insertUserQuery);
			}else{
				pstmt = (PreparedStatement) con.prepareStatement(updateUserQuery);
			}
			
			if( administrator.getFirstName() != null )
                pstmt.setString( 1, administrator.getFirstName() );
            else{
                throw new RARException( "AdministratorManager.save: can't save a user: FirstName undefined" );
            }if( administrator.getLastName() != null )
                pstmt.setString( 2, administrator.getLastName() );
            else
                throw new RARException( "AdministratorManager.save: can't save a user: LastName undefined" );
            if( administrator.getUserName() != null )
                pstmt.setString( 3, administrator.getUserName() );
            else
                throw new RARException( "AdministratorManager.save: can't save a user: UserName undefined" );
            if( administrator.getPassword() != null )
                pstmt.setString( 4, administrator.getPassword());
            else
                throw new RARException( "AdministratorManager.save: can't save a user: Password undefined" );
            if( administrator.getEmail() != null )
                pstmt.setString( 5, administrator.getEmail());
            else
                throw new RARException( "AdministratorManager.save: can't save a user: Email undefined" );
            if( administrator.getAddress() != null )
                pstmt.setString( 6, administrator.getAddress());
            else
                throw new RARException( "AdministratorManager.save: can't save a user: Address undefined" );
         
            if( administrator.getCreatedDate() != null ){
            	java.util.Date myDate = administrator.getCreatedDate();
            	java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
            	pstmt.setDate(7, sqlDate);
            }else
                throw new RARException( "AdministratorManager.save: can't save a user: CreatedDate undefined" );
            if( administrator.isPersistent() )
                pstmt.setString( 8, administrator.getEmail() );

			System.out.println("query: "+pstmt.asSql());
            inscnt = pstmt.executeUpdate();
            
            // retrieve last incremented id
            //
            if( inscnt > 0 ) {
                String sql = "select last_insert_id()";
                if( pstmt.execute( sql ) ) {
                    ResultSet r = pstmt.getResultSet();
                    while( r.next() ) {
                        userId = r.getLong( 1 );
                    }
                }
            }else {
                throw new RARException( "AdministratorManager.save: failed to get userId" ); 
            }
            
		} catch(SQLException e){
			e.printStackTrace();
            throw new RARException( "AdministratorManager.save: failed to save a user: " + e );
		}

		/*
		 * ADMIN
		 */
		try {
			// check if persistent
			//
			if( !administrator.isPersistent() ){
				pstmt = (PreparedStatement) con.prepareStatement( insertAdministratorQuery );
			}else{
				pstmt = (PreparedStatement) con.prepareStatement( updateAdministratorQuery );
			}
			
			if( userId != 0 )
                pstmt.setLong( 1, userId );
            else{
                throw new RARException( "AdminsitratorManager.save: can't save a administrator: userId undefined" );
            }
			
            if( administrator.isPersistent() )
                pstmt.setLong( 2, administrator.getId() );

            System.out.println("query: "+pstmt.asSql());
            inscnt = pstmt.executeUpdate();
            
            // retrieve last incremented value if persistent for pk
            //
            if( !administrator.isPersistent() ) {
                if( inscnt > 0 ) {
                    String sql = "select last_insert_id()";
                    if( pstmt.execute( sql ) ) {
                        ResultSet r = pstmt.getResultSet();
                        while( r.next() ) {
                            administratorId = r.getLong( 1 );
                            if( administratorId > 0 )
                                administrator.setId( administratorId );
                        }
                    }
                }
            }
            else {
                throw new RARException( "AdministratorManager.save: failed to save a administrator" ); 
            }
		}catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "AdministratorManager.save: failed to save a administrator: " + e );
		}
	}//store
	
	
	public List<Administrator> restore(Administrator modelAdministrator) throws RARException{
		
		String selectAdministratorQuery = 
				"SELECT "
				+ "USER.user_id, USER.fname, USER.lname, USER.uname, USER.pword, USER.email, USER.address, USER.create_date, "
				+ "ADMIN.admin_id "
				+ "FROM USER INNER JOIN ADMIN ON USER.user_id=ADMIN.user_id";
		
		StringBuffer query = new StringBuffer(1000);
		StringBuffer condition = new StringBuffer(1000);
		List<Administrator> administrators = new ArrayList<Administrator>();
		Statement stmt = null;
		condition.setLength(0);
		query.append(selectAdministratorQuery);
		
		// NULL CHECKER
		if( modelAdministrator != null ){
			if( modelAdministrator.getId() >= 0 ){
				query.append( " where ADMIN.admin_id = " + modelAdministrator.getId() );
			}else if( modelAdministrator.getUserName() != null) {
				query.append( " where USER.uname = " + modelAdministrator.getUserName());
			}else {
				
				if(modelAdministrator.getPassword() != null){
					condition.append( " USER.pword = '" + modelAdministrator.getPassword() + "'");
				}
				
				if(modelAdministrator.getEmail() != null ) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.email = '" + modelAdministrator.getEmail() + "'" );
                }
				
				if(modelAdministrator.getFirstName() != null) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.fname = '" + modelAdministrator.getFirstName() + "'" );
                }
				
				if(modelAdministrator.getLastName() != null) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.lname = '" + modelAdministrator.getLastName() + "'" );
                }
				
				if(modelAdministrator.getAddress() != null) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.address = '" + modelAdministrator.getAddress() + "'" );
                }   
				
				if(modelAdministrator.getCreatedDate() != null) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.create_date = '" + modelAdministrator.getCreatedDate() + "'" );
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
			if( stmt.execute(query.toString()) ){
				
				ResultSet r = stmt.getResultSet();
				
				String fname;
	            String lname;
	            String uname;
	            String pword;
	            String email;
	            String address;
	            Date createDate;
	            
	            long administratorId;
	            
	            while( r.next() ) {
	            	fname = r.getString(2);
	                lname = r.getString(3);
	                uname = r.getString(4);
	                pword = r.getString(5);
	                email = r.getString(6);
	                address = r.getString(7);
	                createDate = r.getDate(8);
	                
	                administratorId = r.getLong(9);
	                
	                
	                Administrator administrator = 
	                		objectLayer.createAdministrator(fname, lname, uname, pword, email,
	            			address, createDate);
	                administrator.setId(administratorId);
	                administrators.add(administrator);
	            }
			}
            return administrators;
            
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "AdministratorManager.get: failed to get any administrators: " + e );
		}
	}//restore
	
	public void delete(Administrator admin) throws RARException {
		String deleteAdministratorQuery = 
				"DELETE `USER` "
				+ "FROM `USER` "
				+ "INNER JOIN ADMIN ON USER.user_id=ADMIN.user_id";
		
		StringBuffer query = new StringBuffer(1000);
		StringBuffer condition = new StringBuffer(1000);
		Statement stmt = null;
		condition.setLength(0);
		query.append(deleteAdministratorQuery);

		if ( admin != null ){
			query.append( " WHERE ADMIN.user_id=" + admin.getId() );
		}
		
		try {
			stmt = con.createStatement();
			System.out.println("query: " + query.toString());
			stmt.executeUpdate(query.toString());
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("AdministratorManager.delete: failed to delete an administrator" + e);
		}
//		String deleteAdministratorSql = "DELETE ADMIN FROM ADMIN INNER JOIN `USER` ON ADMIN.user_id = USER.user_id WHERE USER.uname = ?;";
//		PreparedStatement stmt;
//		int inscnt;
//
//		if (!admin.isPersistent()) // checks if Administrator object is persistent. If not, nothing to delete
//			return;
//		
//		try {
//			stmt = (PreparedStatement) con.prepareStatement(deleteAdministratorSql);
//			stmt.setString(1, admin.getUserName());
//
//			inscnt = stmt.executeUpdate();
//			if(inscnt == 1) {
//				return;
//			}
//			else
//				throw new RARException("AdministratorManager.delete: failed to delete an administrator");
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//			throw new RARException("AdministratorManager.delete: failed to delete an administrator" + e);
//		}
	}
}
