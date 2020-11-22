package com.wartest.service;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.wartest.dao.ArmDao;
import com.wartest.dao.LordDao;
import com.wartest.dao.RaceDao;
import com.wartest.dao.TroopDao;
import com.wartest.model.Arm;
import com.wartest.model.Lord;
import com.wartest.model.Race;
import com.wartest.model.Troop;
import com.wartest.model.User;
import com.wartest.util.DbUtil;
import com.wartest.util.StringUtil;

public class TroopService {          
	                                    
	public static DbUtil dbUtil = new DbUtil();       
	public static RaceDao raceDao = new RaceDao();    
	public static LordDao lordDao = new LordDao();    
	public static ArmDao armDao = new ArmDao();       
	public static TroopDao troopDao = new TroopDao();
	
	/**
	 * Show edit information when mouse pressed on MyTroopTable
	 * @param event
	 */
	public static void mousePressedOnMyTroopTable(MouseEvent event, 
			JTextField troopIDTxt,
			JTextField troopNameTxt,
			JTextField troopMemoTxt,
			JTable selectedArmsTable, 
			JTable myTroopTable,
			JComboBox raceJcb,
			JComboBox lordJcb, 
			JComboBox armJcb) {
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			
			int row = myTroopTable.getSelectedRow();
			Integer troopID = (Integer) myTroopTable.getValueAt(row, 0);
			troopIDTxt.setText(troopID.toString());
			troopNameTxt.setText((String) myTroopTable.getValueAt(row, 1));
			troopMemoTxt.setText((String) myTroopTable.getValueAt(row, 2));
			
			// Set the race Jcb to the specific item
			Race race = null;
			ResultSet rs = raceDao.findAllRaces(con);
			while (rs.next()) {
				race = new Race();
				race.setRace(rs.getString("race"));
				raceJcb.addItem(race);
			}
			String raceName = (String) myTroopTable.getValueAt(row, 4);
			for (int i = 0; i < raceJcb.getItemCount(); i++) {
				Race currentRace = (Race) raceJcb.getItemAt(i);
				if (currentRace.getRace().equals(raceName)) {
					raceJcb.setSelectedIndex(i);
				}
			}
			
			// Set the Lord Jcb to the specific item
			Lord lord = null;
			rs = lordDao.findLordByRace(con, raceName);
			lordJcb.removeAllItems();
			while (rs.next()) {
				lord = new Lord();
				lord.setLordID(rs.getInt("lordID"));
				lord.setName(rs.getString("name"));
				lord.setRace(raceName);
				lordJcb.addItem(lord);
			}
			String lordName = (String) myTroopTable.getValueAt(row, 3);
			for (int i = 0; i < lordJcb.getItemCount(); i++) {
				Lord currentLord = (Lord) lordJcb.getItemAt(i);
				if (currentLord.getName().equals(lordName)) {
					lordJcb.setSelectedIndex(i);
				}
			}
			
			// Filter the Arm Jcb to the specific range
			Arm arm = null;
			rs = armDao.findArmByRace(con, raceName);
			armJcb.removeAllItems();
			while (rs.next()) {
				arm = new Arm();
				arm.setArmID(rs.getInt("armID"));
				arm.setName(rs.getString("name"));
				arm.setRace(raceName);
				arm.setType(rs.getString("type"));
				armJcb.addItem(arm);
			}
			
			// Show the arms on the selected Arms Table
			String tID = troopIDTxt.getText();
			rs = armDao.findArmsByTroopID(con, Integer.parseInt(tID));
			DefaultTableModel dtm = (DefaultTableModel) selectedArmsTable.getModel();
			dtm.setRowCount(0); // Clear table
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("armID"));
				v.add(rs.getString("name"));
				v.add(rs.getString("race"));
				v.add(rs.getString("type"));
				dtm.addRow(v);
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
	
	// -----------------------------------------------------------------------------------------------------
	
