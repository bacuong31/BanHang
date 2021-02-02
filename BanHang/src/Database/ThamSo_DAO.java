package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.BangDia;
import Object.ThamSo;
import Perform_Object.QuyDinh_Perform;


public class ThamSo_DAO {
	public static boolean insertThamSo(ThamSo thamso) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO THAMSO VALUES(?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, thamso.getMaThamSo());
		pstmt.setString(2, thamso.getTenThamSo());
		pstmt.setDouble(3, thamso.getGiaTri());
		pstmt.setString(4, thamso.getChuThich());
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteThamSo(String mathamso) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM THAMSO WHERE MaThamSo = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, mathamso);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateThamSo(ThamSo thamso) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE THAMSO SET TenThamSo = ?, GiaTri = ?, ChuThich = ? WHERE MaThamSo = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		
		pstmt.setString(1, thamso.getTenThamSo());
		pstmt.setDouble(2, thamso.getGiaTri());
		pstmt.setString(3, thamso.getChuThich());
		pstmt.setString(4, thamso.getMaThamSo());
		
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static ThamSo selectThamSo(String mathamso) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM THAMSO WHERE MaThamSo = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, mathamso);
		ResultSet rs = pstmt.executeQuery();
		ThamSo temp = new ThamSo();
		if(rs.next()) {
			temp.setMaThamSo(rs.getString(1));
			temp.setTenThamSo(rs.getString(2));
			temp.setGiaTri(rs.getDouble(3));
			temp.setChuThich(rs.getString(4));
		}
		return temp;
	}
	public static ResultSet executeQueryselectThamSo(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<QuyDinh_Perform> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM THAMSO";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<QuyDinh_Perform> arr = new ArrayList<QuyDinh_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			QuyDinh_Perform temp = new QuyDinh_Perform();
			temp.setMaQuyDinh(rs.getString(1));
			temp.setTenQuyDinh(rs.getString(2));
			temp.setGiaTri(rs.getDouble(3));
			temp.setChuThich(rs.getString(4));
			arr.add(temp);
		}
		return arr;
	}

	public static Double getHeSoThueDiaNguyenVen() throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT GiaTri FROM THAMSO WHERE TenThamSo = 'Hệ số tiền thuê đĩa nguyên vẹn'";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		Double s = 0.0;
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			s = rs.getDouble(1);
		}
		return s;
	}

	public static Double getHeSoTienCoc() throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT GiaTri FROM THAMSO WHERE TenThamSo = 'Hệ số tiền đặt cọc'";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		Double s = 0.0;
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			s = rs.getDouble(1);
		}
		return s;
		
	}

	public static double getHeSoTheoTen(String ten) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT GiaTri FROM THAMSO WHERE TenThamSo = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, ten);
		Double s = 0.0;
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			s = rs.getDouble(1);
		}
		return s;
	}

	public static double getTienPhat() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT GiaTri FROM THAMSO WHERE TenThamSo = 'Tiền phạt mỗi ngày sau khi thuê quá hạn'";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		Double s = 0.0;
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			s = rs.getDouble(1);
		}
		return s;
	
	}

}
