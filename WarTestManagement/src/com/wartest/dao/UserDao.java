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
		int numOfDel = depstm.executeUpdate();
		
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
			int num = pstm2.executeUpdate();
		}
		
		return resultUser;
	}
}