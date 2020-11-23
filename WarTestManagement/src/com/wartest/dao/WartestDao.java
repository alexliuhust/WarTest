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
	 * Get how many arms the target Troop contains (By Troop Name)
	 * @param con
	 * @param troopName
	 * @return
	 * @throws Exception
	 */
	public Integer countNumberOfArmsByTroopName(Connection con, String troopName) throws Exception {
		String sql = "select count(*) as count from "
				+ "composition as c join troop as t on c.troopID = t.troopID "
				+ "where t.name = ?";
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
		String sql = "select w.warID, t1.name as troop1, t2.name as troop2, w.location, t3.name as victor, w.arms_left "
				+ "from wartest as w "
				+ "inner join troop as t1 on w.troopID1 = t1.troopID "
				+ "inner join troop as t2 on w.troopID2 = t2.troopID "
				+ "inner join troop as t3 on w.victor = t3.troopID "
				+ "where w.userID = ?";
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

}



















