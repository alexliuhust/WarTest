package com.wartest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wartest.model.Wartest;

public class WartestDao {
	
	/**
	 * Get how many arms the target Troop contains (By Troop ID)
	 * @param con
	 * @param troopID
	 * @return
	 * @throws Exception
	 */
	public Integer countNumberOfArmsByTroopID(Connection con, Integer troopID) throws Exception {
		String sql = "call count_number_of_arms_by_troopID(?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, troopID);
		ResultSet rs = pstmt.executeQuery();
		
		int count = 0;
		if (rs.next())
			count = rs.getInt("count");
		return count;
	}
	
	/**
	 * Get how many arms the target Troop contains (By Troop Name)
	 * @param con
	 * @param troopName
	 * @return
	 * @throws Exception
	 */
	public Integer countNumberOfArmsByTroopName(Connection con, String troopName) throws Exception {
		String sql = "call count_number_of_arms_by_troopname(?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, troopName);
		ResultSet rs = pstmt.executeQuery();
		
		int count = 0;
		if (rs.next())
			count = rs.getInt("count");
		return count;
	}
	
	
	/**
	 * Find All Wartests By User ID
	 * @param con
	 * @param currentUserID
	 * @return
	 * @throws Exception
	 */
	public ResultSet findAllWartestsByUserID(Connection con, Integer currentUserID) throws Exception {
		String sql = "call find_all_wartests_by_userID(?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, currentUserID);
		return pstmt.executeQuery();
	}
	
	/**
	 * Add a Wartest to the database
	 * @param con
	 * @param wartest
	 * @return
	 * @throws Exception
	 */
	public int addAWartest(Connection con, Wartest wartest) throws Exception {
		Integer victor = wartest.getVictor();
		Integer looser = 0;
		if (victor.equals(wartest.getTroop1())) looser = wartest.getTroop2();
		else looser = wartest.getTroop1();
		
		String sql = "call add_wartest(?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, wartest.getUserID());
		pstmt.setInt(2, looser);
		pstmt.setInt(3, victor);
		pstmt.setString(4, wartest.getLocation());
		pstmt.setInt(5, wartest.getArms_left());
		return pstmt.executeUpdate();
	}
	
	/**
	 * Delete a Wartest
	 * @param con
	 * @param warID
	 * @return
	 * @throws Exception
	 */
	public int deleteAWartest(Connection con, Integer warID) throws Exception {
		String sql = "delete from wartest where warID = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, warID);
		return pstmt.executeUpdate();
	}
	
	/**
	 * Update a Wartest
	 * @param con
	 * @param troopName
	 * @return
	 * @throws Exception
	 */
	public int updateAWartest(Connection con, Wartest wartest) throws Exception {
		Integer warID = wartest.getWarID();
		String location = wartest.getLocation();
		Integer amrsLeft = wartest.getArms_left();
		Integer victor = wartest.getVictor();
		Integer looser = 0;
		if (victor.equals(wartest.getTroop1())) looser = wartest.getTroop2();
		else looser = wartest.getTroop1();
		
		String sql = "call update_wartest(?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, looser);
		pstmt.setInt(2, victor);
		pstmt.setString(3, location);
		pstmt.setInt(4, amrsLeft);
		pstmt.setInt(5, warID);
		return pstmt.executeUpdate();
	}

}
