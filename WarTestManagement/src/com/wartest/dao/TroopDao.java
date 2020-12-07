package com.wartest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wartest.model.Troop;

public class TroopDao {
	
	/**
	 * Find Troops with names by current User ID
	 * @param con
	 * @param currentUserId
	 * @return
	 * @throws Exception
	 */
	public ResultSet findTroopsByUserID(Connection con, Integer currentUserId) throws Exception { 
		String sql = "select troopID, name from troop where userID = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, currentUserId);
		return pstmt.executeQuery();
	}
	
	/**
	 * Find Troops with Races and Lords by current User ID
	 * @param con
	 * @param currentUserId
	 * @return
	 * @throws Exception
	 */
	public ResultSet findTroopsByUserID_withRaceAndLord(Connection con, Integer currentUserId) throws Exception { 
		String sql = "call find_troops_by_userID_with_race_Lord(?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, currentUserId);
		return pstmt.executeQuery();
	}
	
	/**
	 * Find a Troop by Troop Name
	 * @param con
	 * @param troopName
	 * @return
	 * @throws Exception
	 */
	public ResultSet findTroopByTroopName(Connection con, String troopName) throws Exception { 
		String sql = "select * from troop where name = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, troopName);
		return pstmt.executeQuery();
	}
	
	/**
	 * Find a Troop by Troop Name and this troop's ID is not the current troop ID
	 * @return
	 * @throws Exception
	 */
	public ResultSet findTroopByTroopNameButNotThisID(Connection con, String troopName, Integer troopID) throws Exception { 
		String sql = "select * from troop where name = ? and troopID <> ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, troopName);
		pstmt.setInt(2, troopID);
		return pstmt.executeQuery();
	}
	
	/**
	 * Update a Troop
	 * @param con
	 * @param troop
	 * @return
	 * @throws Exception
	 */
	public int[] updateOneTroop(Connection con, Troop troop) throws Exception {
		int[] ans = new int[3];
		ans[0] = ans[1] = ans[2] = -1;
		
		// Update a Troop record and clear old records in composition
		String sql = "call update_troop_clear_comp(?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, troop.getTroopID());
		pstmt.setString(2, troop.getName());
		pstmt.setString(3, troop.getMemo());
		pstmt.setInt(4, troop.getLordID());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			ans[0] = rs.getInt("u_trp");
			ans[1] = rs.getInt("d_comp");
		} else {
			return ans;
		}
		
		// Insert new arms into the composition table
		ans[2] = 0;
		String addComp = "call insert_new_compositions(?,?)";
		if (troop.getTroopID() > 0) {
			for (Integer armID : troop.getArms()) {
				pstmt = con.prepareStatement(addComp);
				pstmt.setInt(1, troop.getTroopID());
				pstmt.setInt(2, armID);
				ans[2] += pstmt.executeUpdate();
			}
		}
		
		return ans;
	}
	
	/**
	 * Delete a troop by troopID
	 * @param con
	 * @param troopID
	 * @return
	 * @throws Exception
	 */
	public int[] deleteTroop(Connection con, Integer troopID) throws Exception {
		int[] ans = new int[3];
		ans[0] = ans[1] = ans[2] = -1; 
		
		String sql = "call delete_troop(?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, troopID);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			ans[0] = rs.getInt("d_war");
			ans[1] = rs.getInt("d_comp");
			ans[2] = rs.getInt("d_trp");
		} 
		
		return ans;
	}

	/**
	 * Add a Troop into the database
	 * @param con
	 * @param troop
	 * @param currentUser
	 * @return
	 * @throws Exception
	 */
	public int[] addOneTroop(Connection con, Troop troop) throws Exception {
		int ans[] = new int[2];
		ans[0] = ans[1] = -1;
		
		// Insert the new troop into the troop table and get the troop ID of the new inserted troop
		String sql = "call insert_troop_get_troopID(?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, troop.getUserID());
		pstmt.setString(2, troop.getName());
		pstmt.setString(3, troop.getMemo());
		pstmt.setInt(4, troop.getLordID());
		Integer troopID = -1;
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			ans[0] = rs.getInt("i_trp");
			troopID = rs.getInt("get_trpid");
		}
		
		// Store the arms information of the new inserted troop into the composition table
		String addComp = "call insert_new_compositions(?,?)";
		ans[1] = 0;
		if (troopID > 0) {
			for (Integer armID : troop.getArms()) {
				pstmt = con.prepareStatement(addComp);
				pstmt.setInt(1, troopID);
				pstmt.setInt(2, armID);
				ans[1] += pstmt.executeUpdate();
			}
		}
		
		return ans;
	}
	
}
