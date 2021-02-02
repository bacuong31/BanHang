package Database;

import java.sql.Connection;
import java.util.Date;

import Helper.DateHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.NhanVien;

public class NhanVien_DAO {
	public static boolean insertNhanVien(NhanVien nv) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
	
		String sqlQuery = "INSERT INTO NHANVIEN VALUES(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, nv.getMaNV());
		pstmt.setString(2, nv.getHoTen());
		pstmt.setString(3, DateHelper.format(nv.getNgaySinh()));
		pstmt.setInt(4, nv.getSdt());
		pstmt.setString(5, nv.getUsername());
		pstmt.setString(6, nv.getPassword());
		pstmt.setBoolean(7, true);
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int softDeleteNhanVien(NhanVien nv) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE NHANVIEN SET isActive = 0 WHERE MaNV = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, nv.getMaNV());
		int res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateNhanVien(NhanVien nv) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE NHANVIEN SET HoTen = ? , NgaySinh = ? , Sdt = ? , Username = ? , Password = ? WHERE MaNV = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, nv.getHoTen());
		pstmt.setString(2, DateHelper.format(nv.getNgaySinh()));
		pstmt.setInt(3, nv.getSdt());
		pstmt.setString(4, nv.getUsername());
		pstmt.setString(5, nv.getPassword());
		pstmt.setString(6, nv.getMaNV());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static NhanVien selectNhanVien(String manv) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM NHANVIEN WHERE MaNV = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, manv);
		ResultSet rs = pstmt.executeQuery();
		NhanVien temp = new NhanVien();
		if (rs.next()) {
			temp.setMaNV(rs.getString(1));
			temp.setHoTen(rs.getString(2));
			temp.setNgaySinh(DateHelper.parse(rs.getString(3)));
			temp.setSdt(rs.getInt(4));
			temp.setUsername(rs.getString(5));
			temp.setPassword(rs.getString(6));
		}
		return temp;
	}
	public static ArrayList<NhanVien> executeQueryselectNhanVien(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		ArrayList<NhanVien> arr = new ArrayList<NhanVien>();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			NhanVien temp = new NhanVien();
			temp.setMaNV(rs.getString(1));
			temp.setHoTen(rs.getString(2));
			temp.setNgaySinh(DateHelper.parse(rs.getString(3)));
			temp.setSdt(rs.getInt(4));
			temp.setUsername(rs.getString(5));
			temp.setPassword(rs.getString(6));
			arr.add(temp);
		}
		return arr;
	}
	
	public static ArrayList<NhanVien> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM NHANVIEN WHERE isActive = 1";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<NhanVien> arr = new ArrayList<NhanVien>();
		while(rs.next()) {
			NhanVien temp = new NhanVien();
			temp.setMaNV(rs.getString(1));
			temp.setHoTen(rs.getString(2));
			temp.setNgaySinh(DateHelper.parse(rs.getString(3)));
			temp.setSdt(rs.getInt(4));
			temp.setUsername(rs.getString(5));
			temp.setPassword(rs.getString(6));
			arr.add(temp);
		}
		return arr;
	}
}
