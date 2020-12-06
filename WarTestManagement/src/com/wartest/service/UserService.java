package com.wartest.service;

import java.awt.event.ActionEvent;
import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.wartest.dao.UserDao;
import com.wartest.model.User;
import com.wartest.util.DbUtil;
import com.wartest.util.StringUtil;
import com.wartest.view.LogInFrm;
import com.wartest.view.MainFrm;
import com.wartest.view.RegisterFrm;

public class UserService {
	
	public static DbUtil dbUtil = new DbUtil();   
	public static UserDao userDao = new UserDao();
	
	/**
	 * Action Event: Register 
	 * @param event
	 */
	public static void registerActionPerformed(ActionEvent event, 
			JTextField usernameTxt,   
			JPasswordField passwordTXT,
			RegisterFrm registerFrm) {
		
		String userName = usernameTxt.getText();
		String password = (new String(passwordTXT.getPassword()));
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "Username cannot be EMPTY!");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "Password cannot be EMPTY!");
			return;
		}
		if (userName.length() > 12) {
			JOptionPane.showMessageDialog(null, "Username should be within 12 characters!");
			return;
		}
		if (password.length() > 12) {
			JOptionPane.showMessageDialog(null, "Password should be within 12 characters!");
			return;
		}
		User user = new User(userName, password);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int num = userDao.register(con, user);
			if (num == 1) {
				JOptionPane.showMessageDialog(null, "Successfully Registered");
				registerFrm.dispose();
				new LogInFrm(userName, password).setVisible(true);
			} else if (num == -1) {
				JOptionPane.showMessageDialog(null, "This username has already existed!\nPlease try another one!");
				usernameTxt.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "Failed to sign you up...");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to sign you up...");
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Action Event: Log In
	 * @param e
	 */
	public static void loginActionPerformed(ActionEvent event, 
			JTextField usernameTxt,   
			JPasswordField passwordTXT,
			LogInFrm logInFrm) {
		
		String userName = usernameTxt.getText();
		String password = (new String(passwordTXT.getPassword()));
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "Username cannot be EMPTY!");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "Password cannot be EMPTY!");
			return;
		}
		if (userName.length() > 12) {
			JOptionPane.showMessageDialog(null, "Username should be within 12 characters!");
			return;
		}
		if (password.length() > 12) {
			JOptionPane.showMessageDialog(null, "Password should be within 12 characters!");
			return;
		}
		User user = new User(userName, password);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			User currentUser = userDao.login(con, user);
			if (currentUser != null) {
				JOptionPane.showMessageDialog(null, currentUser.getUsername() + ", Logged You In!");
				logInFrm.dispose();
				new MainFrm(currentUser).setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "Incorrect username or password!");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to log you in...");
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
