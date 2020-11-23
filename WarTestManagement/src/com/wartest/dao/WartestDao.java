package com.wartest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wartest.model.Wartest;

public class WartestDao {
	
	/**
	 * Get how many arms the target Troop contains
	 * @param con
	 * @param troopID
	 * @return
	 * @throws Exception
	 */
	public Integer countNumberOfArmsByTroopID(Connection con, Integer troopID) throws Exception {
		String sql = "select count(*) as count from composition where troopID = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, troopID);
		ResultSet rs = pstmt.executeQuery();
		
		int count = 0;
		if (rs.next())
			count = rs.getInt("count");
		return count;
	}
	
	/**
	 * Add a Wartest to the database
	 * @param con
	 * @param wartest
	 * @return
	 * @throws Exception
	 */
	public int addAWartest(Connection con, Wartest wartest) throws Exception {
		String sql = "insert into wartest (userID, troopID1, troopID2, location, victor, arms_left) "
				+ "values(?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, wartest.getUserID());
		pstmt.setInt(2, wartest.getTroop1());
		pstmt.setInt(3, wartest.getTroop2());
		pstmt.setString(4, wartest.getLocation());
		pstmt.setInt(5, wartest.getVictor());
		pstmt.setInt(6, wartest.getArms_left());
		return pstmt.executeUpdate();
	}

}
