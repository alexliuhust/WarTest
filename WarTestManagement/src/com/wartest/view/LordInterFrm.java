package com.wartest.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.wartest.dao.LordDao;
import com.wartest.model.Lord;
import com.wartest.util.DbUtil;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LordInterFrm extends JInternalFrame {
	private JTextField s_lordNameTxt;
	private JTextField s_lordRaceTxt;
	private JTable lordtable;
	private JTextField lordNameTxt;
	private JTextField lordRaceTxt;
	private JTextField hpTxt;
	private JTextField attTxt;
	private JTextField arTxt;
	private JTextField spTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private LordDao lordDao = new LordDao();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LordInterFrm frame = new LordInterFrm();
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
	public LordInterFrm() {
		setTitle("Lord");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 631, 520);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		s_lordNameTxt = new JTextField();
		s_lordNameTxt.setColumns(10);
		
		JLabel lblNewLabel1 = new JLabel("Race");
		lblNewLabel1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		s_lordRaceTxt = new JTextField();
		s_lordRaceTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lordSearchActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblNewLabel1_1 = new JLabel("Race");
		lblNewLabel1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Lord Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(62)
					.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(104)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
					.addComponent(lblNewLabel1_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(171))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(35, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(s_lordNameTxt, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblNewLabel1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(s_lordRaceTxt, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnNewButton))
							.addComponent(scrollPane)))
					.addGap(44))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(s_lordNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel1)
						.addComponent(s_lordRaceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(123, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblNewLabel1_2 = new JLabel("Race");
		lblNewLabel1_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		lordNameTxt = new JTextField();
		lordNameTxt.setEditable(false);
		lordNameTxt.setColumns(10);
		
		lordRaceTxt = new JTextField();
		lordRaceTxt.setEditable(false);
		lordRaceTxt.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("----------Data----------");
		lblNewLabel_2_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblNewLabel_2_2 = new JLabel("HP");
		lblNewLabel_2_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		hpTxt = new JTextField();
		hpTxt.setEditable(false);
		hpTxt.setColumns(10);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Attack");
		lblNewLabel_2_2_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		attTxt = new JTextField();
		attTxt.setEditable(false);
		attTxt.setColumns(10);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Armor");
		lblNewLabel_2_2_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		arTxt = new JTextField();
		arTxt.setEditable(false);
		arTxt.setColumns(10);
		
		JLabel lblNewLabel_2_2_1_1_1 = new JLabel("Speed");
		lblNewLabel_2_2_1_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		spTxt = new JTextField();
		spTxt.setEditable(false);
		spTxt.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(25)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lordNameTxt, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(lblNewLabel1_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lordRaceTxt, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(28)
							.addComponent(lblNewLabel_2_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(hpTxt, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addGap(38)
							.addComponent(lblNewLabel_2_2_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(attTxt, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(lblNewLabel_2_2_1_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(arTxt, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(lblNewLabel_2_2_1_1_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spTxt, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(193)
							.addComponent(lblNewLabel_2_1)))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel1_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lordNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lordRaceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNewLabel_2_2_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel_2_2_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_2_2_1_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addComponent(attTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(arTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(spTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(hpTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(134, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		lordtable = new JTable();
		
		lordtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lordTableMousePressed(e);
			}
		});
		lordtable.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"LordID", "Name", "Race"}) {
			
			boolean[] columnEditables = new boolean[] {false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		lordtable.getColumnModel().getColumn(0).setPreferredWidth(20);
		lordtable.getColumnModel().getColumn(1).setPreferredWidth(160);
		lordtable.getColumnModel().getColumn(2).setPreferredWidth(160);
		
		scrollPane.setColumnHeaderView(lordtable);
		getContentPane().setLayout(groupLayout);
		
		this.fillTable(new Lord());
	}
	
	
	/**
	 * Mouse Pressing On Table Row Event Process
	 * @param e
	 */
	private void lordTableMousePressed(MouseEvent event) {
		int row = lordtable.getSelectedRow();
		Integer lordID = (Integer) lordtable.getValueAt(row, 0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = lordDao.findLordByID(con, lordID);
			while (rs.next()) {
				this.lordNameTxt.setText(rs.getString("name"));
				this.lordRaceTxt.setText(rs.getString("race"));
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
	 * Lord Search Event Process
	 * @param event
	 */
	private void lordSearchActionPerformed(ActionEvent event) {
		String s_lordName = this.s_lordNameTxt.getText();
		String s_lordRace = this.s_lordRaceTxt.getText();
		Lord lord = new Lord();
		lord.setName(s_lordName);
		lord.setRace(s_lordRace);
		this.fillTable(lord);
	}

	/**
	 * Initialize Table
	 * @param lord
	 */
	private void fillTable(Lord lord) {
		DefaultTableModel dtm = (DefaultTableModel) lordtable.getModel();
		dtm.setRowCount(0); // Clear table before every search
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = lordDao.findLordsByNameOrRace(con, lord);
			while(rs.next()) {
				Vector v = new Vector();
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
