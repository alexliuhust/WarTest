package com.wartest.service;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.wartest.dao.TroopDao;
import com.wartest.dao.WartestDao;
import com.wartest.model.Location;
import com.wartest.model.Troop;
import com.wartest.model.User;
import com.wartest.util.DbUtil;

public class WartestFrmService {
	
	public static DbUtil dbUtil = new DbUtil();
	public static WartestDao wartestDao = new WartestDao();
	public static TroopDao troopDao = new TroopDao();

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
	 * Fill the Wartest Table
	 */
	public static void fillWartestTable(
			JTable wartestTable,
			User currentUser) {
		
		Integer currentUserId = currentUser.getUserID();
		DefaultTableModel dtm = (DefaultTableModel) wartestTable.getModel();
		dtm.setRowCount(0); // Clear table
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			
			ResultSet rs = wartestDao.findAllWartestsByUserID(con, currentUserId);
			while (rs.next()) {
				Vector<Object> v = new Vector<>();
				v.add(rs.getInt("warID"));
				v.add(rs.getString("troop1"));
				v.add(rs.getString("troop2"));
				v.add(rs.getString("location"));
				v.add(rs.getString("victor"));
				v.add(rs.getInt("arms_left"));
				dtm.addRow(v);
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
