package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.NhanVien;

public class NhanVien_DAO {
	public static boolean insertNhanVien(NhanVien nv) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
	
		String sqlQuery = "INSERT INTO NHANVIEN VALUES(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, nv.getMaNV());
		pstmt.setString(2, nv.getHoTen());
		pstmt.setDate(3, nv.getNgaySinh());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteNhanVien(String manv) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM NHANVIEN WHERE MaNV = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, manv);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateNhanVien(NhanVien nv) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE NHANVIEN SET HoTen = ? , NgaySinh = ? WHERE MaNV = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, nv.getHoTen());
		pstmt.setDate(2, nv.getNgaySinh());
		pstmt.setString(3, nv.getMaNV());
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
			temp.setNgaySinh(rs.getDate(3));
		}
		return temp;
	}
	public static ResultSet executeQueryselectNhanVien(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	public static ArrayList<NhanVien> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM NHANVIEN";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<NhanVien> arr = new ArrayList<NhanVien>();
		while(rs.next()) {
			NhanVien temp = new NhanVien();
			temp.setMaNV(rs.getString(1));
			temp.setHoTen(rs.getString(2));
			temp.setNgaySinh(rs.getDate(3));
			arr.add(temp);
		}
		return arr;
	}
}
