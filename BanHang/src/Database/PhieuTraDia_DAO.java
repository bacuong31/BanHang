package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;
import Object.PhieuTraDia;



public class PhieuTraDia_DAO {
	public static boolean insertPhieuTraDia(PhieuTraDia phieutradia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO PHIEUTRADIA VALUES(?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, phieutradia.getMaPhieuTra());
		pstmt.setString(2, phieutradia.getPhieuThamChieu());
		pstmt.setDate(3, phieutradia.getNgayTra());
		pstmt.setString(4, phieutradia.getMaNhanVien());
		pstmt.setString(5, phieutradia.getHoaDonBoiThuong());
		pstmt.setString(6, phieutradia.getHoaDonHoanTra());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deletePhieuTraDia(String maphieutra) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM PHIEUTRADIA WHERE MaPhieuTra = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieutra);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updatePhieuTraDia(PhieuTraDia phieutradia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE PHIEUTRADIA SET PhieuThamChieu = ?, NgayTra = ?, MaNhanVien = ?, HoaDonBoiThuong = ?, HoaDonHoanTra = ? WHERE MaPhieuTra = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, phieutradia.getPhieuThamChieu());
		pstmt.setDate(2, phieutradia.getNgayTra());
		pstmt.setString(3, phieutradia.getMaNhanVien());
		pstmt.setString(4, phieutradia.getHoaDonBoiThuong());
		pstmt.setString(5, phieutradia.getHoaDonHoanTra());
		pstmt.setString(6, phieutradia.getMaPhieuTra());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static PhieuTraDia selectPhieuTraDia(String maphieutra) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM PHIEUTRADIA WHERE MAPHIEUTRA = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieutra);
		ResultSet rs = pstmt.executeQuery();
		PhieuTraDia temp = new PhieuTraDia();
		if(rs.next()) {
			temp.setMaPhieuTra(rs.getString(1));
			temp.setPhieuThamChieu(rs.getString(2));
			temp.setNgayTra(rs.getDate(3));
			temp.setMaNhanVien(rs.getString(4));
			temp.setHoaDonBoiThuong(rs.getString(5));
			temp.setHoaDonHoanTra(rs.getString(6));
		}
		return temp;
	}
	public static ResultSet executeQueryselectPhieuTraDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<PhieuTraDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM PHIEUTRADIA";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<PhieuTraDia> arr = new ArrayList<PhieuTraDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			PhieuTraDia temp = new PhieuTraDia();
			temp.setMaPhieuTra(rs.getString(1));
			temp.setPhieuThamChieu(rs.getString(2));
			temp.setNgayTra(rs.getDate(3));
			temp.setMaNhanVien(rs.getString(4));
			temp.setHoaDonBoiThuong(rs.getString(5));
			temp.setHoaDonHoanTra(rs.getString(6));
			arr.add(temp);
		}
		return arr;
	}

}
