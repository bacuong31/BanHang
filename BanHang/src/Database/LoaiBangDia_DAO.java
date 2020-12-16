package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.LoaiBangDia;
import Object.LoaiNoiDung;



public class LoaiBangDia_DAO {
	public static boolean insertLoaiBangDia(LoaiBangDia loaibangdia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO LOAIBANGDIA VALUES(?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, loaibangdia.getMaLoaiBangDia());
		pstmt.setString(2, loaibangdia.getTenLoaiBangDia());
		pstmt.setString(3, loaibangdia.getMaLoaiNoiDung());
		pstmt.setDouble(4, loaibangdia.getGiaTri());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteLoaiBangDia(String maloaibangdia) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM LOAIBANGDIA WHERE MaLoaiBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maloaibangdia);
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
	public static ResultSet executeQueryselectLoaiBangDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
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
}
