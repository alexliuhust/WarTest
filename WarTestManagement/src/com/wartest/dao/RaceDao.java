package com.wartest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wartest.util.StringUtil;
import com.wartest.model.Race;

/**
 * Race Dao
 * @author liu.zehu
 *
 */
public class RaceDao {

	/**
	 * Find Races by Race Name
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
}
