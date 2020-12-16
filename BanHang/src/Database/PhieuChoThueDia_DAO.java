package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;
import Object.PhieuChoThueDia;


public class PhieuChoThueDia_DAO {
	public static boolean insertPhieuChoThueDia(PhieuChoThueDia phieuchothuedia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO PHIEUCHOTHUEDIA VALUES(?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, phieuchothuedia.getMaPhieuThue());
		pstmt.setDate(2, phieuchothuedia.getNgayThue());
		pstmt.setDate(3, phieuchothuedia.getNgayHenTra());
		pstmt.setString(4, phieuchothuedia.getKhachHangThue());
		pstmt.setString(5, phieuchothuedia.getNhanVienTiepNhan());
		pstmt.setString(6, phieuchothuedia.getHoaDonDatCoc());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deletePhieuChoThueDia(String maphieuthue) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM PHIEUCHOTHUEDIA WHERE MaPhieuThue = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieuthue);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updatePhieuChoThueDia(PhieuChoThueDia phieuchothuedia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE PHIEUCHOTHUEDIA SET NgayThue = ?, NgayHenTra = ?, KhachHangThue = ?, NhanVienTiepNhan = ?, HoaDonDatCoc = ? WHERE MaPhieuThue = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setDate(1, phieuchothuedia.getNgayThue());
		pstmt.setDate(2, phieuchothuedia.getNgayHenTra());
		pstmt.setString(3, phieuchothuedia.getKhachHangThue());
		pstmt.setString(4, phieuchothuedia.getNhanVienTiepNhan());
		pstmt.setString(5, phieuchothuedia.getHoaDonDatCoc());
		pstmt.setString(6, phieuchothuedia.getMaPhieuThue());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static PhieuChoThueDia selectPhieuChoThueDia(String maphieuthue) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM PHIEUCHOTHUEDIA WHERE MAPHIEUTHUE = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieuthue);
		ResultSet rs = pstmt.executeQuery();
		PhieuChoThueDia temp = new PhieuChoThueDia();
		if(rs.next()) {
			temp.setMaPhieuThue(rs.getString(1));
			temp.setNgayThue(rs.getDate(2));
			temp.setNgayHenTra(rs.getDate(3));
			temp.setKhachHangThue(rs.getString(4));
			temp.setNhanVienTiepNhan(rs.getString(5));
			temp.setHoaDonDatCoc(rs.getString(6));
		}
		return temp;
	}
	public static ResultSet executeQueryselectPhieuChoThueDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<PhieuChoThueDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM PHIEUCHOTHUEDIA";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<PhieuChoThueDia> arr = new ArrayList<PhieuChoThueDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			PhieuChoThueDia temp = new PhieuChoThueDia();
			temp.setMaPhieuThue(rs.getString(1));
			temp.setNgayThue(rs.getDate(2));
			temp.setNgayHenTra(rs.getDate(3));
			temp.setKhachHangThue(rs.getString(4));
			temp.setNhanVienTiepNhan(rs.getString(5));
			temp.setHoaDonDatCoc(rs.getString(6));
			arr.add(temp);
		}
		return arr;
	}

}
