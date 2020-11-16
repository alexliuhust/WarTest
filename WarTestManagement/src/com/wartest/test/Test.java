package com.wartest.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wartest.dao.UserDao;
import com.wartest.model.User;
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
	
	public ResultSet findLordWithRaceLocation(Connection con, Integer lordID) throws Exception {
		String sql = "select l.*, r.location "
				+ "from lords as l join races as r on l.race = r.race "
				+ "where lordID = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, lordID);
		return pstmt.executeQuery();
	}
	public void findLordWithRaceLocationTest() {
		DbUtil dbUtil = new DbUtil();
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = findLordWithRaceLocation(con, 1);
			while(rs.next()) {
				System.out.println(rs.getString("name") + 
					", " + rs.getString("race") + 
					", " + rs.getString("location"));
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
	
	public ResultSet findTroopByUserID(Connection con, Integer troopID) throws Exception {
		String sql = "select t.name as troopname, t.memo as memo,"
				+ "		l.name as lordname,"
				+ "        group_concat(a.name, \"(\", a.type, \")\") as arms"
				+ "	from troop t join lord l on (t.lordID = l.lordID)"
				+ "    join composition c on (t.troopID = c.troopID)"
				+ "    join arm a on (c.armID = a.armID)"
				+ "    where t.userID = ?"
				+ "	group by t.troopID";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, troopID);
		return pstmt.executeQuery();
	}
	public void findTroopByUserIDTest() {
		DbUtil dbUtil = new DbUtil();
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = findTroopByUserID(con, 1);
			while(rs.next()) {
				System.out.println(rs.getString("troopname") + 
					", " + rs.getString("memo") + 
					", " + rs.getString("lordname") + 
					", " + rs.getString("arms"));
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
	
	public void loginTest() {
		String username = "ronald@gmail.com";
		String password = "654321";
		User user = new User(username, password);
		UserDao userDao = new UserDao();
		DbUtil dbUtil = new DbUtil();
		Connection con = null;
		try {
			con = dbUtil.getCon();
			User currentUser = userDao.login(con, user);
			if (currentUser != null) 
				System.out.println(currentUser.getUsername() + " logged in!!");
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
//		System.out.println("-------------findArmByIdTest-------------");
//		test.findArmByIdTest();
//		System.out.println("-------------findArmByRaceTest-------------");
//		test.findArmByRaceTest();
//		System.out.println("-------------findLordWithRaceLocationTest-------------");
//		test.findLordWithRaceLocationTest();
//		System.out.println("-------------findTroopByUserIDTest-------------");
//		test.findTroopByUserIDTest();
		System.out.println("-------------loginTest-------------");
		test.loginTest();
		
	}

}
