package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;
import Object.ChiTietYeuCauNhapDia;

public class ChiTietYeuCauNhapDia_DAO {
	public static boolean insertChiTietYeuCauNhapDia(ChiTietYeuCauNhapDia chitietyeucaunhapdia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO CHITIETYEUCAUNHAPDIA VALUES(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, chitietyeucaunhapdia.getMaYeuCau());
		pstmt.setString(2, chitietyeucaunhapdia.getMaLoaiBangDia());
		pstmt.setInt(3, chitietyeucaunhapdia.getSoLuong());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteChiTietYeuCauNhapDia(String mayeucau, String maloaibangdia) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM CHITIETYEUCAUNHAPDIA WHERE MaYeuCau = ? AND MaLoaiBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, mayeucau);
		pstmt.setString(2, maloaibangdia);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateChiTietYeuCauNhapDia(ChiTietYeuCauNhapDia chitietyeucaunhapdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE CHITIETYEUCAUNHAPDIA SET SoLuong = ? WHERE MaYeuCau = ? AND MaLoaiBangDia = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setInt(1, chitietyeucaunhapdia.getSoLuong());
		pstmt.setString(2, chitietyeucaunhapdia.getMaYeuCau());
		pstmt.setString(3, chitietyeucaunhapdia.getMaLoaiBangDia());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static ChiTietYeuCauNhapDia selectChiTieYeuCauNhapDia(String mayeucau, String maloaibangdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM CHITIETYEUCAUNHAPDIA WHERE MaYeuCau = ? AND MaLoaiBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, mayeucau);
		pstmt.setString(2, maloaibangdia);
		ResultSet rs = pstmt.executeQuery();
		ChiTietYeuCauNhapDia temp = new ChiTietYeuCauNhapDia();
		if(rs.next()) {
			temp.setMaYeuCau(rs.getString(1));
			temp.setMaLoaiBangDia(rs.getString(2));
			temp.setSoLuong(rs.getInt(3));
		}
		return temp;
	}
	public static ResultSet executeQueryselectChiTietYeuCauNhapDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<ChiTietYeuCauNhapDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM CHITIETYEUCAUNHAPDIA";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<ChiTietYeuCauNhapDia> arr = new ArrayList<ChiTietYeuCauNhapDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			ChiTietYeuCauNhapDia temp = new ChiTietYeuCauNhapDia();
			temp.setMaYeuCau(rs.getString(1));
			temp.setMaLoaiBangDia(rs.getString(2));
			temp.setSoLuong(rs.getInt(3));
			arr.add(temp);
		}
		return arr;
	}

}
