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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import com.wartest.model.Troop;
import com.wartest.model.User;
import com.wartest.service.WartestFrmService;
import com.wartest.service.WartestService;

public class WartestAddInterFrm extends JInternalFrame {
	private JTextField currentUserIDTxt;
	private JComboBox<String> locationJcb;
	private JComboBox<Troop> troop1Jcb;
	private JComboBox<Troop> troop2Jcb;
	private JComboBox<Troop> victorJcb;
	private JComboBox<Integer> armsLeftJcb;
	
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
		
		locationJcb = new JComboBox<>();
		
		JLabel lblNewLabel_1 = new JLabel("User ID");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		currentUserIDTxt = new JTextField();
		currentUserIDTxt.setEditable(false);
		currentUserIDTxt.setColumns(10);
		Integer cuid = currentUser.getUserID();
		currentUserIDTxt.setText(cuid.toString());
		
		JLabel lblNewLabel_2 = new JLabel("Troop 1");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		troop1Jcb = new JComboBox<>();
		
		JLabel lblNewLabel_2_1 = new JLabel("Troop 2");
		lblNewLabel_2_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		troop2Jcb = new JComboBox<>();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Result", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WartestService.warTestAddActionPerformed(e, currentUser, locationJcb, troop1Jcb, troop2Jcb, victorJcb, armsLeftJcb);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JButton btnNewButton_1 = new JButton("Confirm");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WartestFrmService.confirmTwoEngagedTroops(e, troop1Jcb, troop2Jcb, victorJcb, armsLeftJcb);
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(troop1Jcb, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_2_1))
								.addGroup(groupLayout.createSequentialGroup()
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
									.addComponent(btnNewButton_1)))))
					.addContainerGap(50, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(223, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
					.addGap(217))
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
					.addGap(31))
		);
		
		JLabel lblNewLabel_3 = new JLabel("Victor");
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		victorJcb = new JComboBox<>();
		
		JLabel lblNewLabel_3_1 = new JLabel("Arms Left");
		lblNewLabel_3_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		armsLeftJcb = new JComboBox<>();
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

		WartestFrmService.fillLocationAndTroopJcbs(currentUser, locationJcb, troop1Jcb, troop2Jcb, victorJcb, armsLeftJcb);
	}
	
}
