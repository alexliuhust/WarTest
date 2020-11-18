package com.wartest.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class ArmInterFrm extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArmInterFrm frame = new ArmInterFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ArmInterFrm() {
		setBounds(100, 100, 450, 300);

	}

}
