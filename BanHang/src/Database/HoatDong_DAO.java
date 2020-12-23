package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import Object.HoatDong;


public class HoatDong_DAO {
	public static boolean insertHoatDong(HoatDong hoatdong) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO HOATDONG VALUES(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, hoatdong.getNhanVienThucHien());
		pstmt.setTimestamp(2, hoatdong.getThoiGianThucHien());
		pstmt.setString(3, hoatdong.getHoatDong());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteHoatDong(HoatDong hoatdong) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM HOATDONG WHERE NhanVienThucHien = ? AND ThoiGianThucHien = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, hoatdong.getNhanVienThucHien());
		pstmt.setTimestamp(2, hoatdong.getThoiGianThucHien());
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateHoatDong(HoatDong hoatdong) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE HOATDONG SET HoatDong = ? WHERE NhanVienThucHien = ? AND ThoiGianThucHien = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, hoatdong.getHoatDong());
		pstmt.setString(2, hoatdong.getNhanVienThucHien());
		pstmt.setTimestamp(3, hoatdong.getThoiGianThucHien());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static HoatDong selectHoatDong(String manv, Timestamp time) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM HOATDONG WHERE NhanVienThucHien = ? AND ThoiGianThucHien = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, manv);
		pstmt.setTimestamp(2, time);
		ResultSet rs = pstmt.executeQuery();
		HoatDong temp = new HoatDong();
		if(rs.next()) {
			temp.setNhanVienThucHien(rs.getString(1));
			temp.setThoiGianThucHien(rs.getTimestamp(2));
			temp.setHoatDong(rs.getString(3));
		}
		return temp;
	}
	public static ResultSet executeQueryselectHoatDong(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<HoatDong> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM HOATDONG";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<HoatDong> arr = new ArrayList<HoatDong>();
		while(rs.next()) {
			HoatDong temp = new HoatDong();
			temp.setNhanVienThucHien(rs.getString(1));
			temp.setThoiGianThucHien(rs.getTimestamp(2));
			temp.setHoatDong(rs.getString(3));
			arr.add(temp);
		}
		return arr;
	}
}
