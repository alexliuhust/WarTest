package com.wartest.service;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.wartest.dao.TroopDao;
import com.wartest.dao.WartestDao;
import com.wartest.model.Troop;
import com.wartest.model.User;
import com.wartest.model.Wartest;
import com.wartest.util.DbUtil;
import com.wartest.util.StringUtil;

public class WartestService {
	
	public static DbUtil dbUtil = new DbUtil();
	public static WartestDao wartestDao = new WartestDao();
	public static TroopDao troopDao = new TroopDao();
	
	/**
	 * Find All Wartests By User ID; initialize the Wartest Table
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
				WartestFrmService.fillLocationAndTroopJcbs(currentUser, locationJcb, troop1Jcb, troop2Jcb, victorJcb, armsLeftJcb);
				JOptionPane.showMessageDialog(null, "Successfully Added a Wartest!");
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
	 * Delete a Wartest from the database
	 * @param event
	 */
	public static void wartestDeleteActionPerformed(ActionEvent event,
			User currentUser,
			JTextField warIDTxt,
			JTable wartestTable,
			JComboBox<String> locationJcb,
			JComboBox<Troop> troop1Jcb,
			JComboBox<Troop> troop2Jcb,
			JComboBox<Troop> victorJcb,
			JComboBox<Integer> armsLeftJcb) {
		
		String warID = warIDTxt.getText();
		if (StringUtil.isEmpty(warID)) {
			JOptionPane.showMessageDialog(null, "Please select a record!");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "Are you sure to delete this record?");
		if (n == 0) {
			Connection con = null; 
			try {
				con = dbUtil.getCon();
				
				int num = wartestDao.deleteAWartest(con, Integer.parseInt(warID));
				if (num == 1) {
					warIDTxt.setText("");
					fillWartestTable(wartestTable, currentUser);
					WartestFrmService.fillLocationAndTroopJcbs(currentUser, locationJcb, troop1Jcb, troop2Jcb, victorJcb, armsLeftJcb);
					JOptionPane.showMessageDialog(null, "Seuccessfully Deleted a Wartest!");
				} else {
					warIDTxt.setText("");
					JOptionPane.showMessageDialog(null, "Failed to delete a Wartest...");
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Failed to delete a Wartest...");
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
	
	/**
	 * Update a Wartest
	 * @param event
	 */
	public static void wartestUpdateActionPerformed(ActionEvent event,
			User currentUser,
			JTextField warIDTxt,
			JTable wartestTable,
			JComboBox<String> locationJcb,
			JComboBox<Troop> troop1Jcb,
			JComboBox<Troop> troop2Jcb,
			JComboBox<Troop> victorJcb,
			JComboBox<Integer> armsLeftJcb) {
		
		if (StringUtil.isEmpty(warIDTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Please select a record!");
			return;
		}
		
		Integer warID = Integer.parseInt(warIDTxt.getText());
		String location = (String) locationJcb.getSelectedItem();
		Integer amrsLeft = (Integer) armsLeftJcb.getSelectedItem();
		
		Troop troop1 = (Troop) troop1Jcb.getSelectedItem();
		String troop1Name = troop1.getName();
		Troop troop2 = (Troop) troop2Jcb.getSelectedItem();
		String troop2Name = troop2.getName();
		Troop victor = (Troop) victorJcb.getSelectedItem();
		String victorName = victor.getName();
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = null;
			
			// Get the troopID of troop1, troop2, victor
			rs = troopDao.findTroopByTroopName(con, troop1Name);
			Integer troop1ID = null;
			if (rs.next()) troop1ID = rs.getInt("troopID");
			
			rs = troopDao.findTroopByTroopName(con, troop2Name);
			Integer troop2ID = null;
			if (rs.next()) troop2ID = rs.getInt("troopID");
			
			rs = troopDao.findTroopByTroopName(con, victorName);
			Integer victorID = null;
			if (rs.next()) victorID = rs.getInt("troopID");
			
			// Update Wartest
			Wartest wartest = new Wartest(warID, troop1ID, troop2ID, location, victorID, amrsLeft);
			int num = wartestDao.updateAWartest(con, wartest);
			if (num == 1) {
				warIDTxt.setText("");
				fillWartestTable(wartestTable, currentUser);
				WartestFrmService.fillLocationAndTroopJcbs(currentUser, locationJcb, troop1Jcb, troop2Jcb, victorJcb, armsLeftJcb);
				JOptionPane.showMessageDialog(null, "Seuccessfully Updated a Wartest!");
			} else {
				warIDTxt.setText("");
				JOptionPane.showMessageDialog(null, "Failed to update a Wartest...");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to update a Wartest...");
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
