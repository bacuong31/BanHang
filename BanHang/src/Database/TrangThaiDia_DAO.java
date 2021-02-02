package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;
import Object.TrangThaiDia;



public class TrangThaiDia_DAO {
	public static boolean insertTrangThaiDia(TrangThaiDia trangthaidia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO TRANGTHAIDIA VALUES(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1,trangthaidia.getMaTrangThai());
		pstmt.setString(2, trangthaidia.getTenTrangThai());
		pstmt.setDouble(3, trangthaidia.getHeSoGiaTri());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteTrangThaiDia(String matrangthai) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM TRANGTHAIDIA WHERE MaTrangThai = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, matrangthai);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateTrangThaiDia(TrangThaiDia trangthaidia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE TRANGTHAIDIA SET TenTrangThai = ? WHERE MaTrangThai = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, trangthaidia.getTenTrangThai());
		pstmt.setString(2, trangthaidia.getMaTrangThai());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static TrangThaiDia selectTrangThaiDia(String matrangthai) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM TRANGTHAIDIA WHERE MaTrangThai = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, matrangthai);
		ResultSet rs = pstmt.executeQuery();
		TrangThaiDia temp = new TrangThaiDia();
		if(rs.next()) {
			temp.setMaTrangThai(rs.getString(1));
			temp.setTenTrangThai(rs.getString(2));
		}
		return temp;
	}
	public static ResultSet executeQueryselectTrangThaiDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<TrangThaiDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM TRANGTHAIDIA";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<TrangThaiDia> arr = new ArrayList<TrangThaiDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			TrangThaiDia temp = new TrangThaiDia();
			temp.setMaTrangThai(rs.getString(1));
			temp.setTenTrangThai(rs.getString(2));
			arr.add(temp);
		}
		return arr;
	}

	public static String getMaTrangThaiTheoTen(String ten) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT MaTrangThai FROM TRANGTHAIDIA WHERE TenTrangThai = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, ten);
		ResultSet rs = pstmt.executeQuery();
		String maTT = null;
		if(rs.next()) {
			maTT = rs.getString(1);
		}
		return maTT;
		
	}

}
