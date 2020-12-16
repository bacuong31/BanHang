package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.KhachHang;


public class KhachHang_DAO {
	public static boolean insertKhachHang(KhachHang kh) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;

		String sqlQuery = "INSERT INTO KHACHHANG VALUES(?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, kh.getMaKH());
		pstmt.setString(2, kh.getHoTen());
		pstmt.setDate(3, kh.getNgaySinh());
		pstmt.setBoolean(4, kh.isLaKHQuen());
		pstmt.setDouble(5, kh.getTienDu());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteKhachHang(String makh) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM KHACHHANG WHERE MaKH = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, makh);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateKhachHang(KhachHang kh) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE KHACHHANG SET HoTen = ? , NgaySinh = ? , LaKHQuen = ? , TienDu = ? WHERE MaKH = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, kh.getHoTen());
		pstmt.setDate(2, kh.getNgaySinh());
		pstmt.setBoolean(3, kh.isLaKHQuen());
		pstmt.setDouble(4, kh.getTienDu());
		pstmt.setString(5, kh.getMaKH());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static KhachHang selectKhachHang(String makh) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM KHACHHANG WHERE MaKH = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, makh);
		ResultSet rs = pstmt.executeQuery();
		KhachHang temp = new KhachHang();
		if(rs.next()) {
			temp.setMaKH(rs.getString(1));
			temp.setHoTen(rs.getString(2));
			temp.setNgaySinh(rs.getDate(3));
			temp.setLaKHQuen(rs.getBoolean(4));
			temp.setTienDu(rs.getDouble(5));
		}
		return temp;
	}
	
	public static ResultSet executeQueryselectKhachHang(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	public static ArrayList<KhachHang> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM KHACHHANG";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<KhachHang> arr = new ArrayList<KhachHang>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			KhachHang temp = new KhachHang();
			temp.setMaKH(rs.getString(1));
			temp.setHoTen(rs.getString(2));
			temp.setNgaySinh(rs.getDate(3));
			temp.setLaKHQuen(rs.getBoolean(4));
			temp.setTienDu(rs.getDouble(5));
			arr.add(temp);
		}
		return arr;
	}
}
