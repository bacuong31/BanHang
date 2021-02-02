package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;
import Object.TrangThaiNhapDia;



public class TrangThaiNhapDia_DAO {
	public static boolean insertTrangThaiNhapDIa(TrangThaiNhapDia trangthainhapdia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO TRANGTHAINHAPDIA VALUES(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, trangthainhapdia.getMaTrangThai());
		pstmt.setString(2, trangthainhapdia.getTenTrangThai());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteTrangThaiNhapDia(String matrangthai) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM TRANGTHAINHAPDIA WHERE MaTrangThai = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, matrangthai);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateTrangThaiNhapDia(TrangThaiNhapDia trangthainhapdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE TRANGTHAINHAPDIA SET TenTrangThai = ? WHERE MaTrangThai = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, trangthainhapdia.getTenTrangThai());
		pstmt.setString(2, trangthainhapdia.getMaTrangThai());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static TrangThaiNhapDia selectTrangThaiNhapDia(String matrangthai) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM TRANGTHAINHAPDIA WHERE MaTrangThai = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, matrangthai);
		ResultSet rs = pstmt.executeQuery();
		TrangThaiNhapDia temp = new TrangThaiNhapDia();
		if(rs.next()) {
			temp.setMaTrangThai(rs.getString(1));
			temp.setTenTrangThai(rs.getString(2));
		}
		return temp;
	}
	public static ResultSet executeQueryselectTrangThaiNhapDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<TrangThaiNhapDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM TRANGTHAINHAPDIA";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<TrangThaiNhapDia> arr = new ArrayList<TrangThaiNhapDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			TrangThaiNhapDia temp = new TrangThaiNhapDia();
			temp.setMaTrangThai(rs.getString(1));
			temp.setTenTrangThai(rs.getString(2));
			arr.add(temp);
		}
		return arr;
	}
	
	public static String getMaTheoTen(String ten) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT MaTrangThai FROM TRANGTHAINHAPDIA WHERE TenTrangThai = ?";
		String maTT = "";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, ten);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			maTT = rs.getString(1);
		}
		return maTT;
	}

}
