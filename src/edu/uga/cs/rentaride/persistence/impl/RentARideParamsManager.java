package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class RentARideParamsManager {

	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public RentARideParamsManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	@SuppressWarnings("resource")
	public void store( RentARideParams rentARideConfig ) throws RARException{
    	
		String paramsInsertQuery = 
		"INSERT INTO RENT_A_RIDE_PARAMS "
		+ "(member_fee, late_fee)"

		+ "VALUES "
		+ "( ?, ?)";

		String paramsUpdateQuery =
		"UPDATE RENT_A_RIDE_PARAMS "
			+ "member_fee=?, late_fee=? ";


		PreparedStatement	pstmt;
		try{
			if( !rentARideConfig.isPersistent()) {
				pstmt = (PreparedStatement) con.prepareStatement(paramsInsertQuery);
			}
			else{
				pstmt = (PreparedStatement) con.prepareStatement(paramsUpdateQuery);
			}

			if(rentARideConfig.getMembershipPrice() >= 0 ) {
				pstmt.setLong(1, rentARideConfig.getMembershipPrice());
			}
			else {
				throw new RARException("RentARideParamsManager.save: can't save a params: member_fee undefined");
			}

			if(rentARideConfig.getLateFee() >= 0 ) {
				pstmt.setLong(2, rentARideConfig.getLateFee());
			}
			else {
				throw new RARException("RentARideParamsManager.save: can't save a params: late_fee undefined");
			}

			pstmt.executeUpdate();
			
		} //try
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException( "RentARideParamsManager.save: failed to save a params: " + e );
		}

    }
	

	public RentARideParams restore() throws RARException{
		
		String selectParamsSql = "SELECT"
			+ "RENT_A_RIDE_PARAMS.member_fee, RENT_A_RIDE_PARAMS.late_fee";
		
		Statement stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		
		condition.setLength(0);

		query.append(selectParamsSql);

		System.out.println("query: "+ selectParamsSql);
		
		RentARideParams params = null;

		try{
			stmt = con.createStatement();
			if(stmt.execute(query.toString())) {
				ResultSet rs = stmt.getResultSet();
				
				int member_fee;
				int late_fee;
				
				while(rs.next()){
					member_fee = rs.getInt(1);
					late_fee = rs.getInt(2);
					
					params = objectLayer.createRentARideParams();
					params.setMembershipPrice(member_fee);
					params.setLateFee(late_fee);
					
				}
			}
			return params;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RARException("RentARideParamsManager.get: failed to get any params: " + e);
		} 

	}
	
    
}
