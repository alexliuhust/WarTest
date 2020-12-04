package com.wartest.service;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.wartest.dao.RaceDao;
import com.wartest.model.Race;
import com.wartest.util.DbUtil;
import com.wartest.util.StringUtil;

public class RaceService {
	
	static DbUtil dbUtil = new DbUtil();   
	static RaceDao raceDao = new RaceDao();
	
	/**
	 * Mouse Pressing On Table Row Event Process
	 * @param event
	 */
	public static void raceTableMousePressed(MouseEvent event, 
			JTable raceTable, 
			JTextField raceNameTxt, 
			JTextField raceLocationTxt, 
			JTextArea raceDescTxt) {
		
		int row = raceTable.getSelectedRow();
		raceNameTxt.setText((String)raceTable.getValueAt(row, 0));
		raceLocationTxt.setText((String)raceTable.getValueAt(row, 1));
		raceDescTxt.setText((String)raceTable.getValueAt(row, 2)); 
	}
	
	/**
	 * Race Search Event Process
	 * @param event
	 */
	public static void raceSearchActionPerformed(ActionEvent event, 
			JTextField s_raceNameTxt,
			JTable raceTable) {
		
		String s_raceName = s_raceNameTxt.getText();
		Race race = new Race();
		race.setRace(StringUtil.replaceQuoteWithStar(s_raceName));
		fillTable(race, raceTable);
	}
	
	/**
	 * Initialize Table
	 * @param race
	 */
	public static void fillTable(Race race, 
			JTable raceTable) {
		
		DefaultTableModel dtm = (DefaultTableModel) raceTable.getModel();
		dtm.setRowCount(0); // Clear table before every search
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = raceDao.findRacesByName(con, race);
			while(rs.next()) {
				Vector<Object> v = new Vector<>();
				v.add(rs.getString("race"));
				v.add(rs.getString("location"));
				v.add(rs.getString("description"));
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

}
