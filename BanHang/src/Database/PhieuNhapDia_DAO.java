package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;
import Object.PhieuNhapDia;



public class PhieuNhapDia_DAO {
	public static boolean insertPhieuNhapDia(PhieuNhapDia phieunhapdia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO PHIEUNHAPDIA VALUES(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, phieunhapdia.getMaPhieuNhap());
		pstmt.setString(2, phieunhapdia.getYeuCauThucHien());
		pstmt.setString(3, phieunhapdia.getHoaDonThanhToan());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deletePhieuNhapDia(String maphieunhap) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM PHIEUNHAPDIA WHERE MaPhieuNhap = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieunhap);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updatePhieuNhapDia(PhieuNhapDia phieunhapdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE PHIEUNHAPDIA SET YeuCauThucHien = ?, HoaDonThanhToan = ? WHERE MaPhieuNhap = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, phieunhapdia.getYeuCauThucHien());
		pstmt.setString(2, phieunhapdia.getHoaDonThanhToan());
		pstmt.setString(3, phieunhapdia.getMaPhieuNhap());
		
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static PhieuNhapDia selectPhieuNhapDia(String maphieunhap) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM PHIEUNHAPDIA WHERE MaPhieuNhap = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieunhap);
		ResultSet rs = pstmt.executeQuery();
		PhieuNhapDia temp = new PhieuNhapDia();
		if(rs.next()) {
			temp.setMaPhieuNhap(rs.getString(1));
			temp.setYeuCauThucHien(rs.getString(2));
			temp.setHoaDonThanhToan(rs.getString(3));
		}
		return temp;
	}
	public static ResultSet executeQueryselectPhieuNhapDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<PhieuNhapDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM PHIEUNHAPDIA";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<PhieuNhapDia> arr = new ArrayList<PhieuNhapDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			PhieuNhapDia temp = new PhieuNhapDia();
			temp.setMaPhieuNhap(rs.getString(1));
			temp.setYeuCauThucHien(rs.getString(2));
			temp.setHoaDonThanhToan(rs.getString(3));
			arr.add(temp);
		}
		return arr;
	}

}
