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
		if (rs.next()) return -1;
		
		// Add the valid new user into the database
		String sql = "insert into user (username, password) values (?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		int num = pstmt.executeUpdate();
		if (num == 1) return num;
		
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
		
		User resultUser = null;
		String sql1 = "select * from user where username = ? and password= ?";
		PreparedStatement pstm1 = con.prepareStatement(sql1);
		pstm1.setString(1, user.getUsername());
		pstm1.setString(2, user.getPassword());
		ResultSet rs = pstm1.executeQuery();
		
		if (rs.next()) {
			resultUser = new User();
			resultUser.setUserID(rs.getInt("userID"));
			resultUser.setUsername(rs.getString("username"));
			resultUser.setPassword(rs.getString("password"));
		}
		
		return resultUser;
	}
	
	
}