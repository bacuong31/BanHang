package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;



public class BangDia_DAO {
	public static boolean insertBangDia(BangDia bangdia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO BANGDIA VALUES(?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, bangdia.getMaBangDia());
		pstmt.setString(2, bangdia.getMaLoaiBangDia());
		pstmt.setString(3, bangdia.getTrangThai());
		pstmt.setBoolean(4, true);
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static boolean softDeleteBangDia(String mabangdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE BANGDIA SET isActive = false WHERE MaBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, mabangdia);
		int res = pstmt.executeUpdate();
		if (res == 0)
			throw new SQLException();
		return true;
		
	}
	public static int deleteBangDia(String mabangdia) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM BANGDIA WHERE MaBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, mabangdia);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateBangDia(BangDia bangdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE BANGDIA SET MaLoaiBangDia = ?, TrangThai = ? WHERE MaBangDia = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, bangdia.getMaLoaiBangDia());
		pstmt.setString(2, bangdia.getTrangThai());
		pstmt.setString(3, bangdia.getMaLoaiBangDia());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static BangDia selectLoaiBangDia(String mabangdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM BANGDIA WHERE MaBangDia = ? AND isActive = true";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, mabangdia);
		ResultSet rs = pstmt.executeQuery();
		BangDia temp = new BangDia();
		if(rs.next()) {
			temp.setMaBangDia(rs.getString(1));
			temp.setMaLoaiBangDia(rs.getString(2));
			temp.setTrangThai(rs.getString(3));
		}
		return temp;
	}
	public static ResultSet executeQueryselectBangDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<BangDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM BANGDIA WHERE isActive = true";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<BangDia> arr = new ArrayList<BangDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			BangDia temp = new BangDia();
			temp.setMaBangDia(rs.getString(1));
			temp.setMaLoaiBangDia(rs.getString(2));
			temp.setTrangThai(rs.getString(3));
			arr.add(temp);
		}
		return arr;
	}
}
