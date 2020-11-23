package com.wartest.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import com.wartest.dao.TroopDao;
import com.wartest.dao.WartestDao;
import com.wartest.model.Location;
import com.wartest.model.Troop;
import com.wartest.model.User;
import com.wartest.model.Wartest;
import com.wartest.util.DbUtil;

public class WartestAddInterFrm extends JInternalFrame {
	private JTextField currentUserIDTxt;
	private JComboBox<String> locationJcb;
	private JComboBox<Troop> troop1Jcb;
	private JComboBox<Troop> troop2Jcb;
	private JComboBox<Troop> victorJcb;
	private JComboBox<Integer> armsLeftJcb;
	
	private DbUtil dbUtil = new DbUtil();
	private WartestDao wartestDao = new WartestDao();
	private TroopDao troopDao = new TroopDao();
	
	private User currentUser = null; // Used to track the current user

	/**
	 * Create the frame.
	 */
	public WartestAddInterFrm(User currentUser_) {
		this.currentUser = currentUser_;
		setTitle("Add a Wartest result");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 634, 386);
		
		JLabel lblNewLabel = new JLabel("Location");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		locationJcb = new JComboBox();
		
		JLabel lblNewLabel_1 = new JLabel("User ID");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		currentUserIDTxt = new JTextField();
		currentUserIDTxt.setEditable(false);
		currentUserIDTxt.setColumns(10);
		Integer cuid = currentUser.getUserID();
		currentUserIDTxt.setText(cuid.toString());
		
		JLabel lblNewLabel_2 = new JLabel("Troop 1");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		troop1Jcb = new JComboBox();
		
		JLabel lblNewLabel_2_1 = new JLabel("Troop 2");
		lblNewLabel_2_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		troop2Jcb = new JComboBox();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Result", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				warTestAddActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JButton btnNewButton_1 = new JButton("Confirm");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmTwoEngagedTroops(e);
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(48)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_2)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(troop1Jcb, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblNewLabel_2_1))
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(locationJcb, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(82)
											.addComponent(lblNewLabel_1)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(currentUserIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(troop2Jcb, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(btnNewButton_1))))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(274)
							.addComponent(btnNewButton)))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(locationJcb, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(currentUserIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(troop1Jcb, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(troop2Jcb, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1))
					.addGap(28)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(btnNewButton)
					.addGap(32))
		);
		
		JLabel lblNewLabel_3 = new JLabel("Victor");
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		victorJcb = new JComboBox();
		
		JLabel lblNewLabel_3_1 = new JLabel("Arms Left");
		lblNewLabel_3_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		armsLeftJcb = new JComboBox();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(victorJcb, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addGap(56)
					.addComponent(lblNewLabel_3_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(armsLeftJcb, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(victorJcb, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(armsLeftJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

		fillLocationAndTroopJcbs();
	}
	
	/**
	 * Add a Wartest to the Database
	 * @param event
	 */
	private void warTestAddActionPerformed(ActionEvent event) {
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
				JOptionPane.showMessageDialog(null, "Successfully Added a Wartest!");
				fillLocationAndTroopJcbs();
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
	 * Confirm Two Engaged Troops
	 * @param event
	 */
	private void confirmTwoEngagedTroops(ActionEvent event) {
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
	 * Fill the location Jcb and the two troop Jcbs
	 */
	private void fillLocationAndTroopJcbs() {
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
