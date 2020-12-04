package com.wartest.service;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.wartest.dao.ArmDao;
import com.wartest.model.Arm;
import com.wartest.util.DbUtil;
import com.wartest.util.StringUtil;

public class ArmService {
	
	public static DbUtil dbUtil = new DbUtil();
	public static ArmDao armDao = new ArmDao();
	
	/**
	 * Mouse Pressing On Table Row Event Process
	 * @param event
	 */
	public static void armTableMousePressed(MouseEvent event, 
			JTable armTable,
			JTextField armNameTxt,
			JTextField armRaceTxt,
			JTextField armTypeTxt,
			JTextField costTxt,
			JTextField scaleTxt,
			JTextField hpTxt,
			JTextField attTxt,
			JTextField arTxt,
			JTextField spTxt) {
		
		int row = armTable.getSelectedRow();
		Integer armID = (Integer) armTable.getValueAt(row, 0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = armDao.findArmById(con, armID);
			while (rs.next()) {
				armNameTxt.setText(rs.getString("name"));
				armRaceTxt.setText(rs.getString("race"));
				armTypeTxt.setText(rs.getString("type"));
				Integer cost = rs.getInt("cost");
				costTxt.setText(cost.toString());
				Integer scale = rs.getInt("scale");
				scaleTxt.setText(scale.toString());
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
	 * Arm Search Event Process
	 * @param event
	 */
	public static void armSearchActionPerformed(ActionEvent event, 
			JTextField s_armNameTxt,
			JTextField s_armRaceTxt,
			JTextField s_armTypeTxt,
			JTable armTable) {
		
		String s_armName = s_armNameTxt.getText();
		String s_armRace = s_armRaceTxt.getText();
		String s_armType = s_armTypeTxt.getText();
		Arm arm = new Arm();
		arm.setName(StringUtil.replaceQuoteWithStar(s_armName));
		arm.setRace(StringUtil.replaceQuoteWithStar(s_armRace));
		arm.setType(StringUtil.replaceQuoteWithStar(s_armType));
		fillTable(arm, armTable);
	}
	
	/**
	 * Initialize Table
	 * @param arm
	 */
	public static void fillTable(Arm arm,
			JTable armTable) {
		
		DefaultTableModel dtm = (DefaultTableModel) armTable.getModel();
		dtm.setRowCount(0); // Clear table before every search
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = armDao.findArmsByNameOrRaceOrType(con, arm);
			while(rs.next()) {
				Vector<Object> v = new Vector<>();
				v.add(rs.getInt("armID"));
				v.add(rs.getString("name"));
				v.add(rs.getString("race"));
				v.add(rs.getString("type"));
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
