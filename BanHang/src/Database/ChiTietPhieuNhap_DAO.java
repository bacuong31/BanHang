package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;
import Object.ChiTietPhieuNhap;

public class ChiTietPhieuNhap_DAO {
	public static boolean insertChiTietPhieuNhap(ChiTietPhieuNhap chitietphieunhap) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO CHITIETPHIEUNHAP VALUES(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, chitietphieunhap.getMaPhieuNhap());
		pstmt.setString(2, chitietphieunhap.getMaLoaiBangDia());
		pstmt.setInt(3, chitietphieunhap.getSoLuong());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteChiTietPhieuNhap(String maphieunhap, String maloaibangdia) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM CHITIETPHIEUNHAP WHERE MaPhieuNhap = ? AND MaLoaiBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieunhap);
		pstmt.setString(2, maloaibangdia);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateChiTietPhieuNhap(ChiTietPhieuNhap chitietphieunhap) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE CHITIETPHIEUNHAP SET SoLuong = ? WHERE MaPhieuNhap = ? AND MaLoaiBangDia = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setInt(1, chitietphieunhap.getSoLuong());
		pstmt.setString(2, chitietphieunhap.getMaPhieuNhap());
		pstmt.setString(3, chitietphieunhap.getMaLoaiBangDia());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static ChiTietPhieuNhap selectChiTietPhieuNhap(String maphieunhap, String maloaibangdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM CHITIETPHIEUNHAP WHERE MaPhieuNhap = ? AND MaLoaiBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieunhap);
		pstmt.setString(2, maloaibangdia);
		ResultSet rs = pstmt.executeQuery();
		ChiTietPhieuNhap temp = new ChiTietPhieuNhap();
		if(rs.next()) {
			temp.setMaPhieuNhap(rs.getString(1));
			temp.setMaLoaiBangDia(rs.getString(2));
			temp.setSoLuong(rs.getInt(3));
		}
		return temp;
	}
	public static ResultSet executeQueryselectChiTietPhieuNhap(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<ChiTietPhieuNhap> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM CHITIETPHIEUNHAP";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<ChiTietPhieuNhap> arr = new ArrayList<ChiTietPhieuNhap>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			ChiTietPhieuNhap temp = new ChiTietPhieuNhap();
			temp.setMaPhieuNhap(rs.getString(1));
			temp.setMaLoaiBangDia(rs.getString(2));
			temp.setSoLuong(rs.getInt(3));
			arr.add(temp);
		}
		return arr;
	}

}
