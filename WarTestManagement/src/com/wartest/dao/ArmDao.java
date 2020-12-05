package com.wartest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wartest.model.Arm;
import com.wartest.util.StringUtil;

/**
 * Arm Dao
 * @author liu.zehu
 *
 */
public class ArmDao {
	
	/**
	 * Find Arms by TroopID
	 * @param con
	 * @param troopID
	 * @return
	 * @throws Exception
	 */
	public ResultSet findArmsByTroopID(Connection con, Integer troopID) throws Exception {
		String sql = "call find_arms_by_troopID(?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, troopID);
		return pstmt.executeQuery();
	}
	
	/**
	 * Search arms by Name, Race or Type
	 * @param con
	 * @param arm
	 * @return
	 * @throws Exception
	 */
	public ResultSet findArmsByNameOrRaceOrType(Connection con, Arm arm) throws Exception {
		String sql = "call find_arms_by_name_race_type(?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		String name = "", race = "", type = "";
		if (StringUtil.isNotEmpty(arm.getName())) name = arm.getName();
		if (StringUtil.isNotEmpty(arm.getRace())) race = arm.getRace();
		if (StringUtil.isNotEmpty(arm.getType())) type = arm.getType();
		pstmt.setString(1, name);
		pstmt.setString(2, race);
		pstmt.setString(3, type);
		return pstmt.executeQuery();
	}
	
	/**
	 * Find Arm by armID
	 * @param con
	 * @param armID
	 * @return
	 * @throws Exception
	 */
	public ResultSet findArmById(Connection con, Integer armID) throws Exception {
		String sql = "select * from arm where armID = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, armID);
		return pstmt.executeQuery();
	}
	
	/**
	 * Find Arms only by Race
	 * @param con
	 * @param race
	 * @return
	 * @throws Exception
	 */
	public ResultSet findArmByRace(Connection con, String race) throws Exception {
		String sql = "select * from arm where race = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, race);
		return pstmt.executeQuery();
	}
	
}
