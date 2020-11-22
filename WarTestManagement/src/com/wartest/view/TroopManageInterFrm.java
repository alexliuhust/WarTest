package com.wartest.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
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

public class TroopManageInterFrm extends JInternalFrame {
	private JTextField troopNameTxt;
	private JTextField currentUserIDTxt;
	private JTextField troopMemoTxt;
	private JTable selectedArmsTable;
	
	private JComboBox raceJcb;
	private JComboBox lordJcb;
	private JComboBox armJcb;
	
	private DbUtil dbUtil = new DbUtil();
	private RaceDao raceDao = new RaceDao();
	private LordDao lordDao = new LordDao();
	private ArmDao armDao = new ArmDao();
	private TroopDao troopDao = new TroopDao();
	
	private User currentUser = null; // Used to track the current user
	private JTextField troopIDTxt;
	private JTable myTroopTable;
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TroopManageInterFrm frame = new TroopManageInterFrm();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TroopManageInterFrm(User currentUser_) {
		this.currentUser = currentUser_;
		setTitle("Troop Management");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 577, 732);
		
		JLabel lblNewLabel = new JLabel("Troop Name");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		troopNameTxt = new JTextField();
		troopNameTxt.setColumns(10);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		currentUserIDTxt = new JTextField();
		currentUserIDTxt.setEditable(false);
		currentUserIDTxt.setColumns(10);
		Integer cuid = currentUser.getUserID();
		currentUserIDTxt.setText(cuid.toString());
		
		troopMemoTxt = new JTextField();
		troopMemoTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Select a Race");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		raceJcb = new JComboBox();
		
