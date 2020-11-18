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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.wartest.dao.RaceDao;
import com.wartest.model.Race;
import com.wartest.util.DbUtil;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class RaceInterFrm extends JInternalFrame {
	private JTextField s_raceName;
	private JTable raceTable;
	private JTextField raceNameTxt;
	private JTextField raceLocationTxt;
	private JTextArea raceDescTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private RaceDao raceDao = new RaceDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RaceInterFrm frame = new RaceInterFrm();
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
	public RaceInterFrm() {
		setTitle("Races");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 640, 614);
		
		JLabel lblNewLabel = new JLabel("Race Name");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		s_raceName = new JTextField();
		s_raceName.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raceSearchActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Race Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblRace = new JLabel("Race");
		lblRace.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Location");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Description");
		lblNewLabel_1_2_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(64)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(s_raceName, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(51)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 527, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(27)
									.addComponent(lblRace, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addGap(81)
									.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addGap(142)
									.addComponent(lblNewLabel_1_2_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))))
					.addGap(54))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(s_raceName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRace, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		raceNameTxt = new JTextField();
		raceNameTxt.setEditable(false);
		raceNameTxt.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Location");
		lblNewLabel_1_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		raceLocationTxt = new JTextField();
		raceLocationTxt.setEditable(false);
		raceLocationTxt.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Description");
		lblNewLabel_1_2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		raceDescTxt = new JTextArea();
		raceDescTxt.setLineWrap(true);
		raceDescTxt.setRows(5);
		raceDescTxt.setEditable(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(raceDescTxt, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_2)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(raceNameTxt, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addGap(52)
							.addComponent(lblNewLabel_1_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(raceLocationTxt, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(raceNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(raceLocationTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(raceDescTxt, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		raceTable = new JTable();
		
		raceTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				raceTableMousePressed(e);
			}
		});
		
		raceTable.setModel(new DefaultTableModel(new Object[][] {},
			new String[] {"Name", "Location", "Description"}) {
			
			boolean[] columnEditables = new boolean[] {false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		raceTable.getColumnModel().getColumn(0).setPreferredWidth(53);
		raceTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		raceTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		
		
		scrollPane.setColumnHeaderView(raceTable);
		getContentPane().setLayout(groupLayout);

		this.fillTable(new Race());
	}
	
	
	
	
	
	
	/**
	 * Mouse Pressing On Table Row Event Process
	 * @param event
	 */
	private void raceTableMousePressed(MouseEvent event) {
		int row = raceTable.getSelectedRow();
		raceNameTxt.setText((String)raceTable.getValueAt(row, 0));
		raceLocationTxt.setText((String)raceTable.getValueAt(row, 1));
		raceDescTxt.setText((String)raceTable.getValueAt(row, 2)); 
	}

	/**
	 * Race Search Event Process
	 * @param event
	 */
	private void raceSearchActionPerformed(ActionEvent event) {
		String s_raceName = this.s_raceName.getText();
		Race race = new Race();
		race.setRace(s_raceName);
		this.fillTable(race);
	}

	/**
	 * Initialize Table
	 * @param race
	 */
	private void fillTable(Race race) {
		DefaultTableModel dtm = (DefaultTableModel) raceTable.getModel();
		dtm.setRowCount(0); // Clear table before every search
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = raceDao.findRacesByName(con, race);
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("race"));
				v.add(rs.getString("location"));
				v.add(rs.getString("description"));
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
