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
		String sql = "call register(?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			System.out.println(rs.getInt("result"));
			return rs.getInt("result");
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