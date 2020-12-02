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
				JOptionPane.showMessageDialog(null, "Fail to sign you in...");
			}
		} catch (Exception e) {
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
