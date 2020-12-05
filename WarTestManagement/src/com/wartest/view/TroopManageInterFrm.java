package com.wartest.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import com.wartest.model.Arm;
import com.wartest.model.Lord;
import com.wartest.model.Race;
import com.wartest.model.User;
import com.wartest.service.TroopFrmService;
import com.wartest.service.TroopService;

public class TroopManageInterFrm extends JInternalFrame {
	private JTextField troopNameTxt;
	private JTextField currentUserIDTxt;
	private JTextField troopMemoTxt;
	private JTable selectedArmsTable;
	private JTextField troopIDTxt;
	private JTable myTroopTable;
	
	private JComboBox<Race> raceJcb;
	private JComboBox<Lord> lordJcb;
	private JComboBox<Arm> armJcb;
	
	private User currentUser = null; // Used to track the current user
	

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
		
		raceJcb = new JComboBox<>();
		
		JButton btnNewButton = new JButton("Apply Filter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TroopFrmService.filterRaceActionPerformed(e, raceJcb, lordJcb, armJcb);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JLabel lblNewLabel_1_1 = new JLabel("Select a Lord");
		lblNewLabel_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		lordJcb = new JComboBox<>();
		lordJcb.setMaximumRowCount(15);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Select Arm(s)");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		armJcb = new JComboBox<>();
		armJcb.setMaximumRowCount(40);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_2 = new JLabel("Selected Arms");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		
		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TroopFrmService.clearArmTable(e, selectedArmsTable);
			}
		});
		btnNewButton_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TroopFrmService.deleteSelectedArm(e, selectedArmsTable);
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JButton btnNewButton_3 = new JButton("Add Arm");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TroopFrmService.addAnArmIntoArmTable(e, selectedArmsTable, armJcb);
			}
		});
		btnNewButton_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		
		JLabel lblNewLabel_3 = new JLabel("Memo");
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JScrollPane myTroopScrollPane = new JScrollPane();
		
		JLabel lblNewLabel_4 = new JLabel("Troop ID");
		lblNewLabel_4.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		
		JButton btnNewButton_2 = new JButton("Submit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TroopService.updateTroopActionPerformed(e, troopIDTxt, currentUserIDTxt, 
						troopNameTxt, troopMemoTxt, selectedArmsTable, myTroopTable, lordJcb, currentUser);
			}
		});
		btnNewButton_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TroopService.deletedTroopActionPerformed(e, troopIDTxt, troopNameTxt, 
						troopMemoTxt, selectedArmsTable, myTroopTable, currentUser);
			}
		});
		btnNewButton_4.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		
		troopIDTxt = new JTextField();
		troopIDTxt.setEditable(false);
		troopIDTxt.setColumns(10);
		
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
				TroopFrmService.mousePressedOnMyTroopTable(e, troopIDTxt, troopNameTxt, troopMemoTxt, 
						selectedArmsTable, myTroopTable, raceJcb, lordJcb, armJcb);
			}
		});
		myTroopTable.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"ID", "Name", "Memo", "Lord", "Race"}) {
			boolean[] columnEditables = new boolean[] {false, false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		myTroopTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		myTroopTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		myTroopTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		myTroopTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		myTroopTable.getColumnModel().getColumn(4).setPreferredWidth(92);
		// myTroopTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
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
		
		
		TroopService.fillMyTroopTable(troopNameTxt, troopMemoTxt, troopIDTxt, selectedArmsTable, myTroopTable, currentUser);
	}
	
}
