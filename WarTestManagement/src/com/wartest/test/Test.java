package com.wartest.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.wartest.model.Arm;
import com.wartest.util.DbUtil;

public class Test {
	
	public ResultSet findArmById(Connection con, Integer armID) throws Exception {
		String sql = "select * from arms where armID = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, armID);
		return pstmt.executeQuery();
		
	}
	public void findArmByIdTest() {
		DbUtil dbUtil = new DbUtil();
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = findArmById(con, 33);
			while(rs.next()) {
				System.out.println(rs.getString("name") + 
					", " + rs.getString("race") + 
					", " + rs.getString("type"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet findArmByRace(Connection con, String race) throws Exception {
		String sql = "select * from arms where race = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, race);
		return pstmt.executeQuery();
	}
	public void findArmByRaceTest() {
		DbUtil dbUtil = new DbUtil();
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = findArmByRace(con, "Skaven");
			while(rs.next()) {
				System.out.println(rs.getInt("armID") + 
					", " + rs.getString("name") + 
					", " + rs.getString("race") + 
					", " + rs.getString("type"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	public static void main(String[] args) {
		Test test = new Test();
		System.out.println("-------------findArmByIdTest-------------");
		test.findArmByIdTest();
		System.out.println("-------------findArmByRaceTest-------------");
		test.findArmByRaceTest();
		
	}

}
