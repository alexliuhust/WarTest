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
		String sql = "select a.armID, a.name, a.race, a.type from "
				+ "composition as c "
				+ "join troop as t on c.troopID = t.troopID "
				+ "join arm as a on c.armID = a.armID "
				+ "where t.troopID = ? group by c.compID; ";
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
		StringBuilder sb = new StringBuilder("select * from arm");
		if (StringUtil.isNotEmpty(arm.getName() )) {
			sb.append(" and name like '%" + arm.getName() + "%'");
		}
		if (StringUtil.isNotEmpty(arm.getRace() )) {
			sb.append(" and race like '%" + arm.getRace() + "%'");
		}
		if (StringUtil.isNotEmpty(arm.getType() )) {
			sb.append(" and type like '%" + arm.getType() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
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
