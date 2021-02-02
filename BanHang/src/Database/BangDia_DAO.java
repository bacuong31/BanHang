package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;
import Perform_Object.BangDia_Perform;
import Perform_Object.ThueDia_Perform;
import javafx.scene.control.CheckBox;



public class BangDia_DAO {
	public static boolean insertBangDia(BangDia bangdia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO BANGDIA VALUES(?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, bangdia.getMaBangDia());
		pstmt.setString(2, bangdia.getMaLoaiBangDia());
		pstmt.setString(3, bangdia.getTrangThai());
		pstmt.setBoolean(4, bangdia.isDangChoThue());
		pstmt.setBoolean(5, true);
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static boolean softDeleteBangDia(BangDia bangdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE BANGDIA SET isActive = 0 WHERE MaBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, bangdia.getMaBangDia());
		int res = pstmt.executeUpdate();
		if (res == 0)
			throw new SQLException();
		return true;
		
	}
//	public static int deleteBangDia(String mabangdia) throws SQLException, ClassNotFoundException{
//		Connection conn = DatabaseManager.getInstance().getConnection();
//		int res = 0;
//		String sqlQuery = "DELETE FROM BANGDIA WHERE MaBangDia = ?";
//		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
//		pstmt.setString(1, mabangdia);
//		res = pstmt.executeUpdate();
//		return res;
//	}
	
