package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;
import Object.DonViCungCap;
import Object.YeuCauNhapDia;


public class DonViCungCap_DAO {
	public static boolean insertDonViCungCap(DonViCungCap donvicungcap) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO DONVICUNGCAP VALUES(?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, donvicungcap.getMaDVCC());
		pstmt.setString(2, donvicungcap.getTenDVCC());
		pstmt.setInt(3, donvicungcap.getSDT());
		pstmt.setString(4, donvicungcap.getDiaChi());
		pstmt.setBoolean(5, true);
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
//	public static int deleteDonViCungCap(DonViCungCap donvicungcap) throws SQLException, ClassNotFoundException{
//		Connection conn = DatabaseManager.getInstance().getConnection();
//		int res = 0;
//		String sqlQuery = "DELETE FROM DONVICUNGCAP WHERE MaDVCC = ?";
//		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
//		pstmt.setString(1, donvicungcap.getMaDVCC());
//		for (YeuCauNhapDia y : donvicungcap.getListYeuCauNhapDia()) {
//			YeuCauNhapDia_DAO.deletetYeuCauNhapDia(y);
//		}
//		res = pstmt.executeUpdate();
//		return res;
//	}
	
	public static int softDeleteDonViCungCap(DonViCungCap donvicungcap) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE DONVICUNGCAP SET isActive = 0 WHERE MaDVCC = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, donvicungcap.getMaDVCC());
		int res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateDonViCungCap(DonViCungCap donvicungcap) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE DONVICUNGCAP SET TenDVCC = ?, SDT = ?, DiaChi = ? WHERE MaDVCC = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, donvicungcap.getTenDVCC());
		pstmt.setInt(2, donvicungcap.getSDT());
		pstmt.setString(3, donvicungcap.getDiaChi());
		pstmt.setString(4, donvicungcap.getMaDVCC());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static DonViCungCap selectDonViCungCap(String madvcc) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM DONVICUNGCAP WHERE MaDVCC = ? AND isActive = 1";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, madvcc);
		ResultSet rs = pstmt.executeQuery();
		DonViCungCap temp = new DonViCungCap();
		if(rs.next()) {
			temp.setMaDVCC(rs.getString(1));
			temp.setTenDVCC(rs.getString(2));
			temp.setSDT(rs.getInt(3));
			temp.setDiaChi(rs.getString(4));
		}
		return temp;
	}
	public static ArrayList<DonViCungCap> executeQueryselectDonViCungCap(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<DonViCungCap> arr = new ArrayList<DonViCungCap>();
		while(rs.next()) {
			DonViCungCap temp = new DonViCungCap();
			temp.setMaDVCC(rs.getString(1));
			temp.setTenDVCC(rs.getString(2));
			temp.setSDT(rs.getInt(3));
			temp.setDiaChi(rs.getString(4));
			arr.add(temp);
		}
		return arr;
	}
	public static ArrayList<DonViCungCap> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM DONVICUNGCAP WHERE isActive = 1";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<DonViCungCap> arr = new ArrayList<DonViCungCap>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			DonViCungCap temp = new DonViCungCap();
			temp.setMaDVCC(rs.getString(1));
			temp.setTenDVCC(rs.getString(2));
			temp.setSDT(rs.getInt(3));
			temp.setDiaChi(rs.getString(4));
			arr.add(temp);
		}
		return arr;
	}
	
	public static String getMaDVCCTheoTen(String tenDVCC) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT MaDVCC FROM DONVICUNGCAP WHERE TenDVCC = ?";
		String maDVCC = "";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, tenDVCC);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			maDVCC = rs.getString(1);
		}
		return maDVCC;
	}

}
