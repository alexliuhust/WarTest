package com.wartest.service;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.wartest.dao.LordDao;
import com.wartest.model.Lord;
import com.wartest.util.DbUtil;
import com.wartest.util.StringUtil;

public class LordService {
	
	public static DbUtil dbUtil = new DbUtil();   
	public static LordDao lordDao = new LordDao();
	
	/**
	 * Mouse Pressing On Table Row Event Process
	 * @param event
	 */
	public static void lordTableMousePressed(MouseEvent event,
			JTextField s_lordNameTxt,
			JTextField s_lordRaceTxt,
			JTable lordtable,   
			JTextField lordNameTxt,
			JTextField lordRaceTxt,
			JTextField hpTxt,    
			JTextField attTxt,   
			JTextField arTxt,  
			JTextField spTxt) {
		
		int row = lordtable.getSelectedRow();
		Integer lordID = (Integer) lordtable.getValueAt(row, 0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = lordDao.findLordByID(con, lordID);
			while (rs.next()) {
				lordNameTxt.setText(rs.getString("name"));
				lordRaceTxt.setText(rs.getString("race"));
				Integer hp = rs.getInt("hp");
				hpTxt.setText(hp.toString());
				Integer att = rs.getInt("attack");
				attTxt.setText(att.toString());
				Integer ar = rs.getInt("armor");
				arTxt.setText(ar.toString());
				Integer sp = rs.getInt("speed");
				spTxt.setText(sp.toString());
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
	 * Lord Search Event Process
	 * @param event
	 */
	public static void lordSearchActionPerformed(ActionEvent event,
			JTextField s_lordNameTxt,
			JTextField s_lordRaceTxt,
			JTable lordtable) {
		
		String s_lordName = s_lordNameTxt.getText();
		String s_lordRace = s_lordRaceTxt.getText();
		Lord lord = new Lord();
		lord.setName(StringUtil.replaceQuoteWithStar(s_lordName));
		lord.setRace(StringUtil.replaceQuoteWithStar(s_lordRace));
		fillTable(lord, lordtable);
	}
	
	
	/**
	 * Initialize Table
	 * @param lord
	 */
	public static void fillTable(Lord lord, 
			JTable lordtable) {
		
		DefaultTableModel dtm = (DefaultTableModel) lordtable.getModel();
		dtm.setRowCount(0); // Clear table before every search
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = lordDao.findLordsByNameOrRace(con, lord);
			while(rs.next()) {
				Vector<Object> v = new Vector<>();
				v.add(rs.getInt("lordID"));
				v.add(rs.getString("name"));
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

}