		JButton btnNewButton = new JButton("Apply Filter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//filterRaceActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JLabel lblNewLabel_1_1 = new JLabel("Select a Lord");
		lblNewLabel_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		lordJcb = new JComboBox();
		lordJcb.setMaximumRowCount(15);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Select Arm(s)");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		armJcb = new JComboBox();
		armJcb.setMaximumRowCount(40);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//deleteSelectedArm(e);
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JLabel lblNewLabel_2 = new JLabel("Selected Arms");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//addTroopActionPerformed(e);
			}
		});
		btnNewButton_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clearArmTable(e);
			}
		});
		btnNewButton_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JButton btnNewButton_3 = new JButton("Add Arm");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//addAnArmIntoArmTable(e);
			}
		});
		btnNewButton_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		
		JLabel lblNewLabel_3 = new JLabel("Memo");
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JScrollPane myTroopScrollPane = new JScrollPane();
		
		JLabel lblNewLabel_4 = new JLabel("Troop ID");
		lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		troopIDTxt = new JTextField();
		troopIDTxt.setEditable(false);
		troopIDTxt.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JLabel lblNewLabel_5 = new JLabel("My Troops");
		lblNewLabel_5.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(myTroopScrollPane, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel_1_1_1)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(armJcb, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnNewButton_3))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lordJcb, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel_1)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(raceJcb, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnNewButton))
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblNewLabel_3)
														.addGap(26)
														.addComponent(troopMemoTxt, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
													.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
														.addComponent(lblNewLabel)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(troopNameTxt, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)))
												.addGap(32)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblUserId, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(currentUserIDTxt, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblNewLabel_4)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(troopIDTxt, 0, 0, Short.MAX_VALUE)))))
										.addPreferredGap(ComponentPlacement.RELATED))
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(btnNewButton_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(btnNewButton_1)
													.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))))
										.addGap(4)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(168)
									.addComponent(lblNewLabel_2)))
							.addGap(52))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(227)
					.addComponent(lblNewLabel_5)
					.addContainerGap(288, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(35, Short.MAX_VALUE)
					.addComponent(lblNewLabel_5)
					.addGap(18)
					.addComponent(myTroopScrollPane, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(troopNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(currentUserIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUserId, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(troopMemoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4)
						.addComponent(troopIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(raceJcb, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lordJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(armJcb, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_1))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_4))
					.addGap(26))
		);
		
		myTroopTable = new JTable();
		myTroopTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mousePressedOnMyTroopTable(e);
			}
		});
		myTroopTable.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"ID", "Name", "Memo", "Lord", "Race"}) {
			boolean[] columnEditables = new boolean[] {false, false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		myTroopTable.getColumnModel().getColumn(0).setPreferredWidth(5);
		myTroopTable.getColumnModel().getColumn(1).setPreferredWidth(30);
		myTroopTable.getColumnModel().getColumn(2).setPreferredWidth(40);
		myTroopTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		myTroopTable.getColumnModel().getColumn(4).setPreferredWidth(40);
		myTroopScrollPane.setViewportView(myTroopTable);
		
		selectedArmsTable = new JTable();
		selectedArmsTable.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"ID", "Name", "Race", "Type"}) {
			boolean[] columnEditables = new boolean[] {false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		selectedArmsTable.getColumnModel().getColumn(0).setPreferredWidth(5);
		selectedArmsTable.getColumnModel().getColumn(1).setPreferredWidth(70);
		selectedArmsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		selectedArmsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		scrollPane.setViewportView(selectedArmsTable);
		
		getContentPane().setLayout(groupLayout);
		
		this.fillMyTroopTable();
	}
	
	
	
	
	
	
	
	
	/**
	 * Show edit information when mouse pressed on MyTroopTable
	 * @param event
	 */
	private void mousePressedOnMyTroopTable(MouseEvent event) {
		Connection con = null;
		try {
			con = dbUtil.getCon();
			
			int row = this.myTroopTable.getSelectedRow();
			Integer troopID = (Integer) this.myTroopTable.getValueAt(row, 0);
			this.troopIDTxt.setText(troopID.toString());
			this.troopNameTxt.setText((String) this.myTroopTable.getValueAt(row, 1));
			this.troopMemoTxt.setText((String) this.myTroopTable.getValueAt(row, 2));
			
			// Set the race Jcb to the specific item
			Race race = null;
			ResultSet rs = raceDao.findAllRaces(con);
			while (rs.next()) {
				race = new Race();
				race.setRace(rs.getString("race"));
				this.raceJcb.addItem(race);
			}
			String raceName = (String) this.myTroopTable.getValueAt(row, 4);
			for (int i = 0; i < this.raceJcb.getItemCount(); i++) {
				Race currentRace = (Race) this.raceJcb.getItemAt(i);
				if (currentRace.getRace().equals(raceName)) {
					this.raceJcb.setSelectedIndex(i);
				}
			}
			
			// Set the Lord Jcb to the specific item
			Lord lord = null;
			rs = lordDao.findLordByRace(con, raceName);
			this.lordJcb.removeAllItems();
			while (rs.next()) {
				lord = new Lord();
				lord.setLordID(rs.getInt("lordID"));
				lord.setName(rs.getString("name"));
				lord.setRace(raceName);
				this.lordJcb.addItem(lord);
			}
			String lordName = (String) this.myTroopTable.getValueAt(row, 3);
			for (int i = 0; i < this.lordJcb.getItemCount(); i++) {
				Lord currentLord = (Lord) this.lordJcb.getItemAt(i);
				if (currentLord.getName().equals(lordName)) {
					this.lordJcb.setSelectedIndex(i);
				}
			}
			
			// Filter the Arm Jcb to the specific range
			Arm arm = null;
			rs = armDao.findArmByRace(con, raceName);
			this.armJcb.removeAllItems();
			while (rs.next()) {
				arm = new Arm();
				arm.setArmID(rs.getInt("armID"));
				arm.setName(rs.getString("name"));
				arm.setRace(raceName);
				arm.setType(rs.getString("type"));
				this.armJcb.addItem(arm);
			}
			
			// Show the arms on the selected Arms Table
			String tID = this.troopIDTxt.getText();
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

	/**
	 * Initialize MyTroopTable
	 */
	public void fillMyTroopTable() {
		DefaultTableModel dtm = (DefaultTableModel) myTroopTable.getModel();
		dtm.setRowCount(0); // Clear table
		Connection con = null;
		try {
			con = dbUtil.getCon();
			Integer currentUserId = this.currentUser.getUserID();
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
	
}
