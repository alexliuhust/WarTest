package com.wartest.service;

import java.awt.event.ActionEvent;
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
import com.wartest.model.User;
import com.wartest.util.DbUtil;

public class TroopFrmService {
	
	public static DbUtil dbUtil = new DbUtil();       
	public static RaceDao raceDao = new RaceDao();    
	public static LordDao lordDao = new LordDao();    
	public static ArmDao armDao = new ArmDao();       
	public static TroopDao troopDao = new TroopDao();
	
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
	
	// -----------------------------------------------------------------------------------------------------

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
	 * Initialize MyTroopTable
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
			
			ResultSet rs = troopDao.findTroopsByUserName_withRaceAndLord(con, currentUserId);
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
