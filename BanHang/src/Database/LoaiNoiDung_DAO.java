package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;
import Object.LoaiNoiDung;


public class LoaiNoiDung_DAO {
	public static boolean insertLoaiNoiDung(LoaiNoiDung loainoidung) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO LOAINOIDUNG VALUES(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, loainoidung.getMaLoaiNoiDung());
		pstmt.setString(2, loainoidung.getTenLoaiNoiDung());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteLoaiNoiDung(String maloainoidung) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM LOAINOIDUNG WHERE MaLoaiNoiDung = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maloainoidung);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateLoaiNoiDung(LoaiNoiDung loainoidung) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE LOAINOIDUNG SET TenLoaiNoiDung = ? WHERE MaLoaiNoiDung = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, loainoidung.getTenLoaiNoiDung());
		pstmt.setString(2, loainoidung.getMaLoaiNoiDung());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static LoaiNoiDung selectLoaiNoiDung(String maloainoidung) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM LOAINOIDUNG WHERE MaLoaiNoiDung = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maloainoidung);
		ResultSet rs = pstmt.executeQuery();
		LoaiNoiDung temp = new LoaiNoiDung();
		if(rs.next()) {
			temp.setMaLoaiNoiDung(rs.getString(1));
			temp.setTenLoaiNoiDung(rs.getString(2));
		}
		return temp;
	}
	public static ResultSet executeQueryselectLoaiNoiDung(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<LoaiNoiDung> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM LOAINOIDUNG";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<LoaiNoiDung> arr = new ArrayList<LoaiNoiDung>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			LoaiNoiDung temp = new LoaiNoiDung();
			temp.setMaLoaiNoiDung(rs.getString(1));
			temp.setTenLoaiNoiDung(rs.getString(2));
			arr.add(temp);
		}
		return arr;
	}

}
