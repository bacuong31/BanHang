package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;
import Object.ChiTietThueDia;


public class ChiTietThueDia_DAO {
	public static boolean insertChiTietThueDia(ChiTietThueDia chitietthuedia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO CHITIETTHUEDIA VALUES(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, chitietthuedia.getMaPhieuThue());
		pstmt.setString(2, chitietthuedia.getMaBangDia());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteChiTietThueDia(String maphieuthue, String mabangdia) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM CHITIETTHUEDIA WHERE MaPhieuThue = ? AND MaBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieuthue);
		pstmt.setString(2, mabangdia);
		res = pstmt.executeUpdate();
		return res;
	}
	
	/*
	 * public static boolean updateChiTietThueDia(String maphieuthue, String
	 * mabangdia, int soluong) throws ClassNotFoundException, SQLException {
	 * Connection conn = DatabaseManager.getInstance().getConnection(); String
	 * sqlQuery =
	 * "UPDATE CHITIETTHUEDIA SET SoLuong = ? WHERE MaPhieuThue = ? AND MaBangDia = ?"
	 * ; int res = 0; PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
	 * pstmt.setInt(1, soluong); pstmt.setString(2, maphieuthue); pstmt.setString(3,
	 * mabangdia); res = pstmt.executeUpdate(); if (res == 0) return false; return
	 * true; }
	 */
	public static ChiTietThueDia selectChiTietThueDia(String maphieuthue, String mabangdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM CHITIETTHUEDIA WHERE MaPhieuThue = ? AND MaBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieuthue);
		pstmt.setString(2, mabangdia);
		ResultSet rs = pstmt.executeQuery();
		ChiTietThueDia temp = new ChiTietThueDia();
		if(rs.next()) {
			temp.setMaPhieuThue(rs.getString(1));
			temp.setMaBangDia(rs.getString(2));
		}
		return temp;
	}
	public static ResultSet executeQueryselectChiTietThueDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<ChiTietThueDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM CHITIETTHUEDIA";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<ChiTietThueDia> arr = new ArrayList<ChiTietThueDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			ChiTietThueDia temp = new ChiTietThueDia();
			temp.setMaPhieuThue(rs.getString(1));
			temp.setMaBangDia(rs.getString(2));
			arr.add(temp);
		}
		return arr;
	}

}
