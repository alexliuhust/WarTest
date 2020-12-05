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
		int[] ans = new int[2];
		ans[0] = ans[1] = -1;
		
		// Update a Troop record
		String sql = "update troop set name = ?, memo = ?, lordID = ? where troopID = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, troop.getName());
		pstmt.setString(2, troop.getMemo());
		pstmt.setInt(3, troop.getLordID());
		pstmt.setInt(4, troop.getTroopID());
		ans[0] = pstmt.executeUpdate();
		
		if (ans[0] != 1) return ans;
		
		// Delete those composition records that belong to the target troop
		sql = "call delete_compositions_belong_to_troopID(?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, troop.getTroopID());
		int num = pstmt.executeUpdate();
		
		if (num < 1) return ans;
		
		// Insert new arms into the composition table
		ans[1] = 0;
		String addComp = "call insert_new_compositions(?,?)";
		if (troop.getTroopID() > 0) {
			for (Integer armID : troop.getArms()) {
				pstmt = con.prepareStatement(addComp);
				pstmt.setInt(1, troop.getTroopID());
				pstmt.setInt(2, armID);
				ans[1] += pstmt.executeUpdate();
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
		
		// Delete the records with target troopID in wartest
		String sql = "delete from wartest where troopID1 = ? or troopID2_v = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, troopID);
		pstmt.setInt(2, troopID);
		ans[0] = pstmt.executeUpdate();
		
		// Delete the compositions that belong to the target troopID
		sql = "call delete_compositions_belong_to_troopID(?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, troopID);
		ans[1] = pstmt.executeUpdate();
		
		if (ans[1] == 0) return ans;
		
		// Delete a troop
		sql = "delete from troop where troopID = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, troopID);
		ans[2] = pstmt.executeUpdate();
		
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
		ans[0] = ans[1] = 0;
		
		// Insert the new troop into the troop table (without the arms information)
		String sql = "insert into troop (name, userID, lordID, memo) "
				+ "values(?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, troop.getName());
		pstmt.setInt(2, troop.getUserID());
		pstmt.setInt(3, troop.getLordID());
		pstmt.setString(4, troop.getMemo());
		ans[0] = pstmt.executeUpdate();
		
		if (ans[0] != 1) return ans;
		
		// Get the troop ID of the new inserted troop
		String findTroopID = "select troopID from troop where name = ?";
		pstmt = con.prepareStatement(findTroopID);
		pstmt.setString(1, troop.getName());
		ResultSet rs = pstmt.executeQuery();
		Integer troopID = -1;
		if (rs.next()) 
			troopID = rs.getInt("troopID");
		
		// Store the arms information of the new inserted troop into the composition table
		String addComp = "call insert_new_compositions(?,?)";
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
