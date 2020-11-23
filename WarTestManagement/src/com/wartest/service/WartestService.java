package com.wartest.service;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
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
	 * Show edit information when mouse pressed on Wartest Table
	 * @param event
	 */
	public static void mousePressedOnWartestTable(MouseEvent event,
			User currentUser,
			JTable wartestTable,
			JTextField warIDTxt,
			JComboBox<String> locationJcb,
			JComboBox<Troop> troop1Jcb,
			JComboBox<Troop> troop2Jcb,
			JComboBox<Troop> victorJcb,
			JComboBox<Integer> armsLeftJcb) {
		
		Connection con = null; 
		try {
			con = dbUtil.getCon();
			WartestFrmService.fillLocationAndTroopJcbs(currentUser, locationJcb, victorJcb, victorJcb, victorJcb, armsLeftJcb);
			
			int row = wartestTable.getSelectedRow();
			Integer WarID = (Integer) wartestTable.getValueAt(row, 0);
			warIDTxt.setText(WarID.toString());
			
			String troop1 = (String) wartestTable.getValueAt(row, 1);
			String troop2 = (String) wartestTable.getValueAt(row, 2);
			String location = (String) wartestTable.getValueAt(row, 3);
			String victor = (String) wartestTable.getValueAt(row, 4);
			Integer armsLeft = (Integer) wartestTable.getValueAt(row, 5);
			
			// Set locationJcb to the selected location
			for (int i = 0; i < locationJcb.getItemCount(); i++) {
				String currentLocation = locationJcb.getItemAt(i);
				if (currentLocation.equals(location))
					locationJcb.setSelectedIndex(i);
			}
			
			// Set troop1Jcb to the selected troop
			for (int i = 0; i < troop1Jcb.getItemCount(); i++) {
				Troop currentTroop = troop1Jcb.getItemAt(i);
				if (currentTroop.getName().equals(troop1))
					troop1Jcb.setSelectedIndex(i);
			}
			
			// Set troop2Jcb to the selected troop
			for (int i = 0; i < troop2Jcb.getItemCount(); i++) {
				Troop currentTroop = troop2Jcb.getItemAt(i);
				if (currentTroop.getName().equals(troop2))
					troop2Jcb.setSelectedIndex(i);
			}
			
			// Set victorJcb to the selected victor
			victorJcb.removeAllItems();
			victorJcb.addItem(new Troop(troop1));
			victorJcb.addItem(new Troop(troop2));
			if (troop1.equals(victor)) victorJcb.setSelectedIndex(0);
			if (troop2.equals(victor)) victorJcb.setSelectedIndex(1);
			
			// Set armsLeftJcb to the selected arms_left
			int count = wartestDao.countNumberOfArmsByTroopName(con, victor);
			armsLeftJcb.removeAllItems();
			for (Integer i = count; i >= 1; i--) {
				armsLeftJcb.addItem(i);
			}
			for (int i = 0; i < armsLeftJcb.getItemCount(); i++) {
				Integer currentNum = armsLeftJcb.getItemAt(i);
				if (currentNum.equals(armsLeft)) 
					armsLeftJcb.setSelectedIndex(i);
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
	
	// ----------------------------------------------------------------------------------------

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
				WartestFrmService.fillLocationAndTroopJcbs(currentUser, locationJcb, victorJcb, victorJcb, victorJcb, armsLeftJcb);
				victorJcb.removeAllItems();
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
	
}
