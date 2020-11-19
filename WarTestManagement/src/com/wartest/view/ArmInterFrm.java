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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.wartest.dao.ArmDao;
import com.wartest.model.Arm;
import com.wartest.util.DbUtil;
import javax.swing.ScrollPaneConstants;

public class ArmInterFrm extends JInternalFrame {
	private JTextField s_armNameTxt;
	private JTextField s_armRaceTxt;
	private JTextField s_armTypeTxt;
	private JTable armTable;
	private JTextField armNameTxt;
	private JTextField armRaceTxt;
	private JTextField armTypeTxt;
	private JTextField costTxt;
	private JTextField scaleTxt;
	private JTextField hpTxt;
	private JTextField attTxt;
	private JTextField arTxt;
	private JTextField spTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private ArmDao armDao = new ArmDao();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ArmInterFrm frame = new ArmInterFrm();
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
	public ArmInterFrm() {
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Arm");
		setBounds(100, 100, 645, 585);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblRace = new JLabel("Race");
		lblRace.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		s_armNameTxt = new JTextField();
		s_armNameTxt.setColumns(10);
		
		s_armRaceTxt = new JTextField();
		s_armRaceTxt.setColumns(10);
		
		s_armTypeTxt = new JTextField();
		s_armTypeTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				armSearchActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblRace_1 = new JLabel("Race");
		lblRace_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblType_1 = new JLabel("Type");
		lblType_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Arm Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(s_armNameTxt, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addGap(39)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(16)
									.addComponent(lblRace, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(s_armRaceTxt, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblRace_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(s_armTypeTxt, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblType_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 538, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(s_armTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(s_armNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(lblRace, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(s_armRaceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRace_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblType_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		armNameTxt = new JTextField();
		armNameTxt.setEditable(false);
		armNameTxt.setColumns(10);
		
		JLabel lblRace_2 = new JLabel("Race");
		lblRace_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		armRaceTxt = new JTextField();
		armRaceTxt.setEditable(false);
		armRaceTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Type");
		lblNewLabel_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		armTypeTxt = new JTextField();
		armTypeTxt.setEditable(false);
		armTypeTxt.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("----------Data----------");
		lblNewLabel_2_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblNewLabel_2_2 = new JLabel("Cost");
		lblNewLabel_2_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblNewLabel_2_3 = new JLabel("Scale");
		lblNewLabel_2_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblNewLabel_2_4 = new JLabel("HP");
		lblNewLabel_2_4.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Attack");
		lblNewLabel_2_2_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblNewLabel_2_2_2 = new JLabel("Armor");
		lblNewLabel_2_2_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblNewLabel_2_2_3 = new JLabel("Speed");
		lblNewLabel_2_2_3.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		costTxt = new JTextField();
		costTxt.setEditable(false);
		costTxt.setColumns(10);
		
		scaleTxt = new JTextField();
		scaleTxt.setEditable(false);
		scaleTxt.setColumns(10);
		
		hpTxt = new JTextField();
		hpTxt.setEditable(false);
		hpTxt.setColumns(10);
		
		attTxt = new JTextField();
		attTxt.setEditable(false);
		attTxt.setColumns(10);
		
		arTxt = new JTextField();
		arTxt.setEditable(false);
		arTxt.setColumns(10);
		
		spTxt = new JTextField();
		spTxt.setEditable(false);
		spTxt.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(176)
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(armNameTxt, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
									.addGap(32)
									.addComponent(lblRace_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(armRaceTxt, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_2_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2_2_1))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(costTxt, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
										.addComponent(attTxt, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
									.addGap(60)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblNewLabel_2_3, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(scaleTxt, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblNewLabel_2_2_2)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(arTxt, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))))
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(armTypeTxt, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblNewLabel_2_2_3)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(spTxt, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(9)
									.addComponent(lblNewLabel_2_4, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(hpTxt, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
									.addGap(61)))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(armNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(armRaceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblRace_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(armTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(scaleTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(costTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2_4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(hpTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_2_2_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2_2_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(attTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2_2_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(arTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(spTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51))
		);
		panel.setLayout(gl_panel);
		
		armTable = new JTable();
		armTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				armTableMousePressed(e);
			}
		});
		armTable.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"ID", "Name", "Race", "Type"}) {
			
			boolean[] columnEditables = new boolean[] {false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		armTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		armTable.getColumnModel().getColumn(1).setPreferredWidth(108);
		armTable.getColumnModel().getColumn(2).setPreferredWidth(106);
		armTable.getColumnModel().getColumn(3).setPreferredWidth(106);
		
		scrollPane.setColumnHeaderView(armTable);
		getContentPane().setLayout(groupLayout);
		
		this.fillTable(new Arm());
	}
	
	/**
	 * Mouse Pressing On Table Row Event Process
	 * @param event
	 */
	private void armTableMousePressed(MouseEvent event) {
		int row = this.armTable.getSelectedRow();
		Integer armID = (Integer) armTable.getValueAt(row, 0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = armDao.findArmById(con, armID);
			while (rs.next()) {
				this.armNameTxt.setText(rs.getString("name"));
				this.armRaceTxt.setText(rs.getString("race"));
				this.armTypeTxt.setText(rs.getString("type"));
				Integer cost = rs.getInt("cost");
				this.costTxt.setText(cost.toString());
				Integer scale = rs.getInt("scale");
				this.scaleTxt.setText(scale.toString());
				Integer hp = rs.getInt("hp");
				this.hpTxt.setText(hp.toString());
				Integer att = rs.getInt("attack");
				this.attTxt.setText(att.toString());
				Integer ar = rs.getInt("armor");
				this.arTxt.setText(ar.toString());
				Integer sp = rs.getInt("speed");
				this.spTxt.setText(sp.toString());
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
	private void armSearchActionPerformed(ActionEvent event) {
		String s_armName = this.s_armNameTxt.getText();
		String s_armRace = this.s_armRaceTxt.getText();
		String s_armType = this.s_armTypeTxt.getText();
		Arm arm = new Arm();
		arm.setName(s_armName);
		arm.setRace(s_armRace);
		arm.setType(s_armType);
		this.fillTable(arm);
	}

	/**
	 * Initialize Table
	 * @param arm
	 */
	private void fillTable(Arm arm) {
		DefaultTableModel dtm = (DefaultTableModel) armTable.getModel();
		dtm.setRowCount(0); // Clear table before every search
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = armDao.findArmsByNameOrRaceOrType(con, arm);
			while(rs.next()) {
				Vector v = new Vector();
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
