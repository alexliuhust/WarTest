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
	 * Find Lords by Name or Race
	 * @param con
	 * @param lord
	 * @return
	 * @throws Exception
	 */
	public ResultSet findLordsByNameOrRace(Connection con, Lord lord) throws Exception{
		StringBuilder sb = new StringBuilder("select * from lord");
		if (StringUtil.isNotEmpty(lord.getName())) {
			sb.append(" and name like '%" + lord.getName() + "%'");
		}
		if (StringUtil.isNotEmpty(lord.getRace())) {
			sb.append(" and race like '%" + lord.getRace() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
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

}
