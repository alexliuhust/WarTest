package com.wartest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wartest.model.User;

/**
 * User DAO
 * @author liu.zehu
 *
 */
public class UserDao {
	
	/**
	 * Register
	 * @param con
	 * @param user
	 * @throws Exception
	 */
	public int register(Connection con, User user) throws Exception {
		// Check if the input username already exists in the database
		String checksql = "select * from user where username = ?";
		PreparedStatement checkpstmt = con.prepareStatement(checksql);
		checkpstmt.setString(1, user.getUsername());
		ResultSet rs = checkpstmt.executeQuery();
		if (rs.next()) {
			//System.out.println("This username has already existed!");
			return -1;
		}
		
		// Add the valid new user into the database
		String sql = "insert into user (username, password) values (?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		int num = pstmt.executeUpdate();
		if (num == 1) {
			//System.out.println("Successfully Add a User!");
			return num;
		}
		return -2;
	}
	
	/**
	 * Login Authentication
	 * @param con: Connection
	 * @param user: User
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con, User user) throws Exception {
		// First, delete the current user ID in the database
		String deletesql = "delete from curuser";
		PreparedStatement depstm = con.prepareStatement(deletesql);
		depstm.executeUpdate();
		
		User resultUser = null;
		String sql1 = "select * from user where username = ? and password= ?";
		PreparedStatement pstm1 = con.prepareStatement(sql1);
		pstm1.setString(1, user.getUsername());
		pstm1.setString(2, user.getPassword());
		ResultSet rs = pstm1.executeQuery();
		
		if (rs.next()) {
			resultUser = new User();
			resultUser.setUserID(rs.getInt("userID"));
			resultUser.setUsername(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
			
			// Add the current user into the database
			String sql2 = "insert into curuser (cuID) values (?)";
			PreparedStatement pstm2 = con.prepareStatement(sql2);
			pstm2.setInt(1, rs.getInt("userID"));
			pstm2.executeUpdate();
		}
		
		return resultUser;
	}
	
	/**
	 * Log Out
	 * @param con
	 * @throws Exception
	 */
	public void logout(Connection con) throws Exception {
		String deletesql = "delete from curuser";
		PreparedStatement depstm = con.prepareStatement(deletesql);
		depstm.executeUpdate();
	}
	
}