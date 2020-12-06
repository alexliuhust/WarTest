package com.wartest.service;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JComboBox;
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
import com.wartest.util.DbUtil;

public class TroopFrmService {
	
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
			JComboBox<Race> raceJcb,
			JComboBox<Lord> lordJcb, 
			JComboBox<Arm> armJcb) {
		
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
			raceJcb.removeAllItems();
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
				Vector<Object> v = new Vector<>();
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
			JComboBox<Arm> armJcb) {
		
		DefaultTableModel dtm = (DefaultTableModel) selectedArmsTable.getModel();
		Connection con = null;
		try {
			con = dbUtil.getCon();
			Vector<Object> v = new Vector<>();
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

	/**
	 * Race Filter Action
	 * @param event
	 */
	public static void filterRaceActionPerformed(ActionEvent event, 
			JComboBox<Race> raceJcb,
			JComboBox<Lord> lordJcb,
			JComboBox<Arm> armJcb) {
		
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
	 * Initialize all Jcbs
	 */
	public static void fillAllJcbs(
			JComboBox<Race> raceJcb,
			JComboBox<Lord> lordJcb,
			JComboBox<Arm> armJcb) {
		
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
