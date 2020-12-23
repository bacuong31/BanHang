package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.DanhSachHoatDong;
import Object.HoatDong;



public class DanhSachHoatDong_DAO {
	public static boolean insertDanhSachHoatDong(DanhSachHoatDong dshoatdong) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO DANHSACHHOATDONG VALUES(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, dshoatdong.getMaHoatDong());
		pstmt.setString(2, dshoatdong.getTenHoatDong());
		pstmt.setBoolean(3, true);
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteDanhSachHoatDong(DanhSachHoatDong dshoatdong) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM DANHSACHHOATDONG WHERE MaHoatDong = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, dshoatdong.getMaHoatDong());
		
		for (HoatDong h : dshoatdong.getListReferenceHoatDong()) {
			HoatDong_DAO.deleteHoatDong(h);
		}
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static int softDeleteDanhSachHoatDong(DanhSachHoatDong dshoatdong) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE DANHSACHHOATDONG SET isActive = false WHERE MaHoatDong = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, dshoatdong.getMaHoatDong());
		int res = pstmt.executeUpdate();
		return res;
	}
	public static boolean updateDanhSachHoatDong(DanhSachHoatDong dshoatdong) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE DANHSACHHOATDONG SET TenHoatDong = ? WHERE MaHoatDong = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, dshoatdong.getTenHoatDong());
		pstmt.setString(2, dshoatdong.getMaHoatDong());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static DanhSachHoatDong selectDanhSachHoatDong(String mahoatdong) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM DANHSACHHOATDONG WHERE MaHoatDong = ? AND isActive = true";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, mahoatdong);
		ResultSet rs = pstmt.executeQuery();
		DanhSachHoatDong temp = new DanhSachHoatDong();
		if (rs.next()) {
			temp.setMaHoatDong(rs.getString(1));
			temp.setTenHoatDong(rs.getString(2));
		}
		return temp;
	}
	public static ArrayList<DanhSachHoatDong> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM DANHSACHHOATDONG WHERE isActive = true";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<DanhSachHoatDong> arr = new ArrayList<DanhSachHoatDong>();
		while(rs.next()) {
			DanhSachHoatDong temp = new DanhSachHoatDong();
			temp.setMaHoatDong(rs.getString(1));
			temp.setTenHoatDong(rs.getString(2));
		}
		return arr;
	}
}
