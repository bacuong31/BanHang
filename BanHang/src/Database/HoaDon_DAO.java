package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.HoaDon;
import Object.HoaDonChi;
import Object.HoaDonThu;


public class HoaDon_DAO {
	public static boolean insertHoaDon(HoaDon hoadon) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO HOADON VALUES(?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, hoadon.getMaHoaDon());
		pstmt.setDate(2, hoadon.getNgayLapHoaDon());
		pstmt.setDouble(3, hoadon.getGiaTri());
		pstmt.setString(4, hoadon.getMota());
		if (hoadon instanceof HoaDonChi) {
			pstmt.setInt(5, 0);
		}
		else if (hoadon instanceof HoaDonThu) {
			pstmt.setInt(5, 1);
		}
		else
			throw new ClassNotFoundException();
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deleteHoaDon(String mahoadon) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM HOADON WHERE MaHoaDon = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, mahoadon);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateHoaDon(HoaDon hoadon) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE HOADON SET NgayLapHoaDon = ? , GiaTri = ? , MoTa = ? WHERE MaHoaDon = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setDate(1, hoadon.getNgayLapHoaDon());
		pstmt.setDouble(2, hoadon.getGiaTri());
		pstmt.setString(3, hoadon.getMota());
		pstmt.setString(4, hoadon.getMaHoaDon());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static HoaDon selectHoaDon(String mahoadon) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM HOADON WHERE MaHoaDon = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, mahoadon);
		ResultSet rs = pstmt.executeQuery();
		HoaDon hoaDon = null;
		if (rs.next()) {
			if (rs.getInt("MaLoaiHoaDon") == 0)
				hoaDon = new HoaDonChi();
			else 
				hoaDon = new HoaDonThu();
			hoaDon.setGiaTri(rs.getDouble("GiaTri"));
			hoaDon.setMaHoaDon(rs.getString("MaHoaDon"));
			hoaDon.setMota(rs.getString("MoTa"));
			hoaDon.setNgayLapHoaDon(rs.getDate("NgayLapHoaDon"));
		}
		
		return hoaDon;
	}
	
	public static ArrayList<HoaDon> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		ArrayList<HoaDon> arr = new ArrayList<HoaDon>();
		
		String sqlQuery = "SELECT * FROM HOADON";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			HoaDon cloneHoaDon = new HoaDon();
			if (rs.getInt("MaLoaiHoaDon") == 0)
				cloneHoaDon = new HoaDonChi();
			else if (rs.getInt("MaLoaiHoaDon") == 1)
				cloneHoaDon = new HoaDonThu();
			else
				throw new ClassNotFoundException();
			cloneHoaDon.setMaHoaDon(rs.getString(1));;
			cloneHoaDon.setNgayLapHoaDon(rs.getDate(2));
			cloneHoaDon.setGiaTri(rs.getDouble(3));
			cloneHoaDon.setMota(rs.getString(4));
			arr.add(cloneHoaDon);
		}
		return arr;
	}
	public static ResultSet executeQueryselectHoaDon(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
}
