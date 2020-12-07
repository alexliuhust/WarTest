package com.wartest.service;

import java.awt.event.ActionEvent;
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
import com.wartest.model.Lord;
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
	 * Find Troops with Races and Lords by current User ID; initialize MyTroopTable
	 */
	public static void fillMyTroopTable(
			JTextField troopNameTxt,
			JTextField troopMemoTxt,
			JTextField troopIDTxt,
			JTable selectedArmsTable,
			JTable myTroopTable, 
			User currentUser) {
		
		Integer currentUserId = currentUser.getUserID();
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
			
			ResultSet rs = troopDao.findTroopsByUserID_withRaceAndLord(con, currentUserId);
			while (rs.next()) {
				Vector<Object> v = new Vector<>();
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
	 * Add a Troop to the database
	 * @param event
	 */
	public static void addTroopActionPerformed(ActionEvent event, 
			JTextField currentUserIDTxt, 
			JTextField troopNameTxt,
			JTextField troopMemoTxt,
			JTable selectedArmsTable, 
			JComboBox<Lord> lordJcb) {
		
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
			if (troopNameTxt.getText().length() > 18) {
				JOptionPane.showMessageDialog(null, "Troop Name is too long!");
				return;
			}
			if (troopMemoTxt.getText().length() > 55) {
				JOptionPane.showMessageDialog(null, "Troop Memo is too long!");
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
	 * Update a selected Troop
	 * @param event
	 */
	public static void updateTroopActionPerformed(ActionEvent event, 
			JTextField troopIDTxt,
			JTextField currentUserIDTxt,
			JTextField troopNameTxt,
			JTextField troopMemoTxt,
			JTable selectedArmsTable,
			JTable myTroopTable,
			JComboBox<Lord> lordJcb,
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
			if (troopNameTxt.getText().length() > 18) {
				JOptionPane.showMessageDialog(null, "Troop Name is too long!");
				return;
			}
			if (troopMemoTxt.getText().length() > 55) {
				JOptionPane.showMessageDialog(null, "Troop Memo is too long!");
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
				if (ans[0] >= 0 && ans[1] >= 0 && ans[2] >= 1) {
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
	 * Delete a selected Troop
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
	
}
