package com.wartest.service;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.wartest.dao.TroopDao;
import com.wartest.dao.WartestDao;
import com.wartest.model.Location;
import com.wartest.model.Troop;
import com.wartest.model.User;
import com.wartest.model.Wartest;
import com.wartest.util.DbUtil;

public class WartestService {
	
	public static DbUtil dbUtil = new DbUtil();
	public static WartestDao wartestDao = new WartestDao();
	public static TroopDao troopDao = new TroopDao();

	/**
	 * Add a Wartest to the Database
	 * @param event
	 */
	public static void warTestAddActionPerformed(ActionEvent event,
			User currentUser,
			JComboBox<String> locationJcb,
			JComboBox<Troop> troop1Jcb,
			JComboBox<Troop> troop2Jcb,
			JComboBox<Troop> victorJcb,
			JComboBox<Integer> armsLeftJcb) {
		
		Wartest wartest = new Wartest();
		wartest.setUserID(currentUser.getUserID());
		wartest.setLocation((String)locationJcb.getSelectedItem());
		Troop troop1 = (Troop) troop1Jcb.getSelectedItem();
		Troop troop2 = (Troop) troop2Jcb.getSelectedItem();
		wartest.setTroop1(troop1.getTroopID());
		wartest.setTroop2(troop2.getTroopID());
		Troop victor = (Troop) victorJcb.getSelectedItem();
		wartest.setVictor(victor.getTroopID());
		Integer arms_left = (Integer) armsLeftJcb.getSelectedItem();
		wartest.setArms_left(arms_left);
		
		Connection con = null; 
		try {
			con = dbUtil.getCon();
			
			int num = wartestDao.addAWartest(con, wartest);
			if (num == 1) {
				JOptionPane.showMessageDialog(null, "Successfully Added a Wartest!");
				fillLocationAndTroopJcbs(currentUser, locationJcb, victorJcb, victorJcb, victorJcb, armsLeftJcb);
				victorJcb.removeAllItems();
			} else {
				JOptionPane.showMessageDialog(null, "Failed to add a Wartest...");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to add a Wartest...");
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
	 * Confirm Two Engaged Troops
	 * @param event
	 */
	public static void confirmTwoEngagedTroops(ActionEvent event,
			JComboBox<Troop> troop1Jcb,
			JComboBox<Troop> troop2Jcb,
			JComboBox<Troop> victorJcb,
			JComboBox<Integer> armsLeftJcb) {
		
		Troop troop1 = (Troop) troop1Jcb.getSelectedItem();
		Troop troop2 = (Troop) troop2Jcb.getSelectedItem();
		if (troop1.getTroopID().equals(troop2.getTroopID())) {
			JOptionPane.showMessageDialog(null, "The two engaged troops cannot be identical!");
			return;
		}
		
		victorJcb.removeAllItems();
		victorJcb.addItem(troop1);
		victorJcb.addItem(troop2);
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			
			int count1 = wartestDao.countNumberOfArmsByTroopID(con, troop1.getTroopID());
			int count2 = wartestDao.countNumberOfArmsByTroopID(con, troop2.getTroopID());
			int maxCount = Math.max(count1, count2);
			armsLeftJcb.removeAllItems();
			for (Integer i = maxCount; i >= 1; i--) {
				armsLeftJcb.addItem(i);
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

	/**
	 * Fill the location Jcb and the two troop Jcbs
	 */
	public static void fillLocationAndTroopJcbs(
			User currentUser,
			JComboBox<String> locationJcb,
			JComboBox<Troop> troop1Jcb,
			JComboBox<Troop> troop2Jcb,
			JComboBox<Troop> victorJcb,
			JComboBox<Integer> armsLeftJcb) {
		
		victorJcb.removeAllItems();
		armsLeftJcb.removeAllItems();
		
		Location loc = new Location();
		for (String l : loc.getLocations()) {
			locationJcb.addItem(l);
		}
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			Troop troop = null;
			ResultSet rs = troopDao.findTroopsByUserID(con, currentUser.getUserID());
			while (rs.next()) {
				troop = new Troop();
				troop.setTroopID(rs.getInt("troopID"));
				troop.setName(rs.getString("name"));
				troop1Jcb.addItem(troop);
				troop2Jcb.addItem(troop);
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
