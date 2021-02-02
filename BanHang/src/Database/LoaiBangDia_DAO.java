package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.LoaiBangDia;
import Object.LoaiNoiDung;
import Perform_Object.LoaiBangDia_Perform;




public class LoaiBangDia_DAO {
	public static boolean insertLoaiBangDia(LoaiBangDia loaibangdia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO LOAIBANGDIA VALUES(?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, loaibangdia.getMaLoaiBangDia());
		pstmt.setString(2, loaibangdia.getTenLoaiBangDia());
		pstmt.setString(3, loaibangdia.getMaLoaiNoiDung());
		pstmt.setDouble(4, loaibangdia.getGiaTri());
		pstmt.setBoolean(5, true);
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteLoaiBangDia(LoaiBangDia loaibangdia) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM LOAIBANGDIA WHERE MaLoaiBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, loaibangdia.getMaLoaiBangDia());
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static int softdeleteLoaiBangDia_Perform(LoaiBangDia_Perform loaiBangDia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "UPDATE LOAIBANGDIA SET isActive = 0 WHERE MaLoaiBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, loaiBangDia.getMaLoaiBangDia());
		res = pstmt.executeUpdate();
		return res;
	}
	public static boolean updateLoaiBangDia(LoaiBangDia loaibangdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE LOAIBANGDIA SET TenLoaiBangDia = ?, MaLoaiNoiDung = ?, GiaTri = ? WHERE MaLoaiBangDia = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, loaibangdia.getTenLoaiBangDia());
		pstmt.setString(2, loaibangdia.getMaLoaiNoiDung());
		pstmt.setDouble(3, loaibangdia.getGiaTri());
		pstmt.setString(4, loaibangdia.getMaLoaiBangDia());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static LoaiBangDia selectLoaiBangDia(String maloaibangdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM LOAIBANGDIA WHERE MaLoaiBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maloaibangdia);
		ResultSet rs = pstmt.executeQuery();
		LoaiBangDia temp = new LoaiBangDia();
		if(rs.next()) {
			temp.setMaLoaiBangDia(rs.getString(1));
			temp.setTenLoaiBangDia(rs.getString(2));
			temp.setMaLoaiNoiDung(rs.getString(3));
			temp.setGiaTri(rs.getDouble(4));
		}
		return temp;
	}
	public static ArrayList<LoaiBangDia_Perform> executeQueryselectLoaiBangDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<LoaiBangDia_Perform> arr = new ArrayList<LoaiBangDia_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			LoaiBangDia_Perform temp = new LoaiBangDia_Perform();
			temp.setMaLoaiBangDia(rs.getString(1));
			temp.setTenLoaiBangDia(rs.getString(2));
			temp.setTenLoaiNoiDung(rs.getString(3));
			temp.setGiaTri(rs.getDouble(4));
			arr.add(temp);
		}
		return arr;
	}
	public static ArrayList<LoaiBangDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM LOAIBANGDIA";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<LoaiBangDia> arr = new ArrayList<LoaiBangDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			LoaiBangDia temp = new LoaiBangDia();
			temp.setMaLoaiBangDia(rs.getString(1));
			temp.setTenLoaiBangDia(rs.getString(2));
			temp.setMaLoaiNoiDung(rs.getString(3));
			temp.setGiaTri(rs.getDouble(4));
			arr.add(temp);
		}
		return arr;
	}
	public static ArrayList<LoaiBangDia_Perform> getAllLoaiBangDia_Perform() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT MaLoaiBangDia, TenLoaiBangDia, TenLoaiNoiDung, GiaTri  FROM LOAIBANGDIA, LOAINOIDUNG WHERE LOAIBANGDIA.MaLoaiNoiDung = LOAINOIDUNG.MaLoaiNoiDung AND isActive = 1";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<LoaiBangDia_Perform> arr = new ArrayList<LoaiBangDia_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			LoaiBangDia_Perform temp = new LoaiBangDia_Perform();
			temp.setMaLoaiBangDia(rs.getString(1));
			temp.setTenLoaiBangDia(rs.getString(2));
			temp.setTenLoaiNoiDung(rs.getString(3));
			temp.setGiaTri(rs.getDouble(4));
			arr.add(temp);
		}
		return arr;
	}
	
	public static Double getGiaTriTheoMa(String maLoaiBangDia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String s = "SELECT GiaTri  FROM LOAIBANGDIA WHERE MaLoaiBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, maLoaiBangDia);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getDouble(1);
		}
		return 0.0;
	}
}
