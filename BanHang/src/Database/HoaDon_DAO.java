package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.DateHelper;
import Object.HoaDon;
import Object.HoaDonChi;
import Object.HoaDonThu;
import Perform_Object.HoaDon_Perform;


public class HoaDon_DAO {
	public static boolean insertHoaDon(HoaDon hoadon) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO HOADON VALUES(?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, hoadon.getMaHoaDon());
		pstmt.setString(2, DateHelper.format(hoadon.getNgayLapHoaDon()));
		pstmt.setDouble(3, hoadon.getGiaTri());
		pstmt.setString(4, hoadon.getMota());
		pstmt.setBoolean(6, true);
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
	
	public static int deleteHoaDon(HoaDon hoadon) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM HOADON WHERE MaHoaDon = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, hoadon.getMaHoaDon());
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static int softDeleteHoaDon(HoaDon_Perform hoadon) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE HOADON SET isActive = 0 WHERE MaHoaDon = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, hoadon.getMaHoaDon());
		int res = pstmt.executeUpdate();
		return res;
		
	}
	public static boolean updateHoaDon(HoaDon hoadon) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE HOADON SET NgayLapHoaDon = ? , GiaTri = ? , MoTa = ? WHERE MaHoaDon = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, DateHelper.format(hoadon.getNgayLapHoaDon()));
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
		String sqlQuery = "SELECT * FROM HOADON WHERE MaHoaDon = ? AND isActive = 1";
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
			hoaDon.setNgayLapHoaDon(DateHelper.parse(rs.getString("NgayLapHoaDon")));
		}
		
		return hoaDon;
	}
	
	public static ArrayList<HoaDon> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		ArrayList<HoaDon> arr = new ArrayList<HoaDon>();
		
		String sqlQuery = "SELECT * FROM HOADON WHERE isActive = 1";
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
			cloneHoaDon.setNgayLapHoaDon(DateHelper.parse(rs.getString(2)));
			cloneHoaDon.setGiaTri(rs.getDouble(3));
			cloneHoaDon.setMota(rs.getString(4));
			arr.add(cloneHoaDon);
		}
		return arr;
	}
	public static ArrayList<HoaDon_Perform> executeQueryselectHoaDon(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<HoaDon_Perform> arr = new ArrayList<HoaDon_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			HoaDon_Perform cloneHoaDon = new HoaDon_Perform();
			cloneHoaDon.setMaHoaDon(rs.getString(1));
			cloneHoaDon.setNgayLapHoaDon(DateHelper.parse(rs.getString(2)));
			cloneHoaDon.setGiaTri(rs.getDouble(3));
			cloneHoaDon.setMota(rs.getString(4));
			if (rs.getInt(5) == 0) {
				cloneHoaDon.setLoaiHoaDon("Hóa đơn chi");
			} else if (rs.getInt(5) == 1) {
				cloneHoaDon.setLoaiHoaDon("Hóa đơn thu");
			}
			arr.add(cloneHoaDon);
		}
		return arr;
	}

	public static ArrayList<HoaDon_Perform> getAllHoaDon_Perform() throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		
		ArrayList<HoaDon_Perform> arr = new ArrayList<HoaDon_Perform>();
		String sqlQuery = "SELECT * FROM HOADON WHERE isActive = 1";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			HoaDon_Perform cloneHoaDon = new HoaDon_Perform();
			cloneHoaDon.setMaHoaDon(rs.getString(1));
			cloneHoaDon.setNgayLapHoaDon(DateHelper.parse(rs.getString(2)));
			cloneHoaDon.setGiaTri(rs.getDouble(3));
			cloneHoaDon.setMota(rs.getString(4));
			if (rs.getInt(5) == 0) {
				cloneHoaDon.setLoaiHoaDon("Hóa đơn chi");
			} else if (rs.getInt(5) == 1) {
				cloneHoaDon.setLoaiHoaDon("Hóa đơn thu");
			}
			arr.add(cloneHoaDon);
		}
		return arr;

	}

	public static ArrayList<HoaDon_Perform> getAllHoaDonChi_Perform() throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		ArrayList<HoaDon_Perform> arr = new ArrayList<HoaDon_Perform>();
		
		String sqlQuery = "SELECT * FROM HOADON WHERE isActive = 1 AND MaLoaiHoaDon = 0";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			HoaDon_Perform cloneHoaDon = new HoaDon_Perform();
			cloneHoaDon.setLoaiHoaDon("Hóa đơn chi");
			cloneHoaDon.setMaHoaDon(rs.getString(1));
			cloneHoaDon.setNgayLapHoaDon(DateHelper.parse(rs.getString(2)));
			cloneHoaDon.setGiaTri(rs.getDouble(3));
			cloneHoaDon.setMota(rs.getString(4));
			arr.add(cloneHoaDon);
		}
		return arr;
	}

	public static ArrayList<HoaDon_Perform> getAllHoaDonThu_Perform() throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		ArrayList<HoaDon_Perform> arr = new ArrayList<HoaDon_Perform>();
		
		String sqlQuery = "SELECT * FROM HOADON WHERE isActive = 1 AND MaLoaiHoaDon = 1";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			HoaDon_Perform cloneHoaDon = new HoaDon_Perform();
			cloneHoaDon.setLoaiHoaDon("Hóa đơn thu");
			cloneHoaDon.setMaHoaDon(rs.getString(1));
			cloneHoaDon.setNgayLapHoaDon(DateHelper.parse(rs.getString(2)));
			cloneHoaDon.setGiaTri(rs.getDouble(3));
			cloneHoaDon.setMota(rs.getString(4));
			arr.add(cloneHoaDon);
		}
		return arr;
	}

	public static ArrayList<HoaDon_Perform> executeQueryselectHoaDonThu(String query) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		ArrayList<HoaDon_Perform> arr = new ArrayList<HoaDon_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			HoaDon_Perform cloneHoaDon = new HoaDon_Perform();
			cloneHoaDon.setMaHoaDon(rs.getString(1));
			cloneHoaDon.setNgayLapHoaDon(DateHelper.parse(rs.getString(2)));
			cloneHoaDon.setGiaTri(rs.getDouble(3));
			cloneHoaDon.setMota(rs.getString(4));
			cloneHoaDon.setLoaiHoaDon("Hóa đơn thu");
			arr.add(cloneHoaDon);
		}
		return arr;
	}

	public static ArrayList<HoaDon_Perform> executeQueryselectHoaDonChi(String query) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		ArrayList<HoaDon_Perform> arr = new ArrayList<HoaDon_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			HoaDon_Perform cloneHoaDon = new HoaDon_Perform();
			cloneHoaDon.setMaHoaDon(rs.getString(1));
			cloneHoaDon.setNgayLapHoaDon(DateHelper.parse(rs.getString(2)));
			cloneHoaDon.setGiaTri(rs.getDouble(3));
			cloneHoaDon.setMota(rs.getString(4));
			cloneHoaDon.setLoaiHoaDon("Hóa đơn chi");
			arr.add(cloneHoaDon);
		}
		return arr;
	}
}
