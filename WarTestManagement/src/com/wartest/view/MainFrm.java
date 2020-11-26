package com.wartest.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.wartest.model.User;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.JMenu;
import javax.swing.JDesktopPane;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane table = null;
	
	private User currentUser = null; // Used to track the current user


	/**
	 * Create the frame.
	 */
	public MainFrm(User currentUser_) {
		this.currentUser = currentUser_;
		setTitle("War Drill Home      **** " + currentUser.getUsername() + " is logged in ****");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(10, 0, 5, 50));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Main Menu");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("My Troops");
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Add Troop");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openTroopAddInterFrmActionPerformed(e);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Manage Troop");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openTroopManageInterFrmActionPerformed(e);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Log Out");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logoutActionPerformed(e);
			}
		});
		
		JMenu mnNewMenu_3 = new JMenu("My War Tests");
		mnNewMenu.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Add War Test");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWartestAddInterFrmActionPerformed(e);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Manage War Test");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWartestManageInterFrmActionPerformed(e);
			}
		});
		
		mnNewMenu_3.add(mntmNewMenuItem_7);
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Library");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Races");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RaceInterFrm raceInterFrm = new RaceInterFrm();
				raceInterFrm.setVisible(true);
				table.add(raceInterFrm);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Lords");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LordInterFrm lordInterFrm = new LordInterFrm();
				lordInterFrm.setVisible(true);
				table.add(lordInterFrm);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Arms");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArmInterFrm armInterFrm = new ArmInterFrm();
				armInterFrm.setVisible(true);
				table.add(armInterFrm);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		table = new JDesktopPane();
		contentPane.add(table, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	/**
	 * Open the Wartest Manage internal frame and track the current user ID
	 * @param event
	 */
	private void openWartestManageInterFrmActionPerformed(ActionEvent event) {
		WartestManageInterFrm wartestManageInterFrm = new WartestManageInterFrm(this.currentUser);
		wartestManageInterFrm.setVisible(true);
		table.add(wartestManageInterFrm);
	}

	/**
	 * Open the Wartest Add internal frame and track the current user ID
	 * @param event
	 */
	private void openWartestAddInterFrmActionPerformed(ActionEvent event) {
		WartestAddInterFrm wartestAddInterFrm = new WartestAddInterFrm(this.currentUser);
		wartestAddInterFrm.setVisible(true);
		table.add(wartestAddInterFrm);
	}

	/**
	 * Open the Troop Manage internal frame and track the current user ID
	 * @param event
	 */
	private void openTroopManageInterFrmActionPerformed(ActionEvent event) {
		TroopManageInterFrm troopManangeInterFrm = new TroopManageInterFrm(this.currentUser);
		troopManangeInterFrm.setVisible(true);
		table.add(troopManangeInterFrm);
	}


	/**
	 * Open the Troop Add internal frame and track the current user ID
	 * @param event
	 */
	private void openTroopAddInterFrmActionPerformed(ActionEvent event) {
		TroopAddInterFrm troopAddInterFrm = new TroopAddInterFrm(this.currentUser);
		troopAddInterFrm.setVisible(true);
		table.add(troopAddInterFrm);
	}


	/**
	 * Action Event: Log Out
	 * @param e
	 */
	private void logoutActionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Do you want to log out?");
		if (result == 0) {
			this.currentUser = null;
			dispose();
			new LogInFrm("", "").setVisible(true);;
		}
	}
}

