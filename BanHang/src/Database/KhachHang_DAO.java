package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.DateHelper;
import Object.KhachHang;
import Object.PhieuChoThueDia;


public class KhachHang_DAO {
	public static boolean insertKhachHang(KhachHang kh) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;

		String sqlQuery = "INSERT INTO KHACHHANG VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, kh.getMaKH());
		pstmt.setString(2, kh.getHoTen());
		pstmt.setString(3, DateHelper.format(kh.getNgaySinh()));
		pstmt.setInt(4, kh.getSdt());
		pstmt.setBoolean(5, kh.isLaKHQuen());
		pstmt.setDouble(6, kh.getTienDu());
		pstmt.setString(7, kh.getDiaChi());
		pstmt.setBoolean(8, true);
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteKhachHang(KhachHang kh) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM KHACHHANG WHERE MaKH = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, kh.getMaKH());
		
		for (PhieuChoThueDia p : kh.getListPhieuChoThueDia()) {
			PhieuChoThueDia_DAO.deletePhieuChoThueDia(p);
		}
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static int softDeleteKhachHang(KhachHang kh) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE KHACHHANG SET isActive = 0 WHERE MaKH = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, kh.getMaKH());
		int res = pstmt.executeUpdate();
		return res;
	}
	public static boolean updateKhachHang(KhachHang kh) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE KHACHHANG SET HoTen = ? , NgaySinh = ? , Sdt = ? , LaKHQuen = ? , TienDu = ? , DiaChi = ? WHERE MaKH = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, kh.getHoTen());
		pstmt.setString(2, DateHelper.format(kh.getNgaySinh()));
		pstmt.setInt(3, kh.getSdt());
		pstmt.setBoolean(4, kh.isLaKHQuen());
		pstmt.setDouble(5, kh.getTienDu());
		pstmt.setString(6, kh.getDiaChi());
		pstmt.setString(7, kh.getMaKH());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static KhachHang selectKhachHang(String makh) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM KHACHHANG WHERE MaKH = ? AND isActive = true";
		
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, makh);
		ResultSet rs = pstmt.executeQuery();
		KhachHang temp = new KhachHang();
		if(rs.next()) {
			temp.setMaKH(rs.getString(1));
			temp.setHoTen(rs.getString(2));
			temp.setNgaySinh(DateHelper.parse(rs.getString(3)));
			temp.setSdt(rs.getInt(4));
			temp.setLaKHQuen(rs.getBoolean(5));
			temp.setTienDu(rs.getDouble(6));
			temp.setDiaChi(rs.getString(7));
		}
		return temp;
	}
	
	public static ArrayList<KhachHang> executeQueryselectKhachHang(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		ArrayList<KhachHang> arr = new ArrayList<KhachHang>();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			KhachHang temp = new KhachHang();
			temp.setMaKH(rs.getString(1));
			temp.setHoTen(rs.getString(2));
			temp.setNgaySinh(DateHelper.parse(rs.getString(3)));
			temp.setSdt(rs.getInt(4));
			temp.setLaKHQuen(rs.getBoolean(5));
			temp.setTienDu(rs.getDouble(6));
			temp.setDiaChi(rs.getString(7));
			arr.add(temp);
		}
		return arr;
	}

	public static ArrayList<KhachHang> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM KHACHHANG WHERE isActive = 1";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<KhachHang> arr = new ArrayList<KhachHang>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			KhachHang temp = new KhachHang();
			temp.setMaKH(rs.getString(1));
			temp.setHoTen(rs.getString(2));
			temp.setNgaySinh(DateHelper.parse(rs.getString(3)));
			temp.setSdt(rs.getInt(4));
			temp.setLaKHQuen(rs.getBoolean(5));
			temp.setTienDu(rs.getDouble(6));
			temp.setDiaChi(rs.getString(7));
			arr.add(temp);
		}
		return arr;
	}

	public static String getTenKhachThue(String maPhieuThue) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT HoTen FROM KHACHHANG, PHIEUCHOTHUEDIA WHERE MaKH = KhachHangThue AND MaPhieuThue = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maPhieuThue);
		ResultSet rs = pstmt.executeQuery();
		String s = "";
		if(rs.next()) {
			s = rs.getString(1);
		}
		return s;
	}
}
