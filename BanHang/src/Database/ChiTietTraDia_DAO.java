package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;
import Object.ChiTietTraDia;
import Perform_Object.ChiTietTraDia_Perform;

public class ChiTietTraDia_DAO {
	public static boolean insertChiTietTraDia(ChiTietTraDia chitiettradia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO CHITIETTRADIA VALUES(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, chitiettradia.getMaPhieuTra());
		pstmt.setString(2, chitiettradia.getMaBangDia());
		pstmt.setString(3, chitiettradia.getTrangThaiDia());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteChiTietTraDia(String maphieutra, String mabangdia) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM CHITIETTRADIA WHERE MaPhieuTra = ? AND MaBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieutra);
		pstmt.setString(2, mabangdia);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateChiTietTraDia(ChiTietTraDia chitiettradia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE CHITIETTRADIA SET TrangThai = ? WHERE MaPhieuTra = ? AND MaBangDia = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, chitiettradia.getTrangThaiDia());
		pstmt.setString(2, chitiettradia.getMaPhieuTra());
		pstmt.setString(3, chitiettradia.getMaBangDia());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static ChiTietTraDia selectChiTietTraDia(String maphieutra, String mabangdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM CHITIETTRADIA WHERE MaPhieuTra = ? AND MaBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieutra);
		pstmt.setString(2, mabangdia);
		ResultSet rs = pstmt.executeQuery();
		ChiTietTraDia temp = new ChiTietTraDia();
		if(rs.next()) {
			temp.setMaPhieuTra(rs.getString(1));
			temp.setMaBangDia(rs.getString(2));
			temp.setTrangThaiDia(rs.getString(3));
		}
		return temp;
	}
	public static ResultSet executeQueryselectChiTietTraDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<ChiTietTraDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM CHITIETTRADIA";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<ChiTietTraDia> arr = new ArrayList<ChiTietTraDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			ChiTietTraDia temp = new ChiTietTraDia();
			temp.setMaPhieuTra(rs.getString(1));
			temp.setMaBangDia(rs.getString(2));
			temp.setTrangThaiDia(rs.getString(3));
			arr.add(temp);
		}
		return arr;
	}

	public static ArrayList<ChiTietTraDia_Perform> getAllChiTietTraDiaPerform(String maPhieuTra) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT CHITIETTRADIA.MaBangDia, TenLoaiBangDia, TenTrangThai FROM CHITIETTRADIA, BANGDIA, LOAIBANGDIA, TRANGTHAIDIA WHERE BANGDIA.MaBangDia = CHITIETTRADIA.MaBangDia"
				+ " AND BANGDIA.MaLoaiBangDia = LOAIBANGDIA.MaLoaiBangDia AND CHITIETTRADIA.TrangThaiDia = TRANGTHAIDIA.MaTrangThai AND MaPhieuTra = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maPhieuTra);
		ArrayList<ChiTietTraDia_Perform> arr = new ArrayList<ChiTietTraDia_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			ChiTietTraDia_Perform temp = new ChiTietTraDia_Perform();
			temp.setMaBangDia(rs.getString(1));
			temp.setTenBangDia(rs.getString(2));
			temp.setTrangThai(rs.getString(3));
			arr.add(temp);
		}
		return arr;

	}

}
