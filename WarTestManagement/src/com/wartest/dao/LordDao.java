package com.wartest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wartest.model.Lord;
import com.wartest.util.StringUtil;

/**
 * Lord Dao
 * @author liu.zehu
 *
 */
public class LordDao {
	
	/**
	 * Search Lords by Name or Race
	 * @param con
	 * @param lord
	 * @return
	 * @throws Exception
	 */
	public ResultSet findLordsByNameOrRace(Connection con, Lord lord) throws Exception{
		String sql = "call find_lords_by_name_race(?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		String name = "", race = "";
		if (StringUtil.isNotEmpty(lord.getName())) name = lord.getName();
		if (StringUtil.isNotEmpty(lord.getRace())) race = lord.getRace();
		pstmt.setString(1, name);
		pstmt.setString(2, race);
		return pstmt.executeQuery();
	}
	
	/**
	 * Find Lord by lordID
	 * @param con
	 * @param lordID
	 * @return
	 * @throws Exception
	 */
	public ResultSet findLordByID(Connection con, Integer lordID) throws Exception{
		String sql = "select * from lord where lordID = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, lordID);
		return pstmt.executeQuery();
	}
	
	/**
	 * Find Lord by Race
	 * @param con
	 * @param race
	 * @return
	 * @throws Exception
	 */
	public ResultSet findLordByRace(Connection con, String race) throws Exception{
		String sql = "select * from lord where race = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, race);
		return pstmt.executeQuery();
	}

}