	public static boolean updateBangDia(BangDia bangdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE BANGDIA SET MaLoaiBangDia = ?, TrangThai = ?, DangChoThue = ? WHERE MaBangDia = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, bangdia.getMaLoaiBangDia());
		pstmt.setString(2, bangdia.getTrangThai());
		pstmt.setString(3, bangdia.getMaLoaiBangDia());
		pstmt.setBoolean(4, bangdia.isDangChoThue());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static BangDia selectLoaiBangDia(String mabangdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM BANGDIA WHERE MaBangDia = ? AND isActive = true";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, mabangdia);
		ResultSet rs = pstmt.executeQuery();
		BangDia temp = new BangDia();
		if(rs.next()) {
			temp.setMaBangDia(rs.getString(1));
			temp.setMaLoaiBangDia(rs.getString(2));
			temp.setTrangThai(rs.getString(3));
		}
		return temp;
	}
	public static ArrayList<BangDia_Perform> executeQueryselectBangDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<BangDia_Perform> arr = new ArrayList<BangDia_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			BangDia_Perform temp = new BangDia_Perform();
			temp.setMaBangDia(rs.getString(1));
			temp.setTenLoaiBangDia(rs.getString(2));
			temp.setTenLoaiNoiDung(rs.getString(3));
			temp.setGiaTien(rs.getDouble(4));
			arr.add(temp);
		}
		return arr;
	}
	public static ArrayList<BangDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM BANGDIA WHERE isActive = 1";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<BangDia> arr = new ArrayList<BangDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			BangDia temp = new BangDia();
			temp.setMaBangDia(rs.getString(1));
			temp.setMaLoaiBangDia(rs.getString(2));
			temp.setTrangThai(rs.getString(3));
			temp.setDangChoThue(rs.getBoolean(4));
			arr.add(temp);
		}
		return arr;
	}

	public static ArrayList<BangDia_Perform> getAllBangDiaPerform() throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT MaBangDia, TenLoaiBangDia, TenLoaiNoiDung, GiaTri FROM BANGDIA, LOAIBANGDIA, LOAINOIDUNG WHERE BANGDIA.isActive = 1 AND DangChoThue = 0 AND BANGDIA.MaLoaiBangDia = LOAIBANGDIA.MaLoaiBangDia AND LOAIBANGDIA.MaLoaiNoiDung = LOAINOIDUNG.MaLoaiNoiDung ORDER BY GiaTri ASC";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<BangDia_Perform> arr = new ArrayList<BangDia_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			BangDia_Perform temp = new BangDia_Perform();
			temp.setMaBangDia(rs.getString(1));
			temp.setTenLoaiBangDia(rs.getString(2));
			temp.setTenLoaiNoiDung(rs.getString(3));
			temp.setGiaTien(rs.getDouble(4));
			arr.add(temp);
		}
		return arr;
	}
	
	public static ArrayList<ThueDia_Perform> getAllBangDiaThueDiaPerform() throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT MaBangDia, TenLoaiBangDia, TenLoaiNoiDung, GiaTri, TenTrangThai FROM TRANGTHAIDIA, BANGDIA, LOAIBANGDIA, LOAINOIDUNG WHERE BANGDIA.isActive = 1 AND DangChoThue = 0 AND BANGDIA.MaLoaiBangDia = LOAIBANGDIA.MaLoaiBangDia AND LOAIBANGDIA.MaLoaiNoiDung = LOAINOIDUNG.MaLoaiNoiDung AND BANGDIA.MaTrangThai = TRANGTHAIDIA.MaTrangThai ORDER BY GiaTri ASC";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<ThueDia_Perform> arr = new ArrayList<ThueDia_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			ThueDia_Perform temp = new ThueDia_Perform();
			temp.setMaBangDia(rs.getString(1));
			temp.setTenBangDia(rs.getString(2));
			temp.setNoiDung(rs.getString(3));
			temp.setGiaTien(rs.getDouble(4));
			temp.setTinhTrang(rs.getString(5));
			temp.setThue(new CheckBox());
			arr.add(temp);
		}
		
		return arr;
	}

	public static String getMaLoaiBangDia(String maBangDia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT MaLoaiBangDia FROM BANGDIA WHERE isActive = 1 AND MaBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maBangDia);
		ResultSet rs = pstmt.executeQuery();
		String ma = null;
		if (rs.next()) {
			ma = rs.getString(1);
		}
		return ma;
	}

	public static ArrayList<ThueDia_Perform> executeQueryselectBangDiaThueDia(String query) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		ArrayList<ThueDia_Perform> arr = new ArrayList<ThueDia_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			ThueDia_Perform temp = new ThueDia_Perform();
			temp.setMaBangDia(rs.getString(1));
			temp.setTenBangDia(rs.getString(2));
			temp.setNoiDung(rs.getString(3));
			temp.setGiaTien(rs.getDouble(4));
			temp.setThue(new CheckBox());
			arr.add(temp);
		}
		return arr;
	}

	public static Double getGiaTienThue(String maBangDia) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT GiaTri, HeSoGiaTri FROM TRANGTHAIDIA, BANGDIA, LOAIBANGDIA WHERE BANGDIA.isActive = 1 AND MaBangDia = ? AND BANGDIA.MaLoaiBangDia = LOAIBANGDIA.MaLoaiBangDia AND BANGDIA.MaTrangThai = TRANGTHAIDIA.MaTrangThai";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maBangDia);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getDouble(1)*rs.getDouble(2);
		}
		return 0.0;
	}

	public static int updateBangDiaDangChoThue(String maBangDia) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE BANGDIA SET DangChoThue = 1 WHERE MaBangDia = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maBangDia);
		res = pstmt.executeUpdate();
		return res;
	}

	public static String getTrangThai(String maBangDia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT TenTrangThai FROM BANGDIA, TRANGTHAIDIA WHERE BANGDIA.MaTrangThai = TRANGTHAIDIA.MaTrangThai AND BANGDIA.MaBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maBangDia);
		ResultSet rs = pstmt.executeQuery();
		String s = "";
		if (rs.next()) {
			s = rs.getString(1);
		}
		return s;
	}

	public static int updateTrangThaiDia(String maBangDia, String maTrangThaiTheoTen) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE BANGDIA SET DangChoThue = 0, MaTrangThai = ? WHERE MaBangDia = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maTrangThaiTheoTen);
		pstmt.setString(2, maBangDia);
		res = pstmt.executeUpdate();
		return res;
	}

	public static void softDeleteBangDiaTheoMa(String maBangDia) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE BANGDIA SET isActive = 0, DangChoThue = 0 WHERE MaBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maBangDia);
		int res = pstmt.executeUpdate();
		if (res == 0)
			throw new SQLException();
	}
}
