package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.CaLamViec;
import Object.PhanCongLamViec;

public class CaLamViec_DAO {
	public static boolean insertCaLamViec(CaLamViec calamviec) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO CALAMVIEC VALUES(?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, calamviec.getMaCaLamViec());
		pstmt.setTime(2, calamviec.getThoiGianBatDau());
		pstmt.setTime(3, calamviec.getThoiGianKetThuc());
		pstmt.setBoolean(4, true);
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteCaLamViec(CaLamViec calamviec) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM CALAMVIEC WHERE MaCaLamViec = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, calamviec.getMaCaLamViec());
		for (PhanCongLamViec pc : calamviec.getListPhanCong()) {
			PhanCongLamViec_DAO.deletePhanCong(pc);
		}
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static int softDeleteCaLamViec(CaLamViec calamviec) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE CALAMVIEC SET isActive = false WHERE MaCaLamViec = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, calamviec.getMaCaLamViec());
		int res = pstmt.executeUpdate();
		return res;
		
	}
	public static boolean updateCaLamViec(CaLamViec calamviec) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE CALAMVIEC SET ThoiGianBatDau = ? , ThoiGianKetThuc = ? WHERE MaCaLamViec = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setTime(1, calamviec.getThoiGianBatDau());
		pstmt.setTime(2, calamviec.getThoiGianKetThuc());
		pstmt.setString(3, calamviec.getMaCaLamViec());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static CaLamViec selectCaLamViec(String macalamviec) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM CALAMVIEC WHERE MaCaLamViec = ? AND isActive = true";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, macalamviec);
		ResultSet rs = pstmt.executeQuery();
		CaLamViec temp = new CaLamViec();
		if(rs.next()) {
			temp.setMaCaLamViec(rs.getString(1));
			temp.setThoiGianBatDau(rs.getTime(2));
			temp.setThoiGianKetThuc(rs.getTime(3));
		}
		return temp;
	}
	public static ResultSet executeQueryselectCaLamViec(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<CaLamViec> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM CALAMVIEC AND isActive = true";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<CaLamViec> arr = new ArrayList<CaLamViec>();
		while(rs.next()) {
			CaLamViec temp = new CaLamViec();
			temp.setMaCaLamViec(rs.getString(1));
			temp.setThoiGianBatDau(rs.getTime(2));
			temp.setThoiGianKetThuc(rs.getTime(3));
			arr.add(temp);
		}
		return arr;
	}
	
}
