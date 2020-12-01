package com.wartest.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import com.wartest.model.User;
import com.wartest.service.TroopFrmService;
import com.wartest.service.TroopService;

public class TroopAddInterFrm extends JInternalFrame {
	private JTextField troopNameTxt;
	private JTextField currentUserIDTxt;
	private JTextField troopMemoTxt;
	private JTable selectedArmsTable;
	
	private JComboBox raceJcb;
	private JComboBox lordJcb;
	private JComboBox armJcb;
	
	private User currentUser = null; // Used to track the current user


	/**
	 * Create the frame.
	 */
	public TroopAddInterFrm(User currentUser_) {
		this.currentUser = currentUser_;
		setIconifiable(true);
		setClosable(true);
		setTitle("Add A Troop");
		setBounds(100, 100, 550, 619);
		
		JLabel lblNewLabel = new JLabel("Troop Name");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		troopNameTxt = new JTextField();
		troopNameTxt.setColumns(10);
		troopNameTxt.setText("My troop");
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		currentUserIDTxt = new JTextField();
		currentUserIDTxt.setEditable(false);
		currentUserIDTxt.setColumns(10);
		Integer cuid = currentUser.getUserID();
		currentUserIDTxt.setText(cuid.toString());
		
		JLabel lblMemo = new JLabel("Memo");
		lblMemo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		troopMemoTxt = new JTextField();
		troopMemoTxt.setColumns(10);
		troopMemoTxt.setText("My memo");
		
		JLabel lblNewLabel_1 = new JLabel("Select a Race");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		raceJcb = new JComboBox();
		
		JButton btnNewButton = new JButton("Apply Filter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TroopFrmService.filterRaceActionPerformed(e, raceJcb, lordJcb, armJcb);
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
				TroopFrmService.deleteSelectedArm(e, selectedArmsTable);
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JLabel lblNewLabel_2 = new JLabel("Selected Arms");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		
		JButton btnNewButton_2 = new JButton("Submit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TroopService.addTroopActionPerformed(e, currentUserIDTxt, troopNameTxt, 
						troopMemoTxt, selectedArmsTable, lordJcb);
			}
		});
		btnNewButton_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TroopFrmService.clearArmTable(e, selectedArmsTable);
			}
		});
		btnNewButton_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JButton btnNewButton_3 = new JButton("Add Arm");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TroopFrmService.addAnArmIntoArmTable(e, selectedArmsTable, armJcb);
			}
		});
		btnNewButton_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(59)
									.addComponent(lblMemo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(35)
									.addComponent(lblNewLabel)
									.addGap(3)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(troopNameTxt, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
									.addGap(26)
									.addComponent(lblUserId, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(currentUserIDTxt, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
								.addComponent(troopMemoTxt)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(raceJcb, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnNewButton))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton_1)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1_1_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(armJcb, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnNewButton_3))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lordJcb, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(183)
							.addComponent(lblNewLabel_2)))
					.addContainerGap(30, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(141, Short.MAX_VALUE)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addGap(174))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(currentUserIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(troopNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(1)
								.addComponent(lblUserId, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblMemo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(troopMemoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(raceJcb, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lordJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(armJcb, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_3))
							.addGap(23)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_1)))
					.addGap(31)
					.addComponent(btnNewButton_2)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
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

		TroopFrmService.fillAllJcbs(raceJcb, lordJcb, armJcb);
	}
	
}
