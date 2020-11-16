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
		User resultUser = null;
		String sql1 = "select * from user where username = ? and password= ?";
		PreparedStatement pstm = con.prepareStatement(sql1);
		
		pstm.setString(1, user.getUsername());
		pstm.setString(2, user.getPassword());
		ResultSet rs = pstm.executeQuery();
		
		if (rs.next()) {
			resultUser = new User();
			resultUser.setUserID(rs.getInt("userID"));
			resultUser.setUsername(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
			
			// Add the current user into the database
			
			
			
		}
		
		return resultUser;
	}
}