	/**
	 * Add a Troop to the database
	 * @param event
	 */
	public static void addTroopActionPerformed(ActionEvent event, 
			JTextField currentUserIDTxt, 
			JTextField troopNameTxt,
			JTextField troopMemoTxt,
			JTable selectedArmsTable, 
			JComboBox lordJcb) {
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			
			if (StringUtil.isEmpty(troopNameTxt.getText())) {
				JOptionPane.showMessageDialog(null, "Troop Name Cannot Be Empty!");
				return;
			}
			if (StringUtil.isEmpty(troopMemoTxt.getText())) {
				JOptionPane.showMessageDialog(null, "Troop Memo Cannot Be Empty!");
				return;
			}
			
			ResultSet rs = troopDao.findTroopByTroopName(con, troopNameTxt.getText());
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "You have used this troop name!\nTry another one!");
				troopNameTxt.setText("My troop");
			} else if (selectedArmsTable.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "You have to select at least one Arm!");
			} else {
				Troop troop = new Troop();
				troop.setName(troopNameTxt.getText());
				troop.setMemo(troopMemoTxt.getText());
				String userID = currentUserIDTxt.getText();
				troop.setUserID(Integer.parseInt(userID));
				Lord selectedLord = (Lord) lordJcb.getSelectedItem();
				troop.setLord(selectedLord.getLordID());
				
				List<Integer> armIDs = new ArrayList<>();
				for (int i = 0; i < selectedArmsTable.getRowCount(); i++) {
					armIDs.add((Integer)selectedArmsTable.getValueAt(i, 0));
				}
				troop.setArms(armIDs);
				
				int[] ans = troopDao.addOneTroop(con, troop);
				if (ans[0] == 1 && ans[1] >= 1) {
					JOptionPane.showMessageDialog(null, "Successfully Added a Troop!");
				} else {
					JOptionPane.showMessageDialog(null, "Failed to add a troop...");
				}
			}
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to add a troop...");
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
	 * Update a Troop
	 * @param event
	 */
	public static void updateTroopActionPerformed(ActionEvent event, 
			JTextField troopIDTxt,
			JTextField currentUserIDTxt,
			JTextField troopNameTxt,
			JTextField troopMemoTxt,
			JTable selectedArmsTable,
			JTable myTroopTable,
			JComboBox lordJcb,
			User currentUser) {
		
		if (StringUtil.isEmpty(troopIDTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Please select a record!");
			return;
		}
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			
			if (StringUtil.isEmpty(troopNameTxt.getText())) {
				JOptionPane.showMessageDialog(null, "Troop Name Cannot Be Empty!");
				return;
			}
			if (StringUtil.isEmpty(troopMemoTxt.getText())) {
				JOptionPane.showMessageDialog(null, "Troop Memo Cannot Be Empty!");
				return;
			}
			Integer troopID = Integer.parseInt(troopIDTxt.getText());
			ResultSet rs = troopDao.findTroopByTroopNameButNotThisID(
					con, troopNameTxt.getText(), troopID);
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "You have used this troop name!\nTry another one!");
				troopNameTxt.setText("My troop");
			} else if (selectedArmsTable.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "You have to select at least one Arm!");
			} else {
				Troop troop = new Troop();
				troop.setName(troopNameTxt.getText());
				troop.setMemo(troopMemoTxt.getText());
				Lord selectedLord = (Lord) lordJcb.getSelectedItem();
				troop.setLord(selectedLord.getLordID());
				troop.setTroopID(troopID);
				
				List<Integer> armIDs = new ArrayList<>();
				for (int i = 0; i < selectedArmsTable.getRowCount(); i++) {
					armIDs.add((Integer)selectedArmsTable.getValueAt(i, 0));
				}
				troop.setArms(armIDs);
				
				int[] ans = troopDao.updateOneTroop(con, troop);
				if (ans[0] == 1 && ans[1] >= 1) {
					JOptionPane.showMessageDialog(null, "Successfully Updated a Troop!");
					fillMyTroopTable(troopNameTxt, troopMemoTxt, troopIDTxt, 
							selectedArmsTable, myTroopTable, currentUser);
				} else {
					JOptionPane.showMessageDialog(null, "Failed to update a troop");
				}
			}
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to update a troop...");
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
	 * Delete selected Troop
	 * @param event
	 */
	public static void deletedTroopActionPerformed(ActionEvent event, 
			JTextField troopIDTxt,
			JTextField troopNameTxt,
			JTextField troopMemoTxt,
			JTable selectedArmsTable,
			JTable myTroopTable,
			User currentUser) {
		
		String troopID = troopIDTxt.getText();
		if (StringUtil.isEmpty(troopID)) {
			JOptionPane.showMessageDialog(null, "Please select a record!");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "Are you sure to delete this record?");
		if (n == 0) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				int[] ans = troopDao.deleteTroop(con, Integer.parseInt(troopID));
				if (ans[0] >= 0 && ans[1] >= 0 && ans[2] == 1) {
					JOptionPane.showMessageDialog(null, "Seuccessfully Deleted a Troop!");
					fillMyTroopTable(troopNameTxt, troopMemoTxt, troopIDTxt, 
							selectedArmsTable, myTroopTable, currentUser);
				} else {
					JOptionPane.showMessageDialog(null, "Failed to delete a Troop...");
				}
				
			} catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Failed to delete a Troop...");
			} finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	// -----------------------------------------------------------------------------------------------------
	
	/**
	 * Delete selected Arm
	 * @param event
	 */
	public static void deleteSelectedArm(ActionEvent event, 
			JTable selectedArmsTable) {
		
		DefaultTableModel dtm = (DefaultTableModel) selectedArmsTable.getModel();
		int[] rows = selectedArmsTable.getSelectedRows();
		for(int i = 0; i < rows.length; i++) {
			dtm.removeRow(rows[i]-i);
		}
	}
	
	/**
	 * Clear the selected Arms Table
	 * @param event
	 */
	public static void clearArmTable(ActionEvent event, 
			JTable selectedArmsTable) {
		
		DefaultTableModel dtm = (DefaultTableModel) selectedArmsTable.getModel();
		dtm.setRowCount(0); // Clear table
	}

	/**
	 * Add a selected Arm into the armTable
	 * @param event
	 */
	public static void addAnArmIntoArmTable(ActionEvent event, 
			JTable selectedArmsTable, 
			JComboBox armJcb) {
		
		DefaultTableModel dtm = (DefaultTableModel) selectedArmsTable.getModel();
		Connection con = null;
		try {
			con = dbUtil.getCon();
			Vector v = new Vector();
			Arm selectedArm = (Arm) armJcb.getSelectedItem();
			v.add(selectedArm.getArmID());
			v.add(selectedArm.getName());
			v.add(selectedArm.getRace());
			v.add(selectedArm.getType());
			dtm.addRow(v);
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
	
	// -----------------------------------------------------------------------------------------------------

	/**
	 * Race Filter Action
	 * @param event
	 */
	public static void filterRaceActionPerformed(ActionEvent event, 
			JComboBox raceJcb,
			JComboBox lordJcb,
			JComboBox armJcb) {
		
		Connection con = null;
		Lord lord = null;
		Arm arm = null;
		String racename = raceJcb.getSelectedItem().toString();
		try {
			con = dbUtil.getCon();
			
			ResultSet rs = lordDao.findLordByRace(con, racename);
			lordJcb.removeAllItems();
			while (rs.next()) {
				lord = new Lord();
				lord.setLordID(rs.getInt("lordID"));
				lord.setName(rs.getString("name"));
				lord.setRace(racename);
				lordJcb.addItem(lord);
			}
			
			rs = armDao.findArmByRace(con, racename);
			armJcb.removeAllItems();
			while (rs.next()) {
				arm = new Arm();
				arm.setArmID(rs.getInt("armID"));
				arm.setName(rs.getString("name"));
				arm.setRace(racename);
				arm.setType(rs.getString("type"));
				armJcb.addItem(arm);
			}
			
		}catch (Exception e) {
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
	 * Initialize MyTroopTable
	 */
	public static void fillMyTroopTable(
			JTextField troopNameTxt,
			JTextField troopMemoTxt,
			JTextField troopIDTxt,
			JTable selectedArmsTable,
			JTable myTroopTable, 
			User currentUser) {
		
		DefaultTableModel dtm = (DefaultTableModel) selectedArmsTable.getModel();
		dtm.setRowCount(0); // Clear table
		dtm = (DefaultTableModel) myTroopTable.getModel();
		dtm.setRowCount(0); // Clear table
		
		troopIDTxt.setText("");
		troopNameTxt.setText("");
		troopMemoTxt.setText("");
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			Integer currentUserId = currentUser.getUserID();
			ResultSet rs = troopDao.findTroopsByUserName_withRaceAndLord(con, currentUserId);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("troopID"));
				v.add(rs.getString("name"));
				v.add(rs.getString("memo"));
				v.add(rs.getString("lord"));
				v.add(rs.getString("race"));
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
	 * Initialize all Jcbs
	 */
	public static void fillAllJcbs(
			JComboBox raceJcb,
			JComboBox lordJcb,
			JComboBox armJcb) {
		
		Connection con = null;
		Race race = null;
		Lord lord = null;
		Arm arm = null;
		try {
			con = dbUtil.getCon();
			
			ResultSet rs = raceDao.findAllRaces(con);
			while (rs.next()) {
				race = new Race();
				race.setRace(rs.getString("race"));
				raceJcb.addItem(race);
			}
			
			rs = lordDao.findLordByRace(con, raceJcb.getSelectedItem().toString());
			while (rs.next()) {
				lord = new Lord();
				lord.setLordID(rs.getInt("lordID"));
				lord.setName(rs.getString("name"));
				lord.setRace(raceJcb.getSelectedItem().toString());
				lordJcb.addItem(lord);
			}
			
			rs = armDao.findArmByRace(con, raceJcb.getSelectedItem().toString());
			while (rs.next()) {
				arm = new Arm();
				arm.setArmID(rs.getInt("armID"));
				arm.setName(rs.getString("name"));
				arm.setRace(raceJcb.getSelectedItem().toString());
				arm.setType(rs.getString("type"));
				armJcb.addItem(arm);
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
