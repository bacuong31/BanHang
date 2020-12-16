package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.PhanCongLamViec;



public class PhanCongLamViec_DAO {
	public static boolean insertPhanCong(PhanCongLamViec phancong) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO PHANCONGLAMVIEC VALUES(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, phancong.getMaNV());
		pstmt.setString(2, phancong.getMaCaLamViec());
		pstmt.setString(3, phancong.getChuThich());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deletePhanCong(String manv, String macalamviec) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM PHANCONGLAMVIEC WHERE MaNV = ? AND MaCaLamViec = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, manv);
		pstmt.setString(2, macalamviec);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updatePhanCong(PhanCongLamViec phancong) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE PHANCONGLAMVIEC SET ChuThich = ? WHERE MaNV = ? AND MaCaLamViec = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, phancong.getChuThich());
		pstmt.setString(2, phancong.getMaNV());
		pstmt.setString(3, phancong.getMaCaLamViec());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static PhanCongLamViec selectPhanCong(String manv, String macalamviec) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM PHANCONGLAMVIEC WHERE MaNV = ? AND MaCaLamViec = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, manv);
		pstmt.setString(2, macalamviec);
		ResultSet rs = pstmt.executeQuery();
		PhanCongLamViec temp = new PhanCongLamViec();
		if(rs.next()) {
			temp.setMaNV(rs.getString(1));
			temp.setMaCaLamViec(rs.getString(2));
			temp.setChuThich(rs.getString(3));
		}
		return temp;
	}
	public static ResultSet executeQueryselectPhanCong(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<PhanCongLamViec> getAll() throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM PHANCONGLAMVIEC";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<PhanCongLamViec> arr = new ArrayList<PhanCongLamViec>();
		while(rs.next()) {
			PhanCongLamViec temp = new PhanCongLamViec();
			temp.setMaNV(rs.getString(1));
			temp.setMaCaLamViec(rs.getString(2));
			temp.setChuThich(rs.getString(3));
			arr.add(temp);
		}
		return arr;
	}
}
