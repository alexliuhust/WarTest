package com.wartest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.wartest.model.Race;
import com.wartest.util.StringUtil;

/**
 * Race Dao
 * @author liu.zehu
 *
 */
public class RaceDao {

	/**
	 * Search Races by Race Name
	 * @param con
	 * @param race
	 * @return
	 * @throws Exception
	 */
	public ResultSet findRacesByName(Connection con, Race race) throws Exception{
		StringBuilder sb = new StringBuilder("select * from race");
		if (StringUtil.isNotEmpty(race.getRace())) {
			sb.append(" and race like '%" + race.getRace() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	/**
	 * Find All Races name
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public ResultSet findAllRaces(Connection con) throws Exception{
		String sql = "select race from race";
		Statement stmt = con.prepareStatement(sql);
		return stmt.executeQuery(sql);
	}
}
