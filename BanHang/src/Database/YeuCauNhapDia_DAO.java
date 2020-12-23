package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;
import Object.YeuCauNhapDia;



public class YeuCauNhapDia_DAO {
	public static boolean insertYeuCauNhapDia(YeuCauNhapDia yeucaunhapdia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO YEUCAUNHAPDIA VALUES(?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, yeucaunhapdia.getMaYeuCau());
		pstmt.setDate(2, yeucaunhapdia.getNgayYeuCau());
		pstmt.setString(3, yeucaunhapdia.getDonViCungCap());
		pstmt.setString(4, yeucaunhapdia.getTrangThaiNhap());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deletetYeuCauNhapDia(YeuCauNhapDia yeucaunhapdia) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM YEUCAUNHAPDIA WHERE MaYeuCau = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, yeucaunhapdia.getMaYeuCau());
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateYeuCauNhapDia(YeuCauNhapDia yeucaunhapdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE YEUCAUNHAPDIA SET NgayYeuCau = ?, DonViCungCap = ?, TrangThaiNhap = ? WHERE MaYeuCau = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setDate(1, yeucaunhapdia.getNgayYeuCau());
		pstmt.setString(2, yeucaunhapdia.getDonViCungCap());
		pstmt.setString(3, yeucaunhapdia.getTrangThaiNhap());
		pstmt.setString(4, yeucaunhapdia.getMaYeuCau());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static YeuCauNhapDia selectYeuCauNhapDia(String mayeucau) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM YEUCAUNHAPDIA WHERE MaYeuCau = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, mayeucau);
		ResultSet rs = pstmt.executeQuery();
		YeuCauNhapDia temp = new YeuCauNhapDia();
		if(rs.next()) {
			temp.setMaYeuCau(rs.getString(1));
			temp.setNgayYeuCau(rs.getDate(2));
			temp.setDonViCungCap(rs.getString(3));
			temp.setTrangThaiNhap(rs.getString(4));
		}
		return temp;
	}
	public static ResultSet executeQueryselectYeuCauNhapDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<YeuCauNhapDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM YEUCAUNHAPDIA";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<YeuCauNhapDia> arr = new ArrayList<YeuCauNhapDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			YeuCauNhapDia temp = new YeuCauNhapDia();
			temp.setMaYeuCau(rs.getString(1));
			temp.setNgayYeuCau(rs.getDate(2));
			temp.setDonViCungCap(rs.getString(3));
			temp.setTrangThaiNhap(rs.getString(4));
			arr.add(temp);
		}
		return arr;
	}

}